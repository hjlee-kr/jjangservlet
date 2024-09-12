package com.jjangplay.member.dao;

import java.util.ArrayList;
import java.util.List;

import com.jjangplay.main.dao.DAO;
import com.jjangplay.member.vo.LoginVO;
import com.jjangplay.member.vo.MemberVO;
import com.jjangplay.util.db.DB;
import com.jjangplay.util.page.PageObject;

public class MemberDAO extends DAO {

	// 필요한객체 -> DAO에서 상속받아 사용
	// DB정보 -> DB클래스를 통해 사용
	
	
	
	// 1. 리스트 (회원리스트)
	// MemberController("1")->Execute->MemberListService->여기까지 왔어요
	public List<MemberVO> list(PageObject obj) throws Exception {
		// 결과를 저장할 객체선언
		List<MemberVO> list = null;
		
		try {
			// 1.드라이버확인
			// 2.연결
			con = DB.getConnection();
			// 3.SQL (LIST)
			// 4.실행객체에 데이터세팅
			pstmt = con.prepareStatement(LIST);
			pstmt.setLong(1, obj.getStartRow());
			pstmt.setLong(2, obj.getEndRow());
			// 5.실행
			rs = pstmt.executeQuery();
			// 6.데이터저장
			if (rs != null) {
				while (rs.next()) {
					// rs -> vo -> list
					if (list == null) list = new ArrayList<MemberVO>();
					
					MemberVO vo = new MemberVO();
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setBirth(rs.getString("birth"));
					vo.setTel(rs.getString("tel"));
					vo.setGradeNo(rs.getInt("gradeNo"));
					vo.setGradeName(rs.getString("gradeName"));
					vo.setStatus(rs.getString("status"));
					vo.setPhoto(rs.getString("photo"));
					
					list.add(vo);
				} // end of while
			} // end of if(rs)
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt, rs);
		}
		
		
		// 결과값 리턴 (호출한 메서드에 전달)
		return list;
	}


	// 2. 회원정보보기
	// MemberController("2")->Execute->MemberViewService->여기까지 왔어요
	// 1명에 대한 자료만 받으면 되기때문에 리턴값을 MemberVO로 선언했다.
	public MemberVO view(String id) throws Exception {
		// 결과값에 대한 변수선언
		MemberVO vo = null;
		try {
			// 1.드라이버확인
			// 2.연결
			con = DB.getConnection();
			// 3.SQL (VIEW)
			// 4.실행객체에 데이터세팅 (id)
			pstmt = con.prepareStatement(VIEW);
			pstmt.setString(1, id);
			// 5.실행
			rs = pstmt.executeQuery();
			// 6.결과값저장
			if (rs != null && rs.next()) {
				vo = new MemberVO();
				
				vo.setId(rs.getString("id"));
				vo.setPw(rs.getString("pw"));
				vo.setName(rs.getString("name"));
				vo.setGender(rs.getString("gender"));
				vo.setBirth(rs.getString("birth"));
				vo.setTel(rs.getString("tel"));
				vo.setEmail(rs.getString("email"));
				vo.setRegDate(rs.getString("regDate"));
				vo.setConDate(rs.getString("conDate"));
				vo.setPhoto(rs.getString("photo"));
				vo.setGradeNo(rs.getInt("gradeNo"));
				vo.setGradeName(rs.getString("gradeName"));
				vo.setStatus(rs.getString("status"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("예외발생 : 회원정보보기 DB처리중 예외발생");
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt, rs);
		}
		
		// 결과값(1명에 대한 자료) 리턴
		return vo;
	}
	
	// 3. 회원가입 처리
	// MemberController("3")->Execute->MemberWriteService->여기까지 왔습니다.
 	public int write(MemberVO vo) throws Exception {
		// 결과를 저장할 수 있는 변수
		int result = 0;
		try {
			// 1.드라이버확인
			// 2.DB연결
			con = DB.getConnection();
			// 3.SQL (WRITE)
			// 4.실행객체에 데이터세팅
			pstmt = con.prepareStatement(WRITE);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getBirth());
			pstmt.setString(6, vo.getTel());
			pstmt.setString(7, vo.getEmail());
			pstmt.setString(8, vo.getPhoto());
			// 5.실행
			result = pstmt.executeUpdate();
			// 6.결과확인
			if (result != 0) {
				System.out.println();
				System.out.println("*** 회원가입이 완료되었습니다. ***");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("예외발생 : 회원가입 DB처리중 예외가 발생했습니다.");
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과값 리턴(호출한 메서드에 넘겨줌)
		return result;
	}

	// 3-1. 아이디 중복체크 처리
	// MemberController("3")->Execute->MemberCheckIdService->여기까지 왔습니다.
 	public String checkId(String id) throws Exception {
		// 결과를 저장할 수 있는 변수
		String result = null;
		try {
			// 1.드라이버확인
			// 2.DB연결
			con = DB.getConnection();
			// 3.SQL (WRITE)
			// 4.실행객체에 데이터세팅
			pstmt = con.prepareStatement(CHECKID);
			pstmt.setString(1, id);

			// 5.실행
			rs = pstmt.executeQuery();
			
			// 6.결과담기
			if (rs != null && rs.next()) {
				result = rs.getString("id");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("예외발생 : 아이디중복체크 중 DB처리중 예외가 발생했습니다.");
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과값 리턴(호출한 메서드에 넘겨줌)
		return result;
	}

 	
 	// 4. 회원정보 수정
 	// MemberController("/member/update.do")
 	// ->Execute->MemberUpdateService->여기까지
 	public int update(MemberVO vo) throws Exception {
 		// 결과받을변수
 		int result = 0;
 		try {
			// 1.드라이버확인
 			// 2.DB연결
 			con = DB.getConnection();
 			// 3.SQL (UPDATE)
 			// 4.실행객체에 데이터세팅
 			pstmt = con.prepareStatement(UPDATE);
 			// pstmt 에 데이터 세팅순서는
 			// SQL문에서 적은 ?순서대로 1번부터 세팅해준다.
 			pstmt.setString(1, vo.getName());
 			pstmt.setString(2, vo.getGender());
 			pstmt.setString(3, vo.getBirth());
 			pstmt.setString(4, vo.getTel());
 			pstmt.setString(5, vo.getEmail());
 			pstmt.setString(6, vo.getPhoto());
 			pstmt.setString(7, vo.getId());
 			pstmt.setString(8, vo.getPw());
 			// 5.실행
 			result = pstmt.executeUpdate();
 			// 6.결과확인
 			if (result == 0) {
 				throw new Exception("예외발생 : 아이디나 비밀번호가 맞지 않습니다.");
 			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getMessage().indexOf("예외발생")>=0) throw e;
			else {
				throw new Exception("예외발생 : 회원 정보 수정 DB 처리중 예외발생");
			}
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt);
		}
 		
 		return result;
 	}

 	// 5. 회원 탈퇴 처리 - 상태를 '탈퇴'로 변경
 	// MemberController("5")->Execute->MemberDeleteService->여기까지
 	public int delete(MemberVO vo) throws Exception {
 		// 결과 저장을 위한 변수
 		int result = 0;
 		
 		try {
			// 1.드라이버 연결
 			// 2.DB연결
 			con = DB.getConnection();
 			// 3.SQL (DELETE)
 			// 4.실행객체에 데이터 세팅
 			pstmt = con.prepareStatement(DELETE);
 			pstmt.setString(1, vo.getId());
 			pstmt.setString(2, vo.getPw());
 			// 5.실행
 			result = pstmt.executeUpdate();
 			// 6.결과확인
 			if (result == 0) {
 				throw new Exception("예외발생 : 아이디나 비밀번호가 맞지 않습니다.");
 			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getMessage().indexOf("예외발생") >= 0) {
				throw e;
			}
			else {
				throw new Exception("예외발생 : 회원탈퇴 DB 처리 중 예외발생");
			}
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt);
		}
 		
 		return result;
 	} // end of delete()
 	
 	
	// 6. 로그인 처리
	// MemberController("6")->Execute->MemberLoginService
	// -> 여기에 (login()) 에 왔습니다.
	public LoginVO login(LoginVO vo) throws Exception {
		// 결과를 저장할 객체선언
		LoginVO loginvo = null;
		
		try {
			// 1.드라이버확인
			// 2.DB연결
			con = DB.getConnection();
			// 3.SQL (LOGIN)
			// 4.실행객체에 데이터세팅
			pstmt = con.prepareStatement(LOGIN);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			// 5.실행
			rs = pstmt.executeQuery();
			// 6.데이터 저장
			if (rs != null && rs.next()) {
				loginvo = new LoginVO();
				loginvo.setId(rs.getString("id"));
				loginvo.setPw(rs.getString("pw"));
				loginvo.setName(rs.getString("name"));
				loginvo.setGradeNo(rs.getInt("gradeNo"));
				loginvo.setGradeName(rs.getString("gradeName"));
				loginvo.setPhoto(rs.getString("photo"));
				loginvo.setNewMsgCnt(rs.getLong("newMsgCnt"));
			}
			
			if (loginvo == null) {
				// 아이디나 패스워드가 맞지 않을때
				throw new Exception("예외발생 : 아이디나 비밀번호가 맞지 않습니다.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getMessage().indexOf("예외발생") >= 0) throw e;
			else {
				throw new Exception("예외발생 : 로그인 DB처리중 예외가 발생했습니다.");
			}
			
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt, rs);
		}
		
		return loginvo;
	}
	
	// 6-1. 최근접속일 수정
	public int conUpdate(String id) throws Exception {
		// 결과 받을 변수선언
		int result = 0;
		
		try {
			// 1.드라이버확인
			// 2.DB연결
			con = DB.getConnection();
			// 3.SQL (UPDATE_CONDATE)
			// 4.실행객체에 데이터세팅
			pstmt = con.prepareStatement(UPDATE_CONDATE);
			pstmt.setString(1, id);
			// 5.실행
			result = pstmt.executeUpdate();
			// 6.결과확인
			if (result == 0) {
				throw new Exception("예외발생 : 아이디가 존재하지 않습니다.");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getMessage().indexOf("예외발생")>=0) throw e;
			else {
				throw new Exception("예외발생 : 최근 접속일 수정 DB처리중 예외발생");
			}
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt);
		}
		
		return result;
	}// end of conUpdate()
	
	
	// 회원등급 변경
	public int changeGradeNo(MemberVO vo) throws Exception {
		// 결과 저장 변수
		int result = 0;
		
		try {
			// 1.드라이버확인
			// 2.DB연결
			con = DB.getConnection();
			// 3.SQL (CHANGEGRADENO)
			// 4.실행객체에 데이터세팅
			pstmt = con.prepareStatement(CHANGEGRADENO);
			pstmt.setInt(1, vo.getGradeNo());
			pstmt.setString(2, vo.getId());
			// 5.실행
			result = pstmt.executeUpdate();
			// 6.결과확인
			if (result == 0) {
 				throw new Exception("예외발생 : 아이디가 맞지 않습니다.");
 			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getMessage().indexOf("예외발생")>=0) {
				throw e;
			}
			else {
				throw new Exception("예외발생 : 회원등급 DB처리 중 예외발생");
			}
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과 리턴
		return result;
	}// end of changeGradeNo()
	

	// 회원상태 변경
	public int changeStatus(MemberVO vo) throws Exception {
		// 결과 저장 변수
		int result = 0;
		
		try {
			// 1.드라이버확인
			// 2.DB연결
			con = DB.getConnection();
			// 3.SQL (CHANGESTATUS)
			// 4.실행객체에 데이터세팅
			pstmt = con.prepareStatement(CHANGESTATUS);
			pstmt.setString(1, vo.getStatus());
			pstmt.setString(2, vo.getId());
			// 5.실행
			result = pstmt.executeUpdate();
			// 6.결과확인
			if (result == 0) {
 				throw new Exception("예외발생 : 아이디가 맞지 않습니다.");
 			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getMessage().indexOf("예외발생")>=0) {
				throw e;
			}
			else {
				throw new Exception("예외발생 : 회원상태 DB처리 중 예외발생");
			}
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt);
		}
		
		// 결과 리턴
		return result;
	}// end of changeStatus()
	
 	// 회원정보의 사진 수정
 	// MemberController("/member/changePhoto.do")
 	// ->Execute->MemberChangePhotoService->여기까지
 	public int changePhoto(MemberVO vo) throws Exception {
 		// 결과받을변수
 		int result = 0;
 		try {
			// 1.드라이버확인
 			// 2.DB연결
 			con = DB.getConnection();
 			// 3.SQL (UPDATE)
 			// 4.실행객체에 데이터세팅
 			pstmt = con.prepareStatement(CHANGEPHOTO);
 			// pstmt 에 데이터 세팅순서는
 			// SQL문에서 적은 ?순서대로 1번부터 세팅해준다.
 			pstmt.setString(1, vo.getPhoto());
 			pstmt.setString(2, vo.getId());
 			// 5.실행
 			result = pstmt.executeUpdate();
 			// 6.결과확인
 			if (result == 0) {
 				throw new Exception("예외발생 : 아이디가 맞지 않습니다.");
 			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (e.getMessage().indexOf("예외발생")>=0) throw e;
			else {
				throw new Exception("예외발생 : 회원 정보 사진 수정 DB 처리중 예외발생");
			}
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt);
		}
 		
 		return result;
 	}


	
	
	
	final String LIST = ""
			+ " select id, name, birth, tel, gradeNo, "
			+ " gradeName, status, photo from "
				+ " (select rownum rnum, id, name, birth, tel,"
				+ " gradeNo, gradeName, status, photo from "
					+ " (select m.id, m.name, "
					+ " to_char(m.birth, 'yyyy-mm-dd') birth, m.tel, "
					+ " m.gradeNo, g.gradeName, m.status, m.photo "
					+ " from member m, grade g "
					+ " where m.gradeNo = g.gradeNo "
					+ " order by id asc"
					+ ")"
				+ ")"
			+ " where rnum>=? and rnum<=?";
	
	
	
	final String VIEW = "select m.id, m.pw, m.name, m.gender, "
			+ " to_char(m.birth, 'yyyy-mm-dd') birth, m.tel, m.email,"
			+ " to_char(m.regDate, 'yyyy-mm-dd') regDate, "
			+ " to_char(m.conDate, 'yyyy-mm-dd') conDate, "
			+ " m.photo, m.gradeNo, g.gradeName, m.status "
			+ " from member m, grade g "
			+ " where (id = ?) and (m.gradeNo = g.gradeNo)";
	
	final String WRITE = "insert into member "
			+ " (id, pw, name, gender, birth, tel, email, photo) "
			+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
	
	final String CHECKID = "select id from member where id=?";
	
	final String UPDATE = "update member "
			+ " set name = ?, gender = ?, birth = ?, "
			+ " tel = ?, email = ?, photo = ? "
			+ " where id = ? and pw = ?";
	
	final String DELETE = "update member "
			+ " set status='탈퇴' "
			+ " where id = ? and pw = ?";
	
	final String LOGIN = "select m.id, m.pw, m.name, m.gradeNo, "
			+ " g.gradeName, m.photo, m.newMsgCnt "
			+ " from member m, grade g "
			+ " where (id = ? and pw = ? and status = '정상') "
			+ " and (g.gradeNo = m.gradeNo)";
	
	final String UPDATE_CONDATE = "update member "
			+ " set conDate = sysDate where id = ?";
	
	
	final String CHANGEGRADENO = "update member "
			+ " set gradeNo = ? where id = ?";
	
	final String CHANGESTATUS = "update member "
			+ " set status = ? where id = ?";
	
	final String CHANGEPHOTO = "update member "
			+ " set photo = ? where id = ?";
	
	
	
} // end of class
