package com.jjangplay.util.io;

import java.util.List;

import com.jjangplay.board.vo.BoardVO;

// 결과값을 화면에 출력하는 클래스
public class BoardPrint {

	// 리스트 출력하는 메서드
	public void print(List<BoardVO> list) {
		System.out.println("<<<--- 일반게시판 리스트 --->>>");
		System.out.println("---------------------------");
		System.out.println("글번호 / 제목 / 작성자 / 작성일 / 조회수");
		System.out.println("---------------------------");
		
		if (list == null) {
			System.out.println("데이터가 없습니다.");
		}
		else {
			//list의 데이터가 순서대로 vo 에 한번씩 담깁니다.
			for (BoardVO vo : list) {
				System.out.print(" " + vo.getNo());
				System.out.print(" / " + vo.getTitle());
				System.out.print(" / " + vo.getWriter());
				System.out.print(" / " + vo.getWriteDate());
				System.out.print(" / " + vo.getHit());
				System.out.println();
			}
		}
	} // end of print(list)
	
	// 게시판 글보기 출력 메서드
	public void print(BoardVO vo) {
		System.out.println();
		System.out.println("<<<--- 일반게시판 글보기 --->>>");
		System.out.println("---------------------------");
		System.out.println("글번호 : " + vo.getNo());
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("내용 : " + vo.getContent());
		System.out.println("작성자 : " + vo.getWriter());
		System.out.println("작성일 : " + vo.getWriteDate());
		System.out.println("조회수 : " + vo.getHit());
		System.out.println("---------------------------");
		System.out.println();
	}
}
