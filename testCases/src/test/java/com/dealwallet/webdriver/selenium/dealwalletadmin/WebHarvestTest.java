package com.dealwallet.webdriver.selenium.dealwalletadmin;

import java.io.FileNotFoundException;
import org.webharvest.definition.ScraperConfiguration;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.variables.Variable;

public class WebHarvestTest {

	 public static void main(String[] args)
	 {
	  try{
	   String strPageURL =  "http://half-wit4u.blogspot.com/2011/01/web-scraping-using-java-api.html";
	       ScraperConfiguration config = new ScraperConfiguration("C:/Users/Tsss-Pc1/Desktop/webHarvestConf.xml");
	       Scraper scraper = new Scraper(config, "D:/");
	       scraper.addVariableToContext("url",strPageURL);
	       scraper.setDebug(true);
	       scraper.execute();
	       Variable varScrappedContent = (Variable)scraper.getContext().getVar("scrappedContent");

	       // Printing the scraped data here
	       System.out.println(varScrappedContent.toString());
	       }catch(FileNotFoundException e){
	         e.printStackTrace();
	       }
	  }
}
