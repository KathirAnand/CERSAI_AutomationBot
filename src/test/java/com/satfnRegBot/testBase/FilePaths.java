package com.satfnRegBot.testBase;

import java.io.File;

/**
 * This class it consists the project constant file paths which is used in the project local repository
 * @author Kathiravan
 *
 */
public class FilePaths {

	public final static String USER_HOME=System.getProperty("user.dir")+File.separator;
	public final static String JAREXCEL = USER_HOME+"SI_Satisfaction_RegData.xlsx";
	public static final String TESTDATA_HOME=USER_HOME+"dataSource"+File.separator;
	public final static String PROPERITES_FILEPATH = TESTDATA_HOME+"config.properties";
	public final static String BOT_ICON = USER_HOME +"icon"+File.separator+"InvBotIcon.png";
	public final static String DATASOURCE_SI_SATFN_REG = TESTDATA_HOME+"SI_Satisfaction_RegData.xlsx";
	public static final String REPORT_HOME=USER_HOME+"reports"+File.separator;
	public static final String EXTENTREPORT_FILE=REPORT_HOME+"test-output\\FailedTestsScreenshots\\";
	public static final String SCREENSHOT_HOME=USER_HOME+"screenshots"+File.separator;
	
}
