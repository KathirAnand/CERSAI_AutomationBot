package com.satfnRegBot.automationScripts;

import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.satfnRegBot.pages.DigitalSignPage;
import com.satfnRegBot.pages.HomePage;
import com.satfnRegBot.pages.LoginPage;
import com.satfnRegBot.pages.RegistrationOfSIPage;
import com.satfnRegBot.pages.UserHomePage;
import com.satfnRegBot.testBase.BaseClass;
import com.satfnRegBot.testBase.FilePaths;

public class SecurityInterestRegistration extends BaseClass{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@BeforeClass
	public void loginAsUser() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickLoginButton();
		Reporter.log("Login button clicked");
		Thread.sleep(1000);
		LoginPage loginPage = new LoginPage(driver);
		System.out.println(actualUserId);
		System.out.println(actualPassword);
		if (actualUserId == null && actualPassword == null) {
			actualUserId = props.getProperty("LOGIN_ID");
			actualPassword = props.getProperty("PASSWORD");
		}
		loginPage.setLoginID(actualUserId);
		loginPage.setPassword(actualPassword);
		Thread.sleep(10000);
		loginPage.clickSubmitButton();
		while (loginPage.invalidCaptchaMsg()) {
			loginPage.clickGoToHomeLink();
			homePage.clickLoginButton();
			loginPage.setLoginID(actualUserId);
			loginPage.setPassword(actualPassword);
		}
	}

	/**
	 * After login, it will navigate to the SI Satisfaction Registration page
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@BeforeMethod
	public void satisfactionPage() throws InterruptedException, IOException {
		DigitalSignPage sign = new DigitalSignPage(driver);
		sign.clickInvokeDigitalSignButton();
		Thread.sleep(4000);
		
		Runtime.getRuntime().exec(FilePaths.AUTOIT_CLICKSIGN);
		Thread.sleep(4000);
		try {
			if(actualUserPIN==null) {
				sign.enterUserPIN(props.getProperty("USER_PIN"));
			}else {
				sign.enterUserPIN(actualUserPIN);
			}
			Thread.sleep(2000);
			sign.clickLoginUsingKeyboard();
			Thread.sleep(1000);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	
		UserHomePage userHome = new UserHomePage(driver);
		userHome.clickHamburgerIcon();
		userHome.clickRegistration();
	}
	
	@Test
	public void securityInterestRegistration() throws InterruptedException {
		RegistrationOfSIPage registrationOfSIDetails = new RegistrationOfSIPage(driver);
		Thread.sleep(1000);
		registrationOfSIDetails.setTransactionType();
		Thread.sleep(1000);
		registrationOfSIDetails.setAssetCategory();
		Thread.sleep(1000);
		registrationOfSIDetails.setSecurityInterestType();
		Thread.sleep(1000);
		registrationOfSIDetails.setTypeOfFinance();
		Thread.sleep(1000);
		registrationOfSIDetails.setChargeType("First Charge");
		registrationOfSIDetails.setTotalSecuredAmount("10000");
		registrationOfSIDetails.setSecurityInterestCreationDate("02/02/2024");
		registrationOfSIDetails.setEntityIdentificationNumber("123456789123");
		registrationOfSIDetails.clickNextBtn();
		Thread.sleep(1000);
	}


}
