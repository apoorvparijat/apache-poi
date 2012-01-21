import org.apache.poi.*;
import java.io.*;

public class ReadFile 
{
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader("blah.txt"));
		String str = br.readLine();
		while(str != null)
		{
			System.out.println(str);
			str = br.readLine();
		}
	}
}
