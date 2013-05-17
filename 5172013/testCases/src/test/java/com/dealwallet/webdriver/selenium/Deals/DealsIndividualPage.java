package com.dealwallet.webdriver.selenium.Deals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//import com.dealwallet.webdriver.selenium.common.Util;

public class DealsIndividualPage<A>
{
	WebDriver d;
	String dealwalletUrl;
	WebDriver browser;
	@BeforeMethod
	public void start()
	{
//		Util util=new Util();
//		dealwalletUrl=util.getdealwalletUrl();
//		d=util.getbrowser();
		d= new FirefoxDriver();
	}
	@Test
	public void testDeals()
	{
		//d.get(dealwalletUrl);
		d.get("http://www.dealwallet.com");
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(60,TimeUnit.MINUTES);
		d.findElement(By.linkText("Deals")).click();
		d.findElement(By.linkText("hyderabad (Change City)")).click();
		d.findElement(By.xpath("//p[2]/a")).click();
		String dealwalletWindow=d.getWindowHandle();
		for(int i=1;i<=3;i++)
		{
			for(int j=3;j<=12;j++)
			{
				d.findElement(By.xpath("//div["+j+"]/div/div/a/img")).click();
				String a1=d.findElement(By.xpath("//blockquote/span")).getText();
				String a2=new String(a1);
				String a3=a2.substring(1);
				double a=Double.parseDouble(a3);
				String href=d.findElement(By.xpath("//p/a")).getAttribute("href");
				String merchant=href.substring(href.length() - 3);
				String merchantid=merchant.replace("/","");
				int x=Integer.parseInt(merchantid);
				d.findElement(By.xpath("//a/span/span")).click();
				for(String merchantWindow:d.getWindowHandles())
				{
					d.switchTo().window(merchantWindow);
				}
				if(x==66)
				{
					String b1=d.findElement(By.xpath("//div[2]/div/div/div/span/nobr")).getText();
					String b2=new String(b1);
					String b3=b2.substring(3);
					double b=Double.parseDouble(b3);
					if(a==b)
					{
					   System.out.println("same values");
					}
					else
					{
					   System.out.println("diff values");
					}
				}
				else
				{
					System.out.println("no data available in this merchant:"+x);
				}
				d.close();
				d.switchTo().window(dealwalletWindow);
				d.navigate().back();
				}


			d.findElement(By.linkText("Next")).click();
		}
	}
	@AfterMethod
	public void stop()
	{
		d.quit();
	}
}
