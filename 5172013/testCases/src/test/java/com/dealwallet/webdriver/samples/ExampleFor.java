package com.dealwallet.webdriver.samples;

import java.io.IOException;
import java.util.Scanner;

public class ExampleFor {
	
		/*int i=1;
	for(;;)
	{
		if(i>10)
			break;
		System.out.println("i value is: "+ i);
		i++;
	}*/
		public static void main(String args[]) {
			//File evidence = new File(“cookedBooks.txt”);
			Scanner keyboard = new Scanner(System.in);
			String reply;
			char ch;
			do {
			System.out.print("Delete evidence? (y/n) ");
			reply = keyboard.next();
			ch = reply.charAt(0);
			System.out.println("reply is:"+reply);
			} while (ch != 'y' && ch != 'n');
			if (reply.equalsIgnoreCase("y")) {
				System.out.println("Okay, here goes...");
			//evidence.delete();
				System.out.println("The evidence has been deleted.");
			} else {
				System.out.println("Sorry, buddy. Just asking.");
			}
			}
	}

