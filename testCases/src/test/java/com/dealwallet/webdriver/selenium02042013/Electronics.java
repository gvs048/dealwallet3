package com.dealwallet.webdriver.selenium02042013;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Electronics {
	WebDriver d;

	@BeforeMethod
	public void start() {
		d = new FirefoxDriver();

	}

	@AfterMethod
	public void end() {
		d.quit();
	}

	@Test
	public void testAandP() {
		d.get("http://www.dealwallet.com");
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String pcode = d.getWindowHandle();
		d.findElement(By.linkText("Products")).click();
		d.findElement(By.xpath("//ul[8]/li/a")).click();
		for (int i = 1; i <= 3; i++) {
			for (int k = 1; k <= 10; k++) {
				d.findElement(By.xpath("//div[" + k + "]/div/div[2]/p/a"))
						.click();
				String ammount = d.findElement(By.cssSelector("span.price"))
						.getText();
				ammount = ammount.replace(",", "").substring(1).trim();
				d.findElement(By.xpath("//a/span")).click();
				for (String ccode : d.getWindowHandles()) {
					d.switchTo().window(ccode);
				}
				if (d.getTitle().equalsIgnoreCase("Page Not Found")) {
					System.out.println("Page Not Found Error");
				} else {
					String s = d.getCurrentUrl();
					System.out.println("url is::" + s);
					String[] temp = s.split("/");
					String sitename = temp[2];
					System.out.println("site name is::" + sitename);
					int size = 0;
					String price;
					switch (sitename) {

					case "www.homeshop18.com":
						size = d.findElements(By.id("hs18Price")).size();
						if (size != 0) {
							price = d.findElement(By.id("hs18Price")).getText();
							price = price.substring(1).replace(",", "").trim();
							if (price.equals(ammount)) {
								System.out.println("Dw Price::" + ammount
										+ " and Marchent price::" + price
										+ "  both are equal");
							} else {
								System.out.println("Dw Price::" + ammount
										+ " and Marchent price::" + price
										+ " both are NOT equal");
							}
						} else {
							System.out.println("NO PRICE TO BE DISPLAYED");
						}
						break;

					case "www.yebhi.com":
						size = d.findElements(
								By.xpath("//div[5]/div[2]/div/div[2]/p"))
								.size();
						if (size != 0) {
							price = d.findElement(
									By.xpath("//div[5]/div[2]/div/div[2]/p"))
									.getText();
							price = price.substring(1);
							String[] parts = price.split(":");
							int len = parts.length;
							if (len == 4) {
								price = parts[1].replace(",", "").trim();
								if (price.equals(ammount)) {
									System.out.println("Dw Price::" + ammount
											+ " and Marchent price::" + price
											+ " both are equal");
								} else {
									System.out.println("Dw Price::" + ammount
											+ " and Marchent price::" + price
											+ " both are NOT equal");
								}
							} else {
								price = parts[0].replace(",", "").trim();
							}
						} else {
							System.out.println("NO PRICE TO BE DISPLAYED");
						}
						break;

					case "www.naaptol.com":
						size = d.findElements(By.xpath("//li/strong")).size();
						if (size != 0) {
							price = d.findElement(By.xpath("//li/strong"))
									.getText();
							price = price.substring(1).replace(",", "").trim();
							if (price.equals(ammount)) {
								System.out.println("Dw Price::" + ammount
										+ " and Marchent price::" + price
										+ "  both are equal");
							} else {
								System.out.println("Dw Price::" + ammount
										+ " and Marchent price::" + price
										+ " both are NOT equal");
							}

						} else {
							System.out.println("NO PRICE TO BE DISPLAYED");
						}
						break;

					case "www.snapdeal.com":
						size = d.findElements(By.xpath("//strong/span")).size();
						if (size != 0) {
							price = d.findElement(By.xpath("//strong/span"))
									.getText();
							price = price.substring(1).replace(",", "").trim();
							if (price.equals(ammount)) {
								System.out.println("DB Price::" + ammount
										+ " and Marchent price::" + price
										+ "  both are equal");
							} else {
								System.out.println("DB Price::" + ammount
										+ " and Marchent price::" + price
										+ " both are NOT equal");
							}
						} else {
							System.out.println("NO PRICE TO BE DISPLAYED");
						}
						break;

					default:
						System.out.println("Site not found");
					}
				}
				d.close();
				System.out.println("++++++++++++++++++++++");
				d.switchTo().window(pcode);
				d.navigate().back();
			}
			d.findElement(By.linkText("Next")).click();
		}
	}
}
