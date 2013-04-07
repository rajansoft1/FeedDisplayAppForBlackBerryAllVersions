package mypack;

import org.xml.sax.helpers.*;
import org.xml.sax.*;

import mypack.MyMovieStorage.*;

import java.lang.StringBuffer;
public class MyBocXMLHandler extends DefaultHandler
{
	StringBuffer sb = null;
	MyBoc _mc = null;
	boolean bStarted = false;
	MyMovieStorage rssStore = null;
	
	MyBocXMLHandler() 
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
	     if (localName.equals("MovieID"))
	     {
	         bStarted = true;
	         // new item, let's set up!
	         _mc = rssStore.createMyBoc("",atts.getValue(0),"","","","","","","","","","","","","");
	     }
	    
	     
	 }
	 public void endElement(String namespaceURI, String localName, String qName) throws SAXException
	 {
	     System.out.println("endElement [" + localName + "] value = [" + sb.toString() + "]");
	     
	     if (bStarted == false) return;

	     
	     if (localName.equals("movieTitle"))
	     {
	         _mc.setTitle(sb.toString());
	     }
	     if (localName.equals("movieRelDate"))
	     {
	         _mc.setRelDate(sb.toString());
	     }
	     if (localName.equals("movieDescription"))
	     {
	         _mc.setDescription(sb.toString());
	     }
	     if (localName.equals("movieDirector"))
	     {
	         _mc.setDirector(sb.toString());
	     }
	     if (localName.equals("movieProducer"))
	     {
	         _mc.setProducer(sb.toString());
	     }
	     if (localName.equals("movieStarcast"))
	     {
	         _mc.setStarcast(sb.toString());
	     }
	     if (localName.equals("DomesticBOC"))
	     {
	         _mc.setDomesticBOC(sb.toString());
	     }
	     if (localName.equals("InternationalBOC"))
	     {
	         _mc.setInternationalBOC(sb.toString());
	     }
	     if (localName.equals("TotalCollection"))
	     {
	         _mc.setTotalBOC(sb.toString());
	     }
	    
	     if (localName.equals("movieThumbImage"))
	     {
	         _mc.setThumbImage(sb.toString());
	     }
	     if (localName.equals("movieInnerImage"))
	     {
	         _mc.setInnerImage(sb.toString());
	     }
	     if (localName.equals("movieMeterRank"))
	     {
	         _mc.setMeterRank(sb.toString());
	     }
	     if (localName.equals("movieMeterRating"))
	     {
	         _mc.setMeterRating(sb.toString());
	     }
	     if (localName.equals("MovieID"))
	     {
	         // store this item!
	    	 
	         _mc.setName("box-office");
	         System.out.println("Storing box office item! [" + _mc.toString());
	         
	         rssStore.addBocRecord(_mc); 
	        
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

