package com.dealwallet.webdriver.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class StoresPage
{
	WebDriver d;
	@BeforeMethod
	public void start()
	{
		d=new FirefoxDriver();

	}
	@AfterMethod
	public void close()
	{
		d.quit();
	}
	@Test
	public void testStorePage()
	{
		d.get("http://localhost:8080");
		d.findElement(By.linkText("Stores")).click();
		String pc=d.getWindowHandle();
		d.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		String sname,url;
		for(int i=2;i<97;i++)
		{
			sname=d.findElement(By.xpath("//div["+i+"]/div/div[2]/p/a")).getText();
			d.findElement(By.xpath("//div["+i+"]/div/div[3]/p/a/span/span")).click();
			for(String cc1:d.getWindowHandles())
			{
				d.switchTo().window(cc1);
			}
			//boolean b= d.getPageSource().contains(sname);
			//System.out.println(b);
			//url=d.getCurrentUrl();
			//url.contains(sname)
			sname=sname.toLowerCase();
			sname=sname.replace(" ", "");
			if(d.getPageSource().toLowerCase().contains(sname)==true)
			{
				System.out.println(sname+"::Site name matched");

			}
			else
			{
				String[] sname1=sname.split(" ");
				if(d.getPageSource().toLowerCase().contains(sname1[0])==true)
				{
					System.out.println(sname+"::Site name matched");

				}
				else
				{
					System.out.println(sname+"::Site name not matched");
				}
			}
			d.close();
			d.switchTo().window(pc);
		}

	}
}
