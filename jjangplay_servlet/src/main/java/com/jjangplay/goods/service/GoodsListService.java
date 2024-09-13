package com.jjangplay.goods.service;

import java.util.List;

import com.jjangplay.goods.dao.GoodsDAO;
import com.jjangplay.goods.vo.GoodsVO;
import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;
import com.jjangplay.util.page.PageObject;

public class GoodsListService implements Service {
	
	private GoodsDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		// Init의 daoMap.get("goodsDAO") 의 값(주소값)이 dao에 세팅된다.
		this.dao = (GoodsDAO) dao;
	}

	@Override
	public List<GoodsVO> service(Object obj) throws Exception {
		// DB goods, price Table 을 가지고 리스트 쿼리 처리해서 데이터 가져오기(Return)
		// DB 처리는 DAO 에서 처리 - GoodsDAO.list()
		// GoodsController -> execute() -> 여기[GoodsListSevice]
		// 여기[GoodsListSevice] - GoodsDAO.list()
	//	System.out.println("--- GoodsListService 실행 ---");
		PageObject pageObject = (PageObject) obj;
		
		// 전체 페이지 갯수에 대한 정보를 가져온다. DB에서
	//	pageObject.setTotalRow(dao.getTotalRow(pageObject));
		
		
		return dao.list(pageObject);
	}

}
