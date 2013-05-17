package com.dealwallet.webdriver.selenium.Samles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.concurrent.TimeUnit;

//import org.apache.james.mime4j.field.datetime.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.dealwallet.webdriver.selenium.java.TestRS;
import com.dealwallet.webdriver.selenium.Samles.STforHelthkart;

public class TestHelthkart
{
	WebDriver d;
	STforHelthkart sg=new STforHelthkart();
	@BeforeMethod
	public void startup()
	{
		d=new FirefoxDriver();
		//d=new HtmlUnitDriver();
	}
	@AfterMethod
	public void stop()
	{
		d.quit();
	}
	@Test
	public void testHealthCart()
	{
		sg.setConn(OpenDBConnection());
		TestRS trs= new TestRS();
		d.get("http://www.healthkart.com/");
		d.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		try
		{
			//getCategoriesMethod(sg, sg.getConn());
			getProductsMethod(sg, sg.getConn(), trs);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		CloseDBConnection(sg.getConn());

	}

	/**
	 * @param sg
	 * @param con
	 * @param trs
	 */
	private void getProductsMethod(STforHelthkart sg, Connection con, TestRS trs) {
		//Elements body;
		sg.setBody(getDocument().select("body").select("li").select("a"));
		for(Element cat:sg.getBody())
		{
			sg.setAttr(cat.attr("href"));
			sg.setItems(sg.getAttr().split("/"));
			if(sg.getItems().length==4)
			{
				subCategoryProducts(sg, con, trs);
			}

			else
			{
				System.out.println("It is a main category");
			}
		}
	}

	/**
	 * @param sg
	 * @param con
	 * @param trs
	 */
	private void subCategoryProducts(STforHelthkart sg, Connection con,	TestRS trs) {
		d.navigate().to("http://www.healthkart.com/"+sg.getAttr());
		getDocument();
		for(int i=0;i<sg.getN();i++)
		{
			try
			{
				sg.setBody(getDocument().select("body").select("#prod_grid"));
				for(Element pro:sg.getBody().select("div.grid_6.product"))
				{
					sg.setUrl(pro.select("a").attr("href"));
					sg.setAsin(sg.getUrl().substring(sg.getUrl().lastIndexOf("/")+1, sg.getUrl().lastIndexOf("?")));
					System.out.println("asin id is ::"+sg.getAsin());
					sg.setImg(pro.select("img").attr("src"));
					sg.setPname(pro.select("a").select("img").attr("alt"));
					productPrice(sg, pro);
					Elements bodydesc = productDescription(sg);
					sg.setNval(trs.nextval());
					sg.setRows(trs.CheckinDB(sg.getAsin()));
					if(sg.getRows()==0)
					{
						//insertProductsDB(con,asin,url,pgroup,pname,bodydesc.text());
						insertPFProducts(sg, con, bodydesc);

						//insertPFPriceTbl(con,price,nval+1);
						insertPFPrice(sg, con);

						//insertPFImgTbl(con,img,nval+1);
						insertPFITbl(sg, con);
					}
				}

				pagenationLogiMethod(sg);
			}
			catch(java.lang.NullPointerException ex)
			{
				System.out.println(ex);
				sg.setPagelogic(null);
				sg.setPurl(null);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}

	/**
	 * @param sg
	 */
	private void pagenationLogiMethod(STforHelthkart sg) {
		sg.setEdesc(getDocument().select("body").select("div.catalog_header").select("div.pagi").select("a").last());
		sg.setPurl(sg.getEdesc().attr("href"));
		sg.setPagelogic(sg.getEdesc().text());
		if(sg.getPagelogic().equalsIgnoreCase("Next →"))
		{
			//n+=1;
			sg.setN(sg.getN()+1);
			d.navigate().to("http://www.healthkart.com"+sg.getPurl());
			System.out.println("display page number::"+sg.getN());
		}
		else
		{
			System.out.println("do nothing");
		}
	}
	/**
	 * @param sg
	 * @param pro
	 */
	private void productPrice(STforHelthkart sg, Element pro) {
		sg.setPprice(pro.select("span.hk").text());
		if(sg.getPprice().equalsIgnoreCase(""))
		{
			sg.setPprice(pro.select("div.hk").text());
		}
		else
		{
			//pprice="0";
		}
		sg.setPprice(sg.getPprice().replace("Rs.", "").replace(",", "").trim());
		sg.setPrice(Integer.parseInt(sg.getPprice()));
	}
	/**
	 * @param sg
	 * @return
	 */
	private Elements productDescription(STforHelthkart sg) {
		d.navigate().to("http://www.healthkart.com"+sg.getUrl());
		getDocument();
		Elements bodydesc=getDocument().select("body").select("#description");
		//System.out.println("Description is::"+bodydesc.text());
		d.navigate().back();
		return bodydesc;
	}
	/**
	 * @param sg
	 * @param con
	 * @param bodydesc
	 */
	private void insertPFProducts(STforHelthkart sg, Connection con,
			Elements bodydesc) {
		try {
			sg.setIquery("insert into productfeed_products(id,asin,detailpageurl,productgroup,lastmodefieddate,merchantid,title, description) values(nextval('productfeed_products_id_seq'),?,?,?,CURRENT_DATE,?,?,?);");
			PreparedStatement ps=con.prepareStatement(sg.getIquery());
			ps.setString(1, sg.getAsin());
			ps.setString(2, "http://www.healthkart.com"+sg.getUrl());
			ps.setString(3, sg.getPgroup());
			ps.setInt(4, 159);
			ps.setString(5, sg.getPname());
			ps.setString(6, bodydesc.text());
			ps.executeUpdate();
			ps.close();
			System.out.println("product inserted successfully in productfeed_products table");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param sg
	 * @param con
	 */
	private void insertPFPrice(STforHelthkart sg, Connection con) {
		try {
			sg.setIquery("insert into productfeed_product_prices(id,pricetype,ammount,currencytcode,productid) values(nextval('productfeed_product_prices_id_seq'),1,?,'Rs.',?);");
			PreparedStatement ps=con.prepareStatement(sg.getIquery());
			ps.setInt(1, sg.getPrice());
			ps.setLong(2, sg.getNval());
			ps.executeUpdate();
			ps.close();
			System.out.println("product inserted successfully in productfeed_products_prices table");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param sg
	 * @param con
	 */
	private void insertPFITbl(STforHelthkart sg, Connection con) {
		try {
			sg.setIquery("insert into productfeed_product_images(id,imagetype,imageurl,productid) values(nextval('productfeed_product_images_id_seq'),1,?,?);");
			PreparedStatement ps=con.prepareStatement(sg.getIquery());
			ps.setString(1, sg.getImg());
			ps.setLong(2, sg.getNval());
			ps.executeUpdate();
			ps.close();
			System.out.println("product inserted successfully in productfeed_products_images table");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param sg
	 * @param con
	 */
	private void getCategoriesMethod(STforHelthkart sg, Connection con) {
		Document doc = Jsoup.parse(d.getPageSource());
		Elements body = doc.select("body").select("li");

		for(Element cat:body.select("a"))
		{
			sg.setCatname(cat.text());
			System.out.println(sg.getCatname());
			sg.setUrl("http://www.healthkart.com"+cat.attr("href"));
			System.out.println(sg.getUrl());
			if(sg.getCatname().equalsIgnoreCase("HealthKartPlus >"))
			{
				break;
			}
			else
			{
				insertPFC(sg, con);
			}
		}
	}
	/**
	 * @param sg
	 * @param con
	 */
	private void insertPFC(STforHelthkart sg, Connection con) {
		try {
			sg.setIquery("insert into productfeed_category(id,category,url,merchant_id ) values(nextval('productfeed_category_id_seq'),?,?,?);");
			PreparedStatement ps=con.prepareStatement(sg.getIquery());
			ps.setString(1, sg.getCatname());
			ps.setString(2, sg.getUrl());
			ps.setInt(3, 159);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/*private void getProducts()
	{}

	private void insertPFImgTbl(Connection con, String img, long nval) {
		// TODO Auto-generated method stub

	}
	private void insertPFPriceTbl(Connection con, int pprice, long nval) {
		// TODO Auto-generated method stub


	}
	private void insertProductsDB(Connection con, String asin, String url, String pgroup, String pname, String desc)
	{


	}
	 */


	private Document getDocument() {
		sg.setDoc(Jsoup.parse(d.getPageSource()));
		return sg.getDoc();
	}
	/*private void getCategories() {}
	private void insertDB(Connection con, String catname, String url) {

	}
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
