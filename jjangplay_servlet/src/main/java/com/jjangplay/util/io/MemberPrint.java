package com.jjangplay.util.io;

import java.util.List;

import com.jjangplay.board.vo.BoardVO;
import com.jjangplay.main.controller.Main;
import com.jjangplay.member.vo.MemberVO;

// 결과값을 화면에 출력하는 클래스
public class MemberPrint {

	// 리스트 출력하는 메서드
	public void print(List<MemberVO> list) {
		System.out.println("<<<--- 회원 리스트 --->>>");
		System.out.println("---------------------------");
		System.out.println("아이디 / 이름 / 생년월일 / 연락처 / 등급명 / 상태");
		System.out.println("---------------------------");
		
		if (list == null) {
			System.out.println("데이터가 없습니다.");
		}
		else {
			//list의 데이터가 순서대로 vo 에 한번씩 담깁니다.
			for (MemberVO vo : list) {
				System.out.print(" " + vo.getId());
				System.out.print(" / " + vo.getName());
				System.out.print(" / " + vo.getBirth());
				System.out.print(" / " + vo.getTel());
				System.out.print(" / " + vo.getGradeName());
				System.out.print(" / " + vo.getStatus());
				System.out.println();
			}
		}
	} // end of print(list)
	
	// 회원정보보기 출력 메서드
	public void print(MemberVO vo) {
		System.out.println();
		System.out.println("<<<---" 
				+ ((Main.login.getGradeNo()==9)?"회원":"내")
				+ " 정보보기 --->>>") ;
		System.out.println("---------------------------");
		System.out.println("아이디 : " + vo.getId());
		System.out.println("이름 : " + vo.getName());
		System.out.println("성별 : " + vo.getGender());
		System.out.println("생년월일 : " + vo.getBirth());
		System.out.println("연락처 : " + vo.getTel());
		System.out.println("이메일 : " + vo.getEmail());
		System.out.println("가입일 : " + vo.getRegDate());
		System.out.println("접속일 : " + vo.getConDate());
		System.out.println("사진 : " + vo.getPhoto());
		System.out.println("등급이름 : " + vo.getGradeName());
		if (Main.login.getGradeNo()==9) {
			System.out.println("등급번호 : " + vo.getGradeNo());
			System.out.println("상태 : " + vo.getStatus());
		}
		System.out.println("---------------------------");
		System.out.println();
	}
	
	
	
}
