package com.dealwallet.webdriver.samples;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.text.Normalizer;
import java.util.Random;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
* Generic scraper object that contains the basic methods required to fetch
* and parse HTML content. Extended by other classes that need to scrape.
*
* @author David
*/
public class Scraper {

        public static String pageHTML = "http://www.futurebazaar.com/Handler/ProductShowcaseHandler.ashx?ProductShowcaseInput={%22PgControlId%22:1162861,%22IsConfigured%22:true,%22ConfigurationType%22:%22%22,%22CombiIds%22:%22%22,%22PageNo%22:1,%22DivClientId%22:%22ctl00_ContentPlaceHolder1_ctl 0_ctl03_Showcase%22,%22SortingValues%22:%22%22,%22ShowViewType%22:%22%22,%22PropertyBag%22:null,%22IsRefineExsists%22:true,%22CID%22:%22CU00089697%22,%22CT%22:0,%22TabId%22:0}&Location=&_=1367491183773"; // the HTML for the page
        public Document pageSoup; // the JSoup scraped hierachy for the page


        public String fetchPageHTML(String URL) throws IOException{
        	System.out.println("bbbbb"+URL);
            // this makes sure we don't scrape the same page twice
            if(Scraper.pageHTML != ""){
                return Scraper.pageHTML;
            }
System.out.println("bbbbb");
            System.getProperties().setProperty("httpclient.useragent", "spider");

            Random randomGenerator = new Random();
            int sleepTime = randomGenerator.nextInt(7000);
            try{
                Thread.sleep(sleepTime); //sleep for x milliseconds
            }catch(Exception e){
                // only fires if topic is interruped by another process, should never happen
            }

            String pageHTML = "";

            HttpClient httpclient = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(URL);

                HttpResponse response = httpclient.execute(httpget);
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    InputStream instream = entity.getContent();
                    String encoding = "UTF-8";

                    StringWriter writer = new StringWriter();
                    IOUtils.copy(instream, writer, encoding);

                    pageHTML = writer.toString();
                    
                    // convert entire page scrape to ASCII-safe string
                    pageHTML = Normalizer.normalize(pageHTML, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

                }

                return pageHTML;
        }

        public Document fetchPageSoup(String pageHTML){
            
            // this makes sure we don't soupify the same page twice
            if(this.pageSoup != null){
                return this.pageSoup;
            }
            
            if(pageHTML.equalsIgnoreCase("")){
                System.out.println("We have no supplied HTML to soupify.");
            }

            Document pageSoup = Jsoup.parse(pageHTML);

            return pageSoup;
        }
        
      
}