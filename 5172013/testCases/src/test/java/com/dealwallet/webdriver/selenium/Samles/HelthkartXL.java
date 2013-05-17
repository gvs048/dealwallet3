package com.dealwallet.webdriver.selenium.Samles;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

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

public class HelthkartXL
{
	WebDriver d;
	STforHelthkart sg=new STforHelthkart();
	FileOutputStream exlFileName;
	WritableWorkbook exlWorkBook;
	WritableSheet exlWorkSheet;
	FileWriter f;
	//Label cellContent;
	//= new FileOutputStream("D:/testExcel.xls");

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
		//sg.setConn(OpenDBConnection());
		//TestRS trs= new TestRS();
		d.get("http://www.healthkart.com/");
		d.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		try
		{
			//getCategoriesMethod(sg, sg.getConn());
			getProductsMethod(sg);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		//CloseDBConnection(sg.getConn());

	}

	/**
	 * @param sg
	 */
	private void getProductsMethod(STforHelthkart sg) {
		//Elements body;
		try {
			f=new FileWriter("D:/helthkartproduts.xls");
			f.append("ID , ASIN_ID , DETAILPAGE_URL , PRODUCT_GROUP , MER_ID , PRODUCT_NAME , PRICE , IMAGE_URL , PROD_DESC ");
			f.append("\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		sg.setBody(getDocument().select("body").select("li").select("a"));
		for(Element cat:sg.getBody())
		{
			sg.setAttr(cat.attr("href"));
			sg.setItems(sg.getAttr().split("/"));
			if(sg.getItems().length==4)
			{
				sg.setPgroup(sg.getItems()[3]);
				subCategoryProducts(sg);
			}

			else
			{
				System.out.println("It is a main category");
			}
		}
		
		try {
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @param sg
	 */
	private void subCategoryProducts(STforHelthkart sg) {
		d.navigate().to("http://www.healthkart.com/"+sg.getAttr());
		getDocument();
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
				sg.setBodydesc(productDescription(sg));
				productDescription(sg);
				if(sg.getRows()==0)
				{
					sg.setI(sg.getI()+1);
					insertPFProducts(sg, sg.getI());
				}
			}
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
	 * @param i 
	 */
	private void insertPFProducts(STforHelthkart sg, int i) {
		try {
			/*sg.setIquery("insert into productfeed_products(id,asin,detailpageurl,productgroup,lastmodefieddate,merchantid,title, description) values(nextval('productfeed_products_id_seq'),?,?,?,CURRENT_DATE,?,?,?);");
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
			 */
			f.append(String.valueOf(i));f.append(",");
			f.append(sg.getAsin());f.append(",");
			f.append("http://www.healthkart.com"+sg.getUrl());f.append(",");
			f.append(sg.getPgroup());f.append(",");
			f.append("159");f.append(",");
			f.append(sg.getPname().replace(",", "").replace(";", ""));f.append(",");
			f.append(sg.getPprice());f.append(",");
			f.append(sg.getImg());f.append(",");
			f.append(sg.getBodydesc().text().replace(",", "").replace(";", ""));f.append(",");
			f.append("\n");
			f.flush();
		}
		catch (IOException e) {
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
		try {
			f=new FileWriter("D:/helthkartcategories.xls");
			f.append("ID , CAT_NAME , CAT_URL, MER_ID ");
			f.append("\n");

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
					sg.setI(sg.getI()+1);
					insertPFC(sg, sg.getI());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @param sg
	 * @param con
	 */
	private void insertPFC(STforHelthkart sg, int i) {
		try {
			f.append(String.valueOf(i));
			f.append(",");
			f.append(sg.getCatname().replace(",",""));
			f.append(",");
			f.append(sg.getUrl());
			f.append(",");
			f.append("159");
			f.append(",");
			f.append("\n");
			f.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}


	private Document getDocument() {
		sg.setDoc(Jsoup.parse(d.getPageSource()));
		return sg.getDoc();
	}

}
