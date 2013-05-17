package com.dealwallet.webdriver.samples;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Zovi {

	WebDriver driver;
	@BeforeTest
	public void startup()
	{
		driver=new FirefoxDriver();
	}
	@AfterTest
	public void stop()
	{
		driver.quit();

	}
	/**
	 * 
	 */
	/*	@Test
	public void testzovi()
	{
		d.get("http://zovi.com/white-regular-fit-washed-ranger-shirt-half-sleeves--A126SRM07602");
		String s=d.findElement(By.xpath("//section[2]/div/label[1]")).getText();
		s=s.replace("Rs", "");
		String s1=d.findElement(By.xpath("//span[2]")).getText();
		System.out.println("Total string is :: "+s);
		System.out.println("Sub string is :: "+s1);
		//int res=s.indexOf("RES is"+s1);
		System.out.println("Actual price is :: "+s.substring(s1.length()+1));
		////section[2]/div/label[2]
		//s1.length();

		d.get("http://dealwallet.com/products/Apparel-Clothing-151");
		d.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		for(int i=1;i<11;i++)
		{
			String s = d.findElement(By.xpath("//div["+ i +"]/div/div/a/img")).getAttribute("src");
			System.out.println("dealwallet page image url::"+s);
			String pc = d.getWindowHandle();
			d.findElement(By.xpath("//div["+ i +"]/div/div[3]/p/a/span/span")).click();
			for(String cc:d.getWindowHandles())
			{
				d.switchTo().window(cc);
			}
			String s1=d.findElement(By.xpath("//a/span/img")).getAttribute("src");
			System.out.println("merchent page image url::"+s1);
			char c1=s1.charAt(8);
			//String c2=String.valueOf(c1);
			String s2=s1.replaceFirst(String.valueOf(c1), "");
			System.out.println(s2);
			String s3=s2.replace("large","166x194");
			System.out.println(s3);

			if(s.equalsIgnoreCase(s3))
			{
				System.out.println("both are equal");
			}
			else
			{
				System.out.println("both are not equal");
			}
			d.close();
			d.switchTo().window(pc);
		}
	}
	 */
	@Test
	public void testExample(){
		driver.get("http://www.dealwallet.com/products/Apparel-Clothing-151");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		for(int i=1;i<=4;i++){
			driver.findElement(By.xpath("//div["+i+"]/div/div[2]/p/a")).click();
			String winHandleBefore= driver.getWindowHandle();
			String price1=driver.findElement(By.xpath("//div[2]/span")).getText();
			System.out.println(price1);
			driver.findElement(By.xpath("//a/span")).click();
			for(String winHandle1:driver.getWindowHandles())
			{
				driver.switchTo().window(winHandle1);
			}
			//driver.navigate().to(url);
			String pageurl= driver.getCurrentUrl();
			if(pageurl.contains("yebhi")){
				String price2=driver.findElement(By.xpath("//div[@id='mainContainer']/div[4]/div/div/div/div/div[2]/div[3]/div/div/div/p/span/span[2]")).getText();
				System.out.println(price2);
			}
			else if(pageurl.contains("snapdeal")){
				String price3=driver.findElement(By.xpath("//div[@id='content_wrapper']/div/div[13]/div[3]/div/div[2]/div[2]/div/div/div/strong/span")).getText();
				System.out.println(price3);
			}
			driver.navigate().refresh();
			driver.close();
			driver.switchTo().window(winHandleBefore);
			driver.navigate().back();

		}
	}
}
