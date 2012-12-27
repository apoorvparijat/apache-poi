package docHandler;

import org.apache.poi.hpsf.*;
import org.apache.poi.poifs.eventfilesystem.*;

public class MyPOIFSReaderListener implements POIFSReaderListener
{

	public void processPOIFSReaderEvent(POIFSReaderEvent event)
	{
		SummaryInformation si = null;
		try{
			si = (SummaryInformation)PropertySetFactory.create(event.getStream());
		}catch(Exception ex)
		{
			throw new RuntimeException("Property set stream \"" + event.getPath() 
							+ event.getName() + "\": " + ex);
		}

		final String title = si.getTitle();
		final int wordCount = si.getWordCount();
		if(title != null)
			System.out.println("Title: \"" + title + "\"");
		else
			System.out.println("Document has no title.");
		System.out.println("Wordcount: " + wordCount);
	}

}
