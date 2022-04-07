// Searching a word in all the converted text files using Edit Distance

package searchEngine;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class editDistance {

    private static dictionary dict;
    
    
    final static String filePath = "D:\\Search Engine Project\\Results\\results.txt";
    final static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public editDistance() {
        dict = new dictionary();
        dict.build(filePath);

    }

    public static void run() {
    	dict = new dictionary();
        dict.build(filePath);
        Scanner sc = new Scanner(System.in);

        String word;

        while (true) {
            System.out.print("\nEnter the word to spell check or press ENTER to quit : ");
            word = sc.nextLine();
            if (word.equals("")) {
                break;
            }
            if (dict.contains(word)) {
                System.out.println("\n" + word + " is spelled correctly");
            } else {
            	
            	System.out.print("\n" + word +" is not found in the files ");
                System.out.println();
                System.out.println(printSuggestions(word));
            }
        }
    }

    public static String printSuggestions(String word) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> print = makeSuggestions(word);
        if (print.size() == 0) {
            return "and No simmilar words found.\n";
        }
        sb.append("Suggestions:\n");
        for (String s : print) {
            sb.append("\n  -" + s);
        }
        return sb.toString();
    }

    private static ArrayList<String> makeSuggestions(String word) {
    	
        ArrayList<String> toReturn = new ArrayList<>();
        toReturn.addAll(charAppended(word));
        toReturn.addAll(charMissing(word));
        toReturn.addAll(charsSwapped(word));
        
        return toReturn;
    }

    private static ArrayList<String> charAppended(String word) { 
        ArrayList<String> toReturn = new ArrayList<>();
        for (char c : alphabet) {
            String atFront = c + word;
            String atBack = word + c;
            if (dict.contains(atFront)) {
                toReturn.add(atFront);
            }
            if (dict.contains(atBack)) {
                toReturn.add(atBack);
            }
        }
        return toReturn;
    }

    private static ArrayList<String> charMissing(String word) {   
        ArrayList<String> toReturn = new ArrayList<>();

        int len = word.length() - 1;
        // try removing char from the front
        if (dict.contains(word.substring(1))) {
            toReturn.add(word.substring(1));
        }
        for (int i = 1; i < len; i++) {
            // try removing each char between (not including) the first and last 
            String newWord = word.substring(0, i);
            newWord = newWord.concat(word.substring((i + 1), word.length()));
            if (dict.contains(newWord)) {
                toReturn.add(newWord);
            }
        }
        if (dict.contains(word.substring(0, len))) {
            toReturn.add(word.substring(0, len));
        }
        return toReturn;
    }

    private static ArrayList<String> charsSwapped(String word) {   
        ArrayList<String> toReturn = new ArrayList<>();

        for (int i = 0; i < word.length() - 1; i++) {
            String newWord = word.substring(0, i); 
            newWord = newWord + word.charAt(i + 1);  
            newWord = newWord + word.charAt(i); 
            newWord = newWord.concat(word.substring((i + 2)));
            if (dict.contains(newWord)) {
                toReturn.add(newWord);
            }
        }
        return toReturn;
    }


}


