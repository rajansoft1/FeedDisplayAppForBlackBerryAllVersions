package mypack;

import org.xml.sax.helpers.*;
import org.xml.sax.*;

import mypack.MyMovieStorage.*;

import java.lang.StringBuffer;
public class MyMovCalXMLHandler extends DefaultHandler
{
	StringBuffer sb = null;
	MyMovieCal _mc = null;
	boolean bStarted = false;
	MyMovieStorage rssStore = null;
	
	MyMovCalXMLHandler() 
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
	     if (localName.equals("releaseDate"))
	     {
	         bStarted = true;
	         // new item, let's set up!
	         _mc = rssStore.createMyMovieCal("",atts.getValue(0),"","","","","","","","","","");
	     }
	     if (localName.equals("movieid"))
	     {
	        
	         // new item, let's set up!
	         _mc.setMovieId(atts.getValue(0));
	     }
	     
	 }
	 public void endElement(String namespaceURI, String localName, String qName) throws SAXException
	 {
	     System.out.println("endElement [" + localName + "] value = [" + sb.toString() + "]");
	     
	     if (bStarted == false) return;

	     
	     if (localName.equals("title"))
	     {
	         _mc.setTitle(sb.toString());
	     }
	     if (localName.equals("description"))
	     {
	         _mc.setDescription(sb.toString());
	     }
	     if (localName.equals("director"))
	     {
	         _mc.setDirector(sb.toString());
	     }
	     if (localName.equals("producer"))
	     {
	         _mc.setProducer(sb.toString());
	     }
	     if (localName.equals("starcast"))
	     {
	         _mc.setStarcast(sb.toString());
	     }
	     if (localName.equals("movietype"))
	     {
	         _mc.setMovietype(sb.toString());
	     }
	     if (localName.equals("titleimage"))
	     {
	         _mc.setTitleImage(sb.toString());
	     }
	     if (localName.equals("BigTitleImage"))
	     {
	         _mc.setBigTitleImage(sb.toString());
	     }
	    
	     if (localName.equals("searchkeywords"))
	     {
	         _mc.setSearchKeywords(sb.toString());
	     }
	     
	     if (localName.equals("releaseDate"))
	     {
	         // store this item!
	    	 
	         _mc.setName("movie-calendar");
	         System.out.println("Storing movie calendar item! [" + _mc.toString());
	         
	         rssStore.addMovieCalRecord(_mc); 
	        
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
