package com.jjangplay.goods.service;

import com.jjangplay.image.dao.ImageDAO;
import com.jjangplay.image.vo.ImageVO;
import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;

public class ImageUpdateService implements Service {

	private ImageDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		// Init의 daoMap.get("imageDAO") 의 값(주소값)이 dao에 세팅된다.
		this.dao = (ImageDAO) dao;
	}
	
	@Override
	public Integer service(Object obj) throws Exception {
		// [ImageController] -> (Execute)
		// -> ImageUpdateService -> [ImageDAO.update()]
		return dao.update((ImageVO)obj);
	}

}
