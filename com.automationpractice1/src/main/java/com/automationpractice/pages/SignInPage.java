package com.automationpractice.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends HeaderSideOfPage {

	private By signUpButtonLocator = By.id("SubmitCreate");
	private By signInButtonLocator = By.id("SubmitLogin");
	private By emailForSignInLocator = By.id("email");
	private By passwordForSignInLocator = By.id("passwd");
	private By emailForSignUpLinkLocator = By.id("email_create");
	private By emailExistsErrorMessageLocator = By.id("create_account_error");
	private By errorMessageLocator = By.xpath("//div[@id='center_column']/div[1]/ol/li");
	
	private String expectedSignInPageURL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
//	private String ErrorMessage = "Authentication failed.";
//	private String missingEmailAddressMessageText = "An email address required.";
//	private String missingPasswordMessageText = "Password is required.";
			
	

	public SignInPage(WebDriver driver, Logger log) {
		super(driver, log);
	}

	public String getExpectedSignInPageURL() {
		return expectedSignInPageURL;
	}

	public By getEmailExistsErrorMessageLocator() {
		return emailExistsErrorMessageLocator;
	};

	public MyAccountMainPage sighInAccount(String email, String password) {
		waitForVisibilityOf(emailForSignInLocator, 5);
		type(email, emailForSignInLocator);
		type(password, passwordForSignInLocator);
		log.info("Entered [" + email + "] as email and [" + password + "] as password for signIn");
		click(signInButtonLocator);
		log.info("Clicked on SignIn Button");
		return new MyAccountMainPage(driver, log);
	}
	
	

	public SignUpPage signUpForAccont(String email) {
		waitForVisibilityOf(emailForSignUpLinkLocator, 5);
		type(email, emailForSignUpLinkLocator);
		log.info("Entered [" + email + "] as email for signUp");
		click(signUpButtonLocator);
		log.info("Clicked on SignUp Button");
		return new SignUpPage(driver, log);
	}

//	@Override
//	public HomePage signOut() {
//		try {
//			click(signOutLocator);
//		} catch (TimeoutException e) {
//			log.info("You can not Sign Out yet.. in order to get out you need first to get in))");
//		}
//		return new HomePage(driver, log);
//	}
	
	public boolean waitForErrorMessageText() {
		waitForVisibilityOf(errorMessageLocator, 10);
		return true;
	}
	
	public String getActualErrorMessage() {
		return find(errorMessageLocator).getText();
	}

}
