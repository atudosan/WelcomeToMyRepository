package com.automationpractice.pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductViewPage extends HeaderSideOfPage {

	private By finalPricePerUnitLocator = By.id("our_price_display");
	private By itemQuantityLocator = By.cssSelector("input#quantity_wanted");
	private By decreaseQuantityButtonLocator = By.xpath("//a[contains(@class,'button-minus')]");
	private By increaseQuantityButtonLocator = By.xpath("//a[contains(@class,'button-plus')]");
	private By selectItemSizeLocator = By.id("group_1");
	//private By allItemColorLocators = By.xpath("//a[contains(@class,'color_pick')]");
	private By addToCartButtonLocator = By.xpath("//button[@name='Submit']");
	private By homePageButtonLocator = By.cssSelector("a.home");
	private By itemNameLocator = By.xpath("//h1[@itemprop='name']");
	private By itemSizeIndicator = By.xpath("//div[@id='uniform-group_1']/span");
			

	public ProductViewPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	// Adding to cart by pressing on add to cart button
	public ProductViewPopUp addToCart() {
		waitForWebElementToBeClickable(addToCartButtonLocator, 10);
		click(addToCartButtonLocator);
		log.info("clicked on AddToCart Button");
		return new ProductViewPopUp(driver, log);
	}

	// return to HomePage by clicking on home icon
	public HomePage returnToHomepage() {
		click(homePageButtonLocator);
		return new HomePage(driver, log);
	}

	// increase quantity of item
	public ProductViewPage increaseQuantityOfItemBy(int i) {
		for (int j = 0; j < i; j++) {
			click(increaseQuantityButtonLocator);
		}
		int y = getTotalQuantityOfAnOrderedItem();
		log.info("Increase quantity by [" + i + "]. Intial was [" + (y - i) + "], now is [" + y + "]");
		return new ProductViewPage(driver, log);
	}

	// decrease quantity of item
	public ProductViewPage decreaseQuantityOfItemBy(int i) {
		int y = getTotalQuantityOfAnOrderedItem();
		if (y > i) {
			for (int j = 0; j < i; j++) {
				click(decreaseQuantityButtonLocator);
				log.info("Decrease quantity by [" + i + "]. Intial was [" + y + "], now is [" + (y - i) + "]");
			}
		} else if (y <= i) {
			log.info("You can not decrese by [" + i + "] beacuse you got only [" + y
					+ "] items. So we let it like it was");
		}
		return new ProductViewPage(driver, log);
	}

	// Select Item Color
	public ProductViewPage selectItemColor(String color) {
		List<WebElement> colors = findAll(By.xpath("//*[@id='color_to_pick_list']/li"));
		WebDriverWait wait = new WebDriverWait(driver, 10);

		for (int i = 1; i <= colors.size(); i++) {
			WebElement actualColor = find(By.xpath("//*[@id='color_to_pick_list']/li[" + i + "]/a"));
			if (actualColor.getAttribute("name").toLowerCase().trim().equals(color.toLowerCase())) {
				actualColor.click();
				wait.until(ExpectedConditions.attributeContains(actualColor, "class", "selected"));
				break;
			}
		}
		if (getColorItemName().equals(color)) {
			log.info("[" + color + "] was selected as a item color");
		} else {
			String colorByDefault = find(By.xpath("//*[@id='color_to_pick_list']/li[1]/a")).getAttribute("name")
					.toLowerCase();
			log.info("[" + color + "] was not found. selected [" + colorByDefault + "] as a item color");
		}
		return new ProductViewPage(driver, log);
	}

	// Select the Color of Item
	public ProductViewPage selectItemSize(String size) {
		WebElement selectSize = find(selectItemSizeLocator);
		Select select = new Select(selectSize);
		if (size.equals("small")) {
			select.selectByVisibleText("S");
			log.info("You selected S size - SMALL");
		} else if (size.equals("medium")) {
			select.selectByVisibleText("M");
			log.info("You selected M size - MEDIUM");
		} else if (size.equals("large")) {
			select.selectByVisibleText("L");
			log.info("You selected L size - LARGE");
		} else {
		log.info("You selected WRONG size, that is why we selected -M- size by default");
		select.selectByVisibleText("M");
	}

	return new ProductViewPage(driver,log);
	}

	// Get selected color name
	public String getColorItemName() {
		String itemColor = find(By.xpath("//ul[@id='color_to_pick_list']/li/a[contains(@class,'selected')]"))
				.getAttribute("name").toLowerCase().trim();
		log.info("The selected item's color is [" + itemColor + "].");
		return itemColor;
	}

	// Get the Price of your Item
	public Double getFinalPricePerUnit() {
		double i = Double.parseDouble(find(finalPricePerUnitLocator).getText().trim().substring(1));
		log.info("The selected item's price per unit is [" + i + "].");
		return i;
	}

	// Get Total Quantity
	public Integer getTotalQuantityOfAnOrderedItem() {
		int i = Integer.parseInt(find(itemQuantityLocator).getAttribute("value"));
		log.info("The selected item's quantity is [" + i + "].");
		return i;
	}

	// Get Item's Name
	public String getOrderedItemName() {
		String itemName = find(itemNameLocator).getText().trim().toLowerCase();
		log.info("The selected item's name is [" + itemName + "].");
		return itemName;
	}
	public String getOrderedItemSize() {
		String itemSize = find(itemSizeIndicator).getText().trim();
		return itemSize;
		
	}
}
