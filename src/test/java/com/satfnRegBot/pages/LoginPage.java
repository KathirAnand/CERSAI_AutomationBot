package com.satfnRegBot.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="j_username")
	protected WebElement loginIDInputField;
	
	@FindBy(id="pwd1")
	protected WebElement passwordInputField;
	
	@FindBy(id="jcaptcha")
	protected WebElement captchInputField;
	
	@FindBy(css="button[title='Login']")
	protected WebElement submitButton;
	
	@FindBy(css="button[title='Close']")
	protected WebElement closeButton;
	
	@FindBy(xpath="//div[contains(text(),'Welcome to CERSAI')]")
	protected WebElement welcomeText;
	
	@FindBy(xpath="//div[contains(text(),'Captcha is Invalid. Try Again..')]")
	protected WebElement invalidCaptchaErrorMessage;
	
	@FindBy(xpath="//a[text()='Go To Home']")
	protected WebElement goToHomeLink;

	
	public void setLoginID(String loginID) {
		clearAndType(loginIDInputField, loginID);
	}
	
	public void setPassword(String password) {
		clearAndType(passwordInputField, password);
	}
	
	public void clickSubmitButton() {
		waitAndClick(submitButton);
	}
	
	public boolean invalidCaptchaMsg() {
		try {
			return validationIsDisplayed(invalidCaptchaErrorMessage);
		}
		catch(NoSuchElementException ex) {
			return false;
		}
		
	}
	
	public void clickGoToHomeLink() {
		clickElement(goToHomeLink);
	}
}
