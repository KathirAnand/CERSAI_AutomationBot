package com.satfnRegBot.testBase;

import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseClass {

	protected static WebDriver driver;
	public static ResourceBundle rb;
	public static Logger logger;

	/**
	 * Properties file and Logger is initialized at before suite
	 */
	@BeforeSuite
	public void setPropertiesAndLogger() {
		rb = ResourceBundle.getBundle("config"); // to get the properties file
		logger = LogManager.getLogger(this.getClass()); // to initiate the logger

	}

	/**
	 * Browser should be launched before the test start
	 */
	@BeforeTest
	public void launchBrowser() {
		String browser = rb.getString("BROWSER");
		if (browser.equalsIgnoreCase("chrome")) {

			// to ignore the Chrome message as "Chrome is controlled by selenium"
			ChromeOptions opt = new ChromeOptions();
			opt.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			opt.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(opt);
		} else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions opt = new EdgeOptions();
			opt.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			opt.addArguments("--remote-allow-origins=*");
			driver = new EdgeDriver(opt);
		} else if (browser.equalsIgnoreCase("firefox")) {
			FirefoxOptions opt = new FirefoxOptions();
//			opt.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});
			opt.addArguments("--remote-allow-origins=*");
			driver = new FirefoxDriver();
		}

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(rb.getString("URL"));
		logger.info(rb.getString("URL") + " URL Opened Successfully in " + browser + " browser");
		driver.manage().window().maximize();
	}

	/**
	 * After the test completed it will close the all the browser instance
	 */
	@AfterTest
	public void tearDown() {
		driver.close();
	}
}
