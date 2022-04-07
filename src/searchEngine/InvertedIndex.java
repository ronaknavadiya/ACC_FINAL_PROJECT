/* Inverted Index - All the words in the text files generated 
   by the web crawler will be stored in HashMap. The HashMap
   will then be searched for the word provided by the user.
   All the text files containing the word will be displayed.  */

package searchEngine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class InvertedIndex {
	
	static File directory = new File("D:\\Search Engine Project\\WebToTextFiles");
	HashMap<String, String> textFiles;
	Scanner sc = new Scanner(System.in);
	
	public void searchFile() throws IOException{
		System.out.println("Enter the word to find which file contains it : ");
		String wordInput = sc.next();
		wordInput = wordInput.toLowerCase();
		int count = 0;
		
		try{
		for(File f : directory.listFiles()){
			
			textFiles = new HashMap<String, String>();
			Document doc = Jsoup.parse(f, "UTF-8");
			String text = doc.text();	
			String[] str = text.split("\\W+");
			
			for(int i = 0; i< str.length; i++){
				if(!textFiles.containsKey(str[i]))
					textFiles.put(str[i].toLowerCase(), f.getName());
			}
			
			if(textFiles.containsKey(wordInput)){     //check if word is found or not
				count++;
				System.out.println("Word: '"+wordInput+"' found in => "+textFiles.get(wordInput));
				}
			}
		
		}catch(IOException e){
//			System.out.println(e.printStackTrace());
		}

		if(count == 0)
			System.out.println("Word: '"+wordInput+"' could not found in any files");
		
	}
	
	
	public static void main(String[] args) throws IOException
	{
//		new InvertedIndex().searchFile();
		
	}

}

