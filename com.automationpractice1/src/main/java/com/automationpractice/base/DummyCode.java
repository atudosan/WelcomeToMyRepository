package com.automationpractice.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DummyCode {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://automationpractice.com/index.php");
		//driver.findElement(By.xpath("//*[@id='homefeatured']/li[5]")).click();
		List<WebElement> items = driver.findElements(By.xpath("//*[@id='homefeatured']/li"));
		items.get(2).click();
	}

}
