package com.dealwallet.webdriver.selenium.dealwalletadmin;

//import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import org.w3c.dom.Document;

public class DealsEbay1
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
	//Document doc = Jsoup.parse(d.getPageSource());
	@Test
	public void testEbay() throws InterruptedException
	{
		d.get("http://deals.ebay.in");
		d.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		String gc=getCategories();
		System.out.println(gc);
		String[] categoriesList=gc.split(",");
		for(String cl:categoriesList)
		{
			System.out.println("Displaying products for:::"+cl);
			d.findElement(By.xpath("//a[contains(text(),'"+cl+"')]")).click();
			//Thread.sleep(20000);
			getProducts();
			d.navigate().back();
		}
	}


	public String getPageContent()
	{
		Document doc = Jsoup.parse(d.getPageSource());
		return doc.text();
	}
	public String getCategories()
	{
		String s=getPageContent();
		String [] items=s.split("See more >>");
		String categories="";
		for(int i=0;i<items.length-1;i++)
		{
			String s1= items[i].toString();
			String [] cat=s1.split("next");
			int len=cat.length;
			if (len==1)
			{
				String [] text= cat[0].split(" ");
				categories+=","+text[55].toString();
			}
			else
			{
				String [] text= cat[1].split(" ");
				String fs="";
				if(text.length==4)
				{
					categories+=","+text[1].toString();
				}
				else
				{
					for(int j=1;j<text.length-2;j++)
					{
						fs=fs+" "+text[j].toString();
					}
					categories+=","+fs.substring(1);
				}
			}
		}
		categories=categories.substring(1);
		return categories;
	}

	public void getProducts()
	{
		Document doc = Jsoup.parse(d.getPageSource());

		Elements body = doc.select("body").select("table");
		//System.out.println(body);
		Elements etitle= body.select("div.mdt");
		for(Element ele:etitle)
		{
			System.out.println(ele.text());
		}
		Elements eprice=body.select("div.priceDiv.discTop");
		for(Element ele:eprice)
		{
			System.out.println(ele.text());
		}
		Elements eimg=body.select("div").select("img");
		for(Element ele:eimg)
		{
			System.out.println(ele.attr("data-original"));
		}
		/*Elements cat=body.tagName("tr");
		System.out.println("+++ poducts are +++"+cat);*/
	}

}

