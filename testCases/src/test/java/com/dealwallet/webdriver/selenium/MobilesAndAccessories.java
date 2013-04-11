package com.dealwallet.webdriver.selenium;

import java.util.concurrent.TimeUnit;
//import static org.junit.Assert.*;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MobilesAndAccessories
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
	public void testCameras() throws InterruptedException
	{
		d.get("http://dealwallet.com/");
		d.manage().window().maximize();
		String pc=d.getWindowHandle();
		d.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		d.findElement(By.linkText("Mobiles & Accessories")).click();
		for(int i=1;i<=3;i++)
		{
			for(int k=1;k<=10;k++)
			{
				String merchant_price ="";
				String dealwallet_price=d.findElement(By.xpath("//div["+k+"]/div/div[3]/span")).getText();
				String temp1 = dealwallet_price.substring(1);
				dealwallet_price=temp1.replace(".0", "");

				d.findElement(By.xpath("//div["+k+"]/div/div[3]/p/a/span/span")).click();
				for(String cc1:d.getWindowHandles())
				{
					d.switchTo().window(cc1);
					}
				int isPresent = d.findElements(By.id("hs18Price")).size();
				if(isPresent==0)
				{
					isPresent = d.findElements(By.cssSelector("span.price")).size();
					if(isPresent==0)
					{
					System.out.println("The Item Selected Is Not In The Merchants Site");
					}
					else
					{
					merchant_price = d.findElement(By.cssSelector("span.price")).getText();
					temp1=merchant_price.substring(3).trim();
					temp1=temp1.replace(",", "");
					merchant_price=temp1.replace("*", "");
					}
				}
				else
				{
				merchant_price = d.findElement(By.id("hs18Price")).getText();
				temp1=merchant_price.substring(1).trim();
				temp1=temp1.replace(",", "");
				merchant_price=temp1.replace("*", "");
				}

				if(dealwallet_price.equals(merchant_price))
				{
					System.out.println(k+"image both price are same");
		     	}
				else
				{
					System.out.println("page no::"+i+" and "+k+"image price are NOT same");
					System.out.println("our::"+dealwallet_price+" & merchent::"+merchant_price);
				}

				d.close();
				System.out.println("++++++++++++++++++++++");
				d.switchTo().window(pc);
				}
			System.out.println(i+" page is completed");
			//Thread.sleep(9000);
			d.findElement(By.linkText("Next")).click();
			Thread.sleep(9000);
			}
	}

}