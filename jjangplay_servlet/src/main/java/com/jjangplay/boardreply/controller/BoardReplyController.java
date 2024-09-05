package com.jjangplay.boardreply.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jjangplay.board.service.BoardDeleteService;
import com.jjangplay.board.service.BoardListService;
import com.jjangplay.board.service.BoardUpdateService;
import com.jjangplay.board.service.BoardViewService;
import com.jjangplay.board.service.BoardWriteService;
import com.jjangplay.board.vo.BoardVO;
import com.jjangplay.boardreply.vo.BoardReplyVO;
import com.jjangplay.main.controller.Init;
import com.jjangplay.util.exe.Execute;
import com.jjangplay.util.io.BoardPrint;
import com.jjangplay.util.io.In;
import com.jjangplay.util.page.PageObject;
import com.jjangplay.util.page.ReplyPageObject;

// Board(일반게시판) 의 메뉴를 선택하고, 데이터 수집(기능별), 예외처리
public class BoardReplyController {

	@SuppressWarnings("unchecked")
	public String execute(HttpServletRequest request) {
		System.out.println("BoardReplyController.execute() ----------------");

		// session정보를 꺼내온다.
		HttpSession session = request.getSession();
		
		// 메뉴입력(uri)
		String uri = request.getRequestURI();
			
		// 데이터 수집을 위한 객체선언
		// 초기값 null 을 주어서 데이터를 받았는지 체크하고 처리한다.
		Object result = null;
			
		Long no = 0L;
			
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
					break;
				case "/board/update.do":
					System.out.println("4. 일반게시판 글수정 처리");
					
					// updateForm 적은 데이터를 가져온다. (DB에 저장하기 위해)
					no = Long.parseLong(request.getParameter("no"));
					content = request.getParameter("content");
					writer = request.getParameter("writer");
					pw = request.getParameter("pw");
					
					vo = new BoardReplyVO();
					vo.setNo(no);
					vo.setContent(content);
					vo.setWriter(writer);
					vo.setPw(pw);
					
					
					Execute.execute(Init.get(uri), vo);
					
					jsp="redirect:view.do?no="+no+"&inc=0";
					break;
				case "/board/delete.do":
					System.out.println("5. 일반게시판 글삭제");
					// 데이터 수집 : 삭제할 글번호, 확인용 비밀번호
					vo = new BoardReplyVO();
					
					vo.setNo(Long.parseLong(request.getParameter("no")));
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
					jsp = "redirect:list.do";
					break;

				default:
					System.out.println("잘못된 메뉴를 선택하셨습니다.");
					System.out.println("[0~5] 번호를 선택해야 합니다.");
				} // end of switch
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
	} // end of execute
} // end of class
