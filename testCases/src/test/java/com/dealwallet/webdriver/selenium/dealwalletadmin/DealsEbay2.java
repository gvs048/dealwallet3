package com.dealwallet.webdriver.selenium.dealwalletadmin;

//import java.util.List;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
//import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import org.w3c.dom.Document;

public class DealsEbay2
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
		d.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		getCategories();
	}

	public void getCategories()
	{
		Document doc = Jsoup.parse(d.getPageSource());
		Elements body = doc.select("body").select("div.container1.containerCatH");
		Elements catagories=body.select("li");
		for(Element cat:catagories)
		{
			System.out.println(cat.text());
			d.findElement(By.xpath("//a[contains(text(),'"+cat.text()+"')]")).click();
			//Thread.sleep(20000);
			getProducts();
			d.navigate().back();
		}
	}

	public void getProducts()
	{
		Document doc = Jsoup.parse(d.getPageSource());
		Elements body = doc.select("body").select("table");
		System.out.println(body);
		String pname="";
		String eprice="";
		String img="";
		for(Element pDetails:body)
		{
			pname=pDetails.select("div.mdt").text();
			System.out.println(pname);
			eprice=pDetails.select("div.priceDiv.discTop").text();
			System.out.println(eprice);
			img=pDetails.select("div").select("img").attr("data-original");
			System.out.println(img);
		}



		/*Elements etitle= body.select("div.mdt");
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
		}*/



	}

}

