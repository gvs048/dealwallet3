package com.dealwallet.webdriver.selenium.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestRS1
{
	public long nextval()
	{
		/*public static void main(String[] args)
	{*/
		Connection conn=null;
		long nval = 0;
		try
		{
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://192.168.3.127:5432/dealwallet_batch_dev","postgres","postgres123");
			//System.out.println("Connection is open now");
			Statement st=null;
			ResultSet rs=null;
			//String query="select nextval('productfeed_products_id_seq')";
			try {
				st = conn.createStatement();
				rs = st.executeQuery("SELECT nextval('productfeed_products_id_seq')");

				if (rs.next()) {
					//System.out.println(rs.getString(1));
					nval=Long.parseLong(rs.getString(1));
					//System.out.println(nval);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
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
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nval;
	}
	public ResultSet allDetails()
	{
		Statement st=null;
		ResultSet rs=null;
		Connection conn=null;
		try
		{
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://192.168.3.127:5432/dealwallet_batch_dev","postgres","postgres123");
			/*Statement st=null;
			ResultSet rs=null;*/
			String query="select p.id,p.asin,p.detailpageurl,p.productgroup,p.title,p.description, pp.ammount,  pi.imageurl from productfeed_product_images pi, productfeed_product_prices pp, productfeed_products p where p.id=pp.productid and p.id=pi.productid and p.detailpageurl LIKE 'http://www.healthkart.com/%'";
			try {
				st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = st.executeQuery(query);

				if (rs.next()) {
					System.out.println(rs.getString(1));
					//nval=Long.parseLong(rs.getString(1));
					//System.out.println(nval);
					System.out.println("rs having data");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

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
			try {
				//st.close();
				//rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rs;
	}
	public int CheckinDB(String asin) {
		Statement st=null;
		ResultSet rs=null;
		Connection conn=null;
		int rows=0;
		try
		{
			Class.forName("org.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://192.168.3.127:5432/dealwallet_batch_dev","postgres","postgres123");
			String query="select count(*) from productfeed_products where asin='"+asin+"'";
			try {
				st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				rs = st.executeQuery(query);

				if (rs.next()) {
					rows=rs.getInt(1);
					System.out.println(rs.getString(1));
					//nval=Long.parseLong(rs.getString(1));
					//System.out.println(nval);
					//System.out.println("rs having data");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

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
			try {
				//st.close();
				//rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return rows;
	}
}
