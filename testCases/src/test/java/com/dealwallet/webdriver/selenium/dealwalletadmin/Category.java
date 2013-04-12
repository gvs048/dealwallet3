package com.dealwallet.webdriver.selenium.dealwalletadmin;

import static java.lang.Thread.sleep;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Category
{

	WebDriver d;
	private Select s;
	@BeforeMethod
	public void startup()
	{
		d=new FirefoxDriver();

	}
	@AfterMethod
	public void stop()
	{
		d.quit();
	}
	@Test
	public void testcategory() throws InterruptedException
	{
		d.get("http://localhost:8081/dealwalletadmin");
		d.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		d.findElement(By.id("login_username")).sendKeys("mudam.srinivas@gmail.com");
		d.findElement(By.id("login_password")).sendKeys("srinivas");
		d.findElement(By.xpath("//div[3]/button")).click();
		//String text="";
		//text=d.findElement(By.xpath("//form/label")).getText();
		int size=d.findElements(By.xpath("//form/label")).size();
		if(size!=0)
		{
			System.out.println("try again with valid user name and password");
		}
		else
		{
			String s=d.findElement(By.xpath("//li[3]/a/span")).getText();
			System.out.println("text is::"+s);
		}
		//text.equalsIgnoreCase("You have entered an invalid username or password!")
		d.findElement(By.xpath("//div[3]/div/div/p[1]/a")).click();
		d.findElement(By.id("mer_id")).click();
		s=new Select(d.findElement(By.id("mer_id")));
		/*s.selectByIndex(9);
		System.out.println("1");
		Thread.sleep(20000);*/
		s.selectByValue("134");
		System.out.println("2");
		sleep(20000);
		/*s.selectByVisibleText("Tradus");
		System.out.println("3");
		Thread.sleep(20000);*/
		/*s= new Select(d.findElement(By.name("category")));
		s.selectByVisibleText("TV, Video and MP3 Players");
		s= new Select(d.findElement(By.name("amCategory")));
		s.selectByVisibleText("TVs, Audio/Video & Gaming");
		d.findElement(By.cssSelector("input[type='submit']")).click();*/
		Thread.sleep(20000);
// this is a samp
}
}
