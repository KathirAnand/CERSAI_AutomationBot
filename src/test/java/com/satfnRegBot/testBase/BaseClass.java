package com.satfnRegBot.testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
//import java.io.IOException;
import java.time.Duration;
//import java.util.ResourceBundle;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.satfnRegBot.pageActions.ProjectSpecificMethods;
import com.satfnRegBot.runner.MainRunner;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass extends MainRunner{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static RemoteWebDriver driver;
	protected final static String excelWritePath=FilePaths.USER_HOME + ProjectSpecificMethods.getExcelNameForWrite();
//	public static ResourceBundle rb;
	public static Logger logger;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public String propertiesFilePath;
	protected Properties props;

	/**
	 * Properties file and Logger is initialized at before suite
	 * @throws Exception 
	 */
	@BeforeSuite
	public void setPropertiesAndLogger() throws Exception {
		try {
			if(configPath!=null) {
				propertiesFilePath = configPath;
				props = new Properties();
				FileInputStream ip = new FileInputStream(propertiesFilePath);
				props.load(ip);
			}else {
				props = new Properties();
				propertiesFilePath = FilePaths.USER_HOME+"config.properties";
				FileInputStream ip = new FileInputStream(propertiesFilePath);
				props.load(ip);
			}
//			rb = ResourceBundle.getBundle("config"); // to get the properties file
			logger = LogManager.getLogger(this.getClass()); // to initiate the logger
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			propertiesFilePath = FilePaths.USER_HOME+"config.properties";
			props = new Properties();
			FileInputStream ip = new FileInputStream(propertiesFilePath);
			props.load(ip);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	/**
	 * Browser should be launched before the test start
	 */
	@BeforeTest
	public void launchBrowser() {
		String browser =props.getProperty("BROWSER");
		if (browser.equalsIgnoreCase("chrome")) {

			// to ignore the Chrome message as "Chrome is controlled by selenium"
			ChromeOptions opt = new ChromeOptions();
			opt.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//			opt.setExperimentalOption("credentials_enable_service", false);
//			opt.setExperimentalOption("profile.password_manager_enabled", false);
			opt.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(opt);
//			Reporter.log("Chrome browser opened",true);
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
		driver.get(props.getProperty("URL"));
		logger.info(props.getProperty("URL") + " URL Opened Successfully in " + browser + " browser");
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
