package com.dealwallet.webdriver.samples;

import org.testng.annotations.Test;

public class group1 {
	@Test(groups="G1")
	public void method1() {  
	  System.out.println("This is Method1_GroupTest1");
	}  

	@Test(groups="G2")
	public void method2() {  
	System.out.println("This is Method2_GroupTest1");
	}  

	@Test(groups="G1")
	public void method3() {  
	System.out.println("This is Method3_GroupTest1");
	}  

	@Test(groups="G2")
	public void method4() {  
	System.out.println("This is Method4_GroupTest1");
	}


}
