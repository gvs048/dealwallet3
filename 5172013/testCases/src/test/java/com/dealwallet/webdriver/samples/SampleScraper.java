package com.dealwallet.webdriver.samples;

import java.io.IOException;

import org.jsoup.nodes.Document;

public class SampleScraper {

	public static void main(String[] args)
	{
		System.out.println("aaaaaa");
		Scraper s= new Scraper();
		System.out.println("aaaaaa");
		Document d = s.fetchPageSoup("www.featurebazaar.com");
		System.out.println("aaaaaa");
		System.out.println(d);
		try {
			System.out.println("bbbb");
			s.fetchPageHTML("www.featurebazaar.com");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
