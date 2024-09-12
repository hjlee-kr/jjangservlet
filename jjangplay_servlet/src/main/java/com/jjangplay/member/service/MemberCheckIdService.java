package com.jjangplay.member.service;


import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;
import com.jjangplay.member.dao.MemberDAO;

public class MemberCheckIdService implements Service {

	private MemberDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MemberDAO) dao;
	}

	@Override
	public String service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// MemberController->execute->여기(MemberCheckIdService)까지 왔어요
		// 여기서 -> MemberDAO().checkId() 실행합니다.
		return dao.checkId((String) obj);
	}

}
