
package docHandler;
import org.apache.poi.*;
import java.io.*;
import java.util.regex.*;

public class ReadFile 
{
	public static void main(String args[]) throws Exception
	{
		if(args[0].equals("-t"))
		{
			System.out.println(getText(args[1]));
		}else if(args[0].equals("-wc"))
		{
			System.out.println(getWordCount(args[1]));
		}else{
			System.out.println(getWordCount(args[0]));
		}
	}

	public static String getText(String filename) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String toReturn = "";
		String str = br.readLine();
		while(str != null)
		{
			toReturn += str+"\n";
			str = br.readLine();
		}
		return toReturn;
	}

	public static int getWordCount(String filename) throws IOException
	{
		String text = getText(filename);
		String pattern = "[^ \r\n'-]+?[ \r\n]+?";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(text.trim());
		int count = 0;
		while(m.find())
			count++;
		return count+1;
	}

}
