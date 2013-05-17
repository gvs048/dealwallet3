package com.dealwallet.webdriver.selenium.java;

public class StringSplitExample
{
public static void main(String [] args)
{
	//String s="/product/muscleblaze-whey-protein/NUT1367?productReferrerId=23";
	String s="/product/nature-s-bounty-flexamin-triple-strength-bone-shieldã?â plus-vitamins-k2-and-d3/NUT2996?productReferrerId=22";
/*	int index= s.indexOf("/");
	System.out.println(index);
	s=s.substring(34, 41);
	System.out.println(s);
*/
	//int i= s.lastIndexOf("/");
	s=s.substring(s.lastIndexOf("/")+1, s.lastIndexOf("?"));
	System.out.println(s);

}
}
