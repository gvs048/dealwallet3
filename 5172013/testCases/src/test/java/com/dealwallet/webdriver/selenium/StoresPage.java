package com.dealwallet.webdriver.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dealwallet.webdriver.samples.SendEmail;

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
		SendEmail sm=new SendEmail();
		d.get("http://www.dealwallet.com/stores");
		d.findElement(By.linkText("Stores")).click();
		String pc=d.getWindowHandle();
		d.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		String sname;
		String status = null;
		for(int i=2;i<97;i++)
		{
			sname=d.findElement(By.xpath("//div["+i+"]/div/div[2]/p/a")).getText();
			d.findElement(By.xpath("//div["+i+"]/div/div[3]/p/a/span/span")).click();
			for(String cc1:d.getWindowHandles())
			{
				d.switchTo().window(cc1);
			}
			sname=sname.toLowerCase();
			sname=sname.replace(" ", "");
			if(d.getPageSource().toLowerCase().contains(sname)==true)
			{
				//System.out.println(sname+"::Site name matched");
				status+=sname+"::Site name matched"+"\n";
			}
			else
			{
				String[] sname1=sname.split(" ");
				if(d.getPageSource().toLowerCase().contains(sname1[0])==true)
				{
					//System.out.println(sname+"::Site name matched");
					status+=sname+"::Site name matched"+"\n";
				}
				else
				{
					//System.out.println(sname+"::Site name not matched");
					status+=sname+"::Site name not matched"+"\n";
				}
			}
			d.close();
			d.switchTo().window(pc);
			System.out.println(status);
		}
		sm.sendingMail(status);
	}
}
