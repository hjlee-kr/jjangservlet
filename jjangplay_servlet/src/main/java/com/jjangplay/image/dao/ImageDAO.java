package com.jjangplay.image.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jjangplay.image.vo.ImageVO;
import com.jjangplay.main.dao.DAO;
import com.jjangplay.util.db.DB;
import com.jjangplay.util.page.PageObject;

public class ImageDAO extends DAO {

	// 필요한 객체는 상속을 받아 사용합니다. : extends DAO
	// 접속정보는 DB클래스를 사용해서 Connection 을 가져오는 메서드만 사용

	// 1-1.전체 데이터 갯수
	// [ImageController] -> (Execute) -> ImageListService -> [ImageDAO.list()]
	public Long getTotalRow(PageObject pageObject) throws Exception {
		// 결과를 저장할 수 있는 변수
		Long totalRow = null;
		
		System.out.println("---- ImageDAO.getTotalRow() 시작 ----");
		try {
			// 1. 드라이버확인
			// 드라이버 확인은 프로그램이 시작될 때 한번만 필요 - MAIN에 구현
			// 2. DB 연결
			con = DB.getConnection();
			// 3. SQL - ImageDAO 클래스에 final 변수로 설정 - TOTALROW
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
		
		System.out.println("---- ImageDAO.getTotalRow() 끝 ----");
		return totalRow;
	} // end of getTotalRow()

	// 1.리스트
	// [ImageController] -> (Execute) -> ImageListService -> [ImageDAO.list()]
	public List<ImageVO> list(PageObject pageObject) throws Exception {
		List<ImageVO> list = null;
		
		System.out.println("---- ImageDAO.list() 시작 ----");
		try {
			// 1. 드라이버확인
			// 드라이버 확인은 프로그램이 시작될 때 한번만 필요 - MAIN에 구현
			// 2. DB 연결
			con = DB.getConnection();
			// 3. SQL - BoardDAO 클래스에 final 변수로 설정 - LIST
			// 4. 실행객체에 데이터 넘기기
			pstmt = con.prepareStatement(getListSQL(pageObject));
			// 검색에 대한 데이터 세팅
			int idx = 0;
		//	idx = setSearchDate(pageObject, pstmt, idx);
			pstmt.setLong(++idx, pageObject.getStartRow());
			pstmt.setLong(++idx, pageObject.getEndRow());
			// 5. 실행 및 데이터 받기
			rs = pstmt.executeQuery();
			// 6. 표시 및 저장
			if (rs != null) {
				while (rs.next()) {
					// list 가 null 이면 ArrayList를 생성해서 저장할 수 있도록 만든다.
					if (list == null) list = new ArrayList<ImageVO>();
					//rs -> BoardVO
					ImageVO vo = new ImageVO(); // 클래스를 사용하는 기본형식
					// ImageVO 안의 no 변수에 rs 안에 no 컬럼에 저장되어있는 값을 넘겨받는다  
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setFileName(rs.getString("fileName"));
					
					// vo->list 에 담는다.
					list.add(vo);
				} // end of while(rs)
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
		
		System.out.println("---- ImageDAO.list() 끝 ----");
		return list;
	} // end of list()
	

	// 2. 글보기 (글번호의 상세페이지)
	// [ImageController] -> (Execute) -> ImageViewService
	// -> [ImageDAO.view()]
	// Image table 에서 한줄의 데이터를 가져옵니다. 
	public ImageVO view(Long no) throws Exception {
		// 결과를 저장할 수 있는 변수선언
		ImageVO vo = null;
		
		try {
			// 1. 드라이버 확인 // 이미완료
			// 2. 연결
			con = DB.getConnection();
			// 3. SQL (VIEW)
			// 4. 실행객체에 데이터 담기
			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, no);
			// 5. 실행
			rs = pstmt.executeQuery();
			
			if (rs != null && rs.next()) {
				vo = new ImageVO(); // 클래스를 사용하는 기본형식
				// IamgeVO 안의 no 변수에 rs 안에 no 컬럼에 저장되어있는 값을 넘겨받는다  
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setFileName(rs.getString("fileName"));
			} // end of if(rs)
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				// 7.DB닫기
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		// DB에서 받은 데이터를 리턴
		return vo;
	} // end of view()
	
	// 3. 글쓰기
	// [ImageController] -> (Execute) -> ImageWriteService -> [ImageDAO.write()]
	public int write(ImageVO obj) throws Exception {
		// 결과를 저장하는 변수선언
		int result = 0;
		
		try {
			// 1. 드라이버확인 (MAIN에서 한번처리로 끝)
			// 2. 연결 - DB class에 getConnection() static 메서드로 구현 
			con = DB.getConnection(); 
			// 3. sql(WRITE)
			// 4. 실행객체에 데이터 세팅
			pstmt = con.prepareStatement(WRITE);
			// BoardVO vo변수 안에 있는 값을 getter를 이용해서 세팅합니다.
			pstmt.setString(1, obj.getTitle());
			pstmt.setString(2, obj.getContent());
			pstmt.setString(3, obj.getId());
			pstmt.setString(4, obj.getFileName());

			// 5. 실행 // insert, update, delete => executeUpdate()
			result = pstmt.executeUpdate();
			// 6. 데이터 보기 및 저장(보관)
			System.out.println();
			System.out.println("*** 글등록이 완료 되었습니다. ***");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		
		// 결과를 리턴합니다.
		return result;
	}
	
	
	// 4. 글수정
	// [ImageController] -> (Execute)
	// -> ImageUpdateService -> [ImageDAO.update()]
	public int update(ImageVO vo) throws Exception {
		// 결과 저장 변수
		int result = 0; // SQL문이 실행성공 : 1, 실행실패 : 0
		
		try {
			// 1. 드라이버확인
			// 2. 연결
			con = DB.getConnection();
			// 3. SQL (UPDATE)
			// 4. 실행객체에 데이터세팅
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
			pstmt.setString(4, vo.getId());
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 보기 및 데이터저장 (실행결과확인)
			if (result == 0) { // 글번호가 없을때
				throw new Exception("예외발생 : 글번호나 비밀번호가 맞지 않습니다.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			//7.닫기
			DB.close(con, pstmt);
		}
		
		return result; //결과값리턴
	}
	
	// 5. 글삭제
	// [ImageController] -> (Execute)
	// -> ImageDeleteService -> [ImageDAO.delete()]
	public int delete(ImageVO vo) throws Exception {
		int result = 0;
		
		try {
			// 1. 드라이버확인 (완료)
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL (DELETE)
			// 4. 실행객체에 데이터 세팅
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, vo.getNo());
			pstmt.setString(2, vo.getId());
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 결과확인
			if (result == 0) {
				throw new Exception("예외발생 : 글번호나 ID가 맞지 않습니다.");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getMessage().indexOf("예외발생") >= 0) throw e; 
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt);
		} 
		
		return result;
	}

	// 6. 이미지 변경 처리
	// [ImageController] -> (Execute) -> ImageChangeService -> [ImageDAO.write()]
	public int imageChange(ImageVO obj) throws Exception {
		// 결과를 저장하는 변수선언
		int result = 0;
		
		try {
			// 1. 드라이버확인 (MAIN에서 한번처리로 끝)
			// 2. 연결 - DB class에 getConnection() static 메서드로 구현 
			con = DB.getConnection(); 
			// 3. sql(WRITE)
			// 4. 실행객체에 데이터 세팅
			pstmt = con.prepareStatement(IMAGECHANGE);
			// ImageVO vo변수 안에 있는 값을 getter를 이용해서 세팅합니다.
			pstmt.setString(1, obj.getFileName());
			pstmt.setLong(2, obj.getNo());

			// 5. 실행 // insert, update, delete => executeUpdate()
			result = pstmt.executeUpdate();
			// 6. 데이터 보기 및 저장(보관)
			System.out.println();
			System.out.println("*** 이미지 변경이 완료 되었습니다. ***");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt);
		}
		
		
		// 결과를 리턴합니다.
		return result;
	}

	
	
	private String getSearch(PageObject pageObject) {
		String sql = "";
		String key = pageObject.getKey();
		String word = pageObject.getWord();
		if (word != null && !word.equals("")) {
			sql += " where 1=0 "; //or 조건을 만들때 1=0을 준다.
			// key 값에 조건이 있다 t : title, w : writer, c : content
			if (key.indexOf("t") >= 0) sql += " or title like ? ";
			if (key.indexOf("c") >= 0) sql += " or content like ? ";
			if (key.indexOf("w") >= 0) sql += " or writer like ? ";
		}
		
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
			if (key.indexOf("w") >= 0) pstmt.setString(++idx, "%" + word +"%");
		}
		
		return idx;
	}
	
	private String getListSQL(PageObject pageObject) {
		String sql = LIST;
		//	sql += getSearch(pageObject);
		sql += " and (m.id=i.id) ";
		sql += " order by no desc)) ";
		sql += " where rnum>=? and rnum<=?";
		return sql;
	}
	
	// SQL 문
	// LIST의 페이지 처리
	final String LIST = ""
			+ " select no, title, id, name, writeDate, fileName from "
			+ " (select rownum rnum, no, title, id, name, writeDate, fileName from "
			+ " (select i.no, i.title, i.id, m.name, "
			+ " to_char(i.writeDate, 'yyyy-dd-mm') writeDate, i.fileName "
			+ " from image i, member m "
			// where 1=1 and (일반조건) and (조인조건)
			+ " where 1=1 ";
	
	final String TOTALROW = "select count(*) from image";

	final String VIEW = "select i.no, i.title, i.content,"
			+ " i.id, m.name, "
			+ " to_char(i.writeDate, 'yyyy-mm-dd') writeDate"
			+ " , i.fileName "
			+ " from image i, member m"
			+ " where (i.no = ?) and (i.id = m.id)"; 

	final String WRITE = "insert into image "
			+ " (no, title, content, id, fileName) "
			+ " values (image_seq.nextval, ?, ?, ?, ?)";

	final String UPDATE = "update image "
			+ " set title = ?, content = ? "
			+ " where no = ? and id = ?";
	
	final String DELETE = "delete from image "
			+ " where no = ? and id = ?";
			
	final String IMAGECHANGE = "update image "
			+ " set fileName = ? "
			+ " where no = ? ";
	
	
} // end of class
