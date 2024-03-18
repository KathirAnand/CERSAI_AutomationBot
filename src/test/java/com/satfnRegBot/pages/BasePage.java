package com.satfnRegBot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.satfnRegBot.pageActions.PageActions;

public class BasePage extends PageActions{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected WebDriver driver;
	protected PageActions action = new PageActions();
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
}
