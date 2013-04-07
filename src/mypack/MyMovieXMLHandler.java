package mypack;

import org.xml.sax.helpers.*;
import org.xml.sax.*;

import mypack.MyMovieStorage.*;

import java.lang.StringBuffer;
public class MyMovieXMLHandler extends DefaultHandler
{
	StringBuffer sb = null;
	MyMovie _mov = null;
	boolean bStarted = false;
	MyMovieStorage rssStore = null;
	
	MyMovieXMLHandler() 
	 {
	     rssStore = new MyMovieStorage();
	 }
	
	public void warning(SAXParseException e) 
	 {
	     System.err.println("warning: " + e.getMessage());
	     bStarted = false;
	 }
	 
	 public void error(SAXParseException e) 
	 {
	     System.err.println("error: " + e.getMessage());
	 }
	 
	 public void fatalError(SAXParseException e) 
	 {
	     System.err.println("fatalError: " + e.getMessage());
	     bStarted = false;
	 }
	 
	 public void startDocument() throws SAXException
	 {
	     System.out.println("startDocument");
	 }
	 public void endDocument() throws SAXException
	 {
	     System.out.println("endDocument");
	     // we've concluded this document, safe to create the feed.
	     rssStore.closeStore();
	 }
	 public void startElement(String namespaceURI, String localName,String qName, Attributes atts) throws SAXException
	 {
	     System.out.println("startElement [" + localName + "], Attributes [" + atts.toString() + "]");
	     sb = new StringBuffer("");
	     if (localName.equals("movieRankthisWeek"))
	     {
	         bStarted = true;
	         // new item, let's set up!
	         _mov = rssStore.createMyMovie("",atts.getValue(0),"","","","");
	     }
	     
	 }
	 public void endElement(String namespaceURI, String localName, String qName) throws SAXException
	 {
	     System.out.println("endElement [" + localName + "] value = [" + sb.toString() + "]");
	     
	     if (bStarted == false) return;

	     
	     if (localName.equals("title"))
	     {
	         _mov.setTitle(sb.toString());
	     }
	     if (localName.equals("movieImage"))
	     {
	         _mov.setMovieImage(sb.toString());
	     }
	     

	     if (localName.equals("movieRankLastWeek"))
	     {
	         _mov.setRankLastWeek(sb.toString());
	     }
	     if (localName.equals("movieRating"))
	     {
	         _mov.setMovieRating(sb.toString());
	     }
	     if (localName.equals("movieRankthisWeek"))
	     {
	         // store this item!
	    	 
	         _mov.setName("movie-meter");
	         System.out.println("Storing movie item! [" + _mov.toString());
	         
	         rssStore.addMovieRecord(_mov); 
	        
	     }
	     sb = new StringBuffer("");
	     
	     
	 }
	 public void characters(char ch[], int start, int length)
	 {
	     String theString = new String(ch,start,length);
	     System.out.println("characters [" + theString + "]");
	     sb.append(theString);
	}
}
