package com.jjangplay.cart.vo;

public class CartVO {

	private Long gno;
	private String name;
	private String imageName;
	private Long delivery_cost;
	
	private Long std_price;
	private Long discount;
	private Long count;//수량
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
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CartVO [gno=" + gno + ", name=" + name + ", imageName=" + imageName + ", delivery_cost=" + delivery_cost
				+ ", std_price=" + std_price + ", discount=" + discount + ", count=" + count + "]";
	}
	
	
}
