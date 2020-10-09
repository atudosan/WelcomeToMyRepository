package com.automationpractice.pages;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderSideOfPage extends BasePageObject {

	protected By signInLocator = By.className("login");
	protected By signOutLocator = By.className("logout");
	protected By contactUsLocator = By.linkText("Contact us");
	protected By myAccountLocator = By.cssSelector("a.account");
	protected By logoIconLocator = By.xpath("//img[contains(@class, 'logo')]");
	protected By searchItemTextLocator = By.id("search_query_top");
	protected By searchIconLoactor = By.cssSelector("button.submit_search");
	protected By shoppingCartStatusLocator = By.xpath("//div[@class='shopping_cart']/a");
	//protected By shoppingCartQuantityIndicatorLocator = By.xpath("//div[@class='shopping_cart']/a/span[1]");
	protected By shoppingCartQuantityIndicatorLocator = By.xpath("//span[@class='ajax_cart_quantity']");
	protected By womanSubMenuLocaor = By.linkText("Women");
	protected By dressesSubMenuLocaor = By.linkText("Dresses");
	protected By tshirtsSubMenuLocator = By.linkText("T-shirts");

	public HeaderSideOfPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public SignInPage navigateToSighInPage() {
		waitForVisibilityOf(signInLocator, 10);
		click(signInLocator);
		log.info("Clicked on SignIn Button");
		return new SignInPage(driver, log);
	}

	public HomePage signOut() {
		waitForVisibilityOf(signOutLocator, 10);
		click(signOutLocator);	
		log.info("Clicked on SignOut Button");
		return new HomePage(driver, log);
	}

	public HomePage deleteAllItemsFromCart() {
		WebElement indicator = find(By.xpath("//*[@id='header']/div[3]/div/div/div[3]/div/a/span[5]"));
		if (indicator.getAttribute("class").contains("unvisible")) {
			WebElement nrItems = find(shoppingCartQuantityIndicatorLocator);
			int nrItemsInt = Integer.parseInt(nrItems.getText());
			List<WebElement> nrProducts = findAll(By.xpath("//dl[@class='products']/dd"));
			if (nrProducts.size() > 0) {
				for (int i = 0; i < nrProducts.size(); i++)
					moveMouseOver(find(shoppingCartStatusLocator));
				find(By.xpath("//span[@class='remove_link']")).click();
			}
			log.info("Remove all [" + nrItemsInt + "] items from shopping cart");
			return new HomePage(driver, log);
		} else {
			log.info("The cart was empty");
		}
		return new HomePage(driver, log);
	}

}
