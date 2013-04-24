package com.dealwallet.webdriver.selenium.Samles;
import com.dealwallet.webdriver.selenium.Samles.STforHelthkart;
public class SettersGettersChecking {

	/**
	 * @param args
	 */


	public static void main(String[] args) {

		STforHelthkart sg=new STforHelthkart();
		sg.getUrl();
		System.out.println(sg.getUrl());

		sg.setUrl("aaaaaaaaaaaaa");
		sg.getUrl();
		System.out.println(sg.getUrl());

	}

}
