// Searching a word in all the converted text files using Edit Distance
package searchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


public class ConcateFiles {

	public static void concateAllFiles() throws IOException {

		// create instance of directory
        File directory = new File("D:\\Search Engine Project\\WebToTextFiles");
  
        // created object of PrintWriter to write in result.txt
        PrintWriter printWriter = new PrintWriter("D:\\Search Engine Project\\Results\\results.txt");
  
        // listing all files
        String[] fileNames = directory.list();  
  

        for (String fileName : fileNames) {
  
        	// using file name to create file instance
            File f = new File(directory, fileName);
  
            // create object of BufferedReader to reads text from a character-input stream
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
  
            String line = bufferedReader.readLine();
            while (line != null) {
  
                // write to the output file
                printWriter.println(line);
                line = bufferedReader.readLine();
            }
            printWriter.flush();
        }
	}
}
