package com.dealwallet.webdriver.selenium.Samles;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;

public class ReadinDataFromXL
{
	Sheet s;
	WebDriver driver;
	@BeforeMethod
	public void setUp()
	{
		driver = new FirefoxDriver();
	}
	@Test
	public void searchGoogle() throws Exception
	{
		for(int row=1; row <=2;row++)
		{
			driver.get("http://www.gmail.com");
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			FileInputStream fi = new FileInputStream("/home/tsssinfotech/Desktop/login.ods");
			Workbook w = Workbook.getWorkbook(fi);
			s = w.getSheet(0);
			String username = s.getCell(0, row).getContents();
			System.out.println("Username "+username);

			driver.findElement(By.name("Email")).sendKeys(username);
			String password= s.getCell(1, row).getContents();
			System.out.println("Password "+password);
			driver.findElement(By.name("Passwd")).sendKeys(password);
			driver.findElement(By.name("signIn")).click();
		}
	}
	@AfterTest
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}
}
