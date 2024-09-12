package com.jjangplay.notice.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jjangplay.main.dao.DAO;
import com.jjangplay.notice.vo.NoticeVO;
import com.jjangplay.util.db.DB;
import com.jjangplay.util.page.PageObject;

public class NoticeDAO extends DAO {

	// DAO 에서 필요한 객체 상속받는다. (3가지: 연결객체, 실행객체, 데이터저장객체)
	// 연결객체 : Connection
	// 실행객체 : PreparedStatement
	// 데이터저장객체 : ResultSet
	// 접속정보 : DB 클래스를 통해서 받는다.

	// 1-1.전체 데이터 갯수
	// [NoticeController] -> (Execute) -> NoticeListService -> [NoticeDAO.list()]
	public Long getTotalRow(PageObject pageObject) throws Exception {
		// 결과를 저장할 수 있는 변수
		Long totalRow = null;
		
		System.out.println("---- NoticeDAO.getTotalRow() 시작 ----");
		try {
			// 1. 드라이버확인
			// 드라이버 확인은 프로그램이 시작될 때 한번만 필요 - MAIN에 구현
			// 2. DB 연결
			con = DB.getConnection();
			// 3. SQL - NoticeDAO 클래스에 final 변수로 설정 - TOTALROW
			// 4. 실행객체에 데이터 넘기기
			pstmt = con.prepareStatement(TOTALROW);
			// 5. 실행 및 데이터 받기
			rs = pstmt.executeQuery();
			// 6. 표시 및 저장
			if (rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			} // end of if(rs)
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7. DB 닫기
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		System.out.println("---- NoticeDAO.getTotalRow() 끝 ----");
		return totalRow;
	} // end of getTotalRow()
	
	
	// 1. 리스트 처리
	public List<NoticeVO> list(PageObject pageObject) throws Exception {
		// 결과를 저장하는 객체
		List<NoticeVO> list = null;
		
		try {
			// 1. 드라이버확인 (완료)
			// 2. DB 연결
			con = DB.getConnection();
			// 3. SQL 작성
			System.out.println("LIST_SQL = " + getListSQL(pageObject));
			// 4. 실행객체에 데이터세팅
			pstmt = con.prepareStatement(getListSQL(pageObject));
			int idx = 0;
			idx = setSearchDate(pageObject, pstmt, idx);
			pstmt.setLong(++idx, pageObject.getStartRow());
			pstmt.setLong(++idx, pageObject.getEndRow());
			// 5. 실행
			rs = pstmt.executeQuery();
			// 6. 데이터 담기 및 표시
			if (rs != null) {
				while (rs.next()) {
					if (list == null) list = new ArrayList<NoticeVO>();
					// rs -> NoticeVO vo
					NoticeVO vo = new NoticeVO();
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					
					// vo -> list 담는다.
					// 한줄 꺼낸것을 list에 저장한다.
					list.add(vo);
				} // end of while(rs)
			} // end of if(rs)
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt, rs);
		}
		
		
		return list; // 받은 데이터 또는 결과 리턴
	} // end of list()
	
