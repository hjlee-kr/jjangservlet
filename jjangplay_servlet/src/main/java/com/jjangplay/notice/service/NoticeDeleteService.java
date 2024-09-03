package com.jjangplay.notice.service;

import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;
import com.jjangplay.notice.dao.NoticeDAO;

public class NoticeDeleteService implements Service {

	private NoticeDAO dao;
	
	public void setDAO(DAO dao) {
		this.dao = (NoticeDAO) dao;
	}
	
	@Override
	public Integer service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// NoticeDeleteService -> NoticeDAO().delete()
		return new NoticeDAO().delete((Long)obj);
	}

}
