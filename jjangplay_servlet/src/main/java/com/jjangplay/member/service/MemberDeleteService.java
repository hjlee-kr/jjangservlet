package com.jjangplay.member.service;

import com.jjangplay.main.service.Service;
import com.jjangplay.member.dao.MemberDAO;
import com.jjangplay.member.vo.MemberVO;

public class MemberDeleteService implements Service{

	@Override
	public Integer service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// MemberController:"5"->Execute->여기(MemberDeleteService)
		// 여기에서 -> MemberDAO().delete(MemberVO)
		return new MemberDAO().delete((MemberVO)obj);
	}

}
