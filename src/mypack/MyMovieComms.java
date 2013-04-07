package mypack;



import java.util.Vector;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.Connector;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.*;
import org.xml.sax.*;

import mypack.MyMovieStorage.MyRssFeed;
import net.rim.device.api.xml.parsers.*;

import net.rim.device.api.ui.*;

import net.rim.device.api.servicebook.*;
import net.rim.device.api.system.*;
public class MyMovieComms
{
	MyMovieStorage rss;
	HttpConnection httpCon = null;
	MyRssFeed feed;
	int NetWay = 0;
	String uid = null;
	int cd = 0;
	MyMovieComms(int nway,String uid)
	{  //Check suitable network mechanism
        NetWay = nway;
        this.uid = uid;
		String[] str = new String[6];
		
		str[0] = "http://mocms.etc.in/rss/movie-meter.xml";
		str[1] = "http://mocms.etc.in/rss/music-meter.xml";
		str[2] = "http://mocms.etc.in/rss/star-meter.xml";
		str[3] = "http://mocms.etc.in/rss/etc-news.xml";
		str[4] = "http://mocms.etc.in/rss/movie-calendar.xml";
		str[5] = "http://mocms.etc.in/rss/box-office-collection.xml";
	
		
		for(int i=0;i<6;i++)
		{
		    downloadData(str[i],i);
			System.out.println("Step no "+i);
		}
	}
	public  void downloadData(String url,int num)
    {
        InputStream inputStream = null;
        HttpConnection httpConnection = null;
        String type="N";
        try
        {
             switch(num)
             {
             case 0 : type = "M";
                     break;
             case 1 : type = "S";
                     break;
             case 2 : type = "A";
                     break;
             case 3 : type = "N";
                     break;
             case 4 : type = "C";
                     break;
             case 5 : type = "B";
                     break;
             }
            
            
            // process each
           
          
            
           
     System.out.println("Type is :"+type+" Netway :"+NetWay);
        	   
        	    	
                
                // good connection?
        	    
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
           // }
            
            // dump feeds to debug window
         //   rss.dumpFeeds();
            
            // close storage
            rss.closeStore();
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
        // notify gui that we're done!
       // ApplicationManager.getApplicationManager().postGlobalEvent(Guid.rssdatadone,0,0); 
    }
}
