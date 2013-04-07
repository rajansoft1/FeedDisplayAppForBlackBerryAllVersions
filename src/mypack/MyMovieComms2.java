package mypack;



import java.util.Vector;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.Connector;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.*;
import org.xml.sax.*;

import mypack.MyMovieStorage.MyRssFeed;
import net.rim.device.api.xml.parsers.*;

import net.rim.device.api.ui.*;

import net.rim.device.api.servicebook.*;
import net.rim.device.api.system.*;
public class MyMovieComms2 implements Runnable
{
	int NetWay = 0;
	int num = 0;
	String url = null;
	String uid = null;
	
	
	public MyMovieComms2(int nway,int num,String uid,String url)
	{
		NetWay = nway;
		this.num = num;
		this.url = url;
		this.uid = uid;
	}
	public void run()
	{
		try
		{
		InputStream inputStream = null;
        HttpConnection httpConnection = null;
        if(NetWay !=0 )
        {
       	switch(NetWay)
       	{       //Wi-Fi
       	case 1: httpConnection = (HttpConnection)Connector.open(url+";interface=wifi");
       	        break;
       	        // WAP 2.0
       	case 2: httpConnection = (HttpConnection)Connector.open(url+";ConnectionUID="+uid);
	                break;
	                // MDS
       	case 3: httpConnection = (HttpConnection)Connector.open(url);
	                break;
	                // Direct TCP
       	case 4: httpConnection = (HttpConnection)Connector.open(url+";deviceside=true");
	                break;
       	}
       	if(httpConnection.getResponseCode() != HttpConnection.HTTP_NOT_FOUND)
         {
       	

            // delete any Items under this feed
       	inputStream = httpConnection.openDataInputStream();
       	 
       	
           // check header field for a specific encoding
           String desiredEncoding = "ISO-8859-1";  //iso-8859-1
           String contenttype = httpConnection.getHeaderField("Content-Type");
           if (contenttype != null)
           {
               contenttype = contenttype.toUpperCase();
               if (contenttype.indexOf("UTF-8") != -1)
               {
                   desiredEncoding = "UTF-8";
               }
           }

           // we need an input source for the sax parser
           InputSource is = new InputSource(inputStream);

           // setup Encoding to match what the web server sent us
           is.setEncoding(desiredEncoding);
           
           // create the factory
           SAXParserFactory factory = SAXParserFactory.newInstance();

           // create a parser
           SAXParser parser = factory.newSAXParser();
           
           // instantiate our handler
           switch(num)
           {
               case 0 : MyMovieXMLHandler moxh= new MyMovieXMLHandler();
                        parser.parse(is,moxh);
                        break;
               case 1 : MySongXMLHandler msxh= new MySongXMLHandler();
                        parser.parse(is,msxh);
                        break;
               case 2 : MyActorXMLHandler maxh= new MyActorXMLHandler();
                        parser.parse(is,maxh);
                        break;
               case 3 : MyNewsXMLHandler mnxh= new MyNewsXMLHandler();
                        parser.parse(is,mnxh);
                        break;
               case 4 : MyMovCalXMLHandler mcxh= new MyMovCalXMLHandler();
                        parser.parse(is,mcxh);
                        break;
               case 5 : MyBocXMLHandler mbxh= new MyBocXMLHandler();
                        parser.parse(is,mbxh);
                        break;
                     
            }
           System.out.println("###### Downloading from "+url+" completed ######");
         }
         else
         {
       	  System.err.println("Feed not found");
       	  
         }
       }
       else
       {
       	System.err.println("No internet connection");
       
       }
   }
   catch (IOException ioe)
   {
    System.err.println("IO Exception !: " + ioe.getMessage());
    ioe.printStackTrace();
   }
   catch (SAXException saxe)
   {
    System.err.println("SAX Exception !: " + saxe.getMessage());
    saxe.printStackTrace();
   }
   catch (Exception e)
   {
    System.err.println("General Error " + e.getMessage());
    e.printStackTrace();
   }
 }
	
}
