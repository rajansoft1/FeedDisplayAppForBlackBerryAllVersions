package mypack;

import java.io.*;

import javax.microedition.io.*;

import net.rim.device.api.math.Fixed32;
import net.rim.device.api.servicebook.*;
import net.rim.device.api.system.*;

public class UrlToImage
{
int NetWay = 0;
String uid = null;
public static Bitmap _bmap;
public UrlToImage(String url,String imgType)
{
HttpConnection httpCon = null;
HttpConnection connection = null; 
InputStream inputStream = null; 
EncodedImage bitmap;
byte[] dataArray = null;
int cd = 0;

try 
{ if (WLANInfo.getWLANState() == WLANInfo.WLAN_STATE_CONNECTED)
  {
	httpCon = (HttpConnection)Connector.open("http://mocms.etc.in/rss/etc-news.xml;interface=wifi");
	cd = httpCon.getResponseCode();
  }
	    if(cd != 200)
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
	    	{
	    		httpCon = (HttpConnection)Connector.open("http://mocms.etc.in/rss/etc-news.xml;deviceside=false");
 	    	if(httpCon.getResponseCode()!=HttpConnection.HTTP_OK)
 	    	{
 	    		httpCon = (HttpConnection)Connector.open("http://mocms.etc.in/rss/etc-news.xml;deviceside=true");
     	    	if(httpCon.getResponseCode()!=HttpConnection.HTTP_OK)
     	    	{
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
  if(NetWay !=0 )
  {
	switch(NetWay)
	{       //Wi-Fi
	case 1: connection = (HttpConnection)Connector.open(url+";interface=wifi", Connector.READ, true);
	        break;
	        // WAP 2.0
	case 2: connection = (HttpConnection)Connector.open(url+";ConnectionUID="+uid, Connector.READ, true);
            break;
            // MDS
	case 3: connection = (HttpConnection)Connector.open(url+";deviceside=false", Connector.READ, true);
            break;
            // Direct TCP
	case 4: connection = (HttpConnection)Connector.open(url+";deviceside=true", Connector.READ, true);
            break;
	}
inputStream = connection.openInputStream(); 
byte[] responseData = new byte[10000]; 
int length = 0; 
StringBuffer rawResponse = new StringBuffer(); 
while (-1 != (length = inputStream.read(responseData))) 
{ 
rawResponse.append(new String(responseData, 0, length)); 
} 
int responseCode = connection.getResponseCode(); 
if (responseCode != HttpConnection.HTTP_OK) 
{ 
throw new IOException("HTTP response code: " 
+ responseCode); 
} 

final String result = rawResponse.toString(); 
dataArray = result.getBytes(); 
    }
	
} 
catch (final Exception ex) 
{
 System.out.println(ex.getMessage());
}

finally 
{ 
try 
{ 
inputStream.close(); 
inputStream = null; 
connection.close(); 
connection = null; 
  } 
   catch(Exception e){
	System.out.println(e.getMessage());
   } 
} 


bitmap = EncodedImage.createEncodedImage(dataArray, 0,dataArray.length);
// this will scale your image acc. to your height and width of bitmapfield

int multH;
int multW;
int currHeight = bitmap.getHeight();
int currWidth = bitmap.getWidth();
if(imgType=="I")
{
    multH= Fixed32.div(Fixed32.toFP(currHeight),Fixed32.toFP(193));//height
    multW = Fixed32.div(Fixed32.toFP(currWidth),Fixed32.toFP(193));//width
}
else
{
	multH= Fixed32.div(Fixed32.toFP(currHeight),Fixed32.toFP(60));//height
	multW = Fixed32.div(Fixed32.toFP(currWidth),Fixed32.toFP(60));//width
}
bitmap = bitmap.scaleImage32(multW,multH);

_bmap=bitmap.getBitmap();


}
public Bitmap getbitmap()
{
return _bmap;

}


}
