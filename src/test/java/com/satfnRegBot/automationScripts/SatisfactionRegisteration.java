package com.satfnRegBot.automationScripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
	 * After the browser invoked, it will login to the application with valid
	 * credentials
	 * 
	 * @throws InterruptedException
	 */
	@BeforeClass
	public void loginAsUser() throws InterruptedException {
		HomePage homePage = new HomePage(driver);
		homePage.clickLoginButton();
		Thread.sleep(1000);
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setLoginID(rb.getString("LOGIN_ID"));
		loginPage.setPassword(rb.getString("PASSWORD"));
		Thread.sleep(10000);
		loginPage.clickSubmitButton();
		while (loginPage.invalidCaptchaMsg()) {
			loginPage.clickGoToHomeLink();
			homePage.clickLoginButton();
			loginPage.setLoginID(rb.getString("LOGIN_ID"));
			loginPage.setPassword(rb.getString("PASSWORD"));
		}
	}

	/**
	 * After login, it will navigate to the SI Satisfaction Registration page
	 */
	@BeforeMethod
	public void satisfactionPage() {
		DigitalSignPage sign = new DigitalSignPage(driver);
		sign.clickInvokeDigitalSignButton();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		UserHomePage userHome = new UserHomePage(driver);
		userHome.clickHamburgerIcon();
		userHome.clickSatisfaction();
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
		ExcelUtility read = new ExcelUtility(FilePaths.DATASOURCE_SI_SATFN_REG);
		int totalRowCount = read.getRowCount(sheetName);
		int totalColCount = read.getCellCount(sheetName, totalRowCount);

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
					if (SIId!=null && SIId!="") {
						
						if(!ProjectSpecificMethods.lengthValidation(SIId)) {
							read.setCellData(sheetName, dataRowNo, totalColCount, rb.getString("LENGTH_ERR_MSG"));
							logger.info(SIId + " is not in 12 digits. SI ID must be in 12 digits");
							continue;
						}
						SatisfactionPage satfnPage = new SatisfactionPage(driver);
						satfnPage.setSecurityInterestID(SIId);
						satfnPage.clickProceedButton();
						if (satfnPage.errorMsgIsDisplayed()) {
							satfnPage.clickCloseButtonInErrorMsg();
							read.setCellData(sheetName, dataRowNo, totalColCount, rb.getString("SATISFIED"));
							logger.info(SIId + " is already satisfied");
							continue;
						}

						SIDetailsPage detailsPage = new SIDetailsPage(driver);
						detailsPage.setDate(closedDate);

						detailsPage.selectReasonUseKeyboard();

						if (detailsPage.reasonForDelayIsDisplayed()) {
							detailsPage.setReason(rb.getString("REASON"));
						}

						detailsPage.clickSubmitButton();
						SatisfactionSuccessPage successPage = new SatisfactionSuccessPage(driver);
						transID = successPage.getTransactionID();

						// enters the Transaction ID in log
						logger.info(SIId + " is satisfied with the Transaction ID " + transID);
						// Enters the transaction ID into the Excel sheet
						read.setCellData(sheetName, dataRowNo, totalColCount, transID);
						successPage.clickBackButton();

					} else if (SIId == null || SIId == "") {
						read.setCellData(sheetName, dataRowNo, totalColCount, rb.getString("SATISFIED"));
						logger.info(SIId + " is Not valid or empty");
						continue;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					read.setCellData(sheetName, dataRowNo, totalColCount, "");
					UserHomePage userHome = new UserHomePage(driver);
					userHome.clickHamburgerIcon();
					userHome.clickSatisfaction();
					continue;
				}

			}

		}
	}

}
