package selenium.webdriver.dealwalletadmin;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Category
{

	WebDriver d;
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
	public void testcategory()
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

	}
}
