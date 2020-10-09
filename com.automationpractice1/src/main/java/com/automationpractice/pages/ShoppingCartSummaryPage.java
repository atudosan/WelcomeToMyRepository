package com.automationpractice.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automationpractice.pages.components.ShoppingCartObjectFactory;

public class ShoppingCartSummaryPage extends HeaderSideOfPage {

	private By shoppingCartTableLocator = By.id("cart_summary");
	private By tableRowsLocator = By.xpath("//table[@id='cart_summary']/tbody/tr");

	private By rowProductNameLocator = By.xpath("//td[@class='cart_description']/p/a");
	private By rowSKULocator = By.xpath("//td[@class='cart_description']/small[1]");
	private By rowColorLocator = By.xpath("//td[@class='cart_description']/small[2]/a");
	private By rowInStockLocator = By.xpath("//td[@class='cart_avail']/span");
	private By rowUnitPriceLocator = By.xpath("//td[@class='cart_unit']/span/span");
	private By rowQuantityLocator = By.xpath("//td[contains(@class,'cart_quantity')]/input[1]");
	private By rowTotalPriceLocator = By.xpath("//td[@class='cart_total']/span");

	private By rowProductImageLinkLocator = By.xpath("//td[@class='cart_product']/a/img");
	private By rowProductNameLinkLocator = By.xpath("//td[@class='cart_description']/p/a");
	private By rowIncreaseQuantityButtonLocator = By.xpath("//td[contains(@class,'cart_quantity')]/div/a[2]");
	private By rowDecreaseQuantityButtonLocator = By.xpath("//td[contains(@class,'cart_quantity')]/div/a[1]");
	private By rowRemoveProductButtonLocator = By.xpath("//td[contains(@class,'cart_delete')]/div/a");

	public ShoppingCartSummaryPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Method wich take values from the table and create an Object using those
	// values
	public List<ShoppingCartObjectFactory> getShoppingCart() {
		waitForVisibilityOf(shoppingCartTableLocator, 10);
		WebElement shoppingCartTable = driver.findElement(shoppingCartTableLocator);
		List<WebElement> tableRows = shoppingCartTable.findElements(tableRowsLocator);
		List<ShoppingCartObjectFactory> cartItems = new ArrayList<ShoppingCartObjectFactory>();

		for (WebElement row : tableRows) {
			ShoppingCartObjectFactory cartItem = new ShoppingCartObjectFactory();

			cartItem.setProductName(row.findElement(rowProductNameLocator).getText().trim().toLowerCase());
			cartItem.setSKU(row.findElement(rowSKULocator).getText().trim());
			String[] elems = row.findElement(rowColorLocator).getText().toString().split(",");
			cartItem.setColor(elems[0].substring(8));
			cartItem.setSize(elems[1].substring(8));
			//cartItem.setColor(row.findElement(rowColorLocator).getText().trim());
			cartItem.setInStock(row.findElement(rowInStockLocator).getText().trim().toLowerCase().equals("in stock"));
			cartItem.setUnitPrice(Double.parseDouble(row.findElement(rowUnitPriceLocator).getText().substring(1).trim()));
			cartItem.setQuantity(Integer.parseInt(row.findElement(rowQuantityLocator).getAttribute("value").trim()));
			cartItem.setTotalPrice(Double.parseDouble(row.findElement(rowTotalPriceLocator).getText().substring(1).trim()));

			cartItem.setProductImageLink(row.findElement(rowProductImageLinkLocator));
			cartItem.setProductNameLink(row.findElement(rowProductNameLinkLocator));
			cartItem.setIncreaseQuantityButton(row.findElement(rowIncreaseQuantityButtonLocator));
			cartItem.setDecreaseQuantityButton(row.findElement(rowDecreaseQuantityButtonLocator));
			cartItem.setRemoveProductButton(row.findElement(rowRemoveProductButtonLocator));

			cartItems.add(cartItem);
		}
		return cartItems;
	}

	
	//decrease quantity by one Item from shoppin cart 
	public ShoppingCartSummaryPage removeAnItemFromShoppingCart(int index) {
		getShoppingCart().get(index).increaseQuantity();
		return new ShoppingCartSummaryPage(driver, log);
	}
	//increase quantity by one Item from shoppin cart
	public ShoppingCartSummaryPage addAnItemFromShoppingCart(int index) {
		getShoppingCart().get(index).increaseQuantity();
		return new ShoppingCartSummaryPage(driver, log);
	}
	//remove an item from shopping cart
	public ShoppingCartSummaryPage deleteAnItemFromShoppingCart(int index) {
		getShoppingCart().get(index).removeProductFromBasket();
		return new ShoppingCartSummaryPage(driver, log);
	}
	
	public ShoppingCartSummaryPage printSizeItem(int index) {
		getShoppingCart().get(index).getSize();
		System.out.println(getShoppingCart().get(index).getSize());
		return new ShoppingCartSummaryPage(driver, log);
	}
	
	public ShoppingCartSummaryPage printColorItem(int index) {
		getShoppingCart().get(index).getColor();
		System.out.println(getShoppingCart().get(index).getColor());
		return new ShoppingCartSummaryPage(driver, log);
	}

	
}















