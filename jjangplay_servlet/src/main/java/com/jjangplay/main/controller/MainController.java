package com.jjangplay.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jjangplay.member.vo.LoginVO;
import com.jjangplay.util.exe.Execute;
import com.jjangplay.util.page.PageObject;

public class MainController {

	public String execute(HttpServletRequest request) {
		System.out.println("MainController.execute()---------------");
		
		String uri = request.getRequestURI();
		
		Object result = null;
		
		String jsp = null;
		
		// 로그인 확인
		HttpSession session = request.getSession();
		int gradeNo =0;//로그인 안한 상태는 gradeNo=0, 1->일반회원, 9->관리자
		LoginVO login = (LoginVO) session.getAttribute("login");
		if (login != null) gradeNo = login.getGradeNo();
		
		try {
			switch(uri) {
			case "/main/main.do":
				System.out.println("1.메인처리");
				// 페이지 처리를 위한 객체생성
				PageObject pageObject = new PageObject();
				
				// 공지사항 리스트
				pageObject.setPerPageNum(5);
				result = Execute.execute(Init.get("/notice/list.do"), pageObject);
				request.setAttribute("noticeList", result);
				
				// 일반게시판 리스트
				pageObject.setPerPageNum(5);
				result = Execute.execute(Init.get("/board/list.do"), pageObject);
				request.setAttribute("boardList", result);
				
				// 이미지게시판 리스트
				pageObject.setPerPageNum(3);
				result = Execute.execute(Init.get("/image/list.do"), pageObject);
				request.setAttribute("imageList", result);
				
				jsp = "main/main";
				break;
			default:
				jsp = "error/404";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// 예외객체를 jsp에서 사용하기 위해 requset에 담는다.
			request.setAttribute("e", e);
			jsp="error/500";
		}
		
		
		return jsp;
	}
}









