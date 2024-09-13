package com.jjangplay.goods.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jjangplay.main.controller.Init;
import com.jjangplay.member.vo.LoginVO;
import com.jjangplay.util.exe.Execute;
import com.jjangplay.util.page.PageObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

// Goods(상품관리게시판) 의 메뉴를 선택하고, 데이터 수집(기능별), 예외처리
public class GoodsController {

	public String execute(HttpServletRequest request) {
		System.out.println("GoodsController.execute() ----------------");

	
			// 메뉴입력(uri)
			String uri = request.getRequestURI();
			
			// 데이터 수집을 위한 객체선언
			// 초기값 null 을 주어서 데이터를 받았는지 체크하고 처리한다.
			Object result = null;
			
			Long no = 0L;
			
			// 이동할 jsp 주소를 담아놀 변수
			String jsp = null;
			
			// 로그인 아이디 꺼내기
			HttpSession session = request.getSession();
			LoginVO loginVO = (LoginVO) session.getAttribute("login"); 
			
			String id = null;
			if (loginVO != null) id = loginVO.getId();
			
			// 파일업로드 설정
			String savePath = "/upload/goods";
			String realSavePath
				= request.getServletContext().getRealPath(savePath);
			// 용량제한
			int sizeLimit = 100 * 1024 * 1024;//100MB
			
			try {
				// 메뉴처리 CRUD
				switch (uri) {
				case "/goods/list.do":
					System.out.println("1. 상품관리 리스트======");
					
					// 세팅을 위한 데이터
					PageObject pageObject = PageObject.getInstance(request);
					
					// DB처리 (Service를 통해서 DAO실행후 결과를 넘겨받는다.)
					// GoodsController->(Execute)->GoodsListService
					// ->GoodsDAO.list()
					result = Execute.execute(Init.get(uri), pageObject);
					
					// jsp에서 보여주기위해 결과값을 담는다.
					request.setAttribute("list", result);

					jsp = "goods/list";
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
