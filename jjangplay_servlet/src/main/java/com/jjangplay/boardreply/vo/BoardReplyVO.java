package com.jjangplay.boardreply.vo;

// DB안의 Board table 의 한행의 데이터을 저장할 수 있는 클래스 
public class BoardReplyVO {

	// DB에 있는 Board_reply Table 칼럼명
	// rno, no, content, writer, writeDate, pw
	private Long rno;
	private Long no;
	private String content;
	private String writer;
	private String writeDate;
	private String pw;

	// getter, setter
	public Long getRno() {
		return rno;
	}
	public void setRno(Long rno) {
		this.rno = rno;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
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
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	@Override
	public String toString() {
		return "BoardReplyVO [rno=" + rno + ", no=" + no + ", content=" + content + ", writer=" + writer
				+ ", writeDate=" + writeDate + ", pw=" + pw + "]";
	}

	
	
}
