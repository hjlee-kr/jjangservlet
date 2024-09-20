package com.jjangplay.cart.service;

import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;
import com.jjangplay.shop.dao.ShopDAO;
import com.jjangplay.util.page.PageObject;

public class ShopListService implements Service {

	private ShopDAO dao;
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (ShopDAO) dao;
	}
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		PageObject pageObject = (PageObject)obj;
		
		// 전체 페이지 갯수 정보를 가져온다.
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		
		return dao.list(pageObject);
	}


}
