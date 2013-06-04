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
		
		
		//for http://www.next.co.in/
		/*d.get("http://www.next.co.in/writing-instrument/fine-writing-instruments/gift-sets/sheaffer-sentinel-323-ball-pen-pencil.html");
		String s= d.findElement(By.cssSelector("span.price")).getText();
		s=s.replace("Rs", "").replace(",","").trim();
		System.out.println(s);*/
		
		
		//for http://www.grabmore.in/ 
		/*//http://www.grabmore.in/products/0899949001182/Eye-Fi-Pro-X2-8-GB-Class-6-SDHC-Wireless-Flash-Memory-Card-EYE-FI-8PC.html
		//http://www.grabmore.in/products/9625433/Samsung-Galaxy-Fit-S5670.html
		d.get("http://www.grabmore.in/products/9625433/Samsung-Galaxy-Fit-S5670.html");
		//d.get("http://www.grabmore.in/products/0899949001182/Eye-Fi-Pro-X2-8-GB-Class-6-SDHC-Wireless-Flash-Memory-Card-EYE-FI-8PC.html");
		String s= d.findElement(By.xpath("//div[@class='price_contain']")).getText();
		System.out.println(s);
		String[] items=s.replace(",", "").replace("\n", " ").split(" ");
		System.out.println(items[1]);*/
		
		
		//https://www.clik2pick.com/ 
		/*//https://www.clik2pick.com/mens-wear/t-shirts/orange-color-polo-t-shirt
		//https://www.clik2pick.com/womens-wear/sarees/elegant-georgette-sarees-blue
		d.get("https://www.clik2pick.com/womens-wear/sarees/elegant-georgette-sarees-blue");
		String s=d.findElement(By.xpath("//div[@class='col2 price']")).getText();
		System.out.println(s);
		s=s.replace("Rs.", "").replace(".00", "").replace(",", "");
		System.out.println(s);
		*/
		
		
		//http://www.fabmart.com/ 
		/*//http://www.fabmart.com/products/black-decker-pw1300c-110bar-pressure-washer
		//http://www.fabmart.com/products/beurer-fb14-massager
		d.get("http://www.fabmart.com/products/black-decker-pw1300c-110bar-pressure-washer");
		String s= d.findElement(By.xpath("//span[2]/span")).getText();
		//System.out.println(s);
		s=s.replace(" - Sold Out", "");
		System.out.println(s);*/
		
		
		//http://www.ezmaal.com/
		/*//http://www.ezmaal.com/micromax-smarty-43-a65-cellphone/p-88507
		//http://www.ezmaal.com/jaipan-jumbo-roti-maker/p-12508
		//http://www.ezmaal.com/sony-vaio-e11115-laptop/p-77765
		d.get("http://www.ezmaal.com/sony-vaio-e11115-laptop/p-77765");
		String s= d.findElement(By.cssSelector("p.price > span")).getText();
		System.out.println(s);
		s=s.replace("Rs.", "").replace(",", "");
		System.out.println(s);*/
		
		
		//http://www.smartoye.com/ 
		/*d.get("http://www.smartoye.com/smart-tv/led/samsung-26eh4000-led-26-inches-hd-television");
		String s=d.findElement(By.cssSelector("div.right > div.price")).getText();
		System.out.println(s);
		s=s.substring(s.lastIndexOf("RS."), s.lastIndexOf(".00")).replace(",", "").replace("RS.", "");
		System.out.println(s);*/
		
		
		//http://www.edigiworld.com
		/*d.get("http://www.edigiworld.com/laptops-tablets/laptops/dell-inspiron15-laptop-with-dos-15-inch-wled.html");
		String s= d.findElement(By.xpath("//p/span")).getText();
		System.out.println(s);
		s=s.replace("Rs", "").replace(",", "").trim();
		System.out.println(s);*/
		
		
		//http://www.ezmaal.com/mobile/c-113
		/*d.get("http://www.ezmaal.com/mobile/c-113");
		String s=d.findElement(By.xpath("//li/a[2]")).getAttribute("href");
		System.out.println(s);*/
		
		
		//http://www.edabba.com/content/bajaj-dx5-dry-iron-wcoat-1000w
		//d.get("http://www.edabba.com/content/bajaj-dx5-dry-iron-wcoat-1000w");
		//d.get("http://www.edabba.com/content/wespro-dual-sim-qwerty-mobile-q6000");
		/*String s=d.findElement(By.xpath("//div[@class='columright']")).getText();
		System.out.println(s);
		s=s.substring(s.indexOf("Rs."),s.lastIndexOf("Rs.") );
		System.out.println("@@@@@ "+s);*/
		/*String[] items=s.split("Rs.");
		System.out.println("0 item "+items[0]);
		System.out.println("1 item "+items[1]);
		System.out.println("2 item "+items[2]);
		System.out.println("3 item "+items[3]);
		System.out.println("lenght"+items.length);*/
		/*int s= d.findElements(By.xpath("//div[@class='price ']")).size();
		if(s==0)
		{
			String price=d.findElement(By.xpath("//div[2]/div[4]/span")).getText();
			System.out.println(price);
			price=price.replace("Rs.", "");
			System.out.println(price);
		}
		else
		{
			String price=d.findElement(By.xpath("//div[@class='price ']")).getText();
			System.out.println(price);
			price=price.replace("Rs.", "");
			System.out.println(price);
			
		}*/
		//System.out.println(s);
		
		
		//mirchimart
		
		/*d.get("http://www.mirchimart.com/DELL-Inspiron-660S-Core-i5-3330s-3rd-Gen--4GB--1TB--185--Win7HB-Desktop/Desktops/p/4L4SD2IO1Y804UBAN");
		String s=d.findElement(By.cssSelector("span.red16")).getText();
		
		System.out.println(s);
		s=s.replace("Rs.", "").trim();
		System.out.println(s);*/

		
		//#ctl00_ContentPlaceHolder1_Price_ctl00_lblOfferPrice
		
		
		//ezoneonline.in
		/*d.get("http://www.ezoneonline.in/Products/Ezone-Cameras-Digital-SLR-Cameras-DSLRs-Body-Plus-Lens/Sony/Sony-SLT-A58K---Black/pid-2876255.aspx&pgctl=598018&cid=CU00040997");
		String s=d.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_Price_ctl00_lblOfferPrice")).getText();
		System.out.println(s);
		s=s.replace("Rs.","").replace(",", "").trim();
		System.out.println(s);*/
		
		//#ContentPlaceHolder1_lblRate
		//http://www.ebikree.com/Products/3153/
		/*d.get("http://www.ebikree.com/Products/3153/");
		String s=d.findElement(By.cssSelector("#ContentPlaceHolder1_lblRate")).getText();
		System.out.println(s);
		s=s.replace("INR", "").replace(".00", "");
		System.out.println(s);*/
	}
	

}