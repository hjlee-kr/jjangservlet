package com.jjangplay.main.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jjangplay.ajax.controller.AjaxController;
import com.jjangplay.board.controller.BoardController;
import com.jjangplay.boardreply.controller.BoardReplyController;
import com.jjangplay.image.controller.ImageController;
import com.jjangplay.member.controller.MemberController;
import com.jjangplay.notice.controller.NoticeController;

// Servlet Project 만드는 순서
// 1. Dynamic Web Project 생성 : web.xml check해서
// 2. 라이브러리 등록 (src/main/webapp/WEB-INF/lib폴더에)
//  - ojdbc6.jar : database (오라클)
//  - sitemesh : sitemesh-2.4.2.jar
//  - jstl : jstl.jar, stardard.jar
//  - 파일업로드 : cos.jar
// 3. sitemesh 적용시키기
//  - 설정 : sitemesh.xml, decorator.xml 파일을 WEB-INF폴더에 넣습니다.
//  - 레이아웃구현 jsp : views/decorater/default_decorator.jsp
// 4. 서버만들기와 설정
// 5. DispatcherServlet servlet작성 : init(), service()
/**
 * Servlet implementation class DispatcherServlet
 */
//@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Controller 선언과 생성 - 1번만 처리된다.
	private BoardController boardController= new BoardController();
	private BoardReplyController boardReplyController
		= new BoardReplyController();
	private NoticeController noticeController= new NoticeController();
	private MemberController memberController = new MemberController();
	private ImageController imageController = new ImageController();
	private MainController mainController = new MainController();
	private AjaxController ajaxControlller = new AjaxController();
       
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		// 드라이버 확인, 객체 생성 처리
		// 서버가 시작될때 먼저 실행되는 부분 - 1번만 실행된다.
		System.out.println("DispatcherServlet.init() --- 초기화 진행 ---");
		try {
			// 객체 생성 및 초기화 + 조립
			Class.forName("com.jjangplay.main.controller.Init");
			// 오라클 드라이버 확인 + 로딩
			Class.forName("com.jjangplay.util.db.DB");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 메뉴를 구성하여 출력하고, 선택(URI)해서 처리하는 내용
		// ex)uri="/board"로 시작하면 BoardController 로 가게 만든다.
		System.out.println("DispatcherServlet.service() ------------------");
		
		String uri = request.getRequestURI();
		System.out.println("uri = " + uri);
		
		// main처리 - localhost, localhost/main.do,
		if (uri.equals("/") || uri.equals("/main.do")) {
			response.sendRedirect("/main/main.do");
			return;
		}
		
		// uri : /module/기능 -> /board/list.do
		// 두번째 /의 위치값이 pos에 저장된다. 없으면 -1
		int pos = uri.indexOf("/", 1);
		System.out.println("pos = " + pos);
		
		if (pos == -1) {
			request.setAttribute("uri", uri);
			request.getRequestDispatcher("/WEB-INF/views/error/noModule_404.jsp")
			.forward(request, response);
			return;
		}
		
		String module = uri.substring(0, pos);
		System.out.println("module = " + module);
		
		String jsp = null;
		
		switch (module) {
		case "/main":
			System.out.println("===메인===");
			jsp = mainController.execute(request);
			break;
		case "/board":
			System.out.println("===일반게시판===");
			jsp = boardController.execute(request);
			break;
		case "/boardreply":
			System.out.println("===일반게시판댓글처리===");
			jsp = boardReplyController.execute(request);
			break;
		case "/notice":
			System.out.println("===공지사항===");
			jsp = noticeController.execute(request);
			break;
		case "/member":
			System.out.println("===회원 관리===");
			jsp = memberController.execute(request);
			break;
		case "/ajax":
			System.out.println("===아이디 중복 체크===");
			jsp = ajaxControlller.execute(request);
			break;
		case "/image":
			System.out.println("===이미지게시판===");
			jsp = imageController.execute(request);
			break;
		default:
			request.setAttribute("uri", uri);
			request.getRequestDispatcher("/WEB-INF/views/error/noModule_404.jsp")
			.forward(request, response);
			return;
		}
		
		System.out.println("jsp=" + jsp);
		
		if (jsp.indexOf("redirect:") == 0) {
			// 리스트로 이동하기 위해 "redirect:"는 자른후 경로를 적어준다.
			response.sendRedirect(jsp.substring("redirect:".length()));
		}
		else {
			// jsp로 forward 한다.
			// "/WEB-INF/views/" + board/list +".jsp"
			// "/WEB-INF/views/" + notice/list +".jsp"
			request.getRequestDispatcher("/WEB-INF/views/" + jsp + ".jsp")
				.forward(request, response);
		}
	}

}








