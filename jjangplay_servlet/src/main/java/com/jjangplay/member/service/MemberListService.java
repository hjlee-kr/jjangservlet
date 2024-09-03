package com.jjangplay.member.service;

import java.util.List;

import com.jjangplay.main.service.Service;
import com.jjangplay.member.dao.MemberDAO;
import com.jjangplay.member.vo.MemberVO;

public class MemberListService implements Service {

	@Override
	public List<MemberVO> service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// MemberController->execute->여기(MemberListService)까지 왔어요
		// 여기서 -> MemberDAO().list() 실행합니다.
		return new MemberDAO().list();
	}

}
