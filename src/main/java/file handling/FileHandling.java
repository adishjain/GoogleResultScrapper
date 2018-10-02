package PrimeNumbers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class FileHandling {
	
	private String text=null;
	private String filename=null;
	
	public FileHandling(String text,String filename) {
		this.text=text;
		this.filename=filename;
	}
	
	public void AppendtoFile()
	{
		
		File file = new File(this.filename);
	    FileWriter writer;
	    try {
	        writer = new FileWriter(file, true);
	        PrintWriter printer = new PrintWriter(writer);
	        printer.append("\n"+this.text);
	        printer.close();
	    } catch (IOException e) {
	        
	        e.printStackTrace();
	    }
			
	}
}
