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

	public static String getTimestamp() {
		return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
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
	
	public void formatDate() {
		
	}
	
}
