package com.satfnRegBot.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;

import com.satfnRegBot.pageActions.ProjectSpecificMethods;
import com.satfnRegBot.testBase.FilePaths;

import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.io.IOException;

public class LoginPage extends BasePage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "j_username")
	protected WebElement loginIDInputField;

	@FindBy(id = "pwd1")
	protected WebElement passwordInputField;

	@FindBy(id = "jcaptcha")
	protected WebElement captchInputField;

	@FindBy(css = "button[title='Login']")
	protected WebElement submitButton;

	@FindBy(css = "button[title='Close']")
	protected WebElement closeButton;

	@FindBy(xpath = "//div[contains(text(),'Welcome to CERSAI')]")
	protected WebElement welcomeText;

	@FindBy(xpath = "//div[contains(text(),'Captcha is Invalid. Try Again..')]")
	protected WebElement invalidCaptchaErrorMessage;

	@FindBy(xpath = "//a[text()='Go To Home']")
	protected WebElement goToHomeLink;

	@FindBy(xpath = "//img[@alt='Login Captcha']")
	protected WebElement captchaImage;

	@FindBy(xpath = "//img[@alt='reload']")
	protected WebElement reloadCaptchaBtn;

	public void setLoginID(String loginID) {
		clearAndType(loginIDInputField, loginID);
	}

	public void setPassword(String password) {
		clearAndType(passwordInputField, password);
	}

	public void clickSubmitButton() {
		clickElement(submitButton);
	}

	public void clickReloadCaptchBtn() {
		waitAndClick(reloadCaptchaBtn);
	}

	public boolean invalidCaptchaMsg() {
		try {
			return validationIsDisplayed(invalidCaptchaErrorMessage);
		} catch (NoSuchElementException ex) {
			return false;
		}

	}

	public void clickGoToHomeLink() {
		clickElement(goToHomeLink);
	}

	public void setCaptcha() {
		String captcha=null;
		ProjectSpecificMethods projMethod=new ProjectSpecificMethods();
		boolean captchImageStatus = true;
		while (captchImageStatus) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			File source = captchaImage.getScreenshotAs(OutputType.FILE);
			// internal path
//			String path = FilePaths.TESTDATA_HOME+ProjectSpecificMethods.getCaptchaNameWithMinutes();

			// for jar(exe)file
			String path = FilePaths.USER_HOME + "\\captcha.png";
			File destination = new File(path);

			try {
				FileHandler.copy(source, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				captcha = projMethod.extractTextFromImage(path);
			} catch (TesseractException e) {
				e.printStackTrace();
			}
			System.out.println(captcha);
			if (captcha != null) {
				clearAndType(captchInputField, captcha);
				captchImageStatus = false;
			} else {
				clickReloadCaptchBtn();
			}
		}

	}
}
