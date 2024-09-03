package com.jjangplay.util.io;

import java.util.List;

import com.jjangplay.board.vo.BoardVO;
import com.jjangplay.notice.vo.NoticeVO;

// 결과값을 화면에 출력하는 클래스
public class NoticePrint {

	// 리스트 출력하는 메서드
	public void print(List<NoticeVO> list) {
		System.out.println("<<<--- 공지사항 리스트 --->>>");
		System.out.println("---------------------------");
		System.out.println("글번호 / 제목 / 게시시작일 / 게시종료일");
		System.out.println("---------------------------");
		
		if (list == null) {
			System.out.println("데이터가 없습니다.");
		}
		else {
			//list의 데이터가 순서대로 vo 에 한번씩 담깁니다.
			for (NoticeVO vo : list) {
				System.out.print(" " + vo.getNo());
				System.out.print(" / " + vo.getTitle());
				System.out.print(" / " + vo.getStartDate());
				System.out.print(" / " + vo.getEndDate());
				System.out.println();
			}
		}
	} // end of print(list)
	
	// 게시판 글보기 출력 메서드
	public void print(NoticeVO vo) {
		System.out.println();
		System.out.println("<<<--- 공지사항 글보기 --->>>");
		System.out.println("---------------------------");
		System.out.println("글번호 : " + vo.getNo());
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("내용 : " + vo.getContent());
		System.out.println("게시시작일 : " + vo.getStartDate());
		System.out.println("게시종료일 : " + vo.getEndDate());
		System.out.println("작성일 : " + vo.getWriteDate());
		System.out.println("수정일 : " + vo.getUpdateDate());
		System.out.println("---------------------------");
		System.out.println();
	}
}
