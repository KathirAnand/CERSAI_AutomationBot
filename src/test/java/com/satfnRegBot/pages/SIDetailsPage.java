package com.satfnRegBot.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class SIDetailsPage extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SIDetailsPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="#satnDate")
	protected WebElement dateOfSatisfactionInputField;
	
	@FindBy(xpath="//button[text()='Confirm Satisfaction']")
	protected WebElement confirmSubmissionButton;
	
	@FindBy(xpath="//a[text()='Cancel']")
	protected WebElement cancelButton;
	
	@FindBy(css="#satnReason")
	protected WebElement reasonDropdown;
	
	@FindBy(xpath="//select[@id='satnReason']/child::option[@value='Loan Repaid']")
	protected WebElement loanRepaid;
	
	@FindBy(xpath="//label[text()='Reason for Delay']")
	protected WebElement reasonForDelayText;
	
	@FindBy(css="#omissionReason")
	protected WebElement reasonInputField;
	
	public void selectReason(String reason) {
		clickElement(reasonDropdown);
		selectByValue(reasonDropdown, reason);
	}
	
	public void selectReason(int index) {
		clickElement(loanRepaid);
		selectByIndex(loanRepaid, index);
	}
	
	public void setDate(String closedDate) {
		scrollAndType(dateOfSatisfactionInputField,closedDate);
	}
	
	public void clickSubmitButton() {
		clickElement(confirmSubmissionButton);
	}
	
	public void setReason(String reason) {
		clearAndType(reasonInputField, reason);
	}
	
	
	public boolean reasonForDelayIsDisplayed() {
		try {
			return validationIsDisplayed(reasonForDelayText);
		}
		catch(NoSuchElementException ex) {
			return false;
		}
	}
	
	public void selectReasonUseKeyboard() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB);
		action.sendKeys(Keys.TAB);
		action.sendKeys("l");
		action.build().perform();
	}
	
}
