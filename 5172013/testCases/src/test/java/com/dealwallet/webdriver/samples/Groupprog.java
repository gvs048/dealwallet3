package com.dealwallet.webdriver.samples;

import org.testng.annotations.Test;
public class Groupprog {
	

	

	 

	@Test(groups="G2")
	public void method1() {  
	  System.out.println("This is Method1_GroupTest2");
	}  

	@Test(groups="G2")
	public void method2() {  
	System.out.println("This is Method2_GroupTest2");
	}  

	@Test(groups="G1")
	public void method3() {  
	System.out.println("This is Method3_GroupTest2");
	}  

	@Test(groups="G3")
	public void method4() {  
	System.out.println("This is Method4_GroupTest2");
	

	}

}
