package com.dealwallet.webdriver.selenium05082013;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import jxl.*; 
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Dealwallet1 {

	WebDriver d;
	@BeforeMethod
	public void startup()
	{
		//d=new FirefoxDriver();
	}
	@AfterMethod
	public void stop()
	{
		//d.quit();
	}
	
	/*@Test
	public void testReading() throws InterruptedException, BiffException, IOException, WriteException
	{
		Workbook workbook = Workbook.getWorkbook(new File("C:\\Users\\Tsss-Pc1\\Desktop\\login.xls"));
		Sheet sheet = workbook.getSheet(0); 
		Cell a1 = sheet.getCell(0,0);
		Cell b2 = sheet.getCell(1,0); 
		String stringa1 = a1.getContents();
		String stringb2 = b2.getContents(); 
		System.out.println(stringa1);
		System.out.println(stringb2);
		
			
	}
*/
	/*@Test
	public void testWriting() 
	{
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File("C:\\Users\\Tsss-Pc1\\Desktop\\login1.xls"));
			WritableSheet sheet = workbook.createSheet("First Sheet", 0); 
			Label label = new Label(2,0, "pass");
			sheet.addCell(label);
			workbook.write();
			workbook.close();

		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void testReading() throws InterruptedException, BiffException, IOException, WriteException
	{
		File file=new File("C:\\Users\\Tsss-Pc1\\Desktop\\login.xls");
		//WritableWorkbook workbook = Workbook.getWorkbook("C:\\Users\\Tsss-Pc1\\Desktop\\login.xls");
		Workbook workbook = Workbook.getWorkbook(file);
		WritableWorkbook copy = Workbook.createWorkbook(new File("C:\\Users\\Tsss-Pc1\\Desktop\\login1.xls"), workbook);
		WritableSheet sheet2 = copy.getSheet(0);
		WritableCell cell = sheet2.getWritableCell(1, 0);

		if (cell.getType() == CellType.LABEL)
		{
		  Label l = (Label) cell;
		  l.setString("modified cell");
		} 
		Label label = new Label(2, 0, "New label record");
		sheet2.addCell(label);
		copy.write();
		copy.close();
			
	}
	
}
