package com.jjangplay.notice.service;

import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;
import com.jjangplay.notice.dao.NoticeDAO;

public class NoticeViewService implements Service {

	private NoticeDAO dao;
	
	public void setDAO(DAO dao) {
		this.dao = (NoticeDAO) dao;
	}
	
	@Override
	public Object service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// NoticeDAO().view()
		// view() 호출할 때 글번호를 넘겨준다.
		return new NoticeDAO().view((Long)obj);
		
	}

}
