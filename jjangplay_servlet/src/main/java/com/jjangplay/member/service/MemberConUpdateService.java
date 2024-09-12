package com.jjangplay.member.service;

import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;
import com.jjangplay.member.dao.MemberDAO;

public class MemberConUpdateService implements Service {

	private MemberDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MemberDAO) dao;
	}

	@Override
	public Integer service(Object obj) throws Exception {
		// (페이지가 빠뀔때마다 로그인 상태이면 )ConUpdateFilter -> Execute
		// -> 여기로 옴
		// -> 여기에서 -> MemberDAO().conUpdate(id)
		return dao.conUpdate((String)obj);
	}

}
