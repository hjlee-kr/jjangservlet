package com.jjangplay.member.service;

import com.jjangplay.main.service.Service;
import com.jjangplay.member.dao.MemberDAO;
import com.jjangplay.member.vo.MemberVO;

public class MemberWriteService implements Service{

	@Override
	public Integer service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// MemberController->Execute->여기까지왔습니다.
		// 여기에서 -> MemberDAO().write()
		return new MemberDAO().write((MemberVO)obj);
	}

}
