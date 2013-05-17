package com.dealwallet.webdriver.samples;

import org.testng.annotations.Test;

public class TestExample {

	SendEmail sm=new SendEmail();
	String s="";
	@Test(enabled=false)
	public void test1()
	{
		s+="@First Test"+ "\n";
		//System.out.println(s);
	}
	@Test(enabled=false)
	public void test2()
	{
		s+="@Second Test"+ "\n";
		//System.out.println(s);
	}
	@Test(priority=2,enabled=false)
	public void test3()
	{
		s+="@Third Test"+ "\n";
		//System.out.println(s);
	}
	@Test(priority=0)
	public void test4()
	{
		s+="@Fourth Test"+ "\n";
		//System.out.println(s);
	}
	@Test(priority=4,enabled=false)
	public void test5()
	{
		s+="@Fifth Test"+ "\n";
		//System.out.println(s);
	}
	@Test(priority=5)
public void testLast()
{
		System.out.println(s);
	//sm.sendingMail(s);
}
}
