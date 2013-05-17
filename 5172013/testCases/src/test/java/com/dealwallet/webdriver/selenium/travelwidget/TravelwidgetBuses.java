package com.dealwallet.webdriver.selenium.travelwidget;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TravelwidgetBuses
{
	WebDriver d;
	@BeforeMethod
	public void start()
	{
		d=new FirefoxDriver();
	}
	@AfterMethod
	public void stop()
	{
		d.quit();
	}
	@Test
	public void testtwBuses() throws InterruptedException
	{
		d.get("http://www.dealwallet.com");
		d.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		d.manage().window().maximize();
		d.findElement(By.linkText("Travel Widget")).click();
		d.findElement(By.xpath("//div[3]/input")).click();
		/*
//departure dropdown
        Select droplist = new Select(d.findElement(By.name("dW_departure")));
        droplist.selectByVisibleText("Hyderabad");
        WebElement option = droplist.getFirstSelectedOption();
        String s= option.getText();
        System.out.println("from location is::"+s);*/

		/*//calender
        d.findElement(By.name("departureDate")).click();
        d.findElement(By.linkText("9")).click();
        Thread.sleep(2000);
        String date=d.findElement(By.name("departureDate")).getText();
System.out.println("Date is::"+date);*/

		/*
//Arrival dropdown
        Select droplist1 = new Select(d.findElement(By.name("dW_arrival")));
        droplist1.selectByVisibleText("Guntur");
        WebElement option1 = droplist1.getFirstSelectedOption();
        String s1= option1.getText();
        System.out.println("To location is::"+s1);*/

		/*d.findElement(By.xpath("//div[5]/input")).click();
        Thread.sleep(6000);*/


		//departure dropdown
		Select droplist = new Select(d.findElement(By.name("dW_fromCityId")));
		droplist.selectByVisibleText("Hyderabad");
		WebElement option = droplist.getFirstSelectedOption();
		String s= option.getText();
		System.out.println("from location is::"+s);

		//Arrival dropdown
		Select droplist1 = new Select(d.findElement(By.name("dW_toCityId")));
		droplist1.selectByVisibleText("Guntur");
		WebElement option1 = droplist1.getFirstSelectedOption();
		String s1= option1.getText();
		System.out.println("To location is::"+s1);

		//calender
		d.findElement(By.name("doj")).click();
		d.findElement(By.linkText("9")).click();
        String doj= d.findElement(By.name("doj")).getText();
        System.out.println("date fo journey::"+doj);
		d.findElement(By.xpath("//div[2]/form/div[5]/input")).click();
		Thread.sleep(8000);
	}
}
