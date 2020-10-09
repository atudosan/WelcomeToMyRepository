package com.automationpractice.pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends HeaderSideOfPage {

	private String pageUrl = "http://automationpractice.com/index.php";
	private By productsItem = By.xpath(("//*[@id='homefeatured']/li"));
	

	public HomePage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public HomePage navigateToWelcomePage() {
		driver.get(pageUrl);
		log.info("Welcome Page is opened");
		return new HomePage(driver, log);
	};

	public ProductViewPopUp selectAnItemUsingQuickView(int index) {
		List<WebElement> items = findAll(productsItem);
		log.info("Added all items to the List");
		moveMouseOver(items.get(index));
		log.info("Pick up an item by moving mouse over it");
		waitForWebElementToBeClickable(
				By.xpath("//*[@id='homefeatured']/li[" + (index + 1) + "]/div/div[2]/div[2]/a[1]"), 5);
		driver.findElement(By.xpath("//*[@id='homefeatured']/li[" + (index + 1) + "]/div/div[2]/div[2]/a[1]")).click();

		return new ProductViewPopUp(driver, log);
	}
	
	public ProductViewPage selectAndNavigateItemPage(int index) {
		List<WebElement> items = findAll(productsItem);
		log.info("Added all items to the List");
		moveMouseOver(items.get(index));
		log.info("Pick up an item and navigate to its page");
		waitForWebElementToBeClickable(
				By.xpath("//*[@id='homefeatured']/li[" + (index + 1) + "]/div/div[2]/div[2]/a[2]"), 5);
		driver.findElement(By.xpath("//*[@id='homefeatured']/li[" + (index + 1) + "]/div/div[2]/div[2]/a[2]")).click();
		return new ProductViewPage(driver, log);
	}



}
