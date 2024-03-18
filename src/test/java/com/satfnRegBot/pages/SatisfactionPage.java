package com.satfnRegBot.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SatisfactionPage extends BasePage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SatisfactionPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="#securityInterestId")
	protected WebElement securityInterestIDInputField;
	
	@FindBy(xpath="//button[text()='Proceed']")
	protected WebElement proceedButton;
	
	@FindBy(xpath="//span[text()='This Security Interest is already satisfied.']")
	protected WebElement alreadySatisfiedErrorMsg;
	
	@FindBy(xpath="//span[text()='Field must begin with either 1 or 4.']")
	protected WebElement beginWith12Or4ErrMsg;
	
	@FindBy(xpath="//button[text()='×']")
	protected WebElement closeButtonAtErrorMsg;
	
	@FindBy(xpath="//span[text()='The field must be a number']")
	protected WebElement fieldMustBeNumberErrorMsg;
	
	@FindBy(xpath="//span[text()='Field length must be 12']")
	protected WebElement fieldLengthErrorMsg;
	
	/**
	 * This method will set the Security Interest ID at the SI ID Input Field
	 * @param securityInterestID
	 */
	public void setSecurityInterestID(String securityInterestID) {
		clearAndType(securityInterestIDInputField, securityInterestID);
	}
	
	/**
	 * This method clicks the proceed button.
	 * If the Security Interest ID is entered, then proceed button should be clicked by using this method
	 */
	public void clickProceedButton() {
		clickElement(proceedButton);
	}
	
	/**
	 * This method clicks the close button.
	 * If the message "Security Interest is already satisfied" is displayed then close icon should be clicked by using this method
	 */
	public void clickCloseButtonInErrorMsg() {
		visibleAndClick(closeButtonAtErrorMsg);
	}
	
	/**
	 * This method will return true, if the "Security Interest is already satisfied" is displayed otherwise it will return false while submitting
	 * @return
	 */
	public boolean errorMsgIsDisplayed() {
		try {
			return validationIsDisplayed(alreadySatisfiedErrorMsg);
		}
		catch(NoSuchElementException ex) {
			return false;
		}
	}
	
	/**
	 * This method will return true, if the error message "Field Length must be 12" is displayed otherwise it will return false while entering the Security Interest ID
	 * @return
	 */
	public boolean fieldLengthErrorMsg(){
		try {
			return validationIsDisplayed(fieldLengthErrorMsg);
		}
		catch(NoSuchElementException ex) {
			return false;
		}
	}
	
	/**
	 * This method will return true, if the error message "Field must be Number" is displayed otherwise it will return false while entering the Security Interest ID
	 * @return
	 */
	public boolean fieldMustBeNumberErrorMsg() {
		try {
			return validationIsDisplayed(fieldMustBeNumberErrorMsg);
		}
		catch(NoSuchElementException ex) {
			return false;
		}
	}
}
