package com.jjangplay.image.vo;

public class ImageVO {
	// private 변수 (이미지게시판에 사용할)
	private Long no;
	private String title;
	private String content;
	private String id;
	private String name;	//member table에서 id를 가지고 찾습니다.
	private String writeDate;
	private String fileName;//위치정보를 포함한 파일명(서버기준)
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "ImageVO [no=" + no + ", title=" + title + ", content=" + content + ", id=" + id + ", name=" + name
				+ ", writeDate=" + writeDate + ", fileName=" + fileName + "]";
	}
	
	
}
