package screens;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.system.*;
public class ShowAll extends Field
{
	   
	    String _text ;
	   
	    private int _height = 15;
	    private int _width = 30;
	    int f1=20;
	    int act=0;
	    private Font font;
	   
	    
	        
	    /**
	     * Constructor.
	     * @param text The text to be displayed on the button
	     * @param style Combination of field style bits to specify display attributes 
	     */
	    public ShowAll(String text) 
	    {
	        super(Field.FOCUSABLE);
	    try{
	    	_text = text;
	   		_width = Display.getWidth();
	   		 switch(_width)
	   		 {
	   		   case 320 : f1 = 18;
                          break;
	   		   case 480 : f1 = 20; 
                          break;
	   		   default  : f1 = 19;
	 	                  break;
	   		 }
	   		 
	   		 FontFamily ff1 = FontFamily.forName("BBAlpha Serif");
	   		 font = ff1.getFont(Font.BOLD, f1);
	   		 _height = font.getHeight()+8;
	   		 _width = font.getAdvance(text)+8;
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
	       this.setBackground(BackgroundFactory.createSolidBackground(Color.BLUE));
	       act = 1;
	        invalidate();
	    }

	    /**
	     * Field implementation.  Changes picture back when focus is lost.
	     * @see net.rim.device.api.ui.Field#onUnfocus()
	     */
	    protected void onUnfocus() 
	    {
	    	this.setBackground(BackgroundFactory.createSolidTransparentBackground(Color.BLUE, 0));
	    	act = 0;
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
	        setExtent(_width,_height);
	        
	    }

	    /**
	     * Field implementation.
	     * @see net.rim.device.api.ui.Field#paint(Graphics)
	     */
	    protected void paint(Graphics graphics) 
	    {       
	        // First draw the background colour and picture
	     if(act==0)
	     {
	        graphics.setColor(Color.DARKSLATEGRAY);
	     }
	     else
	     {
	    	 graphics.setColor(Color.WHITE);
	     }
	        graphics.setFont(font);
	        graphics.drawText(_text,4 , 4);
	           
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


