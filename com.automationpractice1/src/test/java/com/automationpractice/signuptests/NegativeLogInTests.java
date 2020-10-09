package com.automationpractice.signuptests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automationpractice.base.CsvDataProviders;
import com.automationpractice.base.TestUtilities;
import com.automationpractice.pages.HomePage;
import com.automationpractice.pages.SignInPage;

public class NegativeLogInTests extends TestUtilities {

	
	@Test(dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class )
	public void negativeLogInTest(Map<String, String> testData){
		
		//Data
		String no = testData.get("no");
		String email = testData.get("email");
		String password= testData.get("password");
		String expectedErrorMessage= testData.get("expectedMessage");
		String description= testData.get("description");
		
		log.info("Starting negativeLogInTest # "+no+" for "+description);
		
		HomePage home = new HomePage(driver, log);
		home.navigateToWelcomePage().
		navigateToSighInPage().
		sighInAccount(email, password);
		
		SignInPage signIn = new SignInPage(driver, log);
		signIn.sighInAccount(email, password);
		
		
		String actualErrorMessage = signIn.getActualErrorMessage();
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),"Actual error message is not the same as the expected one");
		
		
		
		
		
	}

}




