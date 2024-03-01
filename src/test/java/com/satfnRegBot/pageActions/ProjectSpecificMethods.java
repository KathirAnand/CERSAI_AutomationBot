package com.satfnRegBot.pageActions;

import java.util.Date;

import org.openqa.selenium.TakesScreenshot;

import com.satfnRegBot.testBase.BaseClass;
import com.satfnRegBot.testBase.FilePaths;

import org.openqa.selenium.OutputType;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ProjectSpecificMethods extends BaseClass{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String getTimestamp() {
		return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
	}
	
	public static String getDatestampWithMinutes() {
		return new SimpleDateFormat("dd_MM_yyyy_HH_mm").format(new Date());
	}
	
	public static String getExcelNameForWrite() {
		return "SI_SatisfiedData_"+getDatestampWithMinutes()+".xlsx";
	}
	public static String captureScreen(String tname) throws IOException {

		String timeStamp = getTimestamp();
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = FilePaths.SCREENSHOT_HOME + tname + "_" + timeStamp + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;

	}
	
	public static String removeSpaceInSIID(String securityInterestID) {
		return securityInterestID.replaceAll("\\s", "");
	}
	
	public static boolean lengthValidation(String securityInterestID) {
		String[] chars=securityInterestID.split("");
		return (chars.length==12)?true:false;
	}
}
