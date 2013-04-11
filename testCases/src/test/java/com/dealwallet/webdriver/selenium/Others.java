package com.dealwallet.webdriver.selenium;

import java.util.concurrent.TimeUnit;
//import static org.junit.Assert.*;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Others
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
		d.get("http://dealwallet.com/");
		d.manage().window().maximize();
		String pc=d.getWindowHandle();
		d.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		d.findElement(By.linkText("Others")).click();
		for(int i=1;i<=2;i++)
		{
			for(int k=1;k<=10;k++)
			{
				String merchant_price="";
				String dealwallet_price=d.findElement(By.xpath("//div["+k+"]/div/div[3]/span")).getText();
				String temp1 = dealwallet_price.substring(1);
				dealwallet_price=temp1.replace(".0", "");

				d.findElement(By.xpath("//div["+k+"]/div/div[3]/p/a/span/span")).click();
				for(String cc1:d.getWindowHandles())
				{
					d.switchTo().window(cc1);
				}
				int isPresent = d.findElements(By.xpath("//tr[2]/td[2]")).size();
				if(isPresent==0)
				{
					isPresent = d.findElements(By.xpath("//td[2]")).size();
					if(isPresent==0)
					{
						System.out.println("The Item Selected Is Not In The Merchants Site");
					}
					else
					{
						merchant_price = d.findElement(By.xpath("//td[2]")).getText();
					}
				}
				else
				{
					//String s=d.findElement(By.xpath("//div[@id='product_desc']/table/tbody/tr[2]/td[2]/span[2]")).size();
					int isPresent1 = d.findElements(By.xpath("//div[@id='product_desc']/table/tbody/tr[2]/td[2]/span[2]")).size();
					if(isPresent1!=0)
					{
						String s = d.findElement(By.xpath("//div[@id='product_desc']/table/tbody/tr[2]/td[2]/span[2]")).getText();
						merchant_price=d.findElement(By.xpath("//tr[2]/td[2]")).getText();
						merchant_price=merchant_price.replace(s, "");
					}
					else
					{
						merchant_price = d.findElement(By.xpath("//tr[2]/td[2]")).getText();
					}
				}
				temp1=merchant_price.substring(3).trim();
				temp1=temp1.replace(",", "");
				merchant_price=temp1.replace("*", "");
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