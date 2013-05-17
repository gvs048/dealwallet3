package com.dealwallet.webdriver.selenium;

import java.util.concurrent.TimeUnit;
//import static org.junit.Assert.*;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dealwallet.webdriver.selenium.Common.Util;

public class TravelWidget
{
	WebDriver d;
	@BeforeMethod
	public void before()
	{
		d=new FirefoxDriver();
	}
	@AfterMethod
	public void after()
	{
		d.quit();
	}
	@Test
	public void testOthers() throws InterruptedException
	{
		Util u=new Util();

		d.get(u.getdealwalletUrl());
		d.manage().window().maximize();
		//String pc=d.getWindowHandle();
		d.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		d.findElement(By.linkText("Travel Widget")).click();
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<4;j++)
			{
				for(int k=0;k<2;k++)
				{
					d.findElement(By.xpath("//input[@name='tab-group'])["+ i +"] ")).click();
					d.findElement(By.xpath("//form[@id='travel-widget']/div/div["+ j +"]/input ")).click();
					if(k==0)
					{
						d.findElement(By.id("optionsRadios")).click();
					}
					else
					{
						d.findElement(By.id("optionsRadios1")).click();
					}
					WebElement list = d.findElement(By.name("dw_origin"));
					Select s=new Select(list);
					s.selectByVisibleText("Hyderabad (HYD)");
					//dw_destination
					WebElement list1 = d.findElement(By.name("dw_destination"));
					Select s1=new Select(list1);
					s1.selectByVisibleText("Pune (PNQ)");
					d.findElement(By.name("flight_depart_date")).click();
					d.findElement(By.linkText("24")).click();
					WebElement list2 = d.findElement(By.name("ADT"));
					Select s2=new Select(list2);
					s2.selectByVisibleText("2");
					WebElement list3 = d.findElement(By.name("CHD"));
					Select s3=new Select(list3);
					s3.selectByVisibleText("2");
					WebElement list4 = d.findElement(By.name("INF"));
					Select s4=new Select(list4);
					s4.selectByVisibleText("2");
					WebElement list5 = d.findElement(By.name("class"));
					Select s5=new Select(list5);
					s5.selectByVisibleText("Business");
					d.findElement(By.cssSelector("input.btn.btn-warning")).click();
				}
			}
		}

	}

}