//Crawl the web pages and convert web pages into text files
package searchEngine;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//Uses Jsoup to crawl through web and creates text files with parsed information
public class WebCrawler 
{
	public static String crawl(String link) 
	{	
		String html = urlToHTML(link);
		
		Document doc = Jsoup.parse(html);
		String text = doc.text();
		String title = doc.title();
		
		createFile(title,text,link);
		
		Elements e = doc.select("a");
		String links = "";
		
		for(Element e2 : e) {
			String href = e2.attr("abs:href");              
			if(href.length()>3)
			{
				links = links+"\n"+href;
			}
		}
		return links;
	}
	
	public static void createFile(String title,String text,String link) 
	{
		try {
			String[] titlesplit = title.split("\\|");
			String newTitle = "";
			for(String s : titlesplit) {
				newTitle = newTitle+" "+s;
			}
			File f = new File("WebToTextFiles//"+newTitle+".txt");            // create text file in WebToTextFiles folder
			f.createNewFile();			
			PrintWriter pw = new PrintWriter(f);
			pw.println(link);
			pw.println(text);
			pw.close();
			
		} catch (FileNotFoundException e) {
//			e.printStackTrace();
			} catch (IOException e) {
//				e.printStackTrace();
				}
		
	}
	
	public static String urlToHTML(String link)
	{
		try {
			URL url = new URL(link);
			URLConnection conn = url.openConnection();
			conn.setConnectTimeout(4500);
			conn.setReadTimeout(4500);
			Scanner sc = new Scanner(conn.getInputStream());
			StringBuffer sb = new StringBuffer();
			while(sc.hasNext()) 
			{
				sb.append(" "+sc.next());
			}
			
			String result = sb.toString();
			sc.close();
			return result;
		}
		catch(IOException e) {
//			System.out.println(e);
		} 
		return link;
	}
	
	public static void crawlPages(String links) 
	{
		
		try {
			File f = new File("CrawledPages.txt");                         // stores the links of crawled pages into CrawledPages.txt
			f.createNewFile();
			FileWriter fileWriter = new FileWriter(f);
			fileWriter.close();
						
			System.out.println("Crawlled links..");
			String links2 = "";
			for(String link: links.split("\n")) {				
				links2 = links2 + crawl(link);
				System.out.println(link);				
				FileWriter fw = new FileWriter(f,true);
				fw.write(link + "\n");
				fw.close();
			}
			
			String links3 = "";
			for(String link: links2.split("\n")) {
				In in = new In(f);
				String linksRead = in.readAll();
				if(!linksRead.contains(link)) {
					links3 = links3 + crawl(link);
					System.out.println(link);
					FileWriter fw = new FileWriter(f,true);
					fw.write(link + "\n");
					fw.close();
				}		
				
			}
			
			for(String link: links3.split("\n")) {
				In in = new In(f);
				String linksRead = in.readAll();
				if(!linksRead.contains(link)) {
					crawl(link);
					System.out.println(link);
					FileWriter fw = new FileWriter(f,true);
					fw.write(link + "\n");
					fw.close();
				}			
				
			}
		
		}
		catch(Exception e) {
//			e.printStackTrace();
			}
	}
		
	public static void main(String[] args) {
//		String links="https://www.google.com";   
//		crawlPages(links);
	}
}
