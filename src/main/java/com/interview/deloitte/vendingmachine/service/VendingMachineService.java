package com.interview.deloitte.vendingmachine.service;

import java.util.List;

public interface VendingMachineService {

	List<Product> getAvailableProducts();

	void getProduct(ProductRequest req);
}
