package com.me.vd.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Pbo61 {
	public static void main(String [] args) throws FileNotFoundException{
		Scanner console = new Scanner(System.in);
		System.out.println("Input File : ");
		String inputFileName = console.next();
		System.out.println("Output File : ");
		String outputFileName = console.next();
		
		File inputFile  = new File(inputFileName);
		Scanner in = new Scanner(inputFile);
		PrintWriter out = new PrintWriter(outputFileName);
		
		int lineNumber = 1;
		
		while (in.hasNextLine()) {
			String line  = in.nextLine();
			System.out.println("/* "+ lineNumber + "*/ "+ line);
			lineNumber++;
			
		}
		in.close();
		out.close();
		
	}

}
