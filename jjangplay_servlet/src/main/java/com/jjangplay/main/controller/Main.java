package com.jjangplay.main.controller;

import com.jjangplay.board.controller.BoardController;
import com.jjangplay.member.controller.MemberController;
import com.jjangplay.member.vo.LoginVO;
import com.jjangplay.notice.controller.NoticeController;
import com.jjangplay.util.io.In;

// jjangplay project 의 시작이 되는 클래스
public class Main {
	
	// login 변수안에 데이터가 있으면(null이 아니면) 로그인 상태
	// login 변수안에 데이터가 없으면(null이면) 로그인 안한 상태
	public static LoginVO login = null;
	
	// login 상태에 대한 정보를 알려주는 메서드
	public static void loginInfo() {
		System.out.println();
		System.out.println("<<< --- 로그인 정보 --- >>>");
		System.out.println("----------------------------------");
		if (login == null) {
			// 로그인 안한 상태
			System.out.println("// 로그인이 되어있지 않습니다.");
			System.out.println("// 로그인을 하려면 5.회원관리 메뉴에서");
			System.out.println("// 6.로그인 메뉴를 선택하세요");
		}
		else {
			System.out.println(login.getName() + "(" 
					+ login.getId() + ")님은 " + login.getGradeName() 
					+ "(으)로 로그인 되었습니다.");
			System.out.println(login.getNewMsgCnt() + " 개의 "
					+ " 새로운 메시지가 있습니다.");
		}
		System.out.println("----------------------------------");
	}

	// 프로그램이 시작되는 지점
	public static void main(String[] args) throws ClassNotFoundException {
		
		System.out.println("===============================");
		System.out.println("== 짱 놀이터에 오신것을 환영합니다. ==");
		System.out.println("===============================");
		
		// 드라이버 확인
		Class.forName("com.jjangplay.util.db.DB");
		
		while (true) {
			Main.loginInfo();
			System.out.println();
			System.out.println("<<--- 메인메뉴 --->>");
			System.out.println("***********************************");
			System.out.println("** 1.공지사항, 2.쇼핑몰, 3. 일반게시판 **");
			System.out.println("** 4.사진게시판, 5.회원관리, 0. 종료  **");
			System.out.println("***********************************");
			
			String menu = In.getStr("메뉴");
			
			try {
				switch (menu) {
				case "1":
					System.out.println("1. 공지사항");
					// NoticeController.execute()
					// 생성하고 실행한다.
					new NoticeController().execute();
					break;
				case "2":
					System.out.println("2. 쇼핑몰");
					break;
				case "3":
					System.out.println("3. 일반게시판");
					// BoardController 클래스로 가기 위해서는
					// BoardController 를 생성해야 한다.
					// 생성후 BoardController의 execute()메서드를 실행합니다.
					// BoardController boardController = new BoardController();
					// boardController.execute();
					// 생성하고 메서드실행하도록 -1번만 실행
					// new BoardController() - 생성자 실행되서 참조주소가 나오고
					// 그주소에서 execute()호출해서 실행됩니다.
					new BoardController().execute();
					// BoardController.execute() 실행이 끝나면 이곳으로 옵니다.
					break;
				case "4":
					System.out.println("4. 사진게시판");
					break;
				case "5":
					System.out.println("5. 회원관리");
					// 회원관리 모듈로 이동 (MemberController)
					new MemberController().execute();
					break;
				case "0":
					System.out.println("!!!!!!!!!!!!!!!!!!!!");
					System.out.println("프로그램이 종료되었습니다.");
					System.out.println("안녕히 가세요.");
					System.out.println("!!!!!!!!!!!!!!!!!!!!");
					System.exit(0);
					break;
				} // end of switch
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} // end of try~catch
			
		} // end of while(true)
	} // end of main
} // end of class
