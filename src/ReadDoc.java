import org.apache.poi.poifs.filesystem.*;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.extractor.*;
import java.io.*;

public class ReadDoc
{
	public static void main(String args[])
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
}
