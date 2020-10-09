package com.automationpractice.signuptests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automationpractice.base.TestUtilities;
import com.automationpractice.pages.HomePage;
import com.automationpractice.pages.MyAccountMainPage;
import com.automationpractice.pages.SignInPage;
import com.automationpractice.pages.SignUpPage;

public class SignUpTests extends TestUtilities{
	@Parameters({"firstName", "lastName", "email", "password", "dateofBirth", "monthOfBirth", "yearOfBirth", 
		"address", "city", "state", "postalCode", "phoneNum", })
	@Test
	public void sighUp(String firstName, String lastName, String email, String password, String days, int months, String years, 
			String address, String city, String state, String postalCode, String mobPhone) {
		HomePage welcomePage = new HomePage(driver, log);
		SignInPage signIn = new SignInPage(driver, log);
		SignUpPage signUp = new SignUpPage(driver, log);
		
		SoftAssert softAssert = new SoftAssert();
		
		
		welcomePage.navigateToWelcomePage().
					navigateToSighInPage();		
		String actualUrl = driver.getCurrentUrl();
		
		Assert.assertTrue(actualUrl.contains(signIn.getExpectedSignInPageURL()),"Error!!! There are already existing account which is use your email!");
		log.info("We are on Sign In Page!!!");
				
		signIn.signUpForAccont("t123est@test.com");
		
		actualUrl = driver.getCurrentUrl();
		log.info(actualUrl);
		
		Assert.assertTrue(actualUrl.contains(signUp.getExpectedSignUpPageURL()),"Error!!! There are already existing account which is use your email!");
		log.info("We are on Sign Up Page!!!");	
		
		signUp.selectMrOrMrsRadioButton("male");
		signUp.populatePersInfo(firstName, lastName, email, password, days, months, years);
		
		signUp.populateYourAddressInfo(firstName, lastName, address, city, state, postalCode, mobPhone);
		
		MyAccountMainPage myAccount = signUp.clickOnSubmitButton();
		
		softAssert.assertTrue(myAccount.getMyAccountMainActualUrl().contains(myAccount.getMyAccountMainExpectedUrl()),
				"Error - Diffrent URL!!!");	
		
		softAssert.assertTrue(myAccount.getActualWelcomeMessage().contains(myAccount.getExpectedWelcomeMessage()), 
				"Error: Unexpected Welcome Message!!!");
		
		softAssert.assertTrue(myAccount.getMyActualNameOnAccountPage().equals(myAccount.getMyExpectedNameOnAccountPage
				(firstName, lastName)), "Error: Unexpected Name!!!");
					
		
		softAssert.assertAll();
		
		
	}

}
