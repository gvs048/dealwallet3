package com.dealwallet.webdriver.selenium.Common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * This class is having method for all merchants to identify price and compare the price with DealWallet
 * @author Tsss-Pc1
 *
 */
public class Merchants
{
	//WebDriver d;
	int size=0;
	String price="0";
	//String rvalue;
	
	/**
	 * This method is to identifying price in yebhi.com. 
	 * And comparing both prices, and displays both are equal or not. 
	 * @param ammount - price in DealWllet
	 * @param d - it is an object for WebDriver
	 */
	public void yebhi(String ammount, WebDriver d)
	{
		if(d.getTitle().equalsIgnoreCase("Error | @Yebhi.com  | Yebhi.com"))
		{
			System.out.println("Error while processing.., Unable to open the page");
		}
		else
		{
			if(!d.findElement(By.xpath("//div[@id='Profile_form']/h5")).getText().equalsIgnoreCase("This product is sold out..."))
			{		
				size=d.findElements(By.xpath("//div[5]/div[2]/div/div[2]/p")).size();
				if(size!=0)
				{
					price = d.findElement(By.xpath("//div[5]/div[2]/div/div[2]/p")).getText();
					price=price.substring(1);
					String[] parts=price.split(":");
					int len=parts.length;
					if(len==4)
					{
						price=parts[1].replace(",", "").trim();
						if(price.equals(ammount))
						{
							System.out.println("DW Price::"+ammount+" and Marchent price::"+price+" both are equal");
						}
						else
						{
							System.out.println("DW Price::"+ammount+" and Marchent price::"+price+" both are NOT equal");
						}
					}
					else
					{
						price=parts[0].replace(",","").trim();
					}
				}
				else
				{
					System.out.println("NO PRICE TO BE DISPLAYED");
				}
			}
			else
				System.out.println("This product is sold out...");
		}
	}


	public void homeshop18(String ammount, String price2)
	{
		price2 = price2.substring(1).replace(",", "").trim();
		if (price2.equals(ammount))
		{
			System.out.println("Dw Price::" + ammount+ " and Marchent price::" + price2+ "  both are equal");
		}
		else
		{
			System.out.println("Dw Price::" + ammount+ " and Marchent price::" + price2+ " both are NOT equal");
		}

	}
	
	/**
	 * This method is to identifying price in naaptol.com. 
	 * And comparing both prices, and displays both are equal or not. 
	 * @param ammount - price in DealWllet
	 * @param d - it is an object for WebDriver
	 */
	public void naaptol(String ammount, WebDriver d)
	{
	
		if(d.getTitle().equalsIgnoreCase("Page Not Found"))
		{
			System.out.println("Page Not Found Error");
		}
		else
		{
			int isPresent = d.findElements(By.xpath("//li/strong")).size();
			if(isPresent==0)
			{
				System.out.println("The Item Selected Is Not In The Merchants Site");
			}
			else
			{
				price = d.findElement(By.xpath("//li/strong")).getText();
				price=price.substring(1).replace(",", "").replace("*", "").trim();
				
				if(ammount.equals(price))
				{
					System.out.println("both price are same");
				}
				else
				{
					System.out.println("both price are NOT same");
					System.out.println("our::"+ammount+" & merchent::"+price);
				}

			}
		}

	}
	
	public void letsshop(String ammount, String price)
	{
		price = price.substring(3).replace(",", "").trim();
		if (price.equals(ammount))
		{
			System.out.println("Dw Price::" + ammount+ " and Marchent price::" + price+ "  both are equal");
		}
		else
		{
			System.out.println("Dw Price::" + ammount+ " and Marchent price::" + price+ " both are NOT equal");
		}
	}

	/*public String sitename(String s)
	{
		String[] temp = s.split("/");
		String sitename = temp[2];
		return sitename;
	}*/
	

	public void healthkart(String ammount, String price)
	{
		price=price.substring(2).trim().replace(",","");
		if(price.equals(ammount))
		{
			System.out.println("DB Price::"+ammount+" and Marchent price::"+price+"  both are equal");
		}
		else
		{
			System.out.println("DB Price::"+ammount+" and Marchent price::"+price+" both are NOT equal");
		}
	}

	/**
	 * This method is to identifying price in snapdeal.com. 
	 * And comparing both prices, and displays both are equal or not. 
	 * @param ammount - price in DealWllet
	 * @param d - it is an object for WebDriver
	 */
	public void snapdeal(String ammount, WebDriver d) {
		
		size=d.findElements(By.xpath("//strong/span")).size();
		if(size!=0)
		{
			price=d.findElement(By.xpath("//strong/span")).getText();
			System.out.println(price);
			price=price.replace(",","").trim();
			price=price.replace(".0", "");
			if(price.equals(ammount))
			{
				System.out.println("DW Price::"+ammount+" and Marchent price::"+price+"  both are equal");
			}
			else
			{
				System.out.println("DW Price::"+ammount+" and Marchent price::"+price+" both are NOT equal");
			}
		}
		else
		{
			System.out.println("NO PRICE TO BE DISPLAYED");
		}
	}

