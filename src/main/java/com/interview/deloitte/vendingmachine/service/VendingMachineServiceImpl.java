package com.interview.deloitte.vendingmachine.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class VendingMachineServiceImpl implements VendingMachineService {


	public List<Product> getAvailableProducts() {

		List<Product> productList = new ArrayList<>();

		productList.add(new Product("CHO", "Chocolates", 5, 10));
		productList.add(new Product("CAN", "Candy", 10, 20));
		productList.add(new Product("COD", "Cool drinks", 15, 30));

		return productList;
	}

	@Override
	public void getProduct(ProductRequest req) {
		Integer balanceAmount = 0;
		List<Product> availableProducts = getAvailableProducts();

		Optional<Product> matchedProduct = availableProducts.stream()
				.filter(prod -> prod.getProductCode().equalsIgnoreCase(req.getProductCode())).findFirst();

		if (matchedProduct.isPresent()) {
			Product product = matchedProduct.get();
			System.out.println(
					"You have Selected :" + product.getProductName() + ": of price :" + product.getProductPrice());

			if (req.getAmount() >= product.getProductPrice()) {
				balanceAmount = req.getAmount() - product.getProductPrice();
			} else {
				System.out.println("Added amount is not sufficient to purchase the product");
				System.out.println("Collect your coins..");
				System.exit(0);
			}

			System.out.println("please collect the product :" + product.getProductName());

			System.out.println("\nRemaining Products are : ");
			List<Product> modifiedlist = new ArrayList<>();
			availableProducts.forEach(obj -> {
				if (obj.getProductCode().equalsIgnoreCase(req.getProductCode())) {
					obj.setQuatity(obj.getQuatity() - 1);
				}

				modifiedlist.add(obj);

			});

			availableProducts = modifiedlist;
			availableProducts.stream().forEach(System.out::println);


			if (balanceAmount > 0) {
				System.out.println("please collect the balance Amount :" + balanceAmount);
				Map<String, Integer> splitAmountToCoins = splitAmountToCoins(balanceAmount);
				System.out.println("Please collect the balance amount");
				splitAmountToCoins.entrySet().forEach(returnCoins -> {
					System.out.println(returnCoins.getValue() + " : " + returnCoins.getKey() + " Coins");
				});
			}
			System.out.println("Your transaction is completed");

		} else {
			System.out.println("Product is not available/you entered wrong code ");
		}

	}

	private Map<String, Integer> splitAmountToCoins(Integer balanceAmount) {
		Map<String, Integer> map = new HashMap<>();
		while (balanceAmount > 0) {
			if (balanceAmount >= 25) {
				String key = "quarter";
				addToReturnCoinsMap(map, key);
				balanceAmount = balanceAmount - 25;
			} else if (balanceAmount >= 15) {
				String key = "dime";
				addToReturnCoinsMap(map, key);
				balanceAmount = balanceAmount - 10;
			} else if (balanceAmount >= 5) {
				String key = "nickel";
				addToReturnCoinsMap(map, key);
				balanceAmount = balanceAmount - 5;
			}
		}
		return map;

	}

	private void addToReturnCoinsMap(Map<String, Integer> map, String key) {
		if (map.containsKey(key)) {
			Integer previousval = map.get(key);
			map.put(key, previousval + 1);
		} else {
			map.put(key, 1);
		}
	}

}
