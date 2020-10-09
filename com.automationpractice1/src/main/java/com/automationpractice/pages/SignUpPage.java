package com.automationpractice.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SignUpPage extends HeaderSideOfPage {
	
	private String expectedSignUpPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
	
	private By mrRadioButtonLocator = By.id("id_gender1");
	private By mrsRadioButtonLocator = By.id("id_gender2");
	private By firstNamePersInfoField = By.id("customer_firstname");  
	private By lastNamePersInfoField = By.id("customer_lastname");
	private By emailPersInfoField = By.id("email");
	private By passwordPersInfoField = By.id("passwd");
	private By daysPersInfoField = By.id("days");
	private By monthsPersInfoField = By.id("months");
	private By yearsPersInfoField = By.id("years");
	
	private By firstNameAddressField = By.id("firstname");  
	private By lastNameAddressField = By.id("lastname");
	private By addressAddressField = By.id("address1");
	private By cityAddressField = By.id("city");
	private By stateAddressField = By.id("id_state");
	private By postalCodeAddressField = By.id("postcode");
	private By mobilePhoneAddressField = By.id("phone_mobile");
	private By subbmitButtonLocator = By.id("submitAccount");

	
	
	
	
	
	public SignUpPage(WebDriver driver, Logger log) {
		super(driver, log);
		
	}
	
	public String getExpectedSignUpPageURL() {
		return expectedSignUpPageURL;
	}
	
	public void populatePersInfo(String firstName, String lastName, String email, String password, String days,
			int months, String years ) {
		log.info("Populating Personal Information Fields");
		type(firstName, firstNamePersInfoField);
		type(lastName, lastNamePersInfoField );
		type(randomString(2)+email, emailPersInfoField);
		type(password, passwordPersInfoField);
		Select select = new Select(driver.findElement(daysPersInfoField));
		select.selectByValue(days);
		select = new Select(driver.findElement(monthsPersInfoField));
		select.selectByValue(months+"");
		select = new Select(driver.findElement(yearsPersInfoField));
		select.selectByValue(years);	
	}
	
	public void populateYourAddressInfo(String firstName, String lastName, String address, String city, 
			String state, String postalCode, String mobPhone) {
		log.info("Populating Address Information Fields");
		type(firstName, firstNameAddressField);
		type(lastName, lastNameAddressField );
		type(address, addressAddressField);
		type(city, cityAddressField);
		Select select = new Select(driver.findElement(stateAddressField));
		select.selectByVisibleText(state);
		type(postalCode, postalCodeAddressField);
		type(mobPhone, mobilePhoneAddressField);
	}
	
	public MyAccountMainPage clickOnSubmitButton() {
		click(subbmitButtonLocator);
		log.info("Clicked on Submit Button");
		return new MyAccountMainPage(driver, log);
	}
		
	
	
	
	public SignUpPage selectMrOrMrsRadioButton(String gender) {
		if(gender.toLowerCase().equals("male")) {
			click(mrRadioButtonLocator);
		}
		else if(gender.toLowerCase().equals("female")) {
			click(mrsRadioButtonLocator);
		}
		else{
			click(mrRadioButtonLocator);
			System.out.println("Check spelling, Mr was selected by default!!!");
		}
		log.info(gender+" Radio Button was selected ");
		return new SignUpPage(driver, log);
	}
	
}
