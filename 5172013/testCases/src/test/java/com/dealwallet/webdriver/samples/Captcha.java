package com.dealwallet.webdriver.samples;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Captcha {

	WebDriver d;
	@BeforeTest
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
	public void testCaptha() throws InterruptedException
	{
		d.get("http://www.google.co.in/");
		d.findElement(By.xpath("//li[8]/a/span[2]")).click();
		d.findElement(By.id("link-signup")).click();
		d.findElement(By.id("FirstName")).sendKeys("subbarao");
		d.findElement(By.id("LastName")).sendKeys("Gaddam");
		d.findElement(By.id("GmailAddress")).sendKeys("asdasasas");
		d.findElement(By.id("Passwd")).sendKeys("aaaaaaaaa");
		d.findElement(By.id("PasswdAgain")).sendKeys("aaaaaaaaa");
		d.findElement(By.xpath("//span[@id='BirthMonth']/div")).click();
		d.findElement(By.xpath("//div[@id=':6']/div")).click();
		d.findElement(By.id("BirthDay")).sendKeys("12");
		d.findElement(By.id("BirthYear")).sendKeys("1234");
		d.findElement(By.xpath("//div[@id='Gender']/div")).click();
		d.findElement(By.xpath("//div[@id=':d']/div")).click();
		d.findElement(By.id("RecoveryPhoneNumber")).sendKeys("1234512345");
		d.findElement(By.id("RecoveryEmailAddress")).sendKeys("asad@gmial.com");
		
		
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter the captcha.... ");
		String reply = keyboard.next();
		
		
		
		d.findElement(By.id("recaptcha_response_field")).sendKeys(reply);
		d.findElement(By.id("TermsOfService")).click();
		Thread.sleep(9000);
		d.findElement(By.id("submitbutton")).click();
	}
}
