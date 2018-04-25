package comments;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
//import java.util.Properties;

import edu.stanford.nlp.*;

public class Read {

	
	public String readFile(File file) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		try{
			 br = new BufferedReader(new FileReader(file));
	         
	         String text;
	         while ((text = br.readLine()) != null) {
	        	 sb.append(text +"\n");
	         }
			br.close();
		}catch(Exception e) {}
	
		return sb.toString();
	 }	
	}
