package com.dealwallet.webdriver.selenium05082013.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jxl.Sheet;
import jxl.Workbook;
import com.dealwallet.webdriver.samples.SendEmail;
import org.testng.annotations.Test;

public class SampleStoresDBComparision {

	@Test
	public void test()throws Exception
	{
		SendEmail sm=new SendEmail();
		Connection conn=null;
		try
		{
			String res="";
			FileInputStream fi = new FileInputStream("C:\\Users\\Tsss-Pc1\\Desktop\\stores-in-DB.xls");
			Workbook w = Workbook.getWorkbook(fi);
			Sheet s = w.getSheet(0);

			// Load the Driver class.
			Class.forName("org.postgresql.Driver");
			// Create the connection using the static getConnection method
			//jdbc:postgresql://
			conn=DriverManager.getConnection("jdbc:postgresql://192.168.3.128:5432/dealwallet_dev","postgres","postgres123");
			//Create a Statement class to execute the SQL statement
			Statement stmt=conn.createStatement();

			for(int row=0; row <s.getRows();row++)
			{
				String id = s.getCell(0, row).getContents();
				String sname=s.getCell(1,row).getContents();
				//Execute the SQL statement and get the results in a Resultset
				ResultSet rs=stmt.executeQuery("select * from store where id="+id+"");
				if(!rs.next())
				{
					res+=sname+" :: store is not in DB :: it may be deleted "+"\n" ;
				}
			}
			if(res.equalsIgnoreCase(""))
			{
				res="All stores are there, no one is deleted";
				System.out.println(res);
			}
			else
				System.out.println(res);

			sm.sendingMail(res);
		}
		catch(SQLException se)
		{
			System.out.println("Sql Exception"+se);
		}
		catch(ClassNotFoundException cnfe)
		{
			System.out.println("Class not found Exception"+cnfe);
		}

		finally
		{
			try {
				if(conn != null)
					conn.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
