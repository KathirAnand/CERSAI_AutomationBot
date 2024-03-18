package com.satfnRegBot.testData;

import com.satfnRegBot.testBase.FilePaths;
import com.satfnRegBot.utilities.TestDataProvider;

public class SatisfactionExcelData {
	
	String sheetName = "Sheet1";
	Object [][] data;
	public Object[][] getExcelData(){
		try {
			data = TestDataProvider.getTestData(FilePaths.DATASOURCE_SI_SATFN_REG, sheetName);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return data;
	}
}
