package com.jjangplay.boardreply.dao;


import java.util.ArrayList;
import java.util.List;

import com.jjangplay.boardreply.vo.BoardReplyVO;
import com.jjangplay.main.dao.DAO;
import com.jjangplay.util.db.DB;
import com.jjangplay.util.page.ReplyPageObject;

public class BoardReplyDAO extends DAO {

	// 필요한 객체는 상속을 받아 사용합니다. : extends DAO
	// 접속정보는 DB클래스를 사용해서 Connection 을 가져오는 메서드만 사용

	// 1-1.전체 데이터 갯수
	// [BoardReplyController] -> (Execute)
	// -> BoardReplyListService -> [BoardReplyDAO.list()]
	public Long getTotalRow(ReplyPageObject pageObject) throws Exception {
		// 결과를 저장할 수 있는 변수
		Long totalRow = null;
		
		System.out.println("---- BoardReplyDAO.getTotalRow() 시작 ----");
		try {
			// 1. 드라이버확인
			// 드라이버 확인은 프로그램이 시작될 때 한번만 필요 - MAIN에 구현
			// 2. DB 연결
			con = DB.getConnection();
			// 3. SQL - BoardDAO 클래스에 final 변수로 설정 - TOTALROW
			// 4. 실행객체에 데이터 넘기기
			pstmt = con.prepareStatement(TOTALROW);
			pstmt.setLong(1, pageObject.getNo());
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
		
		System.out.println("---- BoardReplyDAO.getTotalRow() 끝 ----");
		return totalRow;
	} // end of getTotalRow()

	// 1.리스트
	// [BoardReplyController] -> (Execute)
	//-> BoardReplyListService -> [BoardReplyDAO.list()]
	public List<BoardReplyVO> list(ReplyPageObject pageObject) throws Exception {
		List<BoardReplyVO> list = null;
		
		System.out.println("---- BoardReplyDAO.list() 시작 ----");
		try {
			// 1. 드라이버확인
			// 드라이버 확인은 프로그램이 시작될 때 한번만 필요 - MAIN에 구현
			// 2. DB 연결
			con = DB.getConnection();
			// 3. SQL - BoardDAO 클래스에 final 변수로 설정 - LIST
			// 4. 실행객체에 데이터 넘기기
			pstmt = con.prepareStatement(LIST);
			// 검색에 대한 데이터 세팅
			pstmt.setLong(1, pageObject.getNo());
			pstmt.setLong(2, pageObject.getStartRow());
			pstmt.setLong(3, pageObject.getEndRow());
			// 5. 실행 및 데이터 받기
			rs = pstmt.executeQuery();
			// 6. 표시 및 저장
			if (rs != null) {
				while (rs.next()) {
					// list 가 null 이면 ArrayList를 생성해서 저장할 수 있도록 만든다.
					if (list == null) list = new ArrayList<BoardReplyVO>();
					//rs -> BoardReplyVO
					BoardReplyVO vo = new BoardReplyVO(); // 클래스를 사용하는 기본형식
					// BoardReplyVO 안의 no 변수에 rs 안에 no 컬럼에 저장되어있는 값을 넘겨받는다  
					vo.setRno(rs.getLong("rno"));
					vo.setNo(rs.getLong("no"));
					vo.setContent(rs.getString("content"));
					vo.setWriter(rs.getString("writer"));
					vo.setWriteDate(rs.getString("writeDate"));
					
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
		
		System.out.println("---- BoardReplyDAO.list() 끝 ----");
		return list;
	} // end of list()
	
	
	// 3. 글쓰기
	// [BoardReplyController] -> (Execute)
	// -> BoardReplyWriteService -> [BoardReplyDAO.write()]
	public int write(BoardReplyVO obj) throws Exception {
		// 결과를 저장하는 변수선언
		int result = 0;
		
		try {
			// 1. 드라이버확인 (MAIN에서 한번처리로 끝)
			// 2. 연결 - DB class에 getConnection() static 메서드로 구현 
			con = DB.getConnection(); 
			// 3. sql(WRITE)
			// 4. 실행객체에 데이터 세팅
			pstmt = con.prepareStatement(WRITE);
			// BoardReplyVO vo변수 안에 있는 값을 getter를 이용해서 세팅합니다.
			pstmt.setLong(1, obj.getNo());
			pstmt.setString(2, obj.getContent());
			pstmt.setString(3, obj.getWriter());
			pstmt.setString(4, obj.getPw());
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
	// [BoardReplyController] -> (Execute)
	// -> BoardReplyUpdateService -> [BoardReplyDAO.update()]
	public int update(BoardReplyVO vo) throws Exception {
		// 결과 저장 변수
		int result = 0; // SQL문이 실행성공 : 1, 실행실패 : 0
		
		try {
			// 1. 드라이버확인
			// 2. 연결
			con = DB.getConnection();
			// 3. SQL (UPDATE)
			// 4. 실행객체에 데이터세팅
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getWriter());
			pstmt.setLong(3, vo.getRno());
			pstmt.setString(4, vo.getPw());
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
	// [BoardReplyController] -> (Execute)
	// -> BoardReplyDeleteService -> [BoardReplyDAO.delete()]
	public int delete(BoardReplyVO obj) throws Exception {
		int result = 0;
		
		try {
			// 1. 드라이버확인 (완료)
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL (DELETE)
			// 4. 실행객체에 데이터 세팅
			pstmt = con.prepareStatement(DELETE);
			pstmt.setLong(1, obj.getRno());
			pstmt.setString(2, obj.getPw());
			// 5. 실행
			result = pstmt.executeUpdate();
			// 6. 결과확인
			if (result == 0) {
				throw new Exception("예외발생 : 글번호나 비밀번호가 맞지 않습니다.");
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
	
	
	// SQL 문
	// LIST의 페이지 처리
	final String LIST = ""
		+ " select rno, no, content, writer, writeDate from "
			+ " (select rownum rnum, rno, no, content, writer, writeDate from "
				+ " (select rno, no, content, writer, "
				+ " to_char(writeDate, 'yyyy-dd-mm') writeDate "
				+ " from board_reply "
				+ " where no = ? "//일반게시판 글번호에 맞는 댓글만 보여주기위해
				+ " order by rno desc"
				+ ")"
			+ ") "
		+ " where rnum>=? and rnum<=?";
	

	
	final String TOTALROW = "select count(*) from board_reply "
			+ " where no = ? ";

	final String WRITE = "insert into board_reply "
			+ " (rno, no, content, writer, pw) "
			+ " values (board_reply_seq.nextval, ?, ?, ?, ?)";

	final String UPDATE = "update board_reply "
			+ " set content = ?, writer = ? "
			+ " where rno = ? and pw = ?";
	
	final String DELETE = "delete from board_reply "
			+ " where rno = ? and pw = ?";
			
	
	
	
} // end of class
