package com.satfnRegBot.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class RegistrationOfSIPage extends BasePage{
	
	private static final long serialVersionUID = 1L;

	public RegistrationOfSIPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(css="#UIN")
	protected WebElement entityIdentificationNumberInputField;
	
	@FindBy(css="#securityInterestCreationDate")
	protected WebElement securityInterestCreationDateInputField;
	
	@FindBy(css="#totalSecuredAmount")
	protected WebElement totalSecuredAmountInputField;
	
	@FindBy(css="#chargeType")
	protected WebElement chargeTypeInputField;
	
	@FindBy(xpath="//button[text()='Next']")
	protected WebElement nextBtn;
	
	@FindBy(css="#transactionTypes")
	protected WebElement transactionTypes;
	
	@FindBy(css="#assetCategory")
	protected WebElement assetCategory;
	
	@FindBy(css="#SIType")
	protected WebElement SIType;
	
	@FindBy(css="#typeOfFinance")
	protected WebElement typeOfFinance;
	
	public void setTypeOfFinance() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB);
		action.sendKeys("s");
		action.build().perform();
//		selectByValue(typeOfFinance,"1");
	}
	
	public void setSecurityInterestType() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB);
		action.sendKeys("e");
		action.build().perform();
//		selectByValue(SIType, "17");
	}
	
	public void setAssetCategory() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB);
		action.sendKeys("i");
		action.build().perform();
//		selectByValue(assetCategory, "1");
	}
	
	public void setTransactionType() {
			
		clickElement(transactionTypes);
		Actions action = new Actions(driver);
		action.sendKeys("s");
		action.sendKeys(Keys.ENTER);
		action.build().perform();
//		selectByIndex(transactionTypes, 1);
	}
	
	public void clickNextBtn() {
		actionClick(nextBtn);
	}
	
	public void setEntityIdentificationNumber(String identificationNumber) {
		clearAndType(entityIdentificationNumberInputField, identificationNumber);
	}
	
	public void setSecurityInterestCreationDate(String creationDate) {
		scrollAndType(securityInterestCreationDateInputField,creationDate);
	}
	
	public void setTotalSecuredAmount(String totalSecureAmount) {
		clearAndType(totalSecuredAmountInputField,totalSecureAmount);
	}
	
	public void setChargeType(String chargeType) {
		clearAndType(chargeTypeInputField,chargeType);
	}
}
