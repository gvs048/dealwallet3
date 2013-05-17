package com.dealwallet.webdriver.selenium.Database;




import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import jxl.write.WritableWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.dealwallet.webdriver.selenium.Common.Util;

/**
 * @author Tsss-Pc1
 *
 */

public class DBPgrm2 {

    WebDriver d;
    String browser, pageUrl, dbPrice, price, price1,merchantPrice;
    String dbDriverUrl, dbUsername, dbPassword, dbServerUrl, dbName;
    WritableWorkbook wwb;
    FileWriter writer;
    int j = 0;

    /**This Method is used to get details from the util package 
     * and assign the values to the variables
     * @dbDriverUrl
     * @dbUsername
     * @dbPassword
     * @dbServerUrl
     * @dbName
     */
    @BeforeMethod
    public void start()
    {
    	
        Util util = new Util();
        dbDriverUrl = util.getdbDriverUrl();
        dbUsername = util.getdbUsername();
        dbPassword = util.getdbPassword();
        dbServerUrl = util.getdbServerUrl();
        dbName = util.getdbName();
    }

    /**
     * @throws Exception
     */
    /**
     * @throws Exception
     */
    /**
     * @throws Exception
     */
    /**
     * @throws Exception
     */
    /**
     * @throws Exception
     */
    /**
     * @throws Exception
     */
    @Test
    public void startDB() throws Exception
    {
    	writer=new FileWriter("C:/Users/Tsss-Pc1/Desktop/FilpKartProducts.csv");
    	writer.append("dbPrice");writer.append(',');
	    writer.append("merchantPrice");writer.append(',');
	    writer.append("productId");writer.append(',');
	    writer.append("pageUrl");writer.append(',');
	    writer.append('\n');
	    FileWriter fw = new FileWriter("C:/Users/Tsss-Pc1/Desktop/FilpKartProductsdummy.xls");
	    fw.append("productId");fw.append(',');
	    fw.append("dbPrice");fw.append(',');
	    fw.append('\n');
        for (int productid = 1786980; productid < 1898452; productid++)
        {
            Class.forName(dbDriverUrl);
            Connection conn = DriverManager.getConnection("jdbc:postgresql://"+ dbServerUrl +":5432/" + dbName + "", "" + dbUsername+ "", "" + dbPassword + "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select e.detailpageurl,e.id,e.merchantid,d.ammount,d.productid from productfeed_products e,productfeed_product_prices d where d.productid="+ productid + " AND e.id=" + productid + "");
            while (rs.next())
            {
                dbPrice = rs.getString("ammount");
                pageUrl = rs.getString("detailpageurl");
            }
            rs.close();
            stmt.close();
            conn.close();
            Util util = new Util();
            d = util.getbrowser();
            d.navigate().to(pageUrl);
            d.manage().window().maximize();
            d.manage().timeouts().implicitlyWait(60, TimeUnit.MINUTES);
            String title = d.getTitle();
            if (title.equals("Page Not Found"))
            {
                System.out.println("there is no poduct available in merchant site,that product id is:"+ productid);
                fw.append("" + productid + "");fw.append(",");
                fw.append("" + dbPrice + "");fw.append(",");
                fw.append("\n");
                fw.flush();
                d.close();
            }
            else
            {
                price = d.findElement(By.xpath("//div[3]/div[2]/div/div/div/span")).getText();
                merchantPrice = price.substring(4);
                if (dbPrice.equals(merchantPrice))
                {
                    System.out.println("db price:" + dbPrice + ",merchant price:"+ merchantPrice + ",Price same values for::"+ productid);
                }
                else
                {
                    System.out.println("db price:" + dbPrice + ",merchant price:"+ merchantPrice + ",Price diff values for::"+ productid);
                }
                d.close();
                writer.append(""+dbPrice+"");writer.append(',');
        	    writer.append(""+merchantPrice+"");writer.append(',');
        	    writer.append(""+productid+"");writer.append(',');
        	    writer.append(""+pageUrl+"");writer.append(',');
                writer.append('\n');

                writer.flush();
            }
        }
        fw.close();
        writer.close();
    }
}