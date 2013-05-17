package com.dealwallet.webdriver.selenium.scraping;

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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dealwallet.webdriver.selenium.java.TestRS;
public class HealthCartWith
{
	TestRS trs = new TestRS();
	@BeforeMethod
	public void startup()
	{
		trs.setD(new FirefoxDriver());
		//d=new HtmlUnitDriver();
	}
	@AfterMethod
	public void stop()
	{
		trs.getD().quit();
	}
	@Test
	public void testHealthCart()
	{
		trs.getD().get("http://www.healthkart.com/");
		trs.getD().manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		getCategories();
		getProducts();
	}
	private void getProducts()
	{
		Connection con= OpenDBConnection();
		//TestRS trs= new TestRS();
		getDocument();
		//Elements body;
		try
		{
			categoryLogic(con, trs);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	/**
	 * @param con
	 * @param trs
	 * @throws NumberFormatException
	 */
	private void categoryLogic(Connection con, TestRS trs)
			throws NumberFormatException {
		Elements body;
		body= getDocument().select("body").select("li").select("a");
		for(Element cat:body)
		{
			String attr= cat.attr("href");
			String[] items= attr.split("/");
			/*if(items[1].equalsIgnoreCase("Sports Nutrition"))
			{
				//do nothing
			}
			else
			{*/
				if(items.length==4)
				{
					subCategory(con, trs, attr, items);
				}

				else
				{
					System.out.println("It is a main category");
				}
			/*}*/
		}
		CloseDBConnection(con);
	}
	/**
	 * @param con
	 * @param trs
	 * @param attr
	 * @param items
	 * @throws NumberFormatException
	 */
	private void subCategory(Connection con, TestRS trs, String attr,
			String[] items) throws NumberFormatException {
		Elements body;
		trs.getD().navigate().to("http://www.healthkart.com/"+attr);
		getDocument();
		String pgroup=items[3];
		int n=1;

		for(int i=0;i<n;i++)
		{
			body=getDocument().select("body").select("#prod_grid");
			for(Element pro:body.select("div.grid_6.product"))
			{
				subCatProducts(con, trs, pgroup, pro);
			}
			try
			{
				pageLogic(n);
			}
			catch(java.lang.NullPointerException ex)
			{
				System.out.println(ex);
			}
		}
	}
	/**
	 * @param con
	 * @param trs
	 * @param pgroup
	 * @param pro
	 * @throws NumberFormatException
	 */
	private void subCatProducts(Connection con, TestRS trs, String pgroup,
			Element pro) throws NumberFormatException {
		String url="";
		String img="";
		String pname="";
		int price=0;
		String pprice="0";
		String asin="";
		long nval;
		url=pro.select("a").attr("href");
		asin=url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("?"));
		System.out.println("asin id is ::"+asin);
		img=pro.select("img").attr("src");
		pname=pro.select("a").select("img").attr("alt");
		pprice=pro.select("span.hk").text();
		if(pprice.equalsIgnoreCase(""))
		{
			pprice=pro.select("div.hk").text();
		}
		else
		{
			//pprice="0";
		}
		pprice=pprice.replace("Rs.", "").replace(",", "").trim();
		price=Integer.parseInt(pprice);
		Elements bodydesc = bodyDescription(trs, url);
		nval=trs.nextval();
		int rows=trs.CheckinDB(asin);
		if(rows==0)
		{
			insertProductsDB(con,asin,url,pgroup,pname,bodydesc.text());
			insertPFPriceTbl(con,price,nval+1);
			insertPFImgTbl(con,img,nval+1);
		}
	}
	/**
	 * @param trs
	 * @param url
	 * @return
	 */
	private Elements bodyDescription(TestRS trs, String url) {
		trs.getD().navigate().to("http://www.healthkart.com"+url);
		getDocument();
		Elements bodydesc=getDocument().select("body").select("#description");
		trs.getD().navigate().back();
		return bodydesc;
	}
	/**
	 * @param n
	 */
	private void pageLogic(int n) {
		String purl="";
		String pagelogic="";
		Element edesc=getDocument().select("body").select("div.catalog_header").select("div.pagi").select("a").last();
		purl=edesc.attr("href");
		pagelogic=edesc.text();
		if(pagelogic.equalsIgnoreCase("Next →"))
		{
			n+=1;
			trs.getD().navigate().to("http://www.healthkart.com"+purl);
			System.out.println("display page number::"+n);
		}
		else
		{
			System.out.println("do nothing");
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
		Document doc = Jsoup.parse(trs.getD().getPageSource());
		return doc;
	}
	private void getCategories() {
		Document doc = Jsoup.parse(trs.getD().getPageSource());
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
