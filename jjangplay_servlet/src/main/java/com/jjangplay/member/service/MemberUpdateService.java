package com.jjangplay.member.service;

import com.jjangplay.main.service.Service;
import com.jjangplay.member.dao.MemberDAO;
import com.jjangplay.member.vo.MemberVO;

public class MemberUpdateService implements Service {

	@Override
	public Integer service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// MemberController -> Execute -> 여기 (MemberUpdateService)
		// 여기 -> MemberDAO().update(MemberVO)
		return new MemberDAO().update((MemberVO)obj);
	}

}
