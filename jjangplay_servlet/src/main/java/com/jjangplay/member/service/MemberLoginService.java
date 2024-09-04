package com.jjangplay.member.service;

import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;
import com.jjangplay.member.dao.MemberDAO;
import com.jjangplay.member.vo.LoginVO;

public class MemberLoginService implements Service {
	
	private MemberDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MemberDAO) dao;
	}

	@Override
	public LoginVO service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// MemberController->Execute->여기에 왔습니다.
		// 여기에서 -> MemberDAO.login() 로 넘어 갈 예정입니다.
		return dao.login((LoginVO) obj);
	}


}
