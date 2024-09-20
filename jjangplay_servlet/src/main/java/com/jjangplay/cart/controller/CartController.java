package com.jjangplay.cart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jjangplay.cart.vo.CartVO;
import com.jjangplay.main.controller.Init;
import com.jjangplay.util.exe.Execute;
import com.jjangplay.util.page.PageObject;

public class CartController {

	public String execute(HttpServletRequest request) {
		System.out.println("CartController.execute()---------");
		
		// 메뉴입력 (uri)
		String uri = request.getRequestURI();
		
		// 데이터 수집을 위한 객체선언
		// 초기값 null 을 설정하여 데이터를 받았는지 체크
		Object result = null;
		
		// 이동할 주소를 담아놓을 변수
		String jsp = null;
		
		List<CartVO> cartList = null;
		
		// session설정
		HttpSession session = request.getSession();
		
		cartList = (List<CartVO>) session.getAttribute("cartList");
		if (cartList == null) {
			// 카트에 물건이 없으면 리스트를 새로 만든다.
			cartList = new ArrayList<CartVO>();
		}
		
		try {
			// CRUD중 R만 있음
			switch(uri) {
			case "/cart/add.do":
				System.out.println("0. 장바구니 물건을 담는 처리");
				CartVO vo = new CartVO();
				vo.setGno(Long.parseLong(request.getParameter("gno")));
				vo.setStd_price(Long.parseLong(request.getParameter("std_price")));
				vo.setDiscount(Long.parseLong(request.getParameter("discount")));
				vo.setDelivery_cost(Long.parseLong(request.getParameter("delivery_cost")));
				vo.setImageName(request.getParameter("imageName"));
				vo.setName(request.getParameter("name"));
				vo.setCount(1L);
				
				cartList.add(vo);
				String strName = vo.getName()+ "가 장바구니에 담겼습니다.";
				session.setAttribute("msg", strName);
				session.setAttribute("cartList", cartList);
				
				jsp = "redirect:/shop/view.do?gno=" + vo.getGno();
				break;
			case "/cart/list.do":
				System.out.println("1. 장바구니 리스트===");
				// 페이지 처리를 위한 객체
				PageObject pageObject = PageObject.getInstance(request);

				// 서비스실행
				//result = Execute.execute(Init.get(uri), pageObject);
				result = session.getAttribute("cartList");
				System.out.println("result = " + (List<CartVO>) result);
				
				request.setAttribute("list", result);
				request.setAttribute("pageObject", pageObject);
				
				// 화면에 표시할 곳으로 이동
				jsp = "cart/list";
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
