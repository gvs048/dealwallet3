package com.dealwallet.webdriver.selenium.dealwalletadmin;

//package com.tsss;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.*;

public class IndiaVarta {

	private static final int TWO_HUNDRED = 200;
	private static final int THREE_HUNDRED = 300;
	private static final int FOUR_HUNDRED = 400;

	private MultiThreadedHttpConnectionManager connectionManager;
	private HttpClient client;

	/**
	 *
	 */
	public IndiaVarta() {
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
			} else {
				client = new HttpClient(connectionManager);
				resp = client.executeMethod(method);
			}
			if (resp == TWO_HUNDRED) {
				final byte[] respStringBytes = method.getResponseBody();
				if (respStringBytes != null && respStringBytes.length > 0) {
					respString = new String(respStringBytes, "UTF-8");

				}
			}

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

		Document pageSoup = Jsoup.parse(pageHTML);

		return pageSoup;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException
	{
		// TODO Auto-generated method stub
		String htmlString = null;
		String htmlString1 = null;
		IndiaVarta fbz = new IndiaVarta();

		String url = "http://www.shopping.indiatimes.com";
		htmlString = fbz.doLogin(url);
		final Document Document = fbz.fetchPageSoup(htmlString);

		Elements body = Document.select("body");
		// System.out.println("body"+body);
		Elements mainCategories =
				body.select("#top-nav").select("#headermenu").select("li");
		System.out.println("@#"+mainCategories);
		for(Element mainCategory : mainCategories)
		{
			String url1 = mainCategory.select("a").attr("href");
			//System.out.println("url1"+url1);


			if(url1.length() > 1)
			{
				String url2 = "http://www.shopping.indiatimes.com"+url1;
				System.out.println("url2"+url2);
				htmlString1 = fbz.doLogin(url2);
				final Document document1 = fbz.fetchPageSoup(htmlString1);
				Elements body1 = document1.select("body");
				Elements subCategories =
						body1.select("#top-nav").select(".megasubmenu").select(".col").select("li");
				System.out.println("subCategories"+subCategories);
				for(Element subCategory : mainCategories)
				{
					String subCategoryName =
							subCategory.select("#top-nav").select(".megasubmenu").select("a").text();
					String url3 =
							subCategory.select("#top-nav").select(".megasubmenu").select("a").attr("href");
					System.out.println(subCategoryName);
					//System.out.println("http://www.indiatimes.com"+url3);

					if(url3.equals("") || url3 == null)
					{
						continue;
					}

					System.out.println("http://www.shopping.indiatimes.com" + url3);
				}
				System.out.println("-------------------------");

			}
		}
	}
}





	/*int PAGESIZE = 32;
		int pageCount = 1;
		int pcount = 1;
		int pageNumber = 0;
		int index = 0;
		boolean t = false;
		 do {
		index = PAGESIZE * pageNumber;
		System.out.println("pageNumber++++" +pageNumber);
		String url = "http://www.shopping.indiatimes.com/books/books-arts-photography/?start="+ index;
		htmlString = fbz.doLogin(url);
		final Document document = fbz.fetchPageSoup(htmlString);
		Elements body = document.select("body");
		// System.out.println("body"+body);

		 Elements products =body.select("div[class=productcoloumn zur]");
		 // System.out.println(""+products);
		 for (Element product : products)
		 {
		 System.out.println("product title:"
		 +product.select(".listproductthumb").select("a").select("img").attr("alt"));
		 System.out.println("product url:"
		 +product.select(".listproductthumb").select("a").attr("href"));
		 String st1 =
		 product.select(".listproductthumb").select("a").attr("href");
		 String[] arry = st1.split("/");
		 System.out.println("asinId: "+arry[arry.length-1]);

		 System.out.println("image url:"
		 +product.select(".listproductthumb").select("a").select("img").attr("data-original"));

		 // long finalPrice =(long)
		// Double.parseDouble(product.select(".productdetail").select(".newprice").select(".price").text().replace("Rs.",
		// "").trim());
		 System.out.println("product price:"+product.select(".productdetail").select(".newprice").select(".price").text().replace("Rs.",
		 "").trim());
		// System.out.println("product price:" + finalPrice);
		 System.out.println("product coutnt " + pcount++);
//		 System.out.println("--------------------------");


		 }
		 System.out.println("pageCount "+pageCount);

		Elements cat1 = body.select("div[class=frt pager]");

	//	System.out.println("@@@" + cat1);
		// String s1 = cat1.select("a").text();
		 //System.out.println("***"+s1);
		 t = cat1.select("a").text().contains("Next");
		// System.out.println(t);
		 pageCount++;
		 pageNumber++;
		 }while(t);

		 System.out.println("__________________");
		// }
	 */
