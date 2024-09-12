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
	private long perGroupPageNum; //하단에 몇개의 페이지링크를 보여줄 것인가?
	private long startPage;
	private long endPage;
	private long totalPage;
	private long totalRow; // 전체데이터 수
	
	// 검색을 위한 변수선언
	private String key; // twc
	private String word; // 검색어
	
	// 공지분류(기간)를 적용시키는 변수 선언
	// pre: 현재공지, old: 지난공지, res: 예약공지, all: 전체공지
	private String period;
	
	public PageObject() {
		this.page = 1;
		this.perPageNum = 10;
		this.startRow = 1;
		this.endRow = 10;
		
		setPerGroupPageNum(10);
		startPage = 1;
		endPage = 1;
		
		period = "pre";
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
		String strPerPageNum = request.getParameter("perPageNum");
		if (strPerPageNum != null && !strPerPageNum.equals("")) {
			pageObject.setPerPageNum(Long.parseLong(strPerPageNum));
		}
		
		// 검색을 위한 데이터 전달
		pageObject.setKey(request.getParameter("key"));
		pageObject.setWord(request.getParameter("word"));
		
		System.out.println("PageObject.getInstance() end");
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
	public long getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(long totalRow) {
		this.totalRow = totalRow;
		
		System.out.println("page = " + page);
		
		// 시작 줄번호와 끝 줄번호를 계산
		startRow = (page-1)*perPageNum + 1;
		endRow = startRow + perPageNum - 1;
		System.out.println("startRow = " + startRow);
		System.out.println("endRow = " + endRow);
		
		// 리스트 하단에 나타나는 페이지링크를 처리하기 위한 데이터 계산
		totalPage = (totalRow-1)/perPageNum +1;
		// startPage, endPage
		startPage = (page-1)/perGroupPageNum*perGroupPageNum + 1;
		endPage = startPage + perGroupPageNum - 1;
		// endPage는 totalPage보다 클 수 없다.
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

	public long getPerGroupPageNum() {
		return perGroupPageNum;
	}

	public void setPerGroupPageNum(long perGroupPageNum) {
		this.perGroupPageNum = perGroupPageNum;
	}
	
	public String getNotPageQuery() throws Exception {
		return ""
			+ "perPageNum=" + getPerPageNum()
			+ "&key=" + ((getKey() == null)?"":getKey())
			+ "&word="
			+ ((getWord() == null)?"":URLEncoder.encode(getWord(), "utf-8"));
	}
	
	public String getPageQuery() throws Exception {
		return "page=" + getPage()
			+ "&" + getNotPageQuery();
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "PageObject [page=" + page + ", perPageNum=" + perPageNum + ", startRow=" + startRow + ", endRow="
				+ endRow + ", perGroupPageNum=" + perGroupPageNum + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", totalPage=" + totalPage + ", totalRow=" + totalRow + ", key=" + key + ", word=" + word
				+ ", period=" + period + "]";
	}

	
	
}







