package com.jjangplay.member.service;

import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;
import com.jjangplay.member.dao.MemberDAO;
import com.jjangplay.member.vo.MemberVO;

public class MemberViewService implements Service{

	private MemberDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MemberDAO) dao;
	}

	@Override
	public MemberVO service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// MemberController->Execute->여기에 왔습니다.
		// 여기서 -> MemberDAO.view(String id) 로 넘어갑니다.
		return new MemberDAO().view((String)obj);
		
	//	1. new MemberDAO().view((String) obj);
	
	//	2. MemberDAO memverDAO = new MemberDAO();
	//	   memverDAO.view((String) obj);
		
	// 1과 2는 실행결과가 동일하다.	
	}

}
