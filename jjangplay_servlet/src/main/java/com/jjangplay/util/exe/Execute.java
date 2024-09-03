package com.jjangplay.util.exe;

import java.util.Arrays;

import com.jjangplay.board.service.BoardListService;
import com.jjangplay.main.service.Service;
import com.jjangplay.util.auth.Authority;

public class Execute {

	// 서비스를 실행시키고 로그를 출력하는 메서드
	public static Object execute(Service service, Object obj) throws Exception {
		// 권한처리
		Authority.checkAuth(service);
		
		// 처리된 결과를 저장하는 변수 -> 리턴
		Object result = null;
		
		System.out.println("<<<-------------- 실행 로그 출력 ----------------->>>");
		System.out.println("--------------------------------------------------");
		
		// 시작 시간 저장
		Long start = System.nanoTime();
		
		// 실행 객체 정보를 출력
		// service interface 객체를 클래스 객체로 바꿔줍니다. -> service.getClass()
		// 클래스의 이름을 패키지와 함께 가져옵니다. -> getName()
		System.out.println("실행 객체 이름 : " + service.getClass().getName());
		
		// 넘어 오는 데이터 정보 출력
		// 서비스로 넘어가는 데이터
		// 배열인지 물어봅니다. : "obj instanceof Object[]" ; obj 변수가 Object[] 배열이면 true
		// 배열이면 Arrays.toString() 사용하고 아니면 obj를 쓴다"obj.toString()"
		// 배열인지 물어보는 이유는 배열이면 배열안의 데이터 전체를 보여주기 위해서 입니다.
		// Arrays.toString() -> 배열안의 데이터 전체를 보여줍니다.
		System.out.println("서비스로 넘어가는 데이터 : " + 
				((obj instanceof Object[])?Arrays.toString((Object[])obj):obj));
		
		// service 실행
		// Execute.execute(new BoardListService(), null);
		// service 의 참조주소는 BoardListService 클래스 형식의 참조주소
		result = service.service(obj); //->BoardListService.service()
		// BoardListService.service()에서 넘어온 리턴형식은 List<BoardVO)>
		// BoardViewService.service() -> 리턴 BoardVO
		// BoardWriteService.service() -> 리턴되는 자료는 없고, 실행되었는지만 확인됨

		
		// 결과 데이터 출력
		System.out.println("결과 데이터 : " + result);
		
		// 실행 시간
		Long end = System.nanoTime();
		System.out.println("실행 시간 : " + (end - start));
		System.out.println("--------------------------------------------------");
		
		// 결과값을 BoardController 에 리턴
		return result;
	} // end of execute()
} // end of class
