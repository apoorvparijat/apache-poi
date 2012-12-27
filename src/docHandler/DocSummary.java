package docHandler;

import org.apache.poi.hpsf.*;
import org.apache.poi.poifs.eventfilesystem.*;
import java.io.*;

public class DocSummary 
{
	public static void main(String args[]) 
	{
		try{
			final String filename = args[0];
			System.out.println(args[0]);
			POIFSReader r = new POIFSReader();
			r.registerListener(new MyPOIFSReaderListener(),"\005SummaryInformation");
			r.read(new FileInputStream(filename));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

