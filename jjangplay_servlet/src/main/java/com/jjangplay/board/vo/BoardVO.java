package com.jjangplay.board.vo;

// DB안의 Board table 의 한행의 데이터을 저장할 수 있는 클래스 
public class BoardVO {

	// DB에 있는 Board Table 칼럼명
	// no, title, content, writer, writeDate, hit, pw
	private Long no;
	private String title;
	private String content;
	private String writer;
	private String writeDate;
	private Long hit;
	private String pw;

	// getter, setter
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public Long getHit() {
		return hit;
	}
	public void setHit(Long hit) {
		this.hit = hit;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	// toString - 데이터 확인용으로 메서드 내용을 변경함
	// BoardVO 안에 있는 변수들의 값을 출력함
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "BoardVO [no=" + no + ", title=" + title + ", content=" + content
				+ ", writer=" + writer + ", writeDate=" + writeDate
				+ ", hit=" + hit + ", pw=" + pw + "]";
	}
	
	
}
