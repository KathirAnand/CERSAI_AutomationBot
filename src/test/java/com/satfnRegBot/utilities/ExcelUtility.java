package com.satfnRegBot.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//import com.satfnRegBot.testBase.FilePaths;
public class ExcelUtility {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style; 
	public String path;
	
	
	public ExcelUtility(String filePath) {
		this.path=filePath;
	}
	
	/**
	 * to get the number of rows in the sheet
	 * 
	 * 
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	public int getRowCount(String sheetName) throws Exception {
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
//		sheet=workbook.getSheetAt(0);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}
	
	/**
	 * to get the number of column count in the sheet
	 * 
	 * 
	 * @param sheetName
	 * @param rownum
	 * @return
	 * @throws IOException
	 */
	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	/**
	 * to get the data from the particular cell as String by Using the DataFormatter
	 * 
	 * 
	 * @param sheetName
	 * @param rownum
	 * @param colnum
	 * @return
	 * @throws IOException
	 */
	public String getCellData(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try{
		data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
		}
		catch(Exception e)
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
	/**
	 * 
	 * @param sheetName
	 * @param rownum
	 * @param colnum
	 * @param data
	 * @throws IOException
	 */
	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())    // If file not exists then create new file
		{
		workbook=new XSSFWorkbook();
		fo=new FileOutputStream(path);
		workbook.write(fo);
		}
				
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
			
		if(workbook.getSheetIndex(sheetName)==-1) {
			workbook.createSheet(sheetName);
			// If sheet not exists then create new Sheet
		}
		
		sheet=workbook.getSheet(sheetName);
		
		if(sheet.getRow(rownum)==null) {
			sheet.createRow(rownum);
			// If row not exists then create new Row
		}
	
		row=sheet.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);		
		workbook.close();
		fi.close();
		fo.close();
	}
	
//	public static void setCellValue(String firstName,String lastName) throws IOException {
//		FileWriter writer = new FileWriter(FilePaths.SIGNIN_DATAS);
//		writer.append("FirsName,LastName,OwnerFirstName,OwnerLastName,EamilId,Password,PhoneNumber \n");
//		writer.append("Sabari,Vasan,Jocker,Saba,jocker@gmail.com,test1234,8879876987 \n");
//		writer.append("gopi,Vasan,Jocker,gops,jocker@gmail.com,test1234,8879876987");
//		writer.append(firstName+","+lastName);
//		
//		writer.close();
//		System.out.println("File Created at"+FilePaths.SIGNIN_DATAS);
//		
//	}
	
	public void setCellData1(String sheetName,int rownum,int colnum,String data) throws IOException
	{
		File xlfile=new File(path);
		if(!xlfile.exists())    // If file not exists then create new file
		{
		workbook=new XSSFWorkbook();
		fo=new FileOutputStream(path);
		workbook.write(fo);
		}
				
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
			
		if(workbook.getSheetIndex(sheetName)==-1) {
			workbook.createSheet(sheetName);
			// If sheet not exists then create new Sheet
		}
		
		sheet=workbook.getSheet(sheetName);
		
		if(sheet.getRow(rownum)==null) {
			sheet.createRow(rownum);
			// If row not exists then create new Row
		}
	
		row=sheet.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);		
		workbook.close();
		fi.close();
		fo.close();
	}
}
