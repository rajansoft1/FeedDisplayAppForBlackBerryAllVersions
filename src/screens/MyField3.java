package screens;

import java.util.Vector;

import mypack.UrlToImage;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.system.*;
public class MyField3 extends Field
{
	    private Bitmap dback ;
	    private Bitmap sback ;
	    private Bitmap cback ;
	    private String _title ;
	    private String _desc ;
	    String _img ;
	    private Bitmap b1 ;
	    
	    
	    int indexno = 0;
	    String ftype;
	    
	   
	    UrlToImage uti;
	    int f1;
	    int f2;
	    private int _height = 88;
	    private int _width = 360;
	    private int _reqLength ;
	    private int _reqHeight = 70 ;

	    
	
	    private Font font;
	    private Font font2;
	    private int rpos=30;
	    private Vector vctr;
	    private Vector vctr2;
	        
	    /**
	     * Constructor.
	     * @param text The text to be displayed on the button
	     * @param style Combination of field style bits to specify display attributes 
	     */
	     
	    public MyField3(int index,String _type,String title,String desc,String imageUrl) 
	    {
	        super(Field.FOCUSABLE);
	    try{
	    	_width = Display.getWidth();
	    	switch(_width)
	    	{
	    	case 320 : f1 = 18;
	    	           f2 = 17;
	    		break;
	    	case 480 : f1 = 22;
	    	           f2 = 20;
	    		break;
	    	default :  f1 = 21;
	    	           f2 = 19;
	    		break; 
	    	}
	   		 FontFamily ff1 = FontFamily.forName("BBAlpha Serif");
	   		 font = ff1.getFont(Font.PLAIN, f1);
	   		 font2 = ff1.getFont(Font.PLAIN, f2);
	   		 ftype= _type;
	   		
	   		 indexno = index;
	   		 _title = title;
	   		 _desc = desc;
	   		_img = imageUrl;
	   		// uti = new UrlToImage(imageUrl,"T");
	   		// b1 = uti.getbitmap();
	   		b1 = Bitmap.getBitmapResource("blank60.PNG");
	   		
	   		 
	   		 _reqLength = _width - 80;
	   		
	   		
	   		 rpos=10;
	   		 switch(_width)
	   		 {
	   		   case 320 : dback = Bitmap.getBitmapResource("dback320.png");
                          sback = Bitmap.getBitmapResource("select320.png");
                          break;
	   		   case 480 : dback = Bitmap.getBitmapResource("dback480.png");
                          sback = Bitmap.getBitmapResource("select480.png");
                          break;
	   		   default  : dback = Bitmap.getBitmapResource("dback.png");
	 	                  sback = Bitmap.getBitmapResource("select360.png");
	 	                  break;
	   		 }
		    cback = dback;
		    vctr = wrap(_title,_reqLength,font);
		    vctr2 = wrap(_desc,_reqLength,font2);
		   
		      
	   	   }
	   	   catch(Exception ex)
	   	   {
	   		 System.out.println(ex.getMessage());
	   	   }
	   	   Thread td = new Thread(new LoadThumb());
	   	   td.start();
	    }
	    
	    /**
	     * @return The text on the button
	     */
	   
	    /**
	     * Field implementation.
	     * @see net.rim.device.api.ui.Field#getPreferredHeight()
	     */
	    private Vector wrap (String text, int width,Font fnt) 
		{
		    Vector result = new Vector ();
		  
		    String remaining = text;
		    
		    
		    while (remaining.length()>=0)
		    {
		    	int index = getSplitIndex(remaining, width,fnt);
		    	
		    	if (index == -1)
		    		break;
		    	
		    	result.addElement(remaining.substring(0,index));
		    	
		    	remaining = remaining.substring(index);
		    	
		    	if (index == 0)
		    		break;
		    }
		    
		    return result;
		}
		
		private int getSplitIndex(String bigString, int width,Font fnt)
		{
			int index = -1;
			int lastSpace = -1;
			String smallString="";
			boolean spaceEncountered = false;
			boolean maxWidthFound = false;
			
			for (int i=0; i<bigString.length(); i++)
			{
				char current = bigString.charAt(i);
				smallString += current;
				
				
				if (current == ' ')
				{
					lastSpace = i;
					spaceEncountered = true;
				}
				
				int linewidth = fnt.getAdvance(smallString,0,  smallString.length());
				
				if(linewidth>width)
				{
					if (spaceEncountered)
						index = lastSpace+1;
					else
						index = i;
					
					maxWidthFound = true;
					
					break;
					
					
				}
				
				
			}
			
			if (!maxWidthFound)
				index = bigString.length();
			
			
			return index;
		}
	    public int getPreferredHeight() 
	    {
	        return _height;
	    }

