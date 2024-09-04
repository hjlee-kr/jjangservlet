package com.jjangplay.util.auth;

import java.util.HashMap;

import com.jjangplay.main.controller.Main;
import com.jjangplay.main.service.Service;

public class Authority {

	// Service에 따른 권한 설정 - Map
	// HashMap <key, value> key 값은 중복되면 안된다.
	// key -> service 이름
	// value -> 권한
	private static HashMap<String, Integer> authMap =
			new HashMap<String, Integer>();
	
	static {
		// 회원관리
		// 관리자 : 리스트, 회원등급변경, 회원상태변경
		// 회원(로그인) : 내정보보기, 내정보수정, 회원탈퇴
	//	authMap.put("MemberListService", 9);
	//	authMap.put("MemberViewService", 1);
	}
	
	public static void checkAuth(Service service) throws Exception {
		Integer level = authMap.get(service.getClass().getSimpleName());
		
	/*	if (level != null) {
			// 로그인 안했을 때
			if (Main.login == null)
				throw new Exception("예외발생 : 로그인을 하셔야 합니다.");
			
			// 로그인 권한이 낮은 경우 처리
			if (level > Main.login.getGradeNo())
				throw new Exception("예외발생 : 처리할 권한이 없습니다.");
		}*/
	}
	
	
	
	
	
	
	
	
	
	
}
