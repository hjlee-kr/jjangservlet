package com.jjangplay.member.controller;

import java.awt.Label;
import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthOptionPaneUI;

import com.jjangplay.main.controller.Init;
import com.jjangplay.main.controller.Main;
import com.jjangplay.member.service.MemberConUpdateService;
import com.jjangplay.member.service.MemberDeleteService;
import com.jjangplay.member.service.MemberListService;
import com.jjangplay.member.service.MemberLoginService;
import com.jjangplay.member.service.MemberUpdateService;
import com.jjangplay.member.service.MemberViewService;
import com.jjangplay.member.service.MemberWriteService;
import com.jjangplay.member.vo.LoginVO;
import com.jjangplay.member.vo.MemberVO;
import com.jjangplay.util.exe.Execute;
import com.jjangplay.util.io.In;
import com.jjangplay.util.io.MemberPrint;
import com.jjangplay.util.page.PageObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

// 회원관리를 위한 모듈
public class MemberController {

	public String execute(HttpServletRequest request) {

		System.out.println("MemberController.execute()----------------");
		
		// 로그인 처리를 session으로 한다.
		HttpSession session = request.getSession();
		
		// id를 기준으로 회원정보를 관리합니다.
		String id = null;
		LoginVO login = (LoginVO) session.getAttribute("login");
		// 로그인이 되어있는 경우에만 id를 가져옵니다.
		if (login != null) id = login.getId();
			
			
			// uri
			String uri = request.getRequestURI();
			
			// jsp
			String jsp = null;
			
			// 결과저장용
			Object result = null;
			
			// 입력받는데이터
			Long no = 0L;
			String pw;
			MemberVO vo;
			
			// 파일업로드 설정
			// 파일의 절대위치를 지정
			String savePath = "/upload/member";
			String realSavePath
				= request.getServletContext().getRealPath(savePath);
			// 업로드 파일용량 제한
			int sizeLimit = 100 * 1024 * 1024; // 100MByte
			
			File realSavePathFile = new File(realSavePath);
			// 폴더가 존재하지 않으면 만들어 준다.
			if (!realSavePathFile.exists()) {
				realSavePathFile.mkdir();
			}
			
			try {
				switch (uri) {
				case "/member/loginForm.do":
					System.out.println("---로그인 폼---");
					jsp = "member/loginForm";
					break;
				case "/member/login.do":
					System.out.println("---로그인 처리---");
					id = request.getParameter("id");
					pw = request.getParameter("pw");
					
					LoginVO loginVO = new LoginVO();
					loginVO.setId(id);
					loginVO.setPw(pw);
					
					// DB처리
					// 여기(MemberController)에서 -> Execute
					// -> MemberLoginService -> MemberDAO().login()
					// 로그인한 데이터를  session 담는다.
					session.setAttribute("login",
							Execute.execute(Init.get(uri), loginVO));

					
//					if (Main.login != null) {
//						System.out.println();
//						System.out.println("**************************");
//						System.out.println("** 로그인 되었습니다.       **");
//						System.out.println("**************************");
//						
//						// 최근 접속일 변경
//						// 여기서 -> Execute -> MemberConUpdateService -> MemberDAO().conUpdate(id)
//						Execute.execute(new MemberConUpdateService(), Main.login.getId());
//					}
					// 로그인 완료 메시지 처리
					session.setAttribute("msg", "로그인 처리가 되었습니다");
					
					// 원래는 main page로 가야하나 구현 전이어서
					// 일반게시판 list로 이동합니다. 
					jsp = "redirect:/main/main.do";
					break;
				case "/member/logout.do":
					System.out.println("---로그아웃 처리---");
					// session의 login 정보 지우기 -> 로그아웃처리
					session.removeAttribute("login");
					
					session.setAttribute("msg", "로그아웃 되었습니다.");
					
					// 임시 이동 경로
					jsp = "redirect:/main/main.do";
					break;
				case "/member/list.do":
					System.out.println("1.회원 리스트");
					
					PageObject pageObject = PageObject.getInstance(request);
					// MemberController->Execute.execute()->
					// MemberListService->MemberDAO().list()
					// DB처리해서 데이터를 받는다.
					result = Execute.execute(Init.get(uri), pageObject);
					
					// 받아온 데이터를 requset에 담는다.
					request.setAttribute("list", result);
					

					jsp = "member/list";
					break;
				case "/member/view.do":
					System.out.println("2.내 정보 또는 회원정보 보기");
					
					// 로그인이 되어있어야 이곳으로 넘어옵니다.
					// 상단에있는 내 정보보기를 클릭하여 들어오면 request 에 담겨있는 값이 없습니다.
					// 이때는 session에서 id를 가져와서 처리합니다.
					// 관리자가 회원정보리스트에서 클릭하여 이곳으로 들어올때는
					// request에 id값이 담겨있어서 request에 담긴 id값으로 처리합니다.
					id = request.getParameter("id");
					if (id == null) {
						// session->LoginVO login
						id = login.getId();
					}

					// id를 가지고 DB에서 데이터 가져옵니다.
					// 여기서(MemberController)->Execute->MemberViewService->MemberDAO.view()
					result = Execute.execute(Init.get(uri), id);
					
					request.setAttribute("vo", result);
					
					jsp = "member/view";
					break;
				case "/member/writeForm.do":
					System.out.println("3-1.회원가입 폼");
					jsp="member/writeForm";
					break;
				case "/member/write.do":
					System.out.println("3.회원가입 처리");
					
					MultipartRequest multi = 
						new MultipartRequest(request, realSavePath, sizeLimit,
							"utf-8", new DefaultFileRenamePolicy());
					
					// 데이터 수집 (사용자(form) -> 서버 -> request -> multi) 
					id = multi.getParameter("id");
					pw = multi.getParameter("pw");
					String name = multi.getParameter("name");
					String gender = multi.getParameter("gender");
					String birth = multi.getParameter("birth");
					String tel = multi.getParameter("tel");
					String email = multi.getParameter("email");
					String photo = multi.getFilesystemName("photo");
					
					vo = new MemberVO();
					vo.setId(id);
					vo.setPw(pw);
					vo.setName(name);
					vo.setGender(gender);
					vo.setBirth(birth);
					vo.setTel(tel);
					vo.setEmail(email);

					if (!(photo == null || photo.equals("") ) ) {
						vo.setPhoto(savePath + "/" + photo);
					}
					// MemberController->Execute->MemberWriteService
					// ->MemberDAO().write()
					Execute.execute(Init.get(uri), vo);
					
					session.setAttribute("msg", "회원가입이 완료되었습니다.");
					// 원래는 main으로 가야하나 임시로
					// /board/list.do로 이동한다.
					jsp="redirect:/main/main.do";
					break;
				case "/member/updateForm.do":
					System.out.println("4-1. 내 정보 수정 폼");
					id = request.getParameter("id");

					// id를 가지고 DB에서 데이터 가져옵니다.
					// 여기서(MemberController)->Execute->MemberViewService->MemberDAO.view()
					result = Execute.execute(Init.get("/member/view.do"), id);
					
					request.setAttribute("vo", result);

					jsp = "member/updateForm";
					break;
				case "/member/update.do":
					System.out.println("4.내 정보 수정 처리");
				
					// 정보수정과 DB처리 메서드를 이용
					vo = new MemberVO();
					vo.setId(request.getParameter("id"));
					vo.setPw(request.getParameter("pw"));
					vo.setName(request.getParameter("name"));
					vo.setGender(request.getParameter("gender"));
					vo.setBirth(request.getParameter("birth"));
					vo.setTel(request.getParameter("tel"));
					vo.setEmail(request.getParameter("email"));
					
					// MemberController->Execute->MemberUpdateService
					// -> MemberDAO.update()
					Execute.execute(Init.get(uri), vo);
				
					session.setAttribute("msg", "정보가 수정되었습니다.");
					
					jsp="redirect:/member/view.do?id="+vo.getId();
				//	jsp="redirect:/member/view.do";
					break;
				case "5":
					System.out.println("5.회원탈퇴");
					// 회원상태를 "탈퇴" 로 변경
					if (Main.login == null) {
						id = In.getStr("아이디");
						pw = In.getStr("비밀번호");
					}
					else {
						id = Main.login.getId();
						pw = Main.login.getPw();
					}
					
					vo = new MemberVO();
					vo.setId(id);
					vo.setPw(pw);
					
					// DB처리
					// MemberControll(여기)->Execute->MemberDeleteService
					// ->MemberDAO().delete(MemberVO vo)
					result = Execute.execute(new MemberDeleteService(), vo);
					
					// 탈퇴 후에는 로그아웃 처리, 메시지
					if ((Integer)result == 1) {
						Main.login = null; // 로그아웃
						
						System.out.println();
						System.out.println("******************************");
						System.out.println("** 회원 탈퇴 처리가 완료되었습니다. **");
						System.out.println("** 로그아웃이 되었습니다.         **");
						System.out.println("******************************");
					}
					break;

				case "/member/changeGradeNo.do":
					System.out.println("회원 등급 수정 처리");
					
					// 데이터 수집 (사용자(form)->서버(request)->DB
					id = request.getParameter("id");
					Integer gradeNo = Integer.parseInt(request.getParameter("gradeNo"));
					
					// vo에 저장 
					vo = new MemberVO();
					vo.setId(id);
					vo.setGradeNo(gradeNo);
					
					// DB처리 MemberChangeGradeNoService->MemberDAO.changeGradeNo()
					Execute.execute(Init.get(uri), vo);
					
					// 페이지 정보 받기 & uri 에 붙이기
					pageObject = PageObject.getInstance(request);
					
					// 메시지 출력 (사용자가 보도록)
					// 1: ((gradeNo==1)?"일반회원으로":"관리자로")
					// 2: if (gradeNo == 1) "일반회원으로";
					//    else "관리자로";
					// 1과 2는 처리결과가 동일합니다.
					session.setAttribute("msg",
						"회원 [" + id + "] 등급이 " + ((gradeNo==1)?"일반회원으로":"관리자로")
						+ " 변경되었습니다.");
					
					// 페이지 이동 (회원리스트로)
					jsp = "redirect:list.do?" + pageObject.getPageQuery();
					break;
				case "/member/changeStatus.do":
					System.out.println("회원 상태 수정 처리");
					
					// 데이터 수집 (사용자(form)->서버(request)->DB
					id = request.getParameter("id");
					// status에 담기는 값은 "정상,탈퇴,휴면,강퇴" 중 하나
					String status = request.getParameter("status");
					
					// vo에 저장 
					vo = new MemberVO();
					vo.setId(id);
					vo.setStatus(status);
					
					// DB처리 MemberChangeStatusService->MemberDAO.changeStatus()
					Execute.execute(Init.get(uri), vo);
					
					// 페이지 정보 받기 & uri 에 붙이기
					pageObject = PageObject.getInstance(request);
					
					// 메시지 출력 (사용자가 보도록)
					session.setAttribute("msg",
						"회원 [" + id + "] 상태가 [" + status
						+ "]로 변경되었습니다.");
					
					// 페이지 이동 (회원리스트로)
					jsp = "redirect:list.do?" + pageObject.getPageQuery();
					break;

				default:
					request.setAttribute("uri", uri);
					jsp = "error/404";
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				request.setAttribute("e", e);
				jsp = "error/500";
			}
			
		return jsp;
	} // end of execute()
	
	// 정보수정DB처리를 위한 메서드
	private void update(MemberVO vo) throws Exception {
		// 가져온 데이터 수정
		while (true) {
			new MemberPrint().print(vo);
			System.out.println();
			System.out.println("---------------------------------");
			System.out.println("-- 1.이름, 2.성별, 3.생년월일,     --");
			System.out.println("-- 4.연락처, 5.이메일, 6.사진,     --");
			System.out.println("-- 9.수정취소, 0.수정완료          --");
			System.out.println("---------------------------------");
			String menu = In.getStr("수정항목선택");
			switch(menu) {
			case "1":
				vo.setName(In.getStr("이름"));
				break;
			case "2":
				vo.setGender(In.getStr("성별(남자/여자)"));
				break;
			case "3":
				vo.setBirth(In.getStr("생년월일(YYYY-MM-DD)"));
				break;
			case "4":
				vo.setTel(In.getStr("연락처"));
				break;
			case "5":
				vo.setEmail(In.getStr("이메일"));
				break;
			case "6":
				vo.setPhoto(In.getStr("사진"));
				break;
			case "9":
				System.out.println();
				System.out.println("*** 수정이 취소 되었습니다. ***");
				return; // update()메서드를 빠져나간다.
			case "0":
				// 수정하기전 본인확인용 비밀번호를 받는다.
				vo.setPw(In.getStr("비밀번호"));
				// DB 처리
				// 여기 (MemberController) -> Execute -> MemberUpdateService
				// -> MemberDAO().update()
				Execute.execute(new MemberUpdateService(), vo);
				return; // update()메서드를 빠져나간다. - 수정완료
			default:
				System.out.println("###########################");
				System.out.println("## 항목를 잘못 선택 하셨습니다. ##");
				System.out.println("## [0~6,9] 를 선택 하세요.   ##");
				System.out.println("###########################");
			} // end of switch
		} // end of while()
	} // end of update()
} // end of class








