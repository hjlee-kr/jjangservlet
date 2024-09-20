package com.jjangplay.shop.vo;

public class ShopVO {

	private Long gno;
	private String name;
	private String content;
	private String productDate;
	private String modelNo;
	private String company;
	private String imageName;
	private Long delivery_cost;
	
	private Long std_price;
	private Long discount;
	private Double rate;
	private String startDate;
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
		return "ShopVO [gno=" + gno + ", name=" + name + ", content=" + content + ", productDate=" + productDate
				+ ", modelNo=" + modelNo + ", company=" + company + ", imageName=" + imageName + ", delivery_cost="
				+ delivery_cost + ", std_price=" + std_price + ", discount=" + discount + ", rate=" + rate
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
}
