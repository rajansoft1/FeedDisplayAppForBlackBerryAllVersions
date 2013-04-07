package screens;

import mypack.UrlToImage;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.system.*;
public class MyField extends Field
{
	    private Bitmap dback ;
	    private Bitmap sback ;
	    private Bitmap cback ;
	    private String _title ;
	    private String _img ;
	    private Bitmap b1 ;
	    private Bitmap rating ;
	    private int f1=22;
	    private int f2=19;
	    private int _height = 88;
	    private int _width = Display.getWidth();
	    private String _crank ;
	    private String _prank ;
	    private Font font;
	    private Font font2;
	    
	        
	    /**
	     * Constructor.
	     * @param text The text to be displayed on the button
	     * @param style Combination of field style bits to specify display attributes 
	     */
	    public MyField(String title,String imgURL,String crank,String prank,String rate) 
	    {
	        super(Field.FOCUSABLE);
	    try{
	   		 FontFamily ff1 = FontFamily.forName("BBAlpha Serif");
	   		 switch(_width)
	   		 {
	   		   case 320 : dback = Bitmap.getBitmapResource("dback320.png");
                          sback = Bitmap.getBitmapResource("select320.png");
                          f1=20;
                          f2=16;
                          break;
	   		   case 480 : dback = Bitmap.getBitmapResource("dback480.png");
                          sback = Bitmap.getBitmapResource("select480.png");
                          f1=23;
                          f2=20;
                          break;
	   		   default  : dback = Bitmap.getBitmapResource("dback.png");
	 	                  sback = Bitmap.getBitmapResource("select360.png");
	 	                  f1=21;
                          f2=17;
	 	                  break;
	   		 }
	   		 font = ff1.getFont(Font.PLAIN, f1);
	   		 font2 = ff1.getFont(Font.PLAIN, f2);
	   		 _crank = crank;
	   		 _prank = prank;
	   		 _title = title;
	   		 _img = imgURL;
	   		 //b1 = bmp;
	   		 b1 = Bitmap.getBitmapResource("blank60.PNG");
	   		 _width = Display.getWidth();
	   		
	   		switch(Integer.valueOf(rate).intValue())
	   		 {
	   		   case 1 : rating = Bitmap.getBitmapResource("onestar.png");
                        break;
	   		   case 2 : rating = Bitmap.getBitmapResource("twostar.png");
                        break;
	   		   case 3 : rating = Bitmap.getBitmapResource("threestar.png");
                        break;
	           case 4 : rating = Bitmap.getBitmapResource("fourstar.png");
                        break;
	           case 5 : rating = Bitmap.getBitmapResource("fivestar.png");
                        break;
	   		   default : rating = Bitmap.getBitmapResource("nostar.png");
	 	                 break;
	   		 }
		    cback = dback;
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
	    public int getPreferredHeight() 
	    {
	        return _height;
	    }
        public void setBitmap()
        {
        	
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
		   if(_width==320)
		   {
		    graphics.drawBitmap(185, 60, rating.getWidth(),rating.getHeight() , rating, 0, 0);
	        graphics.setColor(Color.BLUE);
	        graphics.setFont(font);
	        graphics.drawText(_title,77 , 10);
	        graphics.setFont(font2);
	        graphics.drawText("Current Rank :",77 , 40);
	        graphics.setColor(Color.DARKSLATEGRAY);
	        graphics.drawText(_crank+" |",174 , 40);
	        graphics.setColor(Color.BLUE);
	        graphics.drawText("Previous Rank :",197 , 40);
	        graphics.setColor(Color.DARKSLATEGRAY);
	        graphics.drawText(_prank,299 , 40);
	        graphics.setColor(Color.BLUE);
	        graphics.drawText("Overall Rating ",77 , 61);
		   }
		   else if(_width==480)
		   {
		    graphics.drawBitmap(220, 62, rating.getWidth(),rating.getHeight() , rating, 0, 0);
	        graphics.setColor(Color.BLUE);
	        graphics.setFont(font);
	        graphics.drawText(_title,77 , 10);
	        graphics.setFont(font2);
	        graphics.drawText("Current Rank :",77 , 40);
	        graphics.setColor(Color.DARKSLATEGRAY);
	        graphics.drawText(_crank+" |",207 , 40);
	        graphics.setColor(Color.BLUE);
	        graphics.drawText("Previous Rank :",242 , 40);
	        graphics.setColor(Color.DARKSLATEGRAY);
	        graphics.drawText(_prank,378 , 40);
	        graphics.setColor(Color.BLUE);
	        graphics.drawText("Overall Rating ",77 , 61);
		   }
		   else 
		   {
		    graphics.drawBitmap(220, 62, rating.getWidth(),rating.getHeight() , rating, 0, 0);
	        graphics.setColor(Color.BLUE);
	        graphics.setFont(font);
	        graphics.drawText(_title,77 , 10);
	        graphics.setFont(font2);
	        graphics.drawText("Current Rank :",77 , 40);
	        graphics.setColor(Color.DARKSLATEGRAY);
	        graphics.drawText(_crank+" |",188 , 40);
	        graphics.setColor(Color.BLUE);
	        graphics.drawText("Previous Rank :",223 , 40);
	        graphics.setColor(Color.DARKSLATEGRAY);
	        graphics.drawText(_prank,335 , 40);
	        graphics.setColor(Color.BLUE);
	        graphics.drawText("Overall Rating ",77 , 61);
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


