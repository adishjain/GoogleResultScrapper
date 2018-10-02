package PrimeNumbers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class UsingFileHandling {

	public static void main(String[] args) {
		
		String filename=null;
		String text=null;
		
		System.out.println("Enter the filename");
		Scanner s=new Scanner(System.in);
		filename=s.nextLine();
		
		System.out.println("Enter the text you want to add into file "+filename);
		text=s.nextLine();
		
		
		FileHandling fh=new FileHandling(text, filename+".txt");
		fh.AppendtoFile();
		
	   
	}
}


