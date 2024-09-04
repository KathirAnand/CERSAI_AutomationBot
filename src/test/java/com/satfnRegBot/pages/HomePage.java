package com.satfnRegBot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="a[title='Login for Central Registry']")
	protected WebElement loginButton;
	
	@FindBy(css="#userClass")
	protected WebElement userProfile;
	
	@FindBy(xpath="//a[text()='Logout']")
	protected WebElement profileLogoutBtn;
	
	@FindBy(css=".popup-close-cross")
	protected WebElement CKYCPopUpCloseBtn;
	
	public void clickLoginButton() {
		clickElement(loginButton);
	}
	
	public void clickCKYCCLoseBtn() {
		clickElement(CKYCPopUpCloseBtn);
	}
	
	public void clickUserProfile() {
		clickElement(userProfile);
	}
	
	public void clickUserLogoutBtn() {
		clickElement(profileLogoutBtn);
	}
}
