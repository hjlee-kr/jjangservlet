package com.jjangplay.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DB {

	// DB 접속 관련된 상수 -> DB클래스에서만 사용 -> con 을 만들어서 전달
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String ID = "java";
	private static final String PW = "java";
	
	// static 메서드 : DB클래스가 실행 또는 로딩 될 때 실행된다.
	static {
		try {
			// 1. 딱 한번만 실행하면 되죠?
			Class.forName(DRIVER);
			System.out.println("1. 드라이버 확인 완료");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("<<드라이버가 없으므로 프로그램을 종료합니다.>>");
			System.exit(1);
		}
	}
	
	// DB.getConnection();
	// DB연결을 위한 메서드
	public static final Connection getConnection() throws Exception {
		return DriverManager.getConnection(URL, ID, PW);
	}
	
	// 사용한 객체 닫기
	public static final void close (Connection con, PreparedStatement pstmt)
			throws SQLException {
		if (con != null) con.close();
		if (pstmt != null) pstmt.close();
	}
	
	// ReseltSet을 사용했을때 객체 닫기
	public static final void close (Connection con, PreparedStatement pstmt,
			ResultSet rs) throws SQLException {
		close(con, pstmt);
		if (rs != null) rs.close();
	}
}
