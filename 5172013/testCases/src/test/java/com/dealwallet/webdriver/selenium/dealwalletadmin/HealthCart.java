package com.dealwallet.webdriver.selenium.dealwalletadmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.james.mime4j.field.datetime.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dealwallet.webdriver.selenium.java.TestRS;
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
		getCategories();
		getProducts();
	}
	private void getProducts()
	{
		TestRS trs= new TestRS();
		getDocument();
		Elements body;
		body= getDocument().select("body").select("li").select("a");
		for(Element cat:body)
		{
			String attr= cat.attr("href");
			String[] items= attr.split("/");
			if(items.length==4)
			{
				Connection con= OpenDBConnection();
				d.navigate().to("http://www.healthkart.com/"+attr);
				getDocument();
				String url="";
				String img="";
				String pname="";
				int price=0;
				String pprice="0";
				String asin="";
				String pgroup=items[3];
				long nval;
				body=getDocument().select("body").select("#prod_grid");
				for(Element pro:body.select("div.grid_6.product"))
				{
					url=pro.select("a").attr("href");
					asin=url.substring(url.lastIndexOf("/")+1, url.indexOf("?"));
					System.out.println("asin id is ::"+asin);
					System.out.println("product URl is ::"+"http://www.healthkart.com"+url);
					img=pro.select("img").attr("src");
					System.out.println("image url is ::"+img);
					pname=pro.select("a").select("img").attr("alt");
					System.out.println("Product name is::"+pname);
					pprice=pro.select("span.hk").text();
					pprice=pprice.replace("Rs.", "").replace(",", "").trim();
					price=Integer.parseInt(pprice);
					System.out.println("Price is::"+pprice);
					d.navigate().to("http://www.healthkart.com"+url);
					getDocument();
					Elements bodydesc=getDocument().select("body").select("div.product_details").select("p");
					System.out.println("Description is::"+bodydesc.text());
					d.navigate().back();
					nval=trs.nextval();
					System.out.println(nval);
					insertProductsDB(con,asin,url,pgroup,pname,bodydesc.text());
					insertPFPriceTbl(con,price,nval+1);
					insertPFImgTbl(con,img,nval+1);
				}
				CloseDBConnection(con);
			}
			else
			{
				System.out.println("It is a main category");
			}
		}

	}
	private void insertPFImgTbl(Connection con, String img, long nval) {
		// TODO Auto-generated method stub
		try {
			String iquery="insert into productfeed_product_images(id,imagetype,imageurl,productid) values(nextval('productfeed_product_images_id_seq'),1,?,?);";
			PreparedStatement ps=con.prepareStatement(iquery);
			ps.setString(1, img);
			ps.setLong(2, nval);
			ps.executeUpdate();
			ps.close();
			System.out.println("product inserted successfully in productfeed_products_images table");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void insertPFPriceTbl(Connection con, int pprice, long nval) {
		// TODO Auto-generated method stub
		try {
			String iquery="insert into productfeed_product_prices(id,pricetype,ammount,currencytcode,productid) values(nextval('productfeed_product_prices_id_seq'),1,?,'Rs.',?);";
			PreparedStatement ps=con.prepareStatement(iquery);
			ps.setInt(1, pprice);
			ps.setLong(2, nval);
			ps.executeUpdate();
			ps.close();
			System.out.println("product inserted successfully in productfeed_products_prices table");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	private void insertProductsDB(Connection con, String asin, String url, String pgroup, String pname, String desc)
	{
		try {
			String iquery="insert into productfeed_products(id,asin,detailpageurl,productgroup,lastmodefieddate,merchantid,title, description) values(nextval('productfeed_products_id_seq'),?,?,?,CURRENT_DATE,?,?,?);";
			PreparedStatement ps=con.prepareStatement(iquery);
			ps.setString(1, asin);
			ps.setString(2, "http://www.healthkart.com"+url);
			ps.setString(3, pgroup);
			ps.setInt(4, 159);
			ps.setString(5, pname);
			ps.setString(6, desc);
			ps.executeUpdate();
			ps.close();
			System.out.println("product inserted successfully in productfeed_products table");
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
			}
			else
			{
				insertDB(con,catname,url);
			}
		}
		CloseDBConnection(con);
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
	private void CloseDBConnection(Connection conn) {
		//Connection conn=null;
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
			conn=DriverManager.getConnection("jdbc:postgresql://192.168.3.127:5432/dealwallet_batch_dev","postgres","postgres123");
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
