package com.interview.deloitte.vendingmachine.service;

public class ProductRequest {

	private String productCode;
	private Integer amount;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
