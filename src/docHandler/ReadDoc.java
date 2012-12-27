
package docHandler;
import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.extractor.*;
import java.io.*;
import java.util.regex.*;

public class ReadDoc
{
	
	public static void main(String args[])
	{
		if(args[0].equals("-t"))
		{
			System.out.println(getText(args[1],false));
		}else if(args[0].equals("-wc"))
		{
			System.out.println(getWordCount(args[1]));
		}else{
			System.out.println(getWordCount(args[0]));
		}
	}

	public static String getText(String filename,boolean replace)
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
				if(replace)
				{
					//paragraphs[i] = paragraphs[i].replaceAll("\\cM?\r?\n","");
					text += paragraphs[i].trim();
				}else{
					text += paragraphs[i];
				}
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return text;
	}

	public static int getWordCount(String filename)
	{
		String text = getText(filename,false);
		String pattern = "[^ \r\n'-]+?[ \r\n]+?";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(text.trim());
		int count = 0;
		while(m.find())
			count++;
		return count+1;
	}
}
