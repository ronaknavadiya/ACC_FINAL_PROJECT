// Searching a word in all the converted text files using Edit Distance
// Splits all  words from the generated all text files
package searchEngine;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ConvertIntoWords {

	public static  void splitWords() throws FileNotFoundException, IOException 
	{

		ArrayList<String> answer = new ArrayList<>();

		String filePath=  "D:\\Search Engine Project\\Results\\results.txt";
		try (FileReader fileReader = new FileReader(filePath)) {
		    StringBuffer sb = new StringBuffer();
		    while (fileReader.ready()) {
		        char ch = (char) fileReader.read();
		        if (ch == ' '||ch == '\n'||ch == ','||ch =='.'||ch == ';'||ch == ':'||ch == '&'||ch =='|') {
		            answer.add(sb.toString());
		            sb = new StringBuffer();
		        } else {
		            sb.append(ch);
		        }
		    }
		    if (sb.length() > 0) {
		        answer.add(sb.toString());
		    }
		}       
		
		FileWriter writer = new FileWriter("D:\\Search Engine Project\\Results\\results.txt"); 
		for(String str: answer) {
		  writer.write(str + System.lineSeparator());
		}
		writer.close();
	}

}

