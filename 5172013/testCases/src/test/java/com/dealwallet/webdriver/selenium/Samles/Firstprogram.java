package com.dealwallet.webdriver.selenium.Samles;

public class Firstprogram {
	//int i=10;
	//System.out.println("value of i in global:"+i);



		int i=20;
		int j=10;
	public void	sum()
		{
		System.out.println("ivalue local:"  +i);
		}
	public void sum1()
	{
		//System.out.println("sum1 value is"+i+j);//concatation 2010 will displayed
		System.out.println("sum1 valueis:"+(i+(j)));
	}
public static  void main(String args[])
{
	Firstprogram f=new Firstprogram();
	f.sum();
	f.sum1();

}
}
