//Use regular expressions to match Emails, Web links, Phonenumbers
package searchEngine;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.reflect.*;


public class RegExpression {
	
	public static String directory = "D:\\Search Engine Project\\WebToTextFiles";
	
	public static boolean findEmailAdresses(String textfile) {

	     // Create a Pattern object
	     Pattern r = Pattern.compile("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b");
	     Boolean isfound = false;

	     // Now create matcher object.
	     Matcher m = r.matcher(textfile);
		     while (m.find( )) {
		         System.out.println("Email Address : " + m.group(0));
		         isfound = true;
		     }
		     return isfound;
	     
	 }

	 public static boolean findWebLinks(String textfile) {

	     // Create a Pattern object
	     Pattern r = Pattern.compile("(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
	     Boolean isfound = false;
	     // Now create matcher object.
	     Matcher m = r.matcher(textfile);
	     while (m.find( )) {
	         System.out.println("WebLink : " + m.group(0));
	         isfound = true;
	     } 
		 return isfound;
	 }
	 
	 public static boolean findContactNumbers(String textfile) {
		 String pattern = "(\\()?(\\d){3}(\\))?[- ](\\d){3}-(\\d){4}";
		 Boolean isfound = false;

	     // Create a Pattern object
	     Pattern r = Pattern.compile(pattern);

	     // Now create matcher object.
	     Matcher m = r.matcher(textfile);
	     while (m.find( )) {
	         System.out.println("Contact Number : " + m.group(0));
	         isfound = true;
	     } 
		 return isfound;
	 }	 
	 
	 public static void findPatterns(int choice) throws NoSuchMethodException,  
	 InvocationTargetException, IllegalAccessException, IOException {
		
		// create hashmap to store the values
		HashMap<Integer, Method> hashMap = new HashMap<Integer, Method>();
		Boolean isfound = false;
		final File newDirectory = new File(directory);
		
		// store the Methods in the HashMap
		try {
				Class[] cArg = new Class[1];
				cArg[0] = String.class;
				hashMap.put(1, RegExpression.class.getMethod("findEmailAdresses", cArg));
				hashMap.put(2, RegExpression.class.getMethod("findContactNumbers", cArg));
				hashMap.put(3, RegExpression.class.getMethod("findWebLinks", cArg));
				
				
		} catch (Exception e) {
//			e.printStackTrace();
		}

		
		for (final File fileEntry : newDirectory.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	String pathname = fileEntry.getPath();
	        	FileReader fileReader = new FileReader(pathname);
	        	
	        	
	        	 try (BufferedReader br = new BufferedReader(fileReader))
	             {
	                 String line;
	                 while ((line = br.readLine()) != null) {
	                	 try { 
	                		 // call the appropriate method!
	                		 Boolean value = (Boolean) hashMap.get(choice).invoke(null, line);
	                		 if(value) {
	                			 isfound=true;
	                		 }
	                	 } catch (Exception e) {
//	                		 e.printStackTrace();
	                	 }
	                }
	                
	             } catch (IOException e) {
//	                 e.printStackTrace();
	             }   	
	        }
	    }
		if(!isfound) {
       	 System.out.println("No value found....");
        }
		
	}
	 
	 
	public static void main(String args[]) throws NoSuchMethodException,  
    InvocationTargetException, IllegalAccessException, IOException {
//		System.out.println("-----------     Regular Expression      ------------");
//		System.out.println("Select your option:"
//				+ "\n1 : Email Adresses"
//				+ "\n2 : Contact Numbers"
//				+ "\n3 : Web Links");
//		System.out.println("-----------------------------------------");
//		int choice = new Scanner(System.in).nextInt();
//		findPatterns(choice);
	}
}
