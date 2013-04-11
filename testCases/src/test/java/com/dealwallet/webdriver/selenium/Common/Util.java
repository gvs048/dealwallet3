package com.dealwallet.webdriver.selenium.Common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Util
{
	String dealwalletUrl,username,password,newpassword,browser,dw_origin,dw_destination,dw_depart_date,dw_arrival_date,hotelcity;
	String dbDriverUrl,dbUsername,dbPassword,dbServerUrl,dbName;
	WebDriver d;
	public Util()
	{
		loadConfig();
		loadBrowser();
		loadDatabase();
	}
	public void loadConfig()
	{
		try
		{
			Properties connProperties=new Properties();
			final InputStream file = new FileInputStream("target/test-classes/environment.properties");
			connProperties.load(file);
			if(connProperties != null)
			{
				dealwalletUrl=connProperties.getProperty("dealwalletUrl");
				username=connProperties.getProperty("username");
				password=connProperties.getProperty("password");
				newpassword=connProperties.getProperty("newpassword");
				dw_origin=connProperties.getProperty("dw_origin");
				dw_destination=connProperties.getProperty("dw_destination");
				dw_depart_date=connProperties.getProperty("dw_depart_date");
				dw_arrival_date=connProperties.getProperty("dw_arrival_date");
				hotelcity=connProperties.getProperty("hotelcity");
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	  }
	public String getdealwalletUrl()
	{
		return dealwalletUrl;
	}
	public String getusername()
	{
		return username;
	}
	public String getpassword()
	{
		return password;
	}
	public String getnewpassword()
	{
		return newpassword;
	}
	public String getdw_origin()
	{
		return dw_origin;
	}
	public String getdw_destination()
	{
		return dw_destination;
	}
	public String getdw_depart_date()
	{
		return dw_depart_date;
	}
	public String getdw_arrival_date()
	{
		return dw_arrival_date;
	}
	public String gethotelcity()
	{
		return hotelcity;
	}
	public void loadBrowser()
	{
		try
		{
			Properties connProperty=new Properties();
			final InputStream file=new FileInputStream("target/test-classes/environment.properties");
			connProperty.load(file);
			if(connProperty !=null)
			{
			    browser=connProperty.getProperty("browser");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public WebDriver getbrowser()
	{
		if(browser.equalsIgnoreCase("firefox"))
		{
			d=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("iexplore"))
		{
			d=new InternetExplorerDriver();
		}
		else if(browser.equalsIgnoreCase("Chrome"))
		{
			d=new ChromeDriver();
		}
		else
		{
			d=new FirefoxDriver();
		}
		return d;
	}
	public void loadDatabase()
	{
		try
		{
			Properties connProperty=new Properties();
			final InputStream file=new FileInputStream("target/test-classes/environment.properties");
			connProperty.load(file);
			if(connProperty != null)
			{
				dbDriverUrl=connProperty.getProperty("dbDriverUrl");
				dbUsername=connProperty.getProperty("dbUsername");
				dbPassword=connProperty.getProperty("dbPassword");
				dbServerUrl=connProperty.getProperty("dbServerUrl");
				dbName=connProperty.getProperty("dbName");
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String getdbDriverUrl()
	{
		return dbDriverUrl;
	}
	public String getdbUsername()
	{
		return dbUsername;
	}
	public String getdbPassword()
	{
		return dbPassword;
	}
	public String getdbServerUrl()
	{
		return dbServerUrl;
	}
	public String getdbName()
	{
		return dbName;
	}

}