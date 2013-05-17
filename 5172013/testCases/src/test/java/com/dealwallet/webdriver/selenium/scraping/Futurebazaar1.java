package com.dealwallet.webdriver.selenium.scraping;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;

public class Futurebazaar1 {

	private static final int TWO_HUNDRED = 200;
	private static final int THREE_HUNDRED = 300;
	private static final int FOUR_HUNDRED = 400;

	private MultiThreadedHttpConnectionManager connectionManager;
	private HttpClient client;

	/**
     *
     */
	public Futurebazaar1() {
		connectionManager = new MultiThreadedHttpConnectionManager();
		final HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		params.setDefaultMaxConnectionsPerHost(THREE_HUNDRED);
		params.setMaxTotalConnections(FOUR_HUNDRED);
		connectionManager.setParams(params);
		client = new HttpClient(connectionManager);
	}

	public String doLogin(final String url) {

		String respString = null;
		final HttpMethod method = new GetMethod(url);
		try {
			final Header contentHeader = new Header("Content-type",
					"html/json; charset=UTF-8");
			method.addRequestHeader(contentHeader);
			int resp = 0;
			if (client != null) {
				resp = client.executeMethod(method);
				System.out.println(resp);
			} else {
				client = new HttpClient(connectionManager);
				resp = client.executeMethod(method);
				//System.out.println(resp);
			}
			if (resp == TWO_HUNDRED) {
				final InputStream respStringBytes = method.getResponseBodyAsStream();
				/*if (respStringBytes != null && (respStringBytes).length > 0) {*/
					//respString = new String(respStringBytes);
					//System.out.println(respString);
				}
		//	}

		} catch (final Exception e) {
			System.err.println(e);
		} finally {
			method.releaseConnection();
		}
		return respString;
	}

	private Document fetchPageSoup(final String pageHTML) {

		if (pageHTML.equalsIgnoreCase("")) {
			System.out.println("ddd");
		}

		//Document pageSoup = Jsoup.parse(pageHTML);
		//Document pageSoup = Jsoup.parse(pageHTML, "10000");
		Document pageSoup = null;
		try {
			//pageSoup = Jsoup.connect(pageHTML).post();
			
			pageSoup = Jsoup.connect("http://www.futurebazaar.com/categories/Home--Living-Luggage--Travel-Airbags--Duffel-bags/cid-CU00089575.aspx").userAgent("Mozilla").get();
		    //pagesoup = Jsoup.parse(pageSoup); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pageSoup;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		/*// TODO Auto-generated method stub
		//String htmlString = null;      
		String htmlString1 = null;
		Futurebazaar1 fbz = new Futurebazaar1();

		

		String url = "http://www.futurebazaar.com/categories/Home--Living-Luggage--Travel-Airbags--Duffel-bags/cid-CU00089575.aspx";
		String htmlString = fbz.doLogin(url);
		System.out.println(htmlString);
		final Document document = fbz.fetchPageSoup(url);
		//System.out.println(document);
		Elements body = document.select("body");
		//System.out.println(body);
    	Elements products =body.select("#Productshowcase");
		System.out.println(products);*/
		
		/* String html = "http://www.futurebazaar.com/categories/Home--Living-Luggage--Travel-Airbags--Duffel-bags/cid-CU00089575.aspx";
		    org.jsoup.nodes.Document doc = Jsoup.parse(html);

		    Elements bodies = doc.select("body");
//System.out.println(bodies);
for(Element body : bodies )
{
    System.out.println(body.getAllElements());
}
		 System.out.println("__________________");*/
		// }
	
	
		// Connect to the website and parse it into a document
		Document doc = Jsoup.connect("http://www.futurebazaar.com/categories/Home--Living-Luggage--Travel-Airbags--Duffel-bags/cid-CU00089575.aspx").get();

	/*	// Select all elements you need (se below for documentation)
		Elements elements = doc.select("div[class=chpstaff] p");

		// Get the text of the first element
		String instructor = elements.first().text();

		// eg. print the result
*/		System.out.println(doc);
	
	}
}

