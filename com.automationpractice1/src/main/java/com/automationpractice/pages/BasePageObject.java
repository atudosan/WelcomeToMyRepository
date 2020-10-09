package com.automationpractice.pages;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {

	protected WebDriver driver;
	protected Logger log;

	public BasePageObject(WebDriver driver, Logger log) {
		this.driver = driver;
		this.log = log;
	}

	// Open page with given URL
	protected void openUrl(String url) {
		driver.get(url);
	}

	// Get the URL from the browser
	public String getActualURL() {
		return driver.getCurrentUrl();
	}

	// Find web-element with given locator
	protected WebElement find(By locator) {
		return driver.findElement(locator);
	}

	protected List<WebElement> findAll(By locator) {
		return driver.findElements(locator);
	}

	public void moveMouseOver(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public String getStyleAtributeOfWebElement(By locator) {
		find(locator).getAttribute("style");
		return new String();
	}

	// Click on web-element with given Locator
	protected void click(By locator) {
		waitForWebElementToBeClickable(locator, 10);
		find(locator).click();
	}

	// Type given text into web-element defined by locator
	protected void type(String text, By locator) {
		waitForVisibilityOf(locator, 5);
		find(locator).clear();
		find(locator).sendKeys(text);
	}

	// Wait for specific ExpectedCondition for given amount of seconds
	protected void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
		timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(condition);
	}

	// Wait for given number of seconds for web-element with given locator to be
	// visible

	protected void waitForVisibilityOf(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
			} catch (StaleElementReferenceException e) {
				// TODO: handle exception
			}
			attempts++;
		}

	}

	protected void waitForWebElementToBeClickable(By locator, Integer... timeOutInSeconds) {
		int attempts = 0;
		while (attempts < 2) {
			try {
				waitFor(ExpectedConditions.elementToBeClickable(locator),
						(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
			} catch (StaleElementReferenceException e) {
				// TODO: handle exception
			}
			attempts++;
		}
	}

	public String randomString(int i) {
		String characters = "abcdefghiklmnoprstuvwxyz";
		String randomString = "";
		//int length = 2;
		Random rand = new Random();
		char[] text = new char[i];
		for (int j = 0; j < i; j++) {
			text[j] = characters.charAt(rand.nextInt(characters.length()));
		}
		for (int j = 0; j < text.length; j++) {
			randomString += text[j];			
		}
		return randomString;
	}

}
