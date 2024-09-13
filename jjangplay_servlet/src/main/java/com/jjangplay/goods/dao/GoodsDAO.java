package com.jjangplay.goods.dao;

import java.util.ArrayList;
import java.util.List;

import com.jjangplay.goods.vo.GoodsVO;
import com.jjangplay.main.dao.DAO;
import com.jjangplay.util.db.DB;
import com.jjangplay.util.page.PageObject;

public class GoodsDAO extends DAO {

	// 1.상품관리 리스트
	public List<GoodsVO> list(PageObject pageObject) throws Exception {
		// 결과값을 받을 변수 선언
		List<GoodsVO> list = null;
		
		try {
			// 1. 드라이버 확인 - DispatcherServlet.init()
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL작성 (상수: LIST) -> 클래스 하단에
			// 4. 실행객체에 데이터세팅
			pstmt = con.prepareStatement(LIST);
			// 5. SQL 실행
			rs = pstmt.executeQuery();
			// 6. 결과확인 (받은 데이터를 list에 저장)
			if (rs != null) {
				while(rs.next()) {
					if (list == null) list = new ArrayList<GoodsVO>();
					
					GoodsVO vo = new GoodsVO();
					vo.setGno(rs.getLong("gno"));
					vo.setName(rs.getString("name"));
					vo.setModelNo(rs.getString("modelNo"));
					vo.setStd_price(rs.getLong("std_price"));
					vo.setStartDate(rs.getString("startDate"));
					vo.setEndDate(rs.getString("endDate"));
					
					list.add(vo);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB 닫기
			DB.close(con, pstmt, rs);
		}
		return list;
	}

	
	
	
	// SQL 쿼리
	final static String LIST = ""
		+ "select g.gno, g.name, g.modelNo,"
		+ " p.std_price,"
		+ " to_char(p.startDate, 'yyyy-mm-dd') startDate,"
		+ " to_char(p.endDate, 'yyyy-mm-dd') endDate "
		+ " from goods g, price p "
		+ " where g.gno = p.gno "
		+ " order by gno desc";

	
} // end of class








