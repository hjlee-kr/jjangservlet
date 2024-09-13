package com.jjangplay.goods.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.jjangplay.goods.vo.GoodsVO;
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
			
			System.out.println("realSavePath = " + realSavePath);
			
			File realSavePathFile = new File(realSavePath);
			// 폴더가 존재하지 않으면 만들어 준다.
			if (!realSavePathFile.exists()) {
				realSavePathFile.mkdir();
			}
			
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
				case "/goods/view.do":
					System.out.println("2. 상품 상세 페이지=====");
					
					Long gno = Long.parseLong(request.getParameter("gno"));
					
					// DB 처리
					result = Execute.execute(Init.get(uri), gno);
					
					System.out.println("result" + result);
					
					// 받은 결과를 request에 담아 jsp로 넘긴다.
					request.setAttribute("vo", result);
					
					jsp = "goods/view";
					break;
				case "/goods/writeForm.do":
					System.out.println("3. 상품관리 등록 폼====");
					jsp = "goods/writeForm";
					break;
				case "/goods/write.do":
					System.out.println("3. 상품관리 등록 처리====");
					
					MultipartRequest multi =
						new MultipartRequest(request, realSavePath,
							sizeLimit, "utf-8", new DefaultFileRenamePolicy());
					
					// writeForm에 기록된 데이터를 세팅한다. (DB처리전)
					GoodsVO vo = new GoodsVO();
				//	vo.setGno(Long.parseLong(multi.getParameter("gno")));
					vo.setName(multi.getParameter("name"));
					vo.setContent(multi.getParameter("content"));
					vo.setProductDate(multi.getParameter("productDate"));
					vo.setModelNo(multi.getParameter("modelNo"));
					vo.setCompany(multi.getParameter("company"));
					vo.setDelivery_cost(Long.parseLong(multi.getParameter("delivery_cost")));
//					vo.setStd_price(Long.parseLong(request.getParameter("std_price")));
//					vo.setDiscount(Long.parseLong(request.getParameter("discount")));
//					vo.setRate(Double.parseDouble(request.getParameter("rate")));
//					vo.setStartDate(request.getParameter("startDate"));
//					vo.setEndDate(request.getParameter("endDate"));
					// 이미지는 경로와 파일이름을 같이 DB에 등록한다. 
					vo.setImageName(savePath + "/" + multi.getFilesystemName("imageName"));
					
					// DB처리
					// GoodsController -> Execute -> GoodsWriteService
					// 상품데이블 등록 실행후 가격데이블 등록 실행
					// GoodsDAO.goodsWrite(), GoodsDAO.priceWrite()
					Execute.execute(Init.get(uri), vo);
					
					// 처리가 끝나면 리스트로 돌아간다.
					jsp = "redirect:/goods/list.do?perPageNum=" +
						multi.getParameter("perPageNum");
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
