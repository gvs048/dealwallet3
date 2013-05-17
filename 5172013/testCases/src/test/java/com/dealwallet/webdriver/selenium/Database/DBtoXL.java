package com.dealwallet.webdriver.selenium.Database;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import org.testng.annotations.Test;

public class DBtoXL 
{
	public static void main(String[] args)
	{
		String con="jdbc:postgresql://192.168.3.127:5432/DWDEVTEST";
		String query="SELECT id, asin, detailpageurl, productgroup, lastmodefieddate, merchantid, title, description FROM productfeed_products;";
		ResultSet rs;
		Connection conn;
		Statement stmt;
		
		try {
			FileWriter fw = new FileWriter("C:/Users/Tsss-Pc1/Desktop/FilpKartProducts.csv");
		    fw.append("id");fw.append(',');
		    fw.append("asin");fw.append(',');
		    fw.append("detailpageurl");fw.append(',');
		    fw.append("productgroup");fw.append(',');
		    fw.append("lastmodefieddate");fw.append(',');
		    fw.append("merchantid");fw.append(',');
		    fw.append("title");fw.append(',');
		    fw.append("description");fw.append(',');
		    fw.append('\n');
		    
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(con, "postgres", "postgres123");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next())
			{
				//dbPrice = rs.getString("ammount");
				// pageUrl = rs.getString("detailpageurl");
				fw.append(rs.getString("id"));fw.append(',');
			    fw.append(rs.getString("asin"));fw.append(',');
			    fw.append(rs.getString("detailpageurl"));fw.append(',');
			    fw.append(rs.getString("productgroup"));fw.append(',');
			    fw.append(rs.getString("lastmodefieddate"));fw.append(',');
			    fw.append(rs.getString("merchantid"));fw.append(',');
			    fw.append(rs.getString("title"));fw.append(',');
			    fw.append(rs.getString("description"));fw.append(',');
			    fw.append('\n');
			}
			fw.flush();
			fw.close();
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
