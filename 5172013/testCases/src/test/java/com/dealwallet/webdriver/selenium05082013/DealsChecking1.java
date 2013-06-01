package com.dealwallet.webdriver.selenium05082013;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DealsChecking1 {

	WebDriver d;
	@BeforeMethod
	public void startup()
	{
		d=new FirefoxDriver();
	}
	@AfterMethod
	public void endup()
	{
		d.quit();
	}
	@Test
	public void testDeal() throws InterruptedException
	{
		d.get("http://www.dealwallet.com/");
		d.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
		String pc= d.getWindowHandle();
		d.findElement(By.linkText("Deals")).click();
		//String text="";
		for(int j=0;j<3;j++)
		{
			for(int i=3;i<13;i++)
			{
				d.findElement(By.xpath("//div["+i+"]/div/div[3]/p/a/span/span")).click();
				for(String cc1:d.getWindowHandles())
				{
					d.switchTo().window(cc1);
				}
				/*if(d.findElement(By.xpath("//div[@id='content_wrapper']/div/div/div/div[2]/div/div/div[5]")).getText().equals("OOPS YOU ARE LATE"))
					System.out.println("Deal Expired in page no:"+(j+1)+" and item no:"+(i-2));*/
				Thread.sleep(6000);
				String s=d.getPageSource();
				if(s.contains("OOPS YOU ARE LATE"))
				{
					System.out.println("Deal Expired in page no:"+(j+1)+" and item no:"+(i-2));
				}
				//System.out.println(s);
				d.close();
				d.switchTo().window(pc);
			}
			d.findElement(By.linkText("Next")).click();
		}
	}
}
