package screens;

import net.rim.device.api.ui.*;
import net.rim.device.api.system.*;
public class BOC extends Field
{
	   
	    private String _dboc ;
	    private String _iboc ;
	    private String _tboc ;
	    
	    private Bitmap dbmp ;
	    private Bitmap ibmp ;
	    private Bitmap tbmp ;
	    int f2;
	    
	    private int _height = 100;
	    private int _width = 320;
	    private Font font;
	   
	        
	    /**
	     * Constructor.
	     * @param text The text to be displayed on the button
	     * @param style Combination of field style bits to specify display attributes 
	     */
	     
	    public BOC(String dboc,String iboc,String tboc) 
	    {
	        super(Field.NON_FOCUSABLE);
	    try{
	    	_width = Display.getWidth();
	    	switch(_width)
	    	{
	    	case 480 : 
	    	           f2 = 20;
	    	           
	    	           dbmp = Bitmap.getBitmapResource("bocdom480.jpg");
	    	           ibmp = Bitmap.getBitmapResource("bocinter480.jpg");
	    	           tbmp = Bitmap.getBitmapResource("boctotal480.jpg");
	    	           _height = 60;
	    		       break;
	    	case 360 : 
	    	           f2 = 19;
	    	           dbmp = Bitmap.getBitmapResource("bocdom360.png");
	    	           ibmp = Bitmap.getBitmapResource("bocinter360.png");
	    	           tbmp = Bitmap.getBitmapResource("boctotal360.png");
	    	           _height = 60;
	    		       break;
	    	default :  
	    	           f2 = 17;
	    	           dbmp = Bitmap.getBitmapResource("bocdom320.png");
	    	           ibmp = Bitmap.getBitmapResource("bocinter320.png");
	    	           tbmp = Bitmap.getBitmapResource("boctotal320.png");
	    	           
	    	           _height = 60;
	    		       break; 
	    	}
	    	_dboc = dboc;
	    	_iboc = iboc;
	    	_tboc = tboc;
	   		 FontFamily ff1 = FontFamily.forName("BBAlpha Serif");
	   		 font = ff1.getFont(Font.PLAIN, f2);
	   		  
	   	   }
	   	   catch(Exception ex)
	   	   {
	   		 System.out.println(ex.getMessage());
	   	   }
	    }
	    
	    /**
	     * @return The text on the button
	     */
	   
	    /**
	     * Field implementation.
	     * @see net.rim.device.api.ui.Field#getPreferredHeight()
	     */
	   
		public void setText(String dboc,String iboc,String tboc)
		{
		  	_dboc = dboc;
		  	_iboc = iboc;
		  	_tboc = tboc;
		  	invalidate();
		}
		
	    public int getPreferredHeight() 
	    {
	    	switch(Display.getWidth())
	    	{
	    	case 480 : 
	    	          _height = 60;
	    		       break;
	    	case 360 : 
	    	          _height = 120;
	    		       break;
	    	default : 	    	           
	    	           _height = 120;
	    		       break; 
	    	}
	        return _height;
	    }

	    /**
	     * Field implementation.
	     * @see net.rim.device.api.ui.Field#getPreferredWidth()
	     */
	    public int getPreferredWidth() 
	    {
	    	
	        return Display.getWidth();
	    }
	    
	    /**
	     * Field implementation.  Changes the picture when focus is gained.
	     * @see net.rim.device.api.ui.Field#onFocus(int)
	     */
	    protected void onFocus(int direction) 
	    {
	       
	    }

	    /**
	     * Field implementation.  Changes picture back when focus is lost.
	     * @see net.rim.device.api.ui.Field#onUnfocus()
	     */
	    protected void onUnfocus() 
	    {
	    	
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
	    {  int wid = Display.getWidth();
	    	switch(wid)
	    	{
	    	case 480 : 
	    	          _height = 70;
	    		       break;
	    	case 360 : 
	    	          _height = 120;
	    		       break;
	    	default : 	    	           
	    	           _height = 120;
	    		       break; 
	    	}
	        setExtent(wid,_height);
	        
	    }

	    /**
	     * Field implementation.
	     * @see net.rim.device.api.ui.Field#paint(Graphics)
	     */
	    protected void paint(Graphics graphics) 
	    {       
	        // First draw the background colour and picture
	       
	    	switch(Display.getWidth())
	    	{
	    	  case 480 : graphics.drawBitmap(12, 10, dbmp.getWidth(), dbmp.getHeight(), dbmp, 0, 0);
	    	             graphics.drawBitmap(167, 10, ibmp.getWidth(), ibmp.getHeight(), ibmp, 0, 0);
	    	             graphics.drawBitmap(324, 10, tbmp.getWidth(), tbmp.getHeight(), tbmp, 0, 0);
	    	             graphics.setFont(font);
	    	             graphics.setColor(Color.DARKSLATEGRAY);
	    	             graphics.drawText(_dboc, 18, 40);
	    	             graphics.drawText(_iboc, 173, 40);
	    	             graphics.drawText(_tboc, 330, 40);
	    		         break;
	    	  case 360 : graphics.drawBitmap(15, 10, dbmp.getWidth(), dbmp.getHeight(), dbmp, 0, 0);
	                     graphics.drawBitmap(185, 10, ibmp.getWidth(), ibmp.getHeight(), ibmp, 0, 0);
	                     graphics.setFont(font);
	                     graphics.setColor(Color.DARKSLATEGRAY);
	                     graphics.drawText(_dboc, 28, 40);
	                     graphics.drawText(_iboc, 198, 40);
	                     graphics.drawBitmap(15, 65, tbmp.getWidth(), tbmp.getHeight(), tbmp, 0, 0);
	                     graphics.drawText(_tboc, 130, 95);
	    		         break;
	    	  default :  graphics.drawBitmap(15, 9, dbmp.getWidth(), dbmp.getHeight(), dbmp, 0, 0);
	                     graphics.drawBitmap(166, 10, ibmp.getWidth(), ibmp.getHeight(), ibmp, 0, 0);
	                     graphics.setFont(font);
	                     graphics.setColor(Color.DARKSLATEGRAY);
	                     graphics.drawText(_dboc, 18, 40);
	                     graphics.drawText(_iboc, 172, 40);
	                     graphics.drawBitmap(15, 65, tbmp.getWidth(), tbmp.getHeight(), tbmp, 0, 0);
	                     graphics.drawText(_tboc, 110, 95);
	    		         break;
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
	   
}





