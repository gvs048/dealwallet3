package com.dealwallet.webdriver.selenium.Common;

public class Merchants
{
	//WebDriver d;
	//String rvalue;
	public void yebhi(String ammount,  String s)
	{
		s=s.substring(1);
		String[] parts=s.split(":");
		int len=parts.length;
		if(len==4)
		{
			s=parts[1].replace(",", "").trim();
			if(s.equals(ammount))
			{
				System.out.println("DB Price::"+ammount+" and Marchent price::"+s+" both are equal");
			}
			else
			{
				System.out.println("DB Price::"+ammount+" and Marchent price::"+s+" both are NOT equal");
			}
		}
		else
		{
			s=parts[0].replace(",","").trim();
			if(s.equals(ammount))
			{
				System.out.println("DB Price::"+ammount+" and Marchent price::"+s+" both are equal");
			}
			else
			{
				System.out.println("DB Price::"+ammount+" and Marchent price::"+s+" both are NOT equal");
			}
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
	public void naaptol(String ammount, String price)
	{
		price = price.substring(1).replace(",", "").replace("*", "").trim();
		if (price.equals(ammount))
		{
			System.out.println("Dw Price::" + ammount+ " and Marchent price::" + price+ "  both are equal");
		}
		else
		{
			System.out.println("Dw Price::" + ammount+ " and Marchent price::" + price+ " both are NOT equal");
		}

	}
	public void snapdeal(String ammount, String price) {
		price = price.replace(",", "").trim();
		if (price.equals(ammount))
		{
			System.out.println("DW Price::" + ammount+ " and Marchent price::" + price+ "  both are equal");
		}
		else
		{
			System.out.println("DB Price::" + ammount+ " and Marchent price::" + price+ " both are NOT equal");
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

	public String sitename(String s)
	{
		String[] temp = s.split("/");
		String sitename = temp[2];
		return sitename;
	}
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

}
