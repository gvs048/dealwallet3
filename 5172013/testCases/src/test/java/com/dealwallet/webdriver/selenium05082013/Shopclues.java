package com.dealwallet.webdriver.selenium05082013;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dealwallet.webdriver.selenium.Common.Merchants;
public class Shopclues
{
	WebDriver d;
	@BeforeMethod
	public void before()
	{
		d=new FirefoxDriver();
	}
	@AfterMethod
	public  void after()
	{
		d.quit();
	}
	
	
	@Test
	public void testAandP() {
		//for shopclues.com
		
		/*d.get("http://www.shopclues.com/steel-made-panda.html");
		String s=d.findElement(By.xpath("//div[@class='float-left product-prices']")).getText();
		System.out.println(s);
		String[] str=s.split("Rs.");
		System.out.println("Actual Price is :: "+str[str.length-1]);*/
		
		//for surf2buy.in
		/*d.get("http://surf2buy.in/chargers/samsung-tab-detachable-travel-charger-eta-p10ibegstd");
		String s= d.findElement(By.cssSelector("div.price")).getText();
		System.out.println(s);
		String[] str=s.split(":");
		System.out.println(str[1]);
		
		str=str[1].split(" ");
		if(str.length>2)
		{
		System.out.println(str[2].replace(",", "").replace(".00", ""));
		}
		else
			System.out.println(str[1].replace(",", "").replace(".00", ""));*/
		
		
		//for http://www.hindbuy.com/ 
		/*d.get("http://www.hindbuy.com/Products/Mobiles--Accessories-Earphones-Philips/Philips/Philips-SHE-3590BK-Headphone-%28Black%29/pid-2440497.aspx");
		String s= d.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_Price_ctl00_lblOfferPrice")).getText();
		//System.out.println(s);
		s=s.replace("Rs.", "");
		System.out.println(s);*/
		
		
		
		
	}
	

}