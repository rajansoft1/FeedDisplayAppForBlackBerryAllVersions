package mypack;

import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import mypack.MyMovieStorage.MyRssFeed;
import net.rim.device.api.servicebook.ServiceBook;
import net.rim.device.api.servicebook.ServiceRecord;
import net.rim.device.api.system.WLANInfo;

public class PrepareApp
{
	MyMovieStorage rss;
	HttpConnection httpCon = null;
	MyRssFeed feed;
	int NetWay = 0;
	String uid = null;
	int cd = 0;
	PrepareApp()
	{  //Check suitable network mechanism
		try{
			if (WLANInfo.getWLANState() == WLANInfo.WLAN_STATE_CONNECTED)
			{
             httpCon = (HttpConnection)Connector.open("http://mocms.etc.in/rss/etc-news.xml;interface=wifi");
             cd = httpCon.getResponseCode();
             System.out.println("$$$$ $$$$$ $$$$ Wi-Fi Resp Code : "+cd);
			}
 	    if(cd!=200)
 	    {
 	    	ServiceBook sb = ServiceBook.getSB();
 	    	ServiceRecord[] records = sb.findRecordsByCid("WPTCP");
 	    	for(int i=0; i < records.length; i++) 
 	    	{
 	    	    //Search through all service records to find the 
 	    	    //valid non-Wi-Fi and non-MMS 
 	    	    //WAP 2.0 Gateway Service Record.
 	    	    if (records[i].isValid() && !records[i].isDisabled()) 
 	    	    {

 	    	        if (records[i].getUid() != null && records[i].getUid().length() != 0) 
 	    	        {
 	    	            if ((records[i].getUid().toLowerCase().indexOf("wifi") == -1) &&
 	    	                (records[i].getUid().toLowerCase().indexOf("mms") == -1))
 	    	            {
 	    	                    uid = records[i].getUid(); 
 	    	                    break;
 	    	            }
 	    	        } 
 	    	    }
 	    	}
 	    	cd=0;
 	    	if(uid!=null)
 	    	{
 	    		httpCon = (HttpConnection)Connector.open("http://mocms.etc.in/rss/etc-news.xml;ConnectionUID="+uid);
 	    		cd = httpCon.getResponseCode();
 	    	}
 	    	
 	    	
 	    	
 	    	if(cd!=HttpConnection.HTTP_OK)
 	    	{ System.out.println("$$$$ $$$$$ $$$$ WAP 2 Resp Code : " + cd);
 	    		httpCon = (HttpConnection)Connector.open("http://mocms.etc.in/rss/etc-news.xml");
     	    	if(httpCon.getResponseCode()!=HttpConnection.HTTP_OK)
     	    	{ System.out.println("$$$$ $$$$$ $$$$ MDS Resp Code : " + httpCon.getResponseCode());
     	    		httpCon = (HttpConnection)Connector.open("http://mocms.etc.in/rss/etc-news.xml;deviceside=true");
         	    	if(httpCon.getResponseCode()!=HttpConnection.HTTP_OK)
         	    	{ System.out.println("$$$$ $$$$$ $$$$ TCP Resp Code : " + httpCon.getResponseCode());
         	    		NetWay = 0;
         	    	}
         	    	else
         	    	{
         	    		NetWay = 4;
         	    		httpCon.close();
         	    	}
     	    	}
     	    	else
     	    	{
     	    		NetWay = 3;
     	    		httpCon.close();
     	    	}
 	    	}
 	    	else
 	    	{
 	    		NetWay = 2;
 	    		httpCon.close();
 	    	}
 	    }
 	    else 
 	    {
 	    	NetWay = 1;
 	    	httpCon.close();
 	    }
		
			
		 rss = new MyMovieStorage();
	     rss.deleteAllFeeds();
	     rss.closeStore();
		}
		 catch (Exception ioe)
	        {
			 if(ioe instanceof IOException)
			 {
	            System.err.println("IO Exception !: " + ioe.getMessage()+ioe.toString());
			 }
			 else
			 {
			    System.err.println("Exception !: " + ioe.getMessage()+ioe.toString());
			 }
	            ioe.printStackTrace();
	        }
	    
	}
	public int getNetWay() 
	{
	   return NetWay;
	}
	public String  getUID() 
	{
	   return uid;
	}
}

