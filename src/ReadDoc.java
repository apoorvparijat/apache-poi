import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.extractor.*;
import java.io.*;
import java.util.regex.*;

public class ReadDoc
{
	
	public static void main(String args[])
	{
		int count = getWordCount(args[0]);
		System.out.println("WordCount: " + count);
	}
	
	public static void mainPrintText(String args[])
	{
		String filename = args[0];
		System.out.println(filename);
		POIFSFileSystem fs = null;
		try
		{
			fs = new POIFSFileSystem(new FileInputStream(filename));

			HWPFDocument doc = new HWPFDocument(fs);
			WordExtractor we = new WordExtractor(doc);

			String[] paragraphs = we.getParagraphText();
			System.out.println("Word document has " + paragraphs.length + " paragraphs.");

			// For loop to print paragraphs.
			
			for(int i = 0; i < paragraphs.length; i++)
			{
				paragraphs[i] = paragraphs[i].replaceAll("\\cM?\r?\n","");
				//System.out.println("Length:" + paragraphs[i].length());
				System.out.println(paragraphs[i]);
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static String getText(String filename)
	{
		POIFSFileSystem fs = null;
		String text = "";
		try
		{
			fs = new POIFSFileSystem(new FileInputStream(filename));

			HWPFDocument doc = new HWPFDocument(fs);
			WordExtractor we = new WordExtractor(doc);

			String[] paragraphs = we.getParagraphText();

			// For loop to print paragraphs.
			
			for(int i = 0; i < paragraphs.length; i++)
			{
				paragraphs[i] = paragraphs[i].replaceAll("\\cM?\r?\n","");
				text += paragraphs[i];
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return text;
	}

	public static int getWordCount(String filename)
	{
		String text = getText(filename);
		String pattern = " ";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(text);
		int count = 0;
		while(m.find())
			count++;
		return count;
	}
}
