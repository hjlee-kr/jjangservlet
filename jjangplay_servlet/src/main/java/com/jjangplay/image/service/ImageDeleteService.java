package com.jjangplay.image.service;

import com.jjangplay.board.dao.BoardDAO;
import com.jjangplay.board.vo.BoardVO;
import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;

public class ImageDeleteService implements Service {

	private BoardDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		// Init의 daoMap.get("boardDAO") 의 값(주소값)이 dao에 세팅된다.
		this.dao = (BoardDAO) dao;
	}
	
	@Override
	public Integer service(Object obj) throws Exception {
		// [BoardController] -> (Execute) -> BoardDeleteService -> [BoardDAO.delete()]
		return new BoardDAO().delete((BoardVO)obj);
	}

}
