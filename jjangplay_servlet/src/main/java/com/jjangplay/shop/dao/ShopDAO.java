package com.jjangplay.shop.dao;

import java.util.ArrayList;
import java.util.List;

import com.jjangplay.main.dao.DAO;
import com.jjangplay.shop.vo.ShopVO;
import com.jjangplay.util.db.DB;
import com.jjangplay.util.page.PageObject;

public class ShopDAO extends DAO {

	// 1-1. 전체 리스트 수 가져오기
	public Long getTotalRow (PageObject pageObject) throws Exception {
		// 결과 담을 변수 선언
		Long totalRow = null;
		System.out.println("ShopDAO.getTotalRow() 시작------------");

		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL작성 (TOTALROW)
			// 4. 실행객체에 데이터 세팅
			pstmt = con.prepareStatement(TOTALROW);
			// 5. 실행 및 데이터 담기
			rs = pstmt.executeQuery();
			
			if (rs != null && rs.next()) {
				totalRow = rs.getLong(1);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("예외발생 : 쇼핑몰 TOTALROW DB 처리중 예외발생");
		} finally {
			// 7.DB닫기
			DB.close(con, pstmt, rs);
		}
		System.out.println("ShopDAO.getTotalRow() 끝------------");
		return totalRow;
	}
	
	
	// 1-2. 리스트
	public List<ShopVO> list (PageObject pageObject) throws Exception {
		// 결과 담을 변수 선언
		List<ShopVO> list = null;
		
		System.out.println("ShopDAO.list() 시작----");
		try {
			// 1. 드라이버확인
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL작성 (LIST)
			System.out.println(getListSQL(pageObject));
			// 4. 실행객체에 데이터 세팅
			pstmt = con.prepareStatement(getListSQL(pageObject));
			int idx = 0;
			pstmt.setLong(++idx, pageObject.getStartRow());
			pstmt.setLong(++idx, pageObject.getEndRow());
			// 5. 실행 및 데이터 받기
			rs = pstmt.executeQuery();
			// 6. 데이터 저장
			if (rs != null) {
				while (rs.next()) {
					// list가 null이면 ArrayList생성
					if (list == null) list = new ArrayList<ShopVO>();
					// rs->ShopVO
					ShopVO vo = new ShopVO();
					vo.setGno(rs.getLong("gno"));
					vo.setName(rs.getString("name"));
					vo.setCompany(rs.getString("company"));
					vo.setImageName(rs.getString("imageName"));
					vo.setStd_price(rs.getLong("std_price"));
					vo.setDiscount(rs.getLong("discount"));
					
					list.add(vo);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("예외발생 : 쇼핑몰 리스트 DB 처리중 예외발생");
		} finally {
			// 7. DB닫기
			DB.close(con, pstmt, rs);
		}
	
		
		System.out.println("ShopDAO.list() 끝----");
		return list;
	}
	
	private String getPeriod(PageObject pageObject) {
		String sql = "";
		
		sql += " and ((1=1) ";
		sql += " and (trunc(sysdate) between trunc(startDate) and trunc(EndDate)) ";
		sql += " ) ";
		return sql;
	}
	
	private String getListSQL(PageObject pageObject) {
		String sql = LIST;
		sql += getPeriod(pageObject);
		sql += " and (g.gno=p.gno) ";
		sql += " order by g.gno desc)) ";
		sql += " where rnum>=? and rnum<=?";
		return sql;
	}
	
	// SQL 문
	final String TOTALROW = ""
			+ " select count(*) from goods g, price p where g.gno = p.gno";
	
	// LIST의 페이지 처리
	final String LIST = ""
			+ " select gno, name, company, imageName, std_price, discount "
			+ " from (select rownum rnum, gno, name, company, imageName, std_price, discount "
			+ " from (select g.gno, g.name, g.company, g.imageName, p.std_price, p.discount "
			+ " from goods g, price p "
			// where 1=1 and (일반조건) and (조인조건)
			+ " where 1=1 ";
	
}
