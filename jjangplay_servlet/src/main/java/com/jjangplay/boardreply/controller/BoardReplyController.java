package com.jjangplay.boardreply.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import com.jjangplay.boardreply.vo.BoardReplyVO;
import com.jjangplay.main.controller.Init;
import com.jjangplay.util.exe.Execute;
import com.jjangplay.util.page.ReplyPageObject;

// Board(일반게시판) 의 메뉴를 선택하고, 데이터 수집(기능별), 예외처리
public class BoardReplyController {

	public String execute(HttpServletRequest request) {
		System.out.println("BoardReplyController.execute() ----------------");

		// session정보를 꺼내온다.
		HttpSession session = request.getSession();
		
		// 메뉴입력(uri)
		String uri = request.getRequestURI();
			
		// 데이터 수집을 위한 객체선언
		// 초기값 null 을 주어서 데이터를 받았는지 체크하고 처리한다.
		Object result = null;
			
		// 이동할 jsp 주소를 담아놀 변수
		String jsp = null;
			
			try {
				// 페이지 정보 받기 - 돌아갈때 view.do에 붙이기 위해서
				ReplyPageObject pageObject
					= ReplyPageObject.getInstance(request);
			
				switch (uri) {
				case "/boardreply/write.do":
					System.out.println("3. 일반게시판 댓글 등록 처리");
					
					// 데이터 수집(사용자->서버) : form->input->name 
					// 내용, 작성자, 비밀번호
					String content = request.getParameter("content");
					String writer = request.getParameter("writer");
					String pw = request.getParameter("pw");
					
					// 입력받은 데이터를 BoardVO 안에 저장(세팅) => DB에 넘겨주기위한
					BoardReplyVO vo = new BoardReplyVO();
					vo.setNo(pageObject.getNo());
					vo.setContent(content);
					vo.setWriter(writer);
					vo.setPw(pw);
					
					//[BoardController] -> (Execute) ->
					// BoardWriteService -> BoardDAO.write()
					Execute.execute(Init.get(uri), vo);
					
					// jsp 정보앞에 "redirect:" 가 붙어있으면 redirect로 처리
					// 없으면 forword
					jsp = "redirect:/board/view.do?no=" + pageObject.getNo()
						+ "&inc=0"
						// 일반게시판 글보기의 페이지 및 검색정보 붙이기
						+ "&" + pageObject.getPageObject().getPageQuery()
						// 댓글의 페이지 정보
						+ "&" + pageObject.getPageQuery()
						;
					session.setAttribute("msg", "댓글이 등록되었습니다.");
					break;
				case "/boardreply/update.do":
					System.out.println("4. 일반게시판 댓글수정 처리");
					
					// updateForm 적은 데이터를 가져온다. (DB에 저장하기 위해)
					Long rno = Long.parseLong(request.getParameter("rno"));
					content = request.getParameter("content");
					writer = request.getParameter("writer");
					pw = request.getParameter("pw");
					
					vo = new BoardReplyVO();
					vo.setRno(rno);
					vo.setContent(content);
					vo.setWriter(writer);
					vo.setPw(pw);
					
					
					Execute.execute(Init.get(uri), vo);
					
					jsp="redirect:/board/view.do?no="
						+ pageObject.getNo() + "&inc=0"
						// 일반게시판 글보기의 페이지 및 검색정보 붙이기
						+ "&" + pageObject.getPageObject().getPageQuery()
						// 댓글의 페이지 정보
						+ "&" + pageObject.getPageQuery();
					
					session.setAttribute("msg", "댓글이 수정되었습니다.");
					break;
				case "/boardreply/delete.do":
					System.out.println("5. 일반게시판 댓글삭제");
					// 데이터 수집 : 삭제할 댓글번호, 확인용 비밀번호
					// vo 객체에 담는다.
					vo = new BoardReplyVO();
					vo.setRno(Long.parseLong(request.getParameter("rno")));
					vo.setPw(request.getParameter("pw"));
					
					// DB처리
					result =Execute.execute(Init.get(uri), vo);
					
					// 결과표시
					if ((Integer) result == 1) {
						System.out.println();
						System.out.println("***********************");
						System.out.println("** " + vo.getNo() + "번 글이 삭제되었습니다.");
						System.out.println("***********************");
					}
					else {
						System.out.println();
						System.out.println("########################");
						System.out.println("## " + vo.getNo() + "번 글이 삭제되지 않았습니다.");
						System.out.println("########################");
					}
					jsp = "redirect:/board/view.do?no=" + pageObject.getNo()
					+ "&inc=0"
					// 일반게시판 글보기의 페이지 및 검색정보 붙이기
					+ "&" + pageObject.getPageObject().getPageQuery()
					// 댓글의 페이지 정보
					+ "&" + pageObject.getPageQuery()
					;
				session.setAttribute("msg", "댓글이 삭제되었습니다.");
					break;

				default:
					request.setAttribute("uri", uri);
					jsp = "error/404";
				} // end of switch
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				request.setAttribute("e", e);
				jsp = "error/500";
			}
		
			return jsp;
	} // end of execute
} // end of class