	// 2. 글보기
	public NoticeVO view(Long no) throws Exception {
		// 결과를 저장하는 객체
		NoticeVO vo = null;
		try {
			// 1.드라이버 확인 (완료)
			// 2.DB연결
			con = DB.getConnection();
			// 3.SQL작성 (VIEW)
			// 4.실행객체에 데이터세팅
			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, no);
			// 5.실행
			rs = pstmt.executeQuery();
			// 6.데이터저장
			if (rs != null && rs.next()) {
				// rs->vo
				vo = new NoticeVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setStartDate(rs.getString("startDate"));
				vo.setEndDate(rs.getString("endDate"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setUpdateDate(rs.getString("updateDate"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e; // NoticeController에서 catch 를 할 수 있도록
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt, rs);
		}
		
		return vo; // 받은 데이터 리턴
	} // end of view()
	
	// 3. 공지사항 글쓰기
	public int write(NoticeVO vo) throws Exception {
		// 결과받는변수
		int result = 0;
		try {
			// 1. 드라이버확인
			// 2. 연결
			con = DB.getConnection();
			// 3. SQL (WRITE)
			// 4. 실행객체에 데이터세팅
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 결과확인
			if (result != 0) {
				System.out.println();
				System.out.println("*** 공지사항이 등록되었습니다. ***");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("예외발생 : 공지등록 DB처리 중 예외가 발행했습니다.");
		} finally {
			// 7.닫기
			DB.close(con, pstmt);
		}
		
		return result;
	}

	// 4. 공지사항 글수정
	public int update(NoticeVO vo) throws Exception {
		// 결과 저장 변수
		int result = 0;
		try {
			// 1.드라이버 확인
			// 2.연결
			con = DB.getConnection();
			// 3.SQL (UPDATE)
			// 4.실행객체에 데이터 세팅
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getStartDate());
			pstmt.setString(4, vo.getEndDate());
			pstmt.setLong(5, vo.getNo());
			// 5.실행
			result = pstmt.executeUpdate();
			// 6.결과 확인
			if (result == 0) {
				throw new Exception("예외발생 : 글번호가 맞지 않습니다.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getMessage().indexOf("예외발생")>=0) throw e;
			else {
				throw new Exception("예외발생 : 공지사항 DB수정중 예외가 발생했습니다.");
			}
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt);
		}
		
		
		return result;
	}
	
	// 5. 공지사항 글삭제
	public int delete(Long no) throws Exception {
		// 결과저장변수
		int result = 0;
		try {
			// 1.드라이버확인
			// 2.연결
			con = DB.getConnection();
			// 3.SQL (DELETE)
			// 4.실행객체에 데이터세팅
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, no);
			// 5.실행
			result = pstmt.executeUpdate();
			// 6.결과확인
			if (result == 0) {
				throw new Exception("예외발생 : 글번호가 맞지 않습니다.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getMessage().indexOf("예외발생")>=0) throw e;
			else {
				throw new Exception("예외발생 : 공지사항 DB수정중 예외가 발생했습니다.");
			}
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt);
		}
		
		return result;
	}
	
	private String getListSQL(PageObject pageObject) {
		String sql = LIST;
		sql += getSearch(pageObject);
		sql += getPeriod(pageObject);
		sql += " order by updateDate, no desc)) ";
		sql += " where rnum>=? and rnum<=?";
		return sql;
	}
	
	private String getSearch(PageObject pageObject) {
		String sql = "";
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		if (word != null && !word.equals("")) {
			sql += " and ( 1=0 "; //or 조건을 만들때 1=0을 준다.
			// key 값에 조건이 있다 t : title, c : content
			if (key.indexOf("t") >= 0) sql += " or title like ? ";
			if (key.indexOf("c") >= 0) sql += " or content like ? ";
			sql += " ) ";
			
		}
		
		return sql;
	}
	
	// 공지사항 기간 조건을 주는 SQL
	private String getPeriod(PageObject pageObject) {
		String sql = "";
		String period = pageObject.getPeriod();
		
		sql += " and ((1=1) ";
		if (period.equals("pre")) {
			// 현재공지
			sql += " and (trunc(sysdate) between trunc(startDate) and trunc(EndDate)) ";
		}
		else if (period.equals("old")) {
			// 지난공지
			sql += " and (trunc(sysdate) > trunc(endDate)) ";
		}
		else if (period.equals("res")) {
			// 예약공지
			sql += " and (trunc(sysdate) < trunc(startDate)) ";
		}
		sql += " ) ";
		return sql;
	}
	
	// pstmt에 데이터 세팅하는 메서드
	private int setSearchDate(PageObject pageObject,
			PreparedStatement pstmt, int idx) throws SQLException  {
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		if (word != null && !word.equals("")) {
			if (key.indexOf("t") >= 0) pstmt.setString(++idx, "%" + word +"%");
			if (key.indexOf("c") >= 0) pstmt.setString(++idx, "%" + word +"%");
		}
		
		return idx;
	}

	
	// SQL문 작성
	final String LIST = ""
			+ " select no, title, startDate, endDate from "
			+ " (select rownum rnum, no, title, startDate, endDate from "
			+ " (select no, title,"
			+ " to_char(startDate, 'yyyy-mm-dd') startDate, "
			+ " to_char(endDate, 'yyyy-mm-dd') endDate "
			+ " from Notice where ( 1=1 ) ";
			
		//	" order by updateDate, no desc)) "
		//	+ " where rnum>=? and rnum<=?";
	
	final String TOTALROW = "select count(*) from notice";
	
	final String VIEW = "select no, title, content, "
			+ " to_char(startDate, 'yyyy-mm-dd') startDate, "
			+ " to_char(endDate, 'yyyy-mm-dd') endDate, "
			+ " to_char(writeDate, 'yyyy-mm-dd') writeDate, "
			+ " to_char(updateDate, 'yyyy-mm-dd') updateDate "
			+ " from Notice where no = ?";
	final String WRITE = "insert into notice "
			+ " (no, title, content, startDate, endDate) "
			+ " values (notice_seq.nextval, ?, ?, ?, ?)";
	final String UPDATE = "update notice set "
			+ " title = ?, content = ?, startDate = ?, endDate = ? "
			+ " where no = ?";
	final String DELETE = "delete from notice "
			+ " where no = ?";
	
	
	
	
	
	
	
	
	
	
} // end of class
