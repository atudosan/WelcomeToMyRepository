package com.automationpractice.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserDriverFactory {
	
	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private String browser;
	private Logger log;
	
	public BrowserDriverFactory(String browser, Logger log) {
		this.browser = browser.toLowerCase();
		this.log = log;
	}
	
	public WebDriver createDriver() {
		
		log.info("Create driver --> "+browser);
		
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
			driver.set(new ChromeDriver());
			log.debug("Created chrome driver");
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			driver.set(new FirefoxDriver());
			log.debug("Created firefox driver");
		} else if (browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", "src/main/resources/msedgedriver.exe");
			driver.set(new EdgeDriver());
			log.debug("Created edge driver");
		} else {
			System.out.println("Do not know how to start " +browser+ ", starting chrome instead!!!");
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
			log.debug("ELSE WORKED OUT!!! chrome came out");
		}

		
		
		return driver.get();
		
		
	}

}
