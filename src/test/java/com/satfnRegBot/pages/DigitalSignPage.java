package com.satfnRegBot.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DigitalSignPage extends BasePage {
	
	public DigitalSignPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//button[text()='Invoke Digital Signature']")
	protected WebElement invokeDigitalSign;
	
	public void clickInvokeDigitalSignButton() {
		clickElement(invokeDigitalSign);
	}
	
	public void clickSignButton() throws AWTException {
		Robot robot =new Robot();
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyPress(KeyEvent.VK_ENTER);
	}

}
