package com.jjangplay.member.service;

import java.util.List;

import com.jjangplay.main.dao.DAO;
import com.jjangplay.main.service.Service;
import com.jjangplay.member.dao.MemberDAO;
import com.jjangplay.member.vo.MemberVO;
import com.jjangplay.util.page.PageObject;

public class MemberListService implements Service {

	private MemberDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MemberDAO) dao;
	}

	@Override
	public List<MemberVO> service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		// MemberController->execute->여기(MemberListService)까지 왔어요
		// 여기서 -> MemberDAO().list() 실행합니다.
		return dao.list((PageObject) obj);
	}

}
