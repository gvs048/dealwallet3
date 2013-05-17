package com.dealwallet.webdriver.samples;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class HtmlUnitSample {
	/*@Test
	public void homePage() throws Exception {
	    final WebClient webClient = new WebClient();
	    final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	    Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
System.out.println(page.getTitleText());
	    final String pageAsXml = page.asXml();
	    Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

	    final String pageAsText = page.asText();
	    Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
System.out.println(pageAsText);
	    webClient.closeAllWindows();
	}
	*/
	
/*	@Test
	public void homePage_Firefox() throws Exception {
	    final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
	    final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	    Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

	    webClient.closeAllWindows();
	}*/
	
	/*@Test
	public void getElements() throws Exception {
	    final WebClient webClient = new WebClient();
	    final HtmlPage page = webClient.getPage("http://www.google.co.in/");
	    final HtmlDivision div = page.getHtmlElementById("lga");
	    final HtmlAnchor anchor = page.getAnchorByHref("https://mail.google.com/mail/?tab=wm");
System.out.println(div);
System.out.println(anchor);
	    webClient.closeAllWindows();
	   	   
	}*/
	
	
	@Test
	public void xpath() throws Exception {
	    final WebClient webClient = new WebClient();
	    final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");

	    //get list of all divs
	    final List<?> divs = page.getByXPath("//div");
System.out.println(divs);
	    //get div which has a 'name' attribute of 'John'
	    final HtmlDivision div = (HtmlDivision) page.getByXPath("//div[@id='bodyColumn']").get(0);
System.out.println(div);
	    webClient.closeAllWindows();
	}
	
	
}
