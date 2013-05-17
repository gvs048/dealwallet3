package com.dealwallet.webdriver.selenium.Samles;
import com.dealwallet.webdriver.selenium.Samles.SampleChild;
public class MainClass
{
	//String s="12345";
	//Merchants m;
	public static void main(String[] args)
	{
		String s="12345";
		SampleChild m=new SampleChild();
		String s1=m.Method1(s);
		System.out.println("ammount in main class is::"+s1);
	}
}
