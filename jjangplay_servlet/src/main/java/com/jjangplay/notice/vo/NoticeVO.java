package com.jjangplay.notice.vo;

public class NoticeVO {

	// 글번호, 제목, 내용, 게시시작일, 게시종료일, 작성일, 수정일
	private Long no;
	private String title;
	private String content;
	private String startDate;
	private String endDate;
	private String writeDate;
	private String updateDate;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	
	@Override
	public String toString() {
		// NoticeVO 안에 있는 데이터를 출력한다.
		return "NoticVO [no=" + no + ", title=" + title + ", content="
				+ content + ", startDate=" + startDate + ", endDate="
				+ endDate + ", writeDate=" + writeDate + 
				", updateDate=" + updateDate + "]";
	}
	
	
}
