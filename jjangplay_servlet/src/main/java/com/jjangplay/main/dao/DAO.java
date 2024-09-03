package com.jjangplay.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAO {

	// 연결 객체
	public Connection con = null;
	// 실행 객체
	public PreparedStatement pstmt = null;
	// 데이터 저장 객체
	public ResultSet rs = null;
}
