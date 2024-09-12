package com.jjangplay.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jjangplay.main.controller.Init;
import com.jjangplay.member.vo.LoginVO;
import com.jjangplay.notice.vo.NoticeVO;
import com.jjangplay.util.exe.Execute;
import com.jjangplay.util.page.PageObject;

public class NoticeController {

	public String execute(HttpServletRequest request) {
			
			// uri
			String uri = request.getRequestURI();
			// 이동할 주소
			String jsp = null;
			
			// 데이터 수집을 위한 변수
			Object result = null;
			
			// 입력 받는 데이터 (글번호를 위한)
			Long no = 0L;
			
			// 처리 결과 표시(모달창)과 로그인 여부 확인 위한
			// session을 가져온다.
			HttpSession session = request.getSession();
			
			String id = null;
			int gradeNo = 0;
			LoginVO login = (LoginVO) session.getAttribute("login");
			
			if (login != null) {
				// login != null 이면 로그인 상태임
				// 아이디와 회원등급을 가져온다.
				id = login.getId();
				gradeNo = login.getGradeNo();
			}
			
			try {
				switch (uri) {
				case "/notice/list.do":
					// NoticeController -> execute -> NoticeListService
					// -> NoticeDAO.list()
					System.out.println("1. 공지사항 리스트");
					// 페이지 처리를 위한 객체, 넘어오는 페이지와 검색정보를 세팅
					PageObject pageObject = PageObject.getInstance(request);
					// 공지사항 표시정보
					String period = request.getParameter("period");
					
					System.out.println("period=" + period);
					
					if (gradeNo == 9) {
						// 관리자는 공지사항 기본이 모든공지 리스트 보기 
						if (period == null || period == "") {
							period = "all";
						}
					}
					else {
						period = "pre";
					}
					pageObject.setPeriod(period);
					
					// DB에서 데이터 가져오기
					result = Execute.execute(Init.get(uri), pageObject);
					// 가져온 데이터 담기
					request.setAttribute("list", result);
					request.setAttribute("pageObject", pageObject);
					jsp = "notice/list";
					break;
				case "/notice/view.do":
					System.out.println("2. 공지사항 글보기");
					// NoticeController -> execute -> NoticeViewService
					// -> NoticeDAO.view()
					// 공지사항은 조회수가 없으므로 increase()메서드가 없다.
					no = Long.parseLong(request.getParameter("no"));
					// DB에서 데이터를 가져오기
					result = Execute.execute(Init.get(uri), no);
					// 가져온 데이터를 담는다.(저장한다)
					request.setAttribute("vo", result);
					// 데이터를 화면에 보여준다.
					jsp = "notice/view"; //jsp 파일 + 경로
					break;
				case "/notice/writeForm.do":
					System.out.println("3. 공지사항 글쓰기 폼");
					jsp = "notice/writeForm";
					break;
				case "/notice/write.do":
					System.out.println("3. 공지사항 글쓰기 처리");
					// 데이터 수집
					// 제목, 내용, 게시시작일, 게시종료일
					String title = request.getParameter("title");
					String content = request.getParameter("content");
					String startDate = request.getParameter("startDate");
					String endDate = request.getParameter("endDate");
					
					NoticeVO vo = new NoticeVO();
					vo.setTitle(title);
					vo.setContent(content);
					vo.setStartDate(startDate);
					vo.setEndDate(endDate);
					
					// NoticeController -> execute -> NoticeWriteService
					// -> NoticeDAO.write()
					Execute.execute(Init.get(uri), vo);
					
					jsp = "redirect:list.do";
					break;
				case "/notice/updateForm.do":
					System.out.println("4. 공지사항 글수정 폼");
					// 수정 글번호
					no = Long.parseLong(request.getParameter("no"));
					
					// 수정할 데이터 가져오기 (view 서비스를 이용해서)
					result = Execute.execute(
							Init.get("/notice/view.do"), no);
					
					// 가져온 데이터를 담는다.
					request.setAttribute("vo", result);
					
					// 담은 데이터를 jsp로 보내서 보여준다.
					jsp = "notice/updateForm";
					break;
				case "/notice/update.do":
					System.out.println("4. 공지사항 글수정 처리");
					
					// updateForm에서 수정한 데이터를 가져옵니다.
					no = Long.parseLong(request.getParameter("no"));
					title = request.getParameter("title");
					content = request.getParameter("content");
					startDate = request.getParameter("startDate");
					endDate = request.getParameter("endDate");
					
					vo = new NoticeVO();
					vo.setNo(no);
					vo.setTitle(title);
					vo.setContent(content);
					vo.setStartDate(startDate);
					vo.setEndDate(endDate);
					
					// DB에 수정 내용 적용
					result = Execute.execute(Init.get(uri), vo);
					if ((Integer)result != 0) {
						System.out.println("*** 수정이 완료되었습니다. ***");
					}
					
					jsp = "redirect:view.do?no="+no;
					break;
				case "/notice/delete.do":
					System.out.println("5. 공지사항 글삭제");
					// 삭제할 글번호 수집
					no = Long.parseLong(request.getParameter("no"));
					
					// DB처리
					result = Execute.execute(Init.get(uri), no);
					if ((Integer)result != 0) {
						System.out.println();
						System.out.println("***  글이 삭제 되었습니다. ***");
					}
					jsp = "redirect:list.do";
					break;
				default:
					request.setAttribute("uri", uri);
					jsp = "error/404";
					break;
				} // end of switch(menu)
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				request.setAttribute("e", e);
				jsp = "error/500";
			} // end of try~catch
			
			return jsp;

	} // end of execute()
} // end of class
