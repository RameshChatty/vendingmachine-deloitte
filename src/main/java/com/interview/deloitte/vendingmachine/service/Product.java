package com.interview.deloitte.vendingmachine.service;

public class Product {

	private String productCode;
	private String productName;
	private Integer productPrice;
	private Integer quatity;

	public Product(String productCode, String productName, Integer productPrice, Integer quatity) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quatity = quatity;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getQuatity() {
		return quatity;
	}

	public void setQuatity(Integer quatity) {
		this.quatity = quatity;
	}

	@Override
	public String toString() {
		return "Product [code=" + productCode + ", product=" + productName + ", price=" + productPrice
				+ ", available Quantity=" + quatity + "]";
	}

}
