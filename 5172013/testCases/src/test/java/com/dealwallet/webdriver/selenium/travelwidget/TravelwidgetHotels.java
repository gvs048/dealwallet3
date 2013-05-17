package com.dealwallet.webdriver.selenium.travelwidget;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TravelwidgetHotels
{
WebDriver d;
@BeforeMethod
public void startup()
{
	d=new FirefoxDriver();
	
	d.get("http://www.dealwallet.com");
	
}
@AfterMethod
public void stop()
{
	d.quit();
}
@Test
public void testHotels()
{
	d.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	d.findElement(By.linkText("Travel Widget")).click();
	d.findElement(By.xpath("//div[2]/input")).click();
	
}
}
