package com.jjangplay.image.service;

import java.util.List;

import com.jjangplay.board.dao.BoardDAO;
import com.jjangplay.board.vo.BoardVO;
import com.jjangplay.image.dao.ImageDAO;
import com.jjangplay.image.vo.ImageVO;
import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;
import com.jjangplay.util.page.PageObject;

public class ImageListService implements Service {
	
	private ImageDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		// Init의 daoMap.get("imageDAO") 의 값(주소값)이 dao에 세팅된다.
		this.dao = (ImageDAO) dao;
	}

	@Override
	public List<ImageVO> service(Object obj) throws Exception {
		// DB Image Table 을 가지고 리스트 쿼리 처리해서 데이터 가져오기(Return)
		// DB 처리는 DAO 에서 처리 - ImageDAO.list()
		// ImageController -> execute() -> [ImageListSevice] - ImageDAO.list()
		System.out.println("--- ImageListService 실행 ---");
		PageObject pageObject = (PageObject) obj;
		
		// 전체 페이지 갯수에 대한 정보를 가져온다. DB에서
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		
		
		return dao.list(pageObject);
	}

}
