package com.dealwallet.webdriver.selenium.Deals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dealwallet.webdriver.selenium.Common.Util;

public class DealsofBng
{
	Util u=new Util();
	String durl;
	WebDriver d;
	@BeforeMethod
	public void start()
	{
		durl=u.getdealwalletUrl();
		d=new FirefoxDriver();
	}
	@AfterMethod
	public void close()
	{
		d.close();
		d.quit();
	}
	@Test
	public void testBngDeals()
	{
		d.get(durl);
		d.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		d.manage().window().maximize();
		d.findElement(By.linkText("Deals")).click();
		d.findElement(By.xpath("//div[2]/div/a")).click();
		d.findElement(By.xpath("//p[2]/a")).click();
		for(int i=1;i<=2;i++)
		{
			for(int j=3;j<=12;j++)
			{
				String DPrice=d.findElement(By.xpath("//div["+j+"]/div/div[3]/span")).getText();
				DPrice=DPrice.substring(1).replace(".0", "");
				System.out.println("dealwallet price"+DPrice);
				String pc=d.getWindowHandle();
				d.findElement(By.xpath("//div["+j+"]/div/div[3]/p/a/span/span")).click();
				for(String cc:d.getWindowHandles())
				{
					d.switchTo().window(cc);
				}
				int a=d.findElements(By.xpath("//div[2]/div/div/div/div/strong")).size();
				String mprice = "";
				if(a==0)
				{
					int b=d.findElements(By.xpath("//figcaption/h2")).size();
					if(b!=0)
					{
						System.out.println("TIMEDEALS OFFER IS::"+d.findElement(By.xpath("//figcaption/h2")).getText());
//						d.close();
//						d.switchTo().window(pc);


					}
					else
					{
					mprice=d.findElement(By.xpath("//div[2]/div/div/div/span/nobr")).getText();
					}
				}
				else
				{
					mprice=d.findElement(By.xpath("//div[2]/div/div/div/div/strong")).getText();
				}
				mprice=mprice.replace("Rs", "").trim();
				System.out.println("merchant price"+mprice);
				if(DPrice.equals(mprice))
				{
					System.out.println("Both are equal");
				}
				else
				{
					System.out.println("both are not equal");
				}
				System.out.println("-------------------------------");
				d.close();
				d.switchTo().window(pc);

			}
			d.findElement(By.linkText("Next")).click();
		}
	}

}
