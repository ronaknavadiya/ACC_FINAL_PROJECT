package searchEngine;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class MainSearchEngine {

	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException{

		Scanner sc = new Scanner(System.in); 
//		Web crawling	
		System.out.println("Enter the URL you want to crawl:");
		String links = sc.nextLine();
		
		//String links="https://www.uwindsor.ca/";

		new WebCrawler().crawlPages(links);  		

		
		// Stored crawled text files in D:\\Search Engine Project\\WebToTextFiles

		//  search engine starts..
		
		
		String wantToContinue;
		
		do
		{
			
		System.out.println("-------------     SEARCH ENGINE     ----------------");
		System.out.println("Please enter the number according to feature you want to search: "
				+ "\n 1. Spell Checker"
				+ "\n 2. Frequency count of given word"
				+ "\n 3. Page Ranking - Sort Pages based on word frequency"
				+ "\n 4. Regular Expression"
				+ "\n 5. Search word from files using Inverted index");
		System.out.println("------------x----------------x---------------x--------");
		
		int userInput = 0;

		try {
			userInput = sc.nextInt();  // Read user input
		}catch (Exception e) {
//			System.out.println("Please enter valid value");
		}
		
		switch (userInput) 
		{
		
		case 1:
			ConcateFiles.concateAllFiles();
			ConvertIntoWords.splitWords();
			editDistance.run();
			break;

		case 2:
			FrequencyCount.count();		// Frequency count
			break;

		case 3: 
			FrequencyCount.sortbyWordCount();	// Page ranking
			break;
		case 4:
			RegExpression reg = new RegExpression();
			System.out.println("-----------     Regular Expression      ------------");
			System.out.println("Select your option:"
					+ "\n1 : Email Adresses"
					+ "\n2 : Contact Numbers"
					+ "\n3 : Web Links");
			System.out.println("-----------------------------------------");
			int choice = new Scanner(System.in).nextInt();
			reg.findPatterns(choice);
			break;
			
		case 5:
			new InvertedIndex().searchFile();
			break;
			
			
		default:
			System.out.println("Invalid choice");
			break;

		}
		
		System.out.println("\nDo you wanna continue searching ?(y/n)");
		 wantToContinue = sc.next();
		
		}
		while(wantToContinue.equalsIgnoreCase("y"));
		
		System.out.println("\nThank You!");

	}
}
