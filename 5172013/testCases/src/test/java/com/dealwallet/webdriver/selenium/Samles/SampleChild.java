package com.dealwallet.webdriver.selenium.Samles;

public class SampleChild
{
	public String Method1(String ammount)
	{
		System.out.println("child class method"+ammount);
		ammount=ammount+ammount;
		return ammount;
	}

}
