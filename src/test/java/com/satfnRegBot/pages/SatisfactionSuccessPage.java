package com.satfnRegBot.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.satfnRegBot.testBase.FilePaths;
import com.satfnRegBot.utilities.ExcelUtility;

public class SatisfactionSuccessPage extends BasePage{

	public SatisfactionSuccessPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//button[text()='Go Back']")
	protected WebElement backButton;
	
	@FindBy(xpath="//div[@id='success']/descendant::div[contains(text(),'Successfully Satisfied')]")
	protected WebElement transactionID;
	
	/**
	 * After the SI is Satisfied then back button will be clickeds
	 */
	public void clickBackButton() {
		waitAndClick(backButton);
	}
	/**
	 * To get the transaction ID of the Registered SI Satisfaction
	 * @return
	 */
	public String getTransactionID() {
		String fullTransID = getTextOfElement(transactionID);
		String[] trans = fullTransID.split(" ");
		return trans[5];
		
	}
	
	/**
	 * 
	 * @param sheetName
	 * @param rowNo
	 * @param colNo
	 * @param value
	 * @throws IOException
	 */
	public void setTransactionID(String sheetName,int rowNo,int colNo,String value) throws IOException {
		ExcelUtility write = new ExcelUtility(FilePaths.DATASOURCE_SI_SATFN_REG);
		write.setCellData(sheetName, rowNo, colNo, value);
	}
}
