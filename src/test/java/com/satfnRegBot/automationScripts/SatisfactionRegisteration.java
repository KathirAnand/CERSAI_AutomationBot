package com.satfnRegBot.automationScripts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.Reporter;

import com.satfnRegBot.pageActions.ProjectSpecificMethods;
import com.satfnRegBot.pages.DigitalSignPage;
import com.satfnRegBot.pages.HomePage;
import com.satfnRegBot.pages.LoginPage;
import com.satfnRegBot.pages.SIDetailsPage;
import com.satfnRegBot.pages.SatisfactionPage;
import com.satfnRegBot.pages.SatisfactionSuccessPage;
import com.satfnRegBot.pages.UserHomePage;
import com.satfnRegBot.testBase.BaseClass;
import com.satfnRegBot.testBase.FilePaths;
import com.satfnRegBot.utilities.ExcelUtility;

/**
 * @author Kathiravan
 */
public class SatisfactionRegisteration extends BaseClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * After the browser invoked, it will login to the application with valid
	 * credentials
	 * 
	 * @throws InterruptedException
	 */
	@BeforeClass
	public void loginAsUser() throws InterruptedException {
		try {
			HomePage homePage = new HomePage(driver);
			homePage.clickCKYCCLoseBtn();
			homePage.clickLoginButton();
			Reporter.log("Login button clicked");
			Thread.sleep(1000);
			LoginPage loginPage = new LoginPage(driver);
			System.out.println(actualUserId);
			System.out.println(actualPassword);
			if (actualUserId == null && actualPassword == null) {
				actualUserId = props.getProperty("LOGIN_ID");
				actualPassword = props.getProperty("PASSWORD");
			}
			loginPage.setLoginID(actualUserId);
			loginPage.setPassword(actualPassword);
			loginPage.setCaptcha();
			Thread.sleep(10000);
			loginPage.clickSubmitButton();
			while (loginPage.invalidCaptchaMsg()) {
				loginPage.clickGoToHomeLink();
				homePage.clickLoginButton();
				loginPage.setLoginID(actualUserId);
				loginPage.setPassword(actualPassword);
				loginPage.setCaptcha();
				Thread.sleep(2000);
				loginPage.clickSubmitButton();
			}
		} catch (Exception e) {
			Thread.sleep(10000);
			e.printStackTrace();
		}
	}

	/**
	 * After login, it will navigate to the SI Satisfaction Registration page
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void invokeDigitalSignature() throws InterruptedException, IOException {
		try {
			DigitalSignPage sign = new DigitalSignPage(driver);
			Thread.sleep(3000);
			sign.clickInvokeDigitalSignButton();
			Thread.sleep(1000);

			Runtime.getRuntime().exec(FilePaths.AUTOIT_CLICKSIGN);
			Thread.sleep(3000);
			try {
				if (actualUserPIN == null) {
					sign.enterUserPIN(props.getProperty("USER_PIN"));
				} else {
					sign.enterUserPIN(actualUserPIN);
				}
				Thread.sleep(1000);
				sign.clickLoginUsingKeyboard();
				Thread.sleep(1000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			UserHomePage userHome = new UserHomePage(driver);
			userHome.clickHamburgerIcon();
			userHome.clickSatisfaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * To Register the SI Satisfaction and data's are feed through the Excel sheet
	 * 
	 * @throws Exception
	 */
	@Test
	public void satisfactionRegister() throws Exception {
		String SIId = "";
		String sheetName = "Sheet1";
		String closedDate = "";
		String transID = "";
		int dataRowNo;

//		int dataRowNoSetExcel=0;
		ExcelUtility read = new ExcelUtility(excelPath);
//		ExcelUtility write = new ExcelUtility(excelWritePath);
//		ExcelUtility read = new ExcelUtility(FilePaths.DATASOURCE_SI_SATFN_REG);
//		ExcelUtility read = new ExcelUtility(FilePaths.JAREXCEL);
		int totalRowCount = read.getRowCount(sheetName);
		int totalColCount = read.getCellCount(sheetName, totalRowCount);
//		for (int j = 0; j <= totalColCount; j++) {
//			switch (j) {
//			case 0:
//				write.setCellData(sheetName, dataRowNoSetExcel, j, "Closed Date");
//				break;
//			case 1:
//				write.setCellData(sheetName, dataRowNoSetExcel, j, "Security Interest Id");
//				break;
//			case 2:
//				write.setCellData(sheetName, dataRowNoSetExcel, j, "Transaction ID/Comments");
//				break;
//			}
//		}

		// if condition states, if there is no data available in the Excel sheet then it
		// will not run the loop
		if (totalRowCount > 0) {
			for (dataRowNo = 1; dataRowNo <= totalRowCount; dataRowNo++) {
				for (int j = 0; j <= totalColCount; j++) {
					if (j == 0) {
						closedDate = read.getCellData(sheetName, dataRowNo, j);

					} else if (j == 1) {
						SIId = read.getCellData(sheetName, dataRowNo, j);
						SIId = ProjectSpecificMethods.removeSpaceInSIID(SIId);
					}
				}

				try {
					if (SIId != null && SIId != "") {

						if (!ProjectSpecificMethods.lengthValidation(SIId)) {

							read.setCellData(sheetName, dataRowNo, totalColCount, props.getProperty("LENGTH_ERR_MSG"));
							Reporter.log(SIId + " is not in 12 digits. SI ID must be in 12 digits");
							logger.info(SIId + " is not in 12 digits. SI ID must be in 12 digits");
							ProjectSpecificMethods
									.writeLogIntoTxtFile(SIId + " is not in 12 digits. SI ID must be in 12 digits");

//							for (int j = 0; j <= totalColCount; j++) {
//								switch (j) {
//								case 0:
//									write.setCellData(sheetName, dataRowNo, j, closedDate);
//									break;
//								case 1:
//									write.setCellData(sheetName, dataRowNo, j, SIId);
//									break;
//								case 2:
//									write.setCellData(sheetName, dataRowNo, j, transID);
//									break;
//								}
//							}
							continue;
						}
						SatisfactionPage satfnPage = new SatisfactionPage(driver);
						satfnPage.setSecurityInterestID(SIId);

						satfnPage.clickProceedButton();

						if (satfnPage.errorMsgIsDisplayed()) {
							satfnPage.clickCloseButtonInErrorMsg();

							read.setCellData(sheetName, dataRowNo, totalColCount, props.getProperty("SATISFIED"));
							Reporter.log(SIId + " is already satisfied");
							logger.info(SIId + " is already satisfied");
							ProjectSpecificMethods.writeLogIntoTxtFile(SIId + " is already satisfied");

//							for (int j = 0; j <= totalColCount; j++) {
//								switch (j) {
//								case 0:
//									write.setCellData(sheetName, dataRowNo, j, closedDate);
//									break;
//								case 1:
//									write.setCellData(sheetName, dataRowNo, j, SIId);
//									break;
//								case 2:
//									write.setCellData(sheetName, dataRowNo, j, props.getProperty("SATISFIED"));
//									break;
//								}
//							}
							continue;
						}

						SIDetailsPage detailsPage = new SIDetailsPage(driver);
						detailsPage.setDate(closedDate);

						Thread.sleep(1000);
//						detailsPage.selectReasonUseKeyboard();
						detailsPage.selectReason(props.getProperty("REASON"));
						Thread.sleep(1000);

						if (ProjectSpecificMethods.daysCount(closedDate) >=29) {
							if (detailsPage.reasonForDelayIsDisplayed()) {
								detailsPage.setReason(props.getProperty("REASON"));
							}
						}

						detailsPage.clickSubmitButton();
						SatisfactionSuccessPage successPage = new SatisfactionSuccessPage(driver);
						transID = successPage.getTransactionID();

						// enters the Transaction ID in log
						Reporter.log(SIId + " is satisfied with the Transaction ID " + transID);
						logger.info(SIId + " is satisfied with the Transaction ID " + transID);
						// Enters the transaction ID into the Excel sheet
						read.setCellData(sheetName, dataRowNo, totalColCount, transID);
						ProjectSpecificMethods
								.writeLogIntoTxtFile(SIId + " is satisfied with the Transaction ID " + transID);

//						for (int j = 0; j <= totalColCount; j++) {
//							switch (j) {
//							case 0:
//								write.setCellData(sheetName, dataRowNo, j, closedDate);
//								break;
//							case 1:
//								write.setCellData(sheetName, dataRowNo, j, SIId);
//								break;
//							case 2:
//								write.setCellData(sheetName, dataRowNo, j, transID);
//								break;
//							}
//						}
						successPage.clickBackButton();

					} else if (SIId == null || SIId == "") {

						read.setCellData(sheetName, dataRowNo, totalColCount, props.getProperty("EMPTYMSG"));
						Reporter.log("Security Interest ID is Not valid or empty");
						logger.info("Security Interest ID is Not valid or empty");
						ProjectSpecificMethods.writeLogIntoTxtFile("Security Interest ID is Not valid or empty");

//						for (int j = 0; j <= totalColCount; j++) {
//							switch (j) {
//							case 0:
//								write.setCellData(sheetName, dataRowNo, j, closedDate);
//								break;
//							case 1:
//								write.setCellData(sheetName, dataRowNo, j, SIId);
//								break;
//							case 2:
//								write.setCellData(sheetName, dataRowNo, j, props.getProperty("EMPTYMSG"));
//								break;
//							}
//						}
						continue;
					}
				} catch (Exception ex) {
					ex.printStackTrace();

					read.setCellData(sheetName, dataRowNo, totalColCount, props.getProperty("UNHANDLED_ERR_MSG"));
					Reporter.log("Unhandled error message");
					ProjectSpecificMethods.writeLogIntoTxtFile(ex.getMessage());

//					for (int j = 0; j <= totalColCount; j++) {
//						switch (j) {
//						case 0:
//							write.setCellData(sheetName, dataRowNo, j, closedDate);
//							break;
//						case 1:
//							write.setCellData(sheetName, dataRowNo, j, SIId);
//							break;
//						case 2:
//							write.setCellData(sheetName, dataRowNo, j, props.getProperty("UNHANDLED_ERR_MSG"));
//							break;
//						}
//					}

					driver.navigate().refresh();
					Thread.sleep(2000);
					UserHomePage userHome = new UserHomePage(driver);
					userHome.clickHamburgerIcon();
					userHome.clickSatisfaction();
					continue;
				}

			}

		}

	}
	
	@AfterClass
	public void userLogOut() {
		HomePage homePage = new HomePage(driver);
		homePage.clickUserProfile();
		homePage.clickUserLogoutBtn();
	}
}
