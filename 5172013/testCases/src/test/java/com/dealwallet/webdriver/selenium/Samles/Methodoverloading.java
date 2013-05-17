package com.dealwallet.webdriver.selenium.Samles;

public class Methodoverloading {
	/*static int a=10;
	static int b=20;
	static int c=30;*/
	//int d;
	int b,c;
	public void sum(int a)
	{
	System.out.println("sum  value is:"+a);
	System.out.println("sum  value is:"+(a+b+c));

	}
	public void sum(int b,int c)
	{
		System.out.println("sum  value is:"+(b+c));
	}
public static void main (String args[])
{
	Methodoverloading m=new Methodoverloading();
	m.sum(100);
	m.sum(100,200);

}

}
