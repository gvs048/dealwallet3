package com.dealwallet.webdriver.samples;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;
import org.testng.annotations.*;

public class Anroid {

		WebDriver driver;

		@BeforeMethod
		public void setUp(){
		driver =new AndroidDriver();

		}
		@AfterMethod
		public void tearDown(){
		driver.quit();
		}
	@Test
		public void testExample(){
		driver.get(" http://google.com ");
		driver.findElement(By.linkText("Hindi")).click();
		}}


