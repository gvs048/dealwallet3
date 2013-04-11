package com.dealwallet.webdriver.selenium.Deals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dealwallet.webdriver.selenium.Common.Util;

public class DealsBng
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
	public void testHydDeals()
	{
		d.get(durl);
		d.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		d.manage().window().maximize();

		for(int k=1;k<=10;k++)
		{
			d.findElement(By.linkText("Deals")).click();
			d.findElement(By.xpath("//div[2]/div/a")).click();
			System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			System.out.println("++++"+d.findElement(By.xpath("//p["+k+"]/a")).getText()+"+++++++++");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			d.findElement(By.xpath("//p["+k+"]/a")).click();
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
					int td=d.findElements(By.linkText("timesdeal Logo")).size();
					String mprice="";
					if(td==0)
					{
						int a=d.findElements(By.xpath("//div[2]/div/div/div/div/strong")).size();

						if(a==0)
						{
							mprice=d.findElement(By.xpath("//div[2]/div/div/div/span/nobr")).getText();
						}
						else
						{
							mprice=d.findElement(By.xpath("//div[2]/div/div/div/div/strong")).getText();
						}
						mprice=mprice.replace("Rs", "").trim();
					}
					else
					{
						int orgPrice=d.findElements(By.cssSelector("li.orgP")).size();
						if(orgPrice==0)
						{
							String tdtext=d.findElement(By.xpath("//figcaption/h2")).getText();
							System.out.println(tdtext);
							//System.out.println("both are not equal");
						}
						else
						{
							String orgP=d.findElement(By.cssSelector("li.orgP")).getText();
							String usaveP=d.findElement(By.cssSelector("li.usave")).getText();
							//System.out.println(orgP+"  and  "+usaveP);
							orgP=orgP.substring(15).replace(",", "");
							usaveP=usaveP.substring(9).replace(",", "");
							System.out.println(orgP+"  and  "+usaveP);
							int mp=Integer.parseInt(orgP) - Integer.parseInt(usaveP);
							mprice=String.valueOf(mp);
						}
					}
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

}
