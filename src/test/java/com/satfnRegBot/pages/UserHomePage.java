package com.satfnRegBot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserHomePage extends BasePage{

	public UserHomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//span[@data-original-title='Menu']")
	protected WebElement hamburgerIcon;
	
	@FindBy(xpath="//a[text()='Security Interest']")
	protected WebElement securityInterestLink;
	
	@FindBy(xpath="//a[text()='Satisfaction']")
	protected WebElement SISatisfactionLink;
	
	@FindBy(css="#userClass")
	protected WebElement notificationMessage;
	
	public void clickHamburgerIcon() {
		visibleAndClick(hamburgerIcon);
	}
	
	public void clickSatisfaction() {
		moveAndClick(securityInterestLink, SISatisfactionLink);
	}
	

}
