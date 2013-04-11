package pavan;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Mouseover {
	WebDriver d;
	@Test
	public void testMousOver() throws InterruptedException
	{
		//d.navigate.to("")
		d.get("http://aptransport.org/");
		WebElement we=d.findElement(By.id("link6"));
		Actions a=new Actions(d);
		a.moveToElement(we).perform();
		Thread.sleep(5000);

	}
  @BeforeMethod

	public void setUp()throws Exception
	{
		d = new FirefoxDriver();
				}
	@AfterMethod

	public void tear()
	{
		d.quit();
	}



}


