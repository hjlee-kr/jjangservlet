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
					vo.setCompany(rs.getString("company"));
					
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

	// 2.상품 상세 페이지
	public GoodsVO view(Long gno) throws Exception {
		// 결과값을 받을 변수 선언
		GoodsVO vo = null;
		
		try {
			// 1. 드라이버 확인 - DispatcherServlet.init()
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL작성 (상수: VIEW) -> 클래스 하단에
			// 4. 실행객체에 데이터세팅
			pstmt = con.prepareStatement(VIEW);
			pstmt.setLong(1, gno);
			// 5. SQL 실행
			rs = pstmt.executeQuery();
			// 6. 결과확인 (받은 데이터를 vo에 저장)
			if (rs != null && rs.next()) {
				vo = new GoodsVO();
				vo.setGno(rs.getLong("gno"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setProductDate(rs.getString("productDate"));
				System.out.println("중간점검1");
				vo.setModelNo(rs.getString("modelNo"));
				System.out.println("중간점검2");
				vo.setCompany(rs.getString("company"));
				vo.setImageName(rs.getString("imageName"));
				vo.setDelivery_cost(rs.getLong("delivery_cost"));
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB 닫기
			DB.close(con, pstmt, rs);
		}
		return vo;
	}


	
	// 3-1.상품등록
	public Integer goodsWrite(GoodsVO vo) throws Exception {
		// 결과값을 받을 변수 선언
		int result = 0;
		
		try {
			// 1. 드라이버 확인 - DispatcherServlet.init()
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL작성 (상수: GOODSWRITE) -> 클래스 하단에
			// 4. 실행객체에 데이터세팅
			pstmt = con.prepareStatement(GOODSWRITE);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getProductDate());
			pstmt.setString(4, vo.getModelNo());
			pstmt.setString(5, vo.getCompany());
			pstmt.setString(6, vo.getImageName());
			pstmt.setLong(7, vo.getDelivery_cost());
			// 5. SQL 실행
			result = pstmt.executeUpdate();
			// 6. 결과확인 (받은 데이터를 list에 저장)
			System.out.println("GoodsWrite() result = " + result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB 닫기
			DB.close(con, pstmt);
		}
		return result;
	}

	
	// 3-2.가격등록
	public Integer priceWrite(GoodsVO vo) throws Exception {
		// 결과값을 받을 변수 선언
		int result = 0;
		
		try {
			// 1. 드라이버 확인 - DispatcherServlet.init()
			// 2. DB연결
			con = DB.getConnection();
			// 3. SQL작성 (상수: PRICESWRITE) -> 클래스 하단에
			// 4. 실행객체에 데이터세팅
			pstmt = con.prepareStatement(PRICESWRITE);
			pstmt.setLong(1, vo.getGno());
			pstmt.setLong(2, vo.getStd_price());
			pstmt.setLong(3, vo.getDiscount());
			pstmt.setDouble(4, vo.getRate());
			pstmt.setString(5, vo.getStartDate());
			pstmt.setString(6, vo.getEndDate());
			// 5. SQL 실행
			result = pstmt.executeUpdate();
			// 6. 결과확인 (받은 데이터를 list에 저장)
			System.out.println("PriceWrite() result = " + result);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 7. DB 닫기
			DB.close(con, pstmt);
		}
		return result;
	}
	
	// SQL 쿼리
	final static String LIST = ""
		+ "select gno, name, modelNo, company "
		+ " from goods "
		+ " order by gno desc";
	
	final static String VIEW = ""
		+ "select gno, name, content, writeDate, productDate, modelNo, "
		+ " company, imageName, delivery_cost from goods "
		+ " where gno = ?";

	final static String GOODSWRITE = ""
		+ "insert into goods (gno, name, content, productDate, modelNo, "
		+ " company, imageName, delivery_cost) "
		+ " values (goods_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
	
	final static String PRICESWRITE = ""
		+ "insert into price (pno, gno, std_price, discount,"
		+ " rate, startDate, endDate) "
		+ " values (price_seq.nextval, ?, ?, ?, ?, ?, ?)";
} // end of class














