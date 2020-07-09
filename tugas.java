package com.me.vd.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class tugas {
	public static void main(String [] args) throws FileNotFoundException{
		Scanner nama = new Scanner(System.in);
		System.out.println("Input file : ");
		String tambah = nama.next();
		System.out.println("Output file : ");
		String hasil = nama.next();

		File input = new File(tambah);
		Scanner in  = new Scanner(input);
		PrintWriter out = new PrintWriter(hasil);

		int angka = 1;

		while (in.hasNextLine()) {
			String aris = in.nextLine();
			out.printf("/* %d */ %S %n",angka,aris);
			angka++;
		}
		in.close();
		out.close();


	}

}