	/**
	 * This method is to identifying price in Jabong.com. 
	 * And comparing both prices, and displays both are equal or not. 
	 * @param ammount - price in DealWllet
	 * @param d - it is an object for WebDriver
	 */
	public void jabong(String ammount, WebDriver d) {
		
		size=d.findElements(By.xpath("//div[@id='price_div']/div[2]/span[2]")).size();
		if(size!=0)
		{
			price=d.findElement(By.xpath("//div[@id='price_div']/div[2]/span[2]")).getText();
			//System.out.println(price);
			price=price.replace(",","").trim();
			price=price.replace(".0", "");
			if(price.equals(ammount))
			{
				System.out.println("DW Price::"+ammount+" and Marchent price::"+price+"  both are equal");
			}
			else
			{
				System.out.println("DW Price::"+ammount+" and Marchent price::"+price+" both are NOT equal");
			}
		}
		else
		{
			System.out.println("NO PRICE TO BE DISPLAYED");
		}
	}

	public void shopclues(String ammount, WebDriver d)
	{
		price=d.findElement(By.xpath("//div[@class='float-left product-prices']")).getText();
		String[] str=price.split("Rs.");
		price=str[str.length-1];
		
		if(price.equals(ammount))
		{
			System.out.println("DW Price::"+ammount+" and Marchent price::"+price+"  both are equal");
		}
		else
		{
			System.out.println("DW Price::"+ammount+" and Marchent price::"+price+" both are NOT equal");
		}
	}
	
	public void surf2buy(String ammount, WebDriver d)
	{
		price=d.findElement(By.cssSelector("div.price")).getText();
		
		String[] str=price.split(":");
		str=str[1].split(" ");
		
		if(str.length>2)
		{
			price=str[2].replace(",", "").replace(".00", "");
		}
		else
		{
			price = str[1].replace(",", "").replace(".00", "");
		}
		
		if(price.equals(ammount))
		{
			System.out.println("DW Price::"+ammount+" and Marchent price::"+price+"  both are equal");
		}
		else
		{
			System.out.println("DW Price::"+ammount+" and Marchent price::"+price+" both are NOT equal");
		}
	}
	
	
	public void hindbuy(String ammount, WebDriver d)
	{
		price= d.findElement(By.cssSelector("#ctl00_ContentPlaceHolder1_Price_ctl00_lblOfferPrice")).getText();
		//System.out.println(s);
		price=price.replace("Rs.", "");
		
		if(price.equals(ammount))
		{
			System.out.println("DW Price::"+ammount+" and Marchent price::"+price+"  both are equal");
		}
		else
		{
			System.out.println("DW Price::"+ammount+" and Marchent price::"+price+" both are NOT equal");
		}
	}
	

	/**
	 * This method is used to identify the site name in the url.
	 * @param d - it is an object for WebDriver
	 * @return "sitename" site name
	 */
	public String[] sitename(WebDriver d) {
		String s = d.getCurrentUrl();
		System.out.println("url is::" + s);
		String[] temp=s.split("www.");
		String[] sitename=temp[1].split(".com");
		System.out.println("site name is::"+sitename[0]);
		return sitename;
	}
	/**
	 * This method is used for identifying the price in the DealWllet.
	 * clicking the shop now button, and switches the control to the merchants site
	 * @param k - it is for iteration number to know how many element in 10 elements.
	 * @param d - it is an object for WebDriver
	 * @return "amount" price in DealWllet.
	 */
	public String dwPrice(int k, WebDriver d) {
		//for identifying the price in DealWllet.
		String amount=d.findElement(By.xpath("//div["+k+"]/div/div[3]/span")).getText();
		amount=amount.replace(",", "").replace(".0", "").substring(1).trim();
		//for shop now button in DealWllet
		d.findElement(By.xpath("//div["+k+"]/div/div[3]/p/a/span/span")).click();
		for (String ccode : d.getWindowHandles()) {
			d.switchTo().window(ccode);
		}
		return amount;
	}
	
	/**
	 * This Method is used for identifying the product link in DealWallet. 
	 * and click on that link. And get the current window code by using getWindowHandle() method.
	 * @return "pcode" current window code.
	 */
	public String productLink(WebDriver d) {
		d.get("http://www.dealwallet.com");
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		String pcode = d.getWindowHandle();
		//for products link in DealWallet
		d.findElement(By.linkText("Products")).click();
		return pcode;
	}
	
	
	
}