	    /**
	     * Field implementation.
	     * @see net.rim.device.api.ui.Field#getPreferredWidth()
	     */
	    public int getPreferredWidth() 
	    {
	        return _width;
	    }
	    
	    /**
	     * Field implementation.  Changes the picture when focus is gained.
	     * @see net.rim.device.api.ui.Field#onFocus(int)
	     */
	    protected void onFocus(int direction) 
	    {
	       this.setBackground(BackgroundFactory.createBitmapBackground(sback));
	       cback = sback;
	        invalidate();
	    }

	    /**
	     * Field implementation.  Changes picture back when focus is lost.
	     * @see net.rim.device.api.ui.Field#onUnfocus()
	     */
	    protected void onUnfocus() 
	    {
	    	this.setBackground(BackgroundFactory.createBitmapBackground(dback));
	    	cback = dback;
	        invalidate();
	    }
	    
	    /**
	     * Field implementation.  
	     * @see net.rim.device.api.ui.Field#drawFocus(Graphics, boolean)
	     */
	    protected void drawFocus(Graphics graphics, boolean on) 
	    {
	        // Do nothing
	    }
	    
	    /**
	     * Field implementation.
	     * @see net.rim.device.api.ui.Field#layout(int, int)
	     */
	    protected void layout(int width, int height) 
	    {
	        setExtent(Display.getWidth(),88);
	        
	    }

	    /**
	     * Field implementation.
	     * @see net.rim.device.api.ui.Field#paint(Graphics)
	     */
	    protected void paint(Graphics graphics) 
	    {       
	        // First draw the background colour and picture
	       
	    	graphics.drawBitmap(0, 0, cback.getWidth(),cback.getHeight() , cback, 0, 0);
		    graphics.drawBitmap(9, 14, b1.getWidth(),b1.getHeight() , b1, 0, 0);
	        graphics.setColor(Color.BLUE);
	        graphics.setFont(font);
	       
	        rpos=10;
	        if(vctr.size()>=3)
	        {
	        for(int i=0;i<3;i++)
	        {
	        	if(rpos<_reqHeight)
	        	{
		         graphics.drawText(vctr.elementAt(i).toString(),77 , rpos);
		         rpos+=25;
	        	}
	        	else
	        	{
	        		break;
	        	}
		    }
	        
	        }
	        else
	        {
	        	for(int i=0;i<vctr.size();i++)
		        {

		        	if(rpos<_reqHeight)
		        	{
			        graphics.drawText(vctr.elementAt(i).toString(),77 , rpos);
			        rpos+=25;
		        	}
		        	else
		        	{
		        		break;
		        	}
			    }
		        
	        }
	        rpos-=5;
	        graphics.setFont(font2);
	        graphics.setColor(Color.DARKSLATEGRAY);
	        
	        if(vctr2.size()>=3)
	        {
	        for(int i=0;i<3;i++)
	        {

	        	if(rpos<_reqHeight)
	        	{
		         graphics.drawText(vctr2.elementAt(i).toString(),77 , rpos);
		         rpos+=22;
	        	}
	        	else
	        	{
	        		break;
	        	}
		    }
	        rpos=10;
	        }
	        else
	        {
	        	for(int i=0;i<vctr2.size();i++)
		        {

		        	if(rpos<_reqHeight)
		        	{
			         graphics.drawText(vctr2.elementAt(i).toString(),77 , rpos);
			         rpos+=22;
		        	}
		        	else
		        	{
		        		break;
		        	}
			    }
	        	rpos=10;
	        }
	        	
	    }
	       
	    /**
	     * Overridden so that the Event Dispatch thread can catch this event
	     * instead of having it be caught here..
	     * @see net.rim.device.api.ui.Field#navigationClick(int, int)
	     */
	    protected boolean navigationClick(int status, int time) 
	    {
	        fieldChangeNotify(1);
	        return true;
	    }
	    private class LoadThumb implements Runnable 
	    {
	 	  public void run() 
	 	  {
	 		  UrlToImage uti = new UrlToImage(_img,"T");
	 		  b1 = uti.getbitmap();
	 		  invalidate();
	 	  }
	    }
}



