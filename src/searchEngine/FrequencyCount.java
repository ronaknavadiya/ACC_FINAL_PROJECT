// Calculates frequency of given word in all converted web pages and give rank based on their occurrence 

package searchEngine;

import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;


public class FrequencyCount { 

	public static  Hashtable<String, Integer> WordCount() throws IOException
	{
		Hashtable<String, Integer> hashTable = new Hashtable<String, Integer>();
		BufferedReader bufferedReader = null; 
		
		try 
		{
			Scanner sc= new Scanner(System.in);
			File directory = new File("D:\\Search Engine Project\\WebToTextFiles"); //reading the converted text files
			File[] files = directory.listFiles();
			System.out.println("Enter word to be searched in the files :"); 
			String wordInput= sc.nextLine();                 //reading user input


			for (File file : files) 
			{
				HashMap <String,Integer> hashMap = new HashMap<String,Integer>(); //hashmap object to store the words
				bufferedReader =  new BufferedReader(new FileReader(file)); 
				String str = bufferedReader.readLine();  					// reading the content lines in the files

				while(str!=null)
				{
					String words[]= str.toLowerCase().split(" ");   // getting the words(converting into lower case first)
					for(String word:words) {
						if(hashMap.containsKey(word)) {       //search for the word in hashmap
							hashMap.put(word,hashMap.get(word)+1);   
						}
						else {
							hashMap.put(word,1);	 
						}
					}
					str=bufferedReader.readLine();
				}

				if(hashMap.containsKey(wordInput)) 
				{	 
					hashTable.put( file.getName(), hashMap.get(wordInput));
				}
				else
				{
					hashTable.put( file.getName(), 0); 
				}
			}

		}catch(Exception e) {

//			e.printStackTrace();

		}
		finally {
			bufferedReader.close();
		}

		return hashTable;
	}

	public static void count() 
	{

		Hashtable<String, Integer> hashTable;
		try {
			hashTable = FrequencyCount.WordCount();

			ArrayList<Map.Entry<?, Integer>> arrayList = new ArrayList(hashTable.entrySet());

			for(int i = 0; i < hashTable.size(); i++) {
				Map.Entry<String, Integer> map = (Entry<String, Integer>) arrayList.get(i);

				if(map.getValue() != 0)
					System.out.println("The word was found " + map.getValue() + " times in => " + map.getKey());
				else {
//					System.out.println("The word not found in => " + map.getKey());	
				}
			}


		} catch (IOException e) {
//			e.printStackTrace();
		}

	}

	public static void sortbyWordCount()                   // sorts the web pages and give ranks
	{
		try
		{
			Hashtable<String, Integer> hashTable = WordCount();

			ArrayList<Map.Entry<?, Integer>> arrayList = new ArrayList(hashTable.entrySet());

			Collections.sort(arrayList, new Comparator<Map.Entry<?, Integer>>()    // Collection.sort inbuilt use merge sort
			{

				public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2)
				{
					return o1.getValue().compareTo(o2.getValue());
				}
			});
			
			Collections.reverse(arrayList);

			System.out.println("Top 10 search results");           
			for(int i = 0; i < 10; i++) {
				Map.Entry<String, Integer> map = (Entry<String, Integer>) arrayList.get(i);
				System.out.println("The word was found " + map.getValue() + " times in => " + map.getKey());
			}

		}
		catch(Exception e) {
//			e.printStackTrace();
		}

	}

//	public static void main(String[] args)   throws IOException
//	{
//		 FrequencyCount.count();
//		 FrequencyCount.sortbyWordCount();
//	}

}
