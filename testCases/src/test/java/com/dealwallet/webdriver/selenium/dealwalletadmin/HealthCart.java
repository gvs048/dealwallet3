package com.dealwallet.webdriver.selenium.dealwalletadmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HealthCart
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
	public void testHealthCart()
	{
		d.get("http://www.healthkart.com/");
		d.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		//getCategories();
		getProducts();
	}
	private void getProducts()
	{
		getDocument();
		//Document doc = Jsoup.parse(d.getPageSource());
		//Elements body = doc.select("body").select("li").select("a");
		Elements body;
		body= getDocument().select("body").select("li").select("a");
		for(Element cat:body)
		{
			String attr= cat.attr("href");
			//System.out.println(attr);
			String[] items= attr.split("/");
			if(items.length==4)
			{
				Connection con= OpenDBConnection();

				//System.out.println("It is a subcategory");
				//getDocument().select("body").select("#prod_grid").select("div.img180").select("img")
				d.navigate().to("http://www.healthkart.com/"+attr);
				getDocument();
				String url="";
				String img="";
				String pname="";
				String pprice="";
				String[] asinid;
				String asin="";
				body=getDocument().select("body").select("#prod_grid");
				for(Element pro:body.select("div.grid_6.product"))
				{
					url=pro.select("a").attr("href");
					System.out.println(url);
					/*asinid=url.split("/");
					asin=asinid[asinid.length-1];*/

					img=pro.select("img").attr("src");
					System.out.println(img);
					pname=pro.select("a").select("img").attr("alt");
					System.out.println(pname);
					pprice=pro.select("span.hk").text();
					System.out.println(pprice);
					insertProducts();
				}
			}
			else
			{
				System.out.println("It is a main category");
			}
		}

	}
	private void insertProducts() {
		// TODO Auto-generated method stub

	}
	private Document getDocument() {
		Document doc = Jsoup.parse(d.getPageSource());
		return doc;
	}
	private void getCategories() {
		Document doc = Jsoup.parse(d.getPageSource());
		Elements body = doc.select("body").select("li");
		Connection con= OpenDBConnection();
		String url="";
		String catname="";
		for(Element cat:body.select("a"))
		{
			catname=cat.text();
			System.out.println(catname);
			url="http://www.healthkart.com"+cat.attr("href");
			System.out.println(url);
			if(catname.equalsIgnoreCase("HealthKartPlus >"))
			{
				break;
				//insertDB(con,catname,url);
			}
			else
			{
				//break;
				insertDB(con,catname,url);
			}
		}
		CloseDBConnection();
	}
	private void insertDB(Connection con, String catname, String url) {
		try {
			String iquery="insert into productfeed_category(id,category,url,merchant_id ) values(nextval('productfeed_category_id_seq'),?,?,?);";
			PreparedStatement ps=con.prepareStatement(iquery);
			ps.setString(1, catname);
			ps.setString(2, url);
			ps.setInt(3, 159);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void CloseDBConnection() {
		Connection conn=null;
		try {
			if(conn != null)
				conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private Connection OpenDBConnection() {
		Connection conn=null;
		try
		{
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://192.168.3.127:5432/dealwallet_batch_qa","postgres","postgres123");
			System.out.println("Connection is open now");
		}
		catch(SQLException se)
		{
			System.out.println("Sql Exception"+se);
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("Class not found Exception"+cnfe);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		finally
		{
			//
		}
		return conn;
	}
}
