package com.jjangplay.goods.service;

import com.jjangplay.goods.dao.GoodsDAO;
import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;

public class GoodsDeletePriceService implements Service {

	private GoodsDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		// Init의 daoMap.get("goodsDAO") 의 값(주소값)이 dao에 세팅된다.
		this.dao = (GoodsDAO) dao;
	}
	
	@Override
	public Object service(Object obj) throws Exception {
		// GoodsController -> execute() ->
		// [GoodsDeletePriceSevice] - GoodsDAO.deletePrice()
		return dao.deletePrice((Long)obj);

	}

}
