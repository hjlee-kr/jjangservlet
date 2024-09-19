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
					
					GoodsVO vo = new GoodsVO();
					
					// DB 처리(상품관리) -> GoodsViewService
					result = Execute.execute(Init.get(uri), gno);
					
					System.out.println("result" + result);
					// 상품관리에서 받은 데이터를 vo에 담는다.
					vo = (GoodsVO) result;
					
					// DB처리(가격관리) -> GoodsViewPriceService
					result = Execute.execute(Init.get("/goods/viewPrice.do"), gno);
					
					// 받아온 가격정보를 상품관리정보가 담겨있는 vo 같이 담는다.
					if (result != null) {
						GoodsVO priceVO = new GoodsVO();
						priceVO = (GoodsVO) result;
						// null이면 가격정보가 등록이 안되어있는 경우이고
						// not null 이면 등록이 되어있는 경우이다.
						vo.setPno(priceVO.getPno());
						vo.setStd_price(priceVO.getStd_price());
						vo.setDiscount(priceVO.getDiscount());
						vo.setRate(priceVO.getRate());
						vo.setStartDate(priceVO.getStartDate());
						vo.setEndDate(priceVO.getEndDate());
					}
					
					// 받은 결과를 request에 담아 jsp로 넘긴다.
					request.setAttribute("vo", vo);
					
					jsp = "goods/view";
					break;
				case "/goods/writeForm.do":
					System.out.println("3-1. 상품관리 등록 폼====");
					jsp = "goods/writeForm";
					break;
				case "/goods/write.do":
					System.out.println("3-2. 상품관리 등록 처리====");
					
					MultipartRequest multi =
						new MultipartRequest(request, realSavePath,
							sizeLimit, "utf-8", new DefaultFileRenamePolicy());
					
					// writeForm에 기록된 데이터를 세팅한다. (DB처리전)
					vo = new GoodsVO();
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
					// GoodsDAO.write(), GoodsDAO.writePrice()
					Execute.execute(Init.get(uri), vo);
					
					// 처리가 끝나면 리스트로 돌아간다.
					jsp = "redirect:/goods/list.do?perPageNum=" +
						multi.getParameter("perPageNum");
					break;
				case "/goods/writePriceForm.do":
					System.out.println("3-3. 상품가격정보 등록 폼=====");
					// 상품가격정보를 위해 반드시 필요한것은?
					// 상품번호가 필요합니다.
					gno = Long.parseLong(request.getParameter("gno"));
					String name = request.getParameter("name");
					
					vo = new GoodsVO();
					vo.setGno(gno);
					vo.setName(name);
					
					request.setAttribute("vo", vo);
					
					jsp = "goods/writePriceForm";
					break;
				case "/goods/writePrice.do":
					System.out.println("3-4. 상품가격정보 등록 처리=====");
					// 등록하기 위한 자료 저장 (폼에서 받은 데이터)
					gno = Long.parseLong(request.getParameter("gno"));
					Long std_price = Long.parseLong(request.getParameter("std_price"));
					Long discount;
					// writePriceForm.do에서 discount 항목을 적지 않았을때 예외상황 피해가기
					String strNum = request.getParameter("discount");
					if (strNum == null || strNum.equals("")) {
						discount = std_price;
					}
					else {
						discount = Long.parseLong(strNum);
					}
					Double rate;
					// writePriceForm.do에서 rate 항목을 적지 않았을때 예외상황 피해가기
					strNum = request.getParameter("rate");
					if (strNum == null || strNum.equals("")) {
						rate = 0.0;
					}
					else {
						rate = Double.parseDouble(strNum);
					}
					String startDate = request.getParameter("startDate");
					String endDate = request.getParameter("endDate");
					
					vo = new GoodsVO();
					vo.setGno(gno);
					vo.setStd_price(std_price);
					vo.setDiscount(discount);
					vo.setRate(rate);
					vo.setStartDate(startDate);
					vo.setEndDate(endDate);
					
					// GoodsWritePriceService()->GoodsDAO.writePrice()
					// DB 처리를 한다.
					Execute.execute(Init.get(uri), vo);

					// 메세지 출력
					session.setAttribute("msg", "상품가격이 등록되었습니다.");
					
					jsp = "redirect:view.do?gno=" + vo.getGno();
					break;
					
				case "/goods/updatePriceForm.do":
					System.out.println("4-3. 상품가격정보 수정 폼====");
					// 기존정보를 받아오기 위해 gno를 세팅
					gno = Long.parseLong(request.getParameter("gno"));
					name = request.getParameter("name");
					// DB에서 기존 가격 정보를 받아온다.
					result = Execute.execute(Init.get("/goods/viewPrice.do"), gno);
					// jsp로 넘길 데이터 세팅(request)
					request.setAttribute("vo", result);
					
					jsp = "goods/updatePriceForm";
					break;
					
				case "/goods/updatePrice.do":
					System.out.println("4-4. 상품가격정보 수정 처리====");
					// 수정하기 위한 자료 저장 (폼에서 받은 데이터)
					gno = Long.parseLong(request.getParameter("gno"));
					std_price = Long.parseLong(request.getParameter("std_price"));

					// updatePriceForm.do에서 discount 항목을 적지 않았을때 예외상황 피해가기
					strNum = request.getParameter("discount");
					if (strNum == null || strNum.equals("")) {
						discount = std_price;
					}
					else {
						discount = Long.parseLong(strNum);
					}
					// updatePriceForm.do에서 rate 항목을 적지 않았을때 예외상황 피해가기
					strNum = request.getParameter("rate");
					if (strNum == null || strNum.equals("")) {
						rate = 0.0;
					}
					else {
						rate = Double.parseDouble(strNum);
					}
					startDate = request.getParameter("startDate");
					endDate = request.getParameter("endDate");
					
					vo = new GoodsVO();
					vo.setGno(gno);
					vo.setStd_price(std_price);
					vo.setDiscount(discount);
					vo.setRate(rate);
					vo.setStartDate(startDate);
					vo.setEndDate(endDate);
					
					// GoodsUpdatePriceService()->GoodsDAO.updatePrice()
					// DB 처리를 한다.
					Execute.execute(Init.get(uri), vo);

					// 메세지 출력
					session.setAttribute("msg", "상품가격이 수정되었습니다.");
					
					jsp = "redirect:view.do?gno=" + vo.getGno();
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
