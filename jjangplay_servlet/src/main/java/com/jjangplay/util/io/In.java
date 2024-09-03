package com.jjangplay.util.io;

import java.util.Scanner;

public class In {
	// 키보드로 입력 받는 객체를 선언하고 초기화
	private static Scanner scanner = new Scanner(System.in);
	
	// 문자열 받는 메서드 작성
	public static String gerStr() {
		return scanner.nextLine();
	}
	
	// 문자열(어떤값을 받는지 설명)을 입력받아서
	// 키보드로 문자열 입력받아 리턴하는 메서드
	public static String getStr (String str) {
		System.out.print(str + " : ");
		return gerStr();
	}
	
	// 리턴 Type 을 Long 으로 하는 받는 메서드
	public static Long getLong (String str) {
		while (true) {
			try {
				return Long.parseLong(getStr(str));
			} catch (Exception e) {
				System.out.println("&%$ 숫자만 입력하셔야 합니다. 다시입력해 주세요");
			} // end of try~catch
		}
	}
	
	// 리턴 Type 을 Integer 로 하는 받는 메서드
	public static Integer getInt (String str) {
		while (true) {
			try {
				return Integer.parseInt(getStr(str));
			} catch (Exception e) {
				System.out.println("&%$ 숫자만 입력하셔야 합니다. 다시입력해 주세요");
			} // end of try~catch
		}
	}
}
