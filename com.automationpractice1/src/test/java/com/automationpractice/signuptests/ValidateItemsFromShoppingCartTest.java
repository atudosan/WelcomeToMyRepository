package com.automationpractice.signuptests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automationpractice.base.CsvDataProviders;
import com.automationpractice.base.TestUtilities;
import com.automationpractice.pages.HomePage;
import com.automationpractice.pages.ProductViewPage;
import com.automationpractice.pages.ShoppingCartSummaryPage;

public class ValidateItemsFromShoppingCartTest extends TestUtilities {

	@Parameters({"browser"})
	@Test(dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
	public void validateItemsFromShoppingCartTest(Map<String, String> testData) {
		String no = testData.get("no");
		String email = testData.get("email");
		String password = testData.get("password");
		int itemIndex = Integer.parseInt(testData.get("itemIndex"));
		int quantity = Integer.parseInt(testData.get("quantity"));
		String color = testData.get("color");
		String size = testData.get("size");
		
		log.info("Starting test number ["+no+"]");
		
		HomePage welcome = new HomePage(driver, log);
		welcome.navigateToWelcomePage().
		navigateToSighInPage().
		sighInAccount(email, password).
		navigateToHomePage().
		selectAndNavigateItemPage(itemIndex).
		increaseQuantityOfItemBy(quantity).
		selectItemColor(color).
		selectItemSize(size);

		ProductViewPage product = new ProductViewPage(driver, log);
		int totalOrderedItems = product.getTotalQuantityOfAnOrderedItem();
		String orderedItemColor = product.getColorItemName();
		Double pricePerOrderedUnit = product.getFinalPricePerUnit();
		String orderedItemName = product.getOrderedItemName();
		String orderedItemSize = product.getOrderedItemSize();

		product.addToCart().proceedToCheckOut();

		ShoppingCartSummaryPage shoppingCart = new ShoppingCartSummaryPage(driver, log);
		int itemQuantityAccordingShoppingCart = shoppingCart.getShoppingCart().get(0).getQuantity();
		String itemColorAccordingShoppingCart = shoppingCart.getShoppingCart().get(0).getColor().toLowerCase();
		double pricePerItemAccordingShoppingCart = shoppingCart.getShoppingCart().get(0).getUnitPrice();
		String itemNameAccordingShoppingCart = shoppingCart.getShoppingCart().get(0).getProductName();
		String itemSizeAccordingShoppingCart = shoppingCart.getShoppingCart().get(0).getSize();

		// shoppingCart.deleteAllItemsFromCart().
		// shoppingCart.removeAnItemFromShoppingCart(0).
		// signOut();

		Assert.assertEquals(itemQuantityAccordingShoppingCart, totalOrderedItems,
				"Expected " + totalOrderedItems + " but got " + itemQuantityAccordingShoppingCart);
		Assert.assertEquals(orderedItemColor, itemColorAccordingShoppingCart,
				"Expected " + orderedItemColor + " but got " + itemColorAccordingShoppingCart);
		Assert.assertEquals(pricePerItemAccordingShoppingCart, pricePerOrderedUnit, 0.1,
				"Expected " + pricePerOrderedUnit + " but got " + pricePerItemAccordingShoppingCart);
		Assert.assertEquals(itemNameAccordingShoppingCart, orderedItemName,
				"Expected " + orderedItemName + " but got " + itemNameAccordingShoppingCart);
		Assert.assertEquals(itemSizeAccordingShoppingCart, orderedItemSize,
				"Expected " + orderedItemSize + " but got " + itemSizeAccordingShoppingCart);

	}

}
