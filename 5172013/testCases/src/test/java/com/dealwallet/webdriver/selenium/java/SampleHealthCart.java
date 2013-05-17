package com.dealwallet.webdriver.selenium.java;

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

/**
 * This class is used to scrap the categorise and products from the healthcart.com 
 * and store the category details into productfeed_category table and products 
 * details into the productfeed_products table.
 * @author Tsss-Pc1
 *
 */
public class SampleHealthCart
{
	/**
	 * d is a reference variable of WebDriver interface 
	 */
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
		/*d.get("http://www.healthkart.com/");
		d.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);*/
		TestRS trs=new TestRS();
		long nval=trs.nextval();
		System.out.println(nval);
		getCategories();
		getProducts();
	}
	private void getProducts()
	{
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
				String pprice="";
				String asin="";
				String pgroup=items[3];
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
					pprice=pprice.replace("Rs.", "").replace(",", "");
					System.out.println("Price is::"+pprice);
					d.navigate().to("http://www.healthkart.com"+url);
					getDocument();
					Elements bodydesc=getDocument().select("body").select("div.product_details").select("p");
					System.out.println("Description is::"+bodydesc.text());
					d.navigate().back();
					pt_nextval(con);
					insertProductsDB(con,asin,url,pgroup,pname,bodydesc.text());

				}
				CloseDBConnection(con);
			}
			else
			{
				System.out.println("It is a main category");
			}
		}

	}
	
	/**
	 * To know the sequence id, we are executing the query in the database table 
	 * and get the result into Result set and then displaying the Sequence number
	 * and finally close the connection. 
	 * @param con The object for connection class
	 * @return void it returns nothing.
	 */
	private void pt_nextval(Connection con)
	{
		
		Statement st=null;
		ResultSet rs=null;
		//String query="select nextval('productfeed_products_id_seq')";
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT nextval('productfeed_products_id_seq')");

			if (rs.next()) {
				System.out.println(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}


	}
	/**
	 * This method is used to insert the Product details in to the  
	 * productfeed_products table.
	 * @param con it is an object for the connection class.
	 * @param asin it is an asin id for a particular product
	 * @param url it is a product url for a particular product. 
	 * @param pgroup it is for a product group of that particular product. 
	 * @param pname it is for a product name 
	 * @param desc it is for a product description. 
	 * @return void it returns nothing.
	 */
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
	/**
	 * This method is used to get the page source and parse by using the Jsoup.
	 * @return Document it returns the Document
	 */
	private Document getDocument() {
		Document doc = Jsoup.parse(d.getPageSource());
		return doc;
	}
	/**
	 * This method is used to get the categories from the merchent site 
	 * and save the details into the Database Table. Productfeed_categories table.
	 * This method returns nothing.
	 */
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
		CloseDBConnection(con);
	}
	/**
	 * This method is used to insert the details into the database Table 
	 * Productfeed_products table.
	 * @param con
	 * @param catname
	 * @param url
	 * It returns nothing.
	 */
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
	/**This method is used to close the connection of the database.
	 * @param conn
	 */
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
	/**This method is used to open the connection to the database.
	 * @return it returns Connection 
	 */
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
