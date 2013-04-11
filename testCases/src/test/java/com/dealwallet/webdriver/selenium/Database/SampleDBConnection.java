package com.dealwallet.webdriver.selenium.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SampleDBConnection
{
	public static void main(String args[]) throws SQLException
	{
		Connection conn=null;
		try
		{
			// Load the Driver class.
			Class.forName("org.postgresql.Driver");
			// Create the connection using the static getConnection method
			//jdbc:postgresql://
			conn=DriverManager.getConnection("jdbc:postgresql://192.168.10.105:5432/DWDEVTEST","postgres","postgres123");
			//Create a Statement class to execute the SQL statement
			Statement stmt=conn.createStatement();
			//Execute the SQL statement and get the results in a Resultset
			ResultSet rs=stmt.executeQuery("select * from productfeed_products;");
			// Iterate through the ResultSet, displaying two values
			// for each row using the getString method
			int rowcount=0;
			System.out.println("no.of rows are::"+rs.getFetchSize());
			while(rs.next())
			{
				//productgroup,title,merchantid,,,id,merchant_name,merchant_site_url
				System.out.println(rs.getString("detailpageurl"));
//				int resultset = rs.getRow();
//				System.out.println("row count is "+resultset);
				//System.out.println("no.of rows are::"+rs.);
				rowcount++;
			}
			System.out.println("no.of rows are::"+rowcount);
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
