package com.dealwallet.webdriver.selenium.dealwalletadmin;

//import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import org.w3c.dom.Document;

public class DealsEbay
{
	WebDriver d;
	@BeforeMethod
	public void startup()
	{
		d=new FirefoxDriver();
	}
	@AfterMethod
	public void stop()
	{
		d.quit();
	}
	@Test
	public void testEbay()
	{
		d.get("http://deals.ebay.in");
		d.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Document doc = Jsoup.parse(d.getPageSource());
		//return doc.text();
		String s=doc.text();
		System.out.println(s);
		String [] items=s.split("See more >>");
		for(int i=0;i<items.length-1;i++)
		{
			String s1= items[i].toString();
			String [] cat=s1.split("next");
			int len=cat.length;
			if (len==1)
			{
				//System.out.println("0 position"+cat[0]);
				String [] text= cat[0].split(" ");
				System.out.println(text[55].toString());
			}
			else
			{
				//System.out.println("1 position"+cat[1]);
				String [] text= cat[1].split(" ");
				String fs="";
				if(text.length==4)
				{
					System.out.println(text[1].toString());
				}
				else
				{
					for(int j=1;j<text.length-2;j++)
					{

						fs=fs+" "+text[j].toString();

					}
					fs=fs.substring(1);
					System.out.println(fs);
				}
			}
		}
		//System.out.println(getPageContent());
	}


	public void getPageContent()
	{
		//Document doc = Jsoup.parse(d.getPageSource());
		//return doc.text();
		//String s=doc.text();
	}
	public void getCategories()
	{

	}
}

