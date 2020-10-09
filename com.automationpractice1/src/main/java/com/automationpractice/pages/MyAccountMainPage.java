package com.automationpractice.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountMainPage extends HeaderSideOfPage {
	
	
	private String myAccountMainExpectedUrl = "http://automationpractice.com/index.php?controller=my-account";
	private String expectedWelcomeMessage = "Welcome to your account. Here you can manage all of your personal information and orders.";
	private By welcomeMessage = By.className("info-account");
	private By myNameOnAccountPage = By.xpath("//div[@class='header_user_info']/a[1]/span[1]");
	private By homePageIconLocator = By.cssSelector("a.home");

	public MyAccountMainPage(WebDriver driver, Logger log) {
		super(driver, log);
	}
	
	public By getHomePageIconLocator() {
		return homePageIconLocator;
	}
	
	public String getActualWelcomeMessage() {
		log.info("Verifing Actual Welcome Message");
		 return find(welcomeMessage).getText();
	}
	
	public String getExpectedWelcomeMessage() {
		return expectedWelcomeMessage;
	}
	
	public String getMyAccountMainExpectedUrl() {
		
		return myAccountMainExpectedUrl;
	}
	
	public String getMyAccountMainActualUrl() {
		log.info("Verifing Actual URL");
		return getActualURL();
	}
	
	public String getMyActualNameOnAccountPage() {
		log.info("Verifing First and LastName");
		return driver.findElement(myNameOnAccountPage).getText();
	}
	
	public String getMyExpectedNameOnAccountPage(String firstName, String lastName) {
		String expectedName = firstName.concat(" "+lastName);
		return expectedName;
	}
	
	public HomePage navigateToHomePage() {
		waitForVisibilityOf(homePageIconLocator, 5);
		click(homePageIconLocator);
		log.info("Clicked on HomePage Icon");
		return new HomePage(driver, log);
	}
	
}
