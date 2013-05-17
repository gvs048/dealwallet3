package pavan;




	import java.io.BufferedWriter;
	import java.io.File;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.List;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.testng.annotations.*;

	public class Links {

		String content[]=new String[100];
		WebDriver d;
		BufferedWriter bw;
		@BeforeMethod
		public void init()
		{
			//d=new InternetExplorerDriver();
			d=new FirefoxDriver();
		}

		@Test
		public void linkNames()throws Exception
		{
			d.get("http://www.google.co.in/");
			d.manage().timeouts().implicitlyWait(60, TimeUnit.MINUTES);
			/*Extract all links from the webpage using selenium webdriver*/
			List<WebElement> all_links_webpage = d.findElements(By.tagName("a"));
			/*Print total no of links on the webpage*/
			System.out.println("Print total no of links on the webpage:"+all_links_webpage.size());
			System.out.println("Text of the link for specific part of webpage");
			for(int i =0; i< all_links_webpage.size();i++){
			System.out.println(((WebElement) all_links_webpage.get(i)).getText());
			content[i]=((WebElement) all_links_webpage.get(i)).getText() ;

	        //print link texts into doc/xml/txt file i.e links.txt/links.doc/links.xml
				File file = new File("/home/tsssinfotech/Desktop/links.xls");

				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				bw = new BufferedWriter(fw);



			}
			for(int i =0; i< all_links_webpage.size();i++)
			{
				bw.write(content[i]);
				//bw.write(",");
				bw.newLine();
			}

			bw.close();
			System.out.println("Done");

			}


		@AfterMethod
		public void stop() throws IOException
		{

			d.quit();

		}

	}


