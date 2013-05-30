package com.dealwallet.webdriver.selenium.scraping;

import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Naaptol
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
	public void testnaaptol()
	{
		/*d.get("http://www.naaptol.com/buy/mobile_phones.html");
		d.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		Document doc = Jsoup.parse(d.getPageSource());
		Elements body = doc.select("body").select("li");
		System.out.println(body);*/

		d.get("http://www.smartoye.com/smart-mobiles/blackberry-phones");
		d.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		Document doc = Jsoup.parse(d.getPageSource());
		Elements body = doc.select("body").select("li");
		//doc.select("body").select("div.catSearchDiv").select("div.subMenuList").select("li")
		System.out.println(body);
	}
}
