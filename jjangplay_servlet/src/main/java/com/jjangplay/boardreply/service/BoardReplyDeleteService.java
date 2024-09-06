package com.jjangplay.boardreply.service;

import com.jjangplay.board.dao.BoardDAO;
import com.jjangplay.board.vo.BoardVO;
import com.jjangplay.boardreply.dao.BoardReplyDAO;
import com.jjangplay.boardreply.vo.BoardReplyVO;
import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;

public class BoardReplyDeleteService implements Service {

	private BoardReplyDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		// Init의 daoMap.get("boardReplyDAO") 의 값(주소값)이 dao에 세팅된다.
		this.dao = (BoardReplyDAO) dao;
	}
	
	@Override
	public Integer service(Object obj) throws Exception {
		// [BoardReplyController] -> (Execute)
		// -> BoardReplyDeleteService -> [BoardReplyDAO.delete()]
		return dao.delete((BoardReplyVO)obj);
	}

}
