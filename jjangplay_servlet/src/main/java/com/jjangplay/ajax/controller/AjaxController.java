package com.jjangplay.ajax.controller;

import javax.servlet.http.HttpServletRequest;

import com.jjangplay.main.controller.Init;
import com.jjangplay.util.exe.Execute;

public class AjaxController {

	public String execute(HttpServletRequest request) {
		System.out.println("AjaxController.execute()---------------");
		String jsp = null;
		
		// id를 저장할 변수
		String id = null;
		
		// uri
		String uri = request.getRequestURI();
		
		try {
			switch (uri) {
			case "/ajax/checkId.do":
				System.out.println("1. 아이디 중복 체크 처리");
				
				// 데이터 수집 (id)
				id = request.getParameter("id");
				
				// DB처리 AjaxController->MemberCheckIdService()->MemberDAO.checkId()
				id = (String) Execute.execute(Init.get(uri), id);
				
				request.setAttribute("id", id);
				
				jsp ="member/checkId";
				break;
			default:
				jsp="error/404.jsp";
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		return jsp;
	}
}
