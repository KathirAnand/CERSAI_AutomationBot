package com.satfnRegBot.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
//import java.net.URL;

//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.ImageHtmlEmail;
//import org.apache.commons.mail.resolver.DataSourceUrlResolver;

import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.satfnRegBot.pageActions.ProjectSpecificMethods;
import com.satfnRegBot.testBase.BaseClass;
import com.satfnRegBot.testBase.FilePaths;

public class ExtentReportUtility extends BaseClass implements ITestListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	String repName;
	
	
	public void onStart(ITestContext testContext) {
		String timeStamp = ProjectSpecificMethods.getTimestamp();// time stamp
		repName = "Test-Report-" + timeStamp + ".html";

		sparkReporter = new ExtentSparkReporter(FilePaths.USER_HOME+repName);// specify location of the report

		sparkReporter.config().setDocumentTitle("Veritas CERSAI SI Automation Report"); // Title of report
		sparkReporter.config().setReportName("SI Satisfaction Registration Bot Report"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Automation Bot");
		extent.setSystemInfo("Module", "Public");
		extent.setSystemInfo("Sub Module", "Veritas");
		extent.setSystemInfo("Operating System", System.getProperty("os.name"));
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		
//		WebDriver driver; //to get the baseClass driver (from QAFox)
//		try {
//			driver= (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
//		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
//			e.printStackTrace();
//		}
		
		try {
			String imgPath = ProjectSpecificMethods.captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		test.log(Status.FAIL, "Test Failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());
	}
	
	//to skip the method or test by using the testNG attribute dependsOnMethods={"testMethodName"}-->if provided testMethod is got failed and then the depended method should be skipped
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test Skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();

		
//		 try { URL url = new
//		 URL(System.getProperty("user.dir")+"\\test-output\\emailable-report.html");
//		 
//		 // Create the email message 
//		 ImageHtmlEmail email = new ImageHtmlEmail();
//		 email.setDataSourceResolver(new DataSourceUrlResolver(url));
//		 email.setHostName("smtp.googlemail.com"); 
//		 email.setSmtpPort(465);
//		 email.setAuthenticator(new DefaultAuthenticator("kathirstuvad434dear@gmail.com","!Daredewin@123")); 
//		 email.setSSLOnConnect(true);
//		 email.setFrom("kathirstuvad434dear@gmail.com"); //Sender
//		 email.setSubject("Test Results");
//		 email.setMsg("Please find Attached Report....");
//		 email.addTo("ssuryap75@gmail.com"); //Receiver 
//		 email.attach(url, "extent report", "please check report..."); 
//		 email.send(); // send the email 
//		  }
//		 catch(Exception e) { e.printStackTrace(); }
//		 
	}
}
