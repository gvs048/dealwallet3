package com.dealwallet.webdriver.selenium.Samles;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class GetPageSourceAfterJS {
    public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(java.util.logging.Level.OFF); /* comment out to turn off annoying htmlunit warnings */
        WebClient webClient = new WebClient();
        String url = "http://www.futurebazaar.com/categories/Home--Living-Luggage--Travel-Airbags--Duffel-bags/cid-CU00089575.aspx";
        System.out.println("Loading page now: "+url);
        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(30 * 1000); /* will wait JavaScript to execute up to 30s */

        String pageAsXml = page.asXml();
        System.out.println("Contains bucket? --> "+pageAsXml.contains("bucket"));

        //get divs which have a 'class' attribute of 'bucket'
        List<?> buckets = page.getByXPath("//div[@class='bucket']");
        System.out.println("Found "+buckets.size()+" 'bucket' divs.");

        //System.out.println("#FULL source after JavaScript execution:\n "+pageAsXml);
    }
}