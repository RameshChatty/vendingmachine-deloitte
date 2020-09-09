package com.interview.deloitte.vendingmachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.interview.deloitte.vendingmachine.service.Product;
import com.interview.deloitte.vendingmachine.service.ProductRequest;
import com.interview.deloitte.vendingmachine.service.VendingMachineService;

@SpringBootTest
public class VendingmachineApplicationTests {



	public static final Map<String, Integer> coinsMap = new HashMap<>();

	static {
		coinsMap.put("nickel", 5);
		coinsMap.put("dime", 10);
		coinsMap.put("quarter", 25);
	}

	@Autowired
	private VendingMachineService vendingMachineService;

	// @Test
	// public void vendingMachineTestcase() throws IOException {
	// Integer sum = 0;
	// Integer balanceAmount = 0;
	// boolean stop = false;
	//
	// System.out.println("System Accepted Coins..");
	//
	// coinsMap.entrySet().forEach(coin -> {
	// System.out.println("coin :" + coin.getKey() + " ---> value : " +
	// coin.getValue());
	// });
	//
	// List<Product> availableProducts =
	// vendingMachineService.getAvailableProducts();
	// availableProducts.stream().forEach(System.out::println);
	// while (!stop) {
	//
	// System.out.println("Please Enter the Product code : ");
	// BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	//
	// String name = reader.readLine();
	//
	// boolean anyMatch = availableProducts.stream()
	// .anyMatch(prod -> prod.getProductCode().equalsIgnoreCase(name));
	// if (anyMatch) {
	//
	// Optional<Product> matchedObject = availableProducts.stream()
	// .filter(prod -> prod.getProductCode().equalsIgnoreCase(name)).findFirst();
	// if (matchedObject.isPresent()) {
	// Product product = matchedObject.get();
	// System.out.println("You have Selected :" + product.getProductName() + ": of
	// price :"
	// + product.getProductPrice());
	// System.out.println("Please add the accepted coins to get Product");
	//
	// reader = new BufferedReader(new InputStreamReader(System.in));
	//
	// String[] addedcoins = reader.readLine().split(" ");
	// Integer[] coins_Array = new Integer[addedcoins.length];
	//
	// for (String val : addedcoins) {
	// sum = sum + Integer.parseInt(val);
	// }
	//
	// System.out.println("Total amount added is : " + sum);
	//
	// if (sum > product.getProductPrice()) {
	// balanceAmount = sum - product.getProductPrice();
	// }
	//
	// System.out.println("please collect the product :" +
	// product.getProductName());
	//
	// System.out.println("Available Products are : ");
	// List<Product> modifiedlist = new ArrayList<>();
	// availableProducts.forEach(obj -> {
	// if (obj.getProductCode().equalsIgnoreCase(name)) {
	// obj.setQuatity(obj.getQuatity() - 1);
	// }
	//
	// modifiedlist.add(obj);
	//
	// });
	//
	//
	// availableProducts = modifiedlist;
	// availableProducts.stream().forEach(System.out::println);
	// System.out.println("Do you want to continue ? [Y/N]");
	// reader = new BufferedReader(new InputStreamReader(System.in));
	// String readLine = reader.readLine();
	//
	// if (readLine.equalsIgnoreCase("Y")) {
	// sum = balanceAmount;
	// } else {
	// System.out.println("please collect the balance Amount :" + balanceAmount);
	// stop = true;
	// }
	//
	// }
	//
	// } else {
	// System.out.println("Entered Invalid Code/please try again");
	// continue;
	// }
	//
	// }
	//
	// }

	@Test
	public void vendingMachineTestcase2() throws IOException {

		acceptedCoins();

		availableProducts();

		System.out.println("please enter the product code");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String productCode = reader.readLine();

		System.out.println("please add the coins [nickel,dime,quarter] (add space b/w the coins)");
		reader = new BufferedReader(new InputStreamReader(System.in));
		String[] coins = reader.readLine().split("\\s");
		Integer[] amount = new Integer[coins.length];
		int sum = 0;
		int index = 0;
		for (String coin : coins) {
			if (coinsMap.containsKey(coin)) {
				amount[index++] = coinsMap.get(coin);
				sum = sum + coinsMap.get(coin);
			} else {
				System.out.println("You have given wrong input");
				System.exit(0);
			}
		}


		ProductRequest req = new ProductRequest();
		req.setAmount(sum);
		req.setProductCode(productCode);
		vendingMachineService.getProduct(req);
	}

	private void availableProducts() {
		System.out.println("=============Available Products ==============");
		List<Product> availableProducts = vendingMachineService.getAvailableProducts();
		availableProducts.stream().forEach(System.out::println);

	}

	private void acceptedCoins() {
		System.out.println("=============Accepted coins ==============");
		coinsMap.entrySet().forEach(coin -> {
			System.out.println("coin :" + coin.getKey() + " ---> value : " + coin.getValue());
		});
	}

}
