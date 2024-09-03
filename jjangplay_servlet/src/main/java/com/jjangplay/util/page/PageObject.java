package com.jjangplay.util.page;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

public class PageObject {

	// 현재 페이지를 표현할때 사용하는 정보(DB)
	private long page; // 현재 보여주고 싶은 페이지
	private long perPageNum; // 페이지당 보여주고싶은 데이터 수
	private long startRow, endRow; // 보여주는 처음 데이터, 끝 데이터
	
	// 페이지 계산을 위한 정보들
	// JSP 하단에 page로 이동하는 태그들 보여줄때 사용
	private long perGroupPageNum;
	private long startPage;
	private long endPage;
	private long totalPage;
	private long tatalRow; // 전체데이터 수
	
	// 검색을 위한 변수선언
	private String key; // twc
	private String word; // 검색어
	
	public PageObject() {
		this.page = 1;
		this.perPageNum = 10;
		this.startRow = 1;
		this.endRow = 10;
		
		perGroupPageNum = 10;
		startPage = 1;
		endPage = 1;
	}
	
	public static PageObject getInstance(HttpServletRequest request) {
		return getInstance(request, "page", "perPageNum");
	}
	
	public static PageObject getInstance(HttpServletRequest request,
			String pageName, String perPageNumName) {
		// 페이지 처리를 위한 객체 사용
		PageObject pageObject = new PageObject();
		// 페이지 처리를 위한 정보를 받는다.
		String strPage = request.getParameter(pageName);
		System.out.println("pageName = " + pageName);
		System.out.println("strPage = " + strPage);
		// 페이지가 있으면 있는페이지로 이동 없으면 첫페이지로 이동
		if (strPage != null && !strPage.equals("")) {
			pageObject.setPage(Long.parseLong(strPage));
		}
		// 한페이지에 표시할 데이터 수를 받는다.
		String strPerPageNum = request.getParameter(perPageNumName);
		// 있으면 세팅한대로 없으면 10으로
		if (strPerPageNum != null && !strPerPageNum.equals("")) {
			pageObject.setPerPageNum(Long.parseLong(strPerPageNum));
		}
		
		// 검색을 위한 데이터 전달
		pageObject.setKey(request.getParameter("key"));
		pageObject.setWord(request.getParameter("word"));
		
		return pageObject;
		
	}
	
	
	public long getPage() {
		return page;
	}
	public void setPage(long page) {
		this.page = page;
	}
	public long getPerPageNum() {
		return perPageNum;
	}
	public void setPerPageNum(long perPageNum) {
		this.perPageNum = perPageNum;
	}
	public long getStartRow() {
		return startRow;
	}
	public void setStartRow(long startRow) {
		this.startRow = startRow;
	}
	public long getEndRow() {
		return endRow;
	}
	public void setEndRow(long endRow) {
		this.endRow = endRow;
	}
	public long getStartPage() {
		return startPage;
	}
	public void setStartPage(long startPage) {
		this.startPage = startPage;
	}
	public long getEndPage() {
		return endPage;
	}
	public void setEndPate(long endPage) {
		this.endPage = endPage;
	}
	public long getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	public long getTatalRow() {
		return tatalRow;
	}
	public void setTatalRow(long tatalRow) {
		this.tatalRow = tatalRow;
		
		System.out.println("page = " + page);
		
		// 시작 줄번호와 끝 줄번호를 계산
		startRow = (page-1)*perPageNum + 1;
		endRow = startRow + perPageNum - 1;
		System.out.println("startRow = " + startRow);
		System.out.println("endRow = " + endRow);
		
		// 페이지 계산
		// 전체페이지
		totalPage = (tatalRow-1)/perPageNum + 1;
		// startPage, endPage
		startPage = (page-1) / perGroupPageNum * perGroupPageNum + 1;
		endPage = startPage + perGroupPageNum - 1;
		if (endPage > totalPage) endPage = totalPage;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
	
	public String getNotPageQuery() throws Exception {
		return ""
			+ "perPageNum=" + getPerPageNum()
			+ "&key=" + ((getKey() == null)?"":getKey())
			+ "&word=" + 
			((getWord() == null)?"":
				URLEncoder.encode(getWord(), "utf-8"));
		
	}
	
	public String getPageQuery() throws Exception {
		return "page=" + getPage()
			+ "&" + getNotPageQuery();
	}
}







