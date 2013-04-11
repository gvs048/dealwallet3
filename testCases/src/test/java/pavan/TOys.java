package pavan;



import java.util.concurrent.TimeUnit;
//import static org.junit.Assert.*;

//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TOys
{
	WebDriver d;
	@BeforeMethod
	public void before()
	{
		d=new FirefoxDriver();
	}

	@Test
	public void testcomputers() throws InterruptedException
	{
		d.get("http://dealwallet.com/");
		d.manage().window().maximize();
		String dw=d.getWindowHandle();
		d.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		d.findElement(By.linkText("Toys")).click();
		for(int i=1;i<=2;i++)
		{
			for(int k=1;k<=10;k++)
			{

				String s=d.findElement(By.xpath("//div["+k+"]/div/div[3]/span")).getText();
				//System.out.print(s);
				String res1=s.substring(1);
				 dw=res1.replace(".0", "");
				d.findElement(By.xpath("//div["+k+"]/div/div[3]/p/a/span/span")).click();
				for(String mw:d.getWindowHandles())
				{
					d.switchTo().window(mw);
				}
				String s1=d.findElement(By.xpath("//li/strong")).getText();
				//System.out.print(s1);
				String res2=s1.substring(1).trim();
				String res3=res2.replace("," ,"");
				String mwprice=res3.replace("*", "");
				if(dw.equals(mwprice))
				{
					System.out.println("image both price are same");
					System.out.println("price is"+mwprice);
					System.out.println("---------");
				}
				else
				{
					System.out.println("page no"+i+"image no"+k+"prices are not same");
					System.out.println("dealwallet"+dw+"merchantprice"+mwprice);
				}

			}
			d.close();

			d.switchTo().window(dw);
			d.findElement(By.linkText("Next")).click();
		}
	}


	@AfterMethod
	public void after()
	{
		d.quit();
	}
}