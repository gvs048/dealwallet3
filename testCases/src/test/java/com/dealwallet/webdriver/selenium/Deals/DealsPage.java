package com.dealwallet.webdriver.selenium.Deals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.dealwallet.webdriver.selenium.Common.Util;

/**
 * this dealpage class compared the prices between dealwallet deals price and merchant deal price
 */
public class DealsPage
{
	WebDriver d;
	String dealwalletUrl;
	/**
	 * here gets the properties(dealwallet url,browser) from properties file
	 * util is class of common package it gets the properties from target folder
	 */
	@BeforeMethod
	public void start()
	{
//		Util util=new Util();
//		dealwalletUrl=util.getdealwalletUrl();
//		d=util.getbrowser();
		d= new FirefoxDriver();
	}
	/**
	 *in this test method we are comparing the prices between dealwallet deal,merchant deal site
	 */
	@Test
	public void testDealsPage()
	{
		d.get("http://www.dealwallet.com");
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(60,TimeUnit.MINUTES);
		d.findElement(By.linkText("Deals")).click();
		String dealwalletWindow=d.getWindowHandle();
		for(int i=1;i<3;i++)
		{
			for(int j=3;j<=12;j++)
			{
				d.findElement(By.xpath("//div["+j+"]/div/div/a/img")).click();
				String dealwalletTexttemp=d.findElement(By.xpath("//blockquote/span")).getText();
				String dealwalletText=dealwalletTexttemp.substring(1);
				double dealwalletPrice=Double.parseDouble(dealwalletText);
				d.findElement(By.xpath("//a/span/span")).click();
				for(String merchantWindow:d.getWindowHandles())
				{
					d.switchTo().window(merchantWindow);
				}
				String merchantTexttemp=d.findElement(By.xpath("//div[2]/div/div/div/span/nobr")).getText();
				String merchantText=merchantTexttemp.substring(3);
				double merchantPrice=Double.parseDouble(merchantText);
				if(dealwalletPrice==merchantPrice)
				{
				   System.out.println("same values");
				}
				else
				{
				   System.out.println("diff values");
				}
				d.close();
				d.switchTo().window(dealwalletWindow);
				d.navigate().back();
			}
			d.findElement(By.linkText("Next")).click();
		}
	}
	/**
	 * here we closed the browser using quit method
	 */
	@AfterMethod
	public void stop()
	{

		d.quit();
	}
}
