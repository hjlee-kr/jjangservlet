package com.jjangplay.goods.vo;

public class GoodsVO {
	// private 변수 (상품관리게시판에 사용할)
	// goods table
	// 상품번호
	private Long gno;
	// 상품명
	private String name;
	// 상품내용
	private String content;
	// 작성일
	private String writeDate;
	// 제조일
	private String productDate;
	// 모델넘버
	private String modelNo;
	// 제조사
	private String company;
	// 제품사진(이미지)-경로+파일이름
	private String imageName;
	// 배송비
	private Long delivery_cost;
	
	// price table
	// 가격넘버
	private Long pno;
	// 정가
	private Long std_price;
	// 할인가
	private Long discount;
	// 할인율
	private Double rate;
	// 판매시작일
	private String startDate;
	// 판매종료일
	private String endDate;
	
	public Long getGno() {
		return gno;
	}
	public void setGno(Long gno) {
		this.gno = gno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getProductDate() {
		return productDate;
	}
	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Long getDelivery_cost() {
		return delivery_cost;
	}
	public void setDelivery_cost(Long delivery_cost) {
		this.delivery_cost = delivery_cost;
	}
	public Long getPno() {
		return pno;
	}
	public void setPno(Long pno) {
		this.pno = pno;
	}
	public Long getStd_price() {
		return std_price;
	}
	public void setStd_price(Long std_price) {
		this.std_price = std_price;
	}
	public Long getDiscount() {
		return discount;
	}
	public void setDiscount(Long discount) {
		this.discount = discount;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
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
	@Override
	public String toString() {
		return "GoodsVO [gno=" + gno + ", name=" + name + ", content=" + content + ", writeDate=" + writeDate
				+ ", productDate=" + productDate + ", modelNo=" + modelNo + ", company=" + company + ", imageName="
				+ imageName + ", delivery_cost=" + delivery_cost + ", pno=" + pno + ", std_price=" + std_price
				+ ", discount=" + discount + ", rate=" + rate + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}
	
	

}









