package com.dealwallet.webdriver.selenium05082013.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import com.dealwallet.webdriver.samples.SendEmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StoresFileAppComparision {

	WebDriver d;
	@BeforeTest
	public void startup()
	{
		d= new FirefoxDriver();
	}
	@Test
	public void test()throws Exception
	{
		SendEmail sm=new SendEmail();
		Connection conn=null;
		try
		{
			String res="";
			FileInputStream fi = new FileInputStream("C:\\Users\\Tsss-Pc1\\Desktop\\stores-in-DB.xls");
			Workbook w = Workbook.getWorkbook(fi);
			Sheet s = w.getSheet(0);

			d.get("http://www.dealwallet.com/stores");
			int size=d.findElements(By.className("store-item")).size();
			System.out.println(size);	
			List<WebElement> l =d.findElements(By.className("store-item"));
			//System.out.println("Store name is :: "+ss);



			for(int row=0; row <s.getRows();row++)
			{
				int i=0;
				//String id = s.getCell(0, row).getContents();
				String sname=s.getCell(1,row).getContents();
				for(WebElement we:l)
				{
					String ss = we.findElement(By.className("span6")).getText();
					if(ss.equalsIgnoreCase(sname))
					{
						//System.out.println("store is available in site :: "+ss);
						i+=1;
						break;
					}
				}
				if(i==0)
				{
					System.out.println("Store ::"+sname+" is not available in site..");
					res+="This stores is missed  :: "+"\n"+sname;
				}
			}
			if(res.equalsIgnoreCase(""))
			{
				res="All stores are there, no one is deleted";
				System.out.println(res);
			}
			else
				System.out.println(res);

			sm.sendingMail(res);
		}
		catch(Exception se)
		{
			System.out.println("Sql Exception"+se);
		}

		finally
		{
			try {
				if(conn != null)
					conn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
