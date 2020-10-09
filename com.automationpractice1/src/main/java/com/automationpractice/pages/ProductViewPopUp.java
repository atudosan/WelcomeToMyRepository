package com.automationpractice.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductViewPopUp extends BasePageObject {

	private By continueShoppingButtonLink = By.xpath("//div[@class='button-container']/span/span");
	private By proceedToCheckOutButtonLocator = By.xpath("//a[contains(@title, 'checkout')]");
	private By closeCrossIconLocator = By.cssSelector("span.cross");

	public ProductViewPopUp(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Continue Shopping After adding an item to shopping cart
	public HomePage continueShopping() {
		waitForVisibilityOf(continueShoppingButtonLink, 5);
		click(continueShoppingButtonLink);
		log.info("Clicked on ContinueShopping Button");
		
		return new HomePage(driver, log);
	}

	public ProductViewPage closePopUpPage() {
		click(closeCrossIconLocator);
		return new ProductViewPage(driver, log);
	}

	// Proceed to check out after adding Items to the Shopping cart
	public ShoppingCartSummaryPage proceedToCheckOut() {
		waitForVisibilityOf(proceedToCheckOutButtonLocator, 5);
		click(proceedToCheckOutButtonLocator);
		log.info("Clicked on ProceedToCheckOut Button");
		return new ShoppingCartSummaryPage(driver, log);

	}

//		
}