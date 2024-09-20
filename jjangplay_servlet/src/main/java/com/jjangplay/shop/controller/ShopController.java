package com.jjangplay.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jjangplay.main.controller.Init;
import com.jjangplay.util.exe.Execute;
import com.jjangplay.util.page.PageObject;

public class ShopController {

	public String execute(HttpServletRequest request) {
		System.out.println("ShopController.execute()---------");
		
		// 메뉴입력 (uri)
		String uri = request.getRequestURI();
		
		// 데이터 수집을 위한 객체선언
		// 초기값 null 을 설정하여 데이터를 받았는지 체크
		Object result = null;
		
		// 이동할 주소를 담아놓을 변수
		String jsp = null;
		
		// session설정
		HttpSession session = request.getSession();
		
		try {
			// CRUD중 R만 있음
			switch(uri) {
			case "/shop/list.do":
				System.out.println("1. 판매 제품 리스트===");
				// 페이지 처리를 위한 객체
				PageObject pageObject = PageObject.getInstance(request);
				String strPerPageNum = request.getParameter("perPageNum");
				if (strPerPageNum == null) pageObject.setPerPageNum(6);
				// 서비스실행
				result = Execute.execute(Init.get(uri), pageObject);
				
				request.setAttribute("list", result);
				request.setAttribute("pageObject", pageObject);
				
				// 화면에 표시할 곳으로 이동
				jsp = "shop/list";
				break;
			case "/shop/view.do":
				System.out.println("2. 판매 제품 상세보기===");
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
	} // end of execute()
} // end of class
