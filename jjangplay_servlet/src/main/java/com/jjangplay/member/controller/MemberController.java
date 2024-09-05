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
import com.jjangplay.member.service.MemberUpdateAdminService;
import com.jjangplay.member.service.MemberUpdateService;
import com.jjangplay.member.service.MemberViewService;
import com.jjangplay.member.service.MemberWriteService;
import com.jjangplay.member.vo.LoginVO;
import com.jjangplay.member.vo.MemberVO;
import com.jjangplay.util.exe.Execute;
import com.jjangplay.util.io.In;
import com.jjangplay.util.io.MemberPrint;
import com.jjangplay.util.page.PageObject;

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
					jsp = "redirect:/board/list.do";
					break;
				case "/member/logout.do":
					System.out.println("---로그아웃 처리---");
					// session의 login 정보 지우기 -> 로그아웃처리
					session.removeAttribute("login");
					
					session.setAttribute("msg", "로그아웃 되었습니다.");
					
					// 임시 이동 경로
					jsp = "redirect:/board/list.do";
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
				case "2":
					System.out.println("2.내 정보 보기");
					// Login인 되어있는지 확인
					if (Main.login == null) throw new Exception("예외발생 : 로그인이 필요합니다.");
					// 내 정보를 보기위해 데이터를 가져옵니다.
					// 아이디, 패스워드, 이름, 성별, 생일, 연락처, 이메일, 가입일, 최근접속일, 사진,
					// 등급번호, 등급이름
					// 일반회원 : 자신의 것만
					// 관리자는 : 선택할 수 있도록
					id = (Main.login.getGradeNo() == 9)?In.getStr("아이디"):Main.login.getId();
					
					// id를 가지고 DB에서 데이터 가져옵니다.
					// 여기서(MemberController)->Execute->MemberViewService->MemberDAO.view()
					result = Execute.execute(new MemberViewService(), id);
					
					// 가져온 데이터를 출력
					new MemberPrint().print((MemberVO)result);
					
					break;
				case "3":
					System.out.println("3.회원가입");
					// 데이터 수집 
					id = In.getStr("아이디");
					pw = In.getStr("비밀번호");
					String name = In.getStr("이름");
					String gerder = In.getStr("성별(남자/여자)");
					String birth = In.getStr("생년월일(YYYY-MM-DD)");
					String tel = In.getStr("연락처(선택)");
					String email = In.getStr("이메일");
					String photo = In.getStr("사진(선택)");
					
					vo = new MemberVO();
					vo.setId(id);
					vo.setPw(pw);
					vo.setName(name);
					vo.setGender(gerder);
					vo.setBirth(birth);
					vo.setTel(tel);
					vo.setEmail(email);
					vo.setPhoto(photo);
					
					// MemberController->Execute->MemberWriteService
					// ->MemberDAO().write()
					Execute.execute(new MemberWriteService(), vo);
					break;
				case "4":
					System.out.println("4.내 정보 수정");
					// 가장먼저 login 되어있는지 확인
					if (Main.login == null) throw new Exception("예외발생 : 로그인이 필요합니다.");
					
					// 내정보 가져오기
					id = Main.login.getId();
					vo = (MemberVO) Execute.execute(new MemberViewService(), id);
					
					// 정보수정과 DB처리 메서드를 이용
					update(vo);
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
				case "6":
					System.out.println("6.로그인 or 로그아웃");
					if (Main.login == null) {
						// 로그인을 위한 데이터 수집 (id, pw)
						id = In.getStr("아이디");
						pw = In.getStr("비밀번호");
						loginVO = new LoginVO();
						loginVO.setId(id);
						loginVO.setPw(pw);
						
						// DB처리
						// 여기(MemberController)에서 -> Execute
						// -> MemberLoginService -> MemberDAO().login()
						Main.login =
							(LoginVO) Execute.execute(new MemberLoginService(), loginVO);
						
						if (Main.login != null) {
							System.out.println();
							System.out.println("**************************");
							System.out.println("** 로그인 되었습니다.       **");
							System.out.println("**************************");
							
							// 최근 접속일 변경
							// 여기서 -> Execute -> MemberConUpdateService -> MemberDAO().conUpdate(id)
							Execute.execute(new MemberConUpdateService(), Main.login.getId());
						}
					}
					else {
						Main.login = null;
						System.out.println();
						System.out.println("**************************");
						System.out.println("** 로그아웃 되었습니다.     **");
						System.out.println("**************************");
					} // end of if~else
					break;
				case "7":
					// 권한을 확인
					if (Main.login.getGradeNo() != 9) {
						throw new Exception("예외발생 : 처리할 권한이 없습니다.");
					}
					
					// 회원정보를 가져옴
					id = In.getStr("아이디");
					vo = (MemberVO) Execute.execute(new MemberViewService(), id);
					
					// 회원등급 및 상태수정을 위한 메서드
					update_admin(vo);
					break;

				default:
					System.out.println("###########################");
					System.out.println("## 메뉴를 잘못 입력하셨습니다. ##");
					System.out.println("## [0~7] 을 입력하세요.     ##");
					System.out.println("###########################");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println("$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@");
				System.out.println("$%@    <오류 출력> ");
				System.out.println("$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@");
				// getSimpleName() : 클래스 이름만 보여주는 메서드(패키지는 안보여준다)
				System.out.println("$%@ 타입 : " + e.getClass().getSimpleName());
				// getMessage() : 예외의 내용을 보여주는 메서드
				System.out.println("$%@ 내용 : " + e.getMessage() );
				System.out.println("$%@ 조치 : 데이터 확인해 보세요");
				System.out.println("$%@       계속 오류가 나면 전산담당자에게 문의하세요.");
				System.out.println("$%@$%@$%@$%@$%@$%@$%@$%@$%@$%@");
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
	
	
	private void update_admin(MemberVO vo) throws Exception {
		// 가져온 데이터 수정
		while (true) {
			new MemberPrint().print(vo);
			System.out.println();
			System.out.println("---------------------------------");
			System.out.println("-- 1.회원등급, 2.회원상태         --");
			System.out.println("-- 9.수정취소, 0.수정완료          --");
			System.out.println("---------------------------------");
			String menu = In.getStr("수정항목선택");
			switch(menu) {
			case "1":
				vo.setGradeNo(In.getInt("회원등급(1:일반회원 or 9:관리자)"));
				break;
			case "2":
				vo.setStatus(In.getStr("회원상태('정상' or '탈퇴')"));
				break;
			case "9":
				System.out.println();
				System.out.println("*** 수정이 취소 되었습니다. ***");
				return; // update()메서드를 빠져나간다.
			case "0":
				// DB 처리
				// 여기 (MemberController) -> Execute -> MemberUpdateAdminService
				// -> MemberDAO().update_admin()
				Execute.execute(new MemberUpdateAdminService(), vo);
				return; // update()메서드를 빠져나간다. - 수정완료
			default:
				System.out.println("###########################");
				System.out.println("## 항목를 잘못 선택 하셨습니다. ##");
				System.out.println("## [0~6,9] 를 선택 하세요.   ##");
				System.out.println("###########################");
			} // end of switch
		} // end of while()
	} // end of update_admin()
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // end of class
