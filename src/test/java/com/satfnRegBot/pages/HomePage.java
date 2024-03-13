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
	
	public void clickLoginButton() {
		clickElement(loginButton);
	}

}
