package com.jjangplay.boardreply.service;

import java.util.List;

import com.jjangplay.board.dao.BoardDAO;
import com.jjangplay.board.vo.BoardVO;
import com.jjangplay.boardreply.dao.BoardReplyDAO;
import com.jjangplay.boardreply.vo.BoardReplyVO;
import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;
import com.jjangplay.util.page.PageObject;
import com.jjangplay.util.page.ReplyPageObject;

public class BoardReplyListService implements Service {
	
	private BoardReplyDAO dao;
	
	// dao setter
	public void setDAO(DAO dao) {
		// Init의 daoMap.get("boardDAO") 의 값(주소값)이 dao에 세팅된다.
		this.dao = (BoardReplyDAO) dao;
	}

	@Override
	public List<BoardReplyVO> service(Object obj) throws Exception {
		// DB Board Table 을 가지고 리스트 쿼리 처리해서 데이터 가져오기(Return)
		// DB 처리는 DAO 에서 처리 - BoardDAO.list()
		// BoardController -> execute() -> [BoardListSevice] - BoardDAO.list()
		System.out.println("--- BoardListService 실행 ---");
		ReplyPageObject pageObject = (ReplyPageObject) obj;
		
		// 전체 페이지 갯수에 대한 정보를 가져온다. DB에서
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		
		
		return dao.list(pageObject);
	}

}
