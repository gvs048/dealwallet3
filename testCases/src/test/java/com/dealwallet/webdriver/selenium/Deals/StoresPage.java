package com.dealwallet.webdriver.selenium.Deals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



/**
 * this class comparing merchant id's here
 * one is get from href tag using getattribute method
 * 2nd is after clicking the image then it takes the url with merchant id using get currentUrl method
 *check all shop now are working or not
 */

public class StoresPage
{
	WebDriver d;
	String dealwalletUrl;
	/**
	 *util is a common package class it gets the properties from taget folder
	 *browser is starting here,it gets the browser from properties file
	 *
	 */
	@BeforeMethod
	public void start()
	{
//		Util util=new Util();
//		dealwalletUrl=util.getdealwalletUrl();
//		d=util.getbrowser();
		d=new FirefoxDriver();
	}
	/**
	 *in this test class we compare merchantid and checking the shopnow button is redirect merchant or not
	 */
	@Test
	public void testStoresPage() throws Exception
	{
		d.get("http://localhost:8080");
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(60,TimeUnit.MINUTES);
		d.findElement(By.linkText("Stores")).click();
		String storesWindow=d.getWindowHandle();
		for(int i=2;i<=100;i++)
		{
			d.findElement(By.xpath("//div["+i+"]/div/div/a/img")).click();
			String href=d.findElement(By.xpath("//p/a")).getAttribute("href");
			String hrefmercahntid=href.substring(href.length() - 3);
			String merchant=hrefmercahntid.replace("/","");
			String merchantId=merchant.replace("e","");
			String urlid=d.getCurrentUrl();
			String imagemerchant=urlid.substring(urlid.length() - 3);
			String imgmerchant=imagemerchant.replace("-","");
			String merchantid=imgmerchant.replace("t","");
			if(merchantId.equals(merchantid))
			{
				System.out.println("same merchants");
			}
			else
			{
				System.out.println("wrong merchants");
			}
			d.findElement(By.xpath("//span/span")).click();
			for(String merchantWindow:d.getWindowHandles())
			{
				d.switchTo().window(merchantWindow);
			}
			Thread.sleep(1000);
			d.close();
			d.switchTo().window(storesWindow);
			d.navigate().back();
		}
	}
	/**
	 * here we are writing browser close code
	 */
	@AfterMethod
	public void stop()
	{
		d.quit();
	}
}
