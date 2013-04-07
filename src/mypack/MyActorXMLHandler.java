package mypack;

import org.xml.sax.helpers.*;
import org.xml.sax.*;

import mypack.MyMovieStorage.*;

import java.lang.StringBuffer;
public class MyActorXMLHandler extends DefaultHandler
{
	StringBuffer sb = null;
	MyActor _act = null;
	boolean bStarted = false;
	MyMovieStorage rssStore = null;
	
	MyActorXMLHandler() 
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
	     if (localName.equals("starRankthisWeek"))
	     {
	         bStarted = true;
	         // new item, let's set up!
	         _act = rssStore.createMyActor("",atts.getValue(0),"","","","");
	     }
	     
	 }
	 public void endElement(String namespaceURI, String localName, String qName) throws SAXException
	 {
	     System.out.println("endElement [" + localName + "] value = [" + sb.toString() + "]");
	     
	     if (bStarted == false) return;

	     
	     if (localName.equals("title"))
	     {
	         _act.setTitle(sb.toString());
	     }
	     if (localName.equals("starImage"))
	     {
	         _act.setStarImage(sb.toString());
	     }
	     if (localName.equals("starRating"))
	     {
	         _act.setStarRating(sb.toString());
	     }
	     if (localName.equals("starRankLastWeek"))
	     {
	         _act.setRankLastWeek(sb.toString());
	     }
	     if (localName.equals("starRankthisWeek"))
	     {
	         // store this item!
	    	 
	         _act.setName("star-meter");
	         System.out.println("Storing star item! [" + _act.toString());
	         
	         rssStore.addActorRecord(_act); 
	        
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
