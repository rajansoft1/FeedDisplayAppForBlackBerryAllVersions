package screens;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.system.*;
public class btnNext extends Field
{
	    private Bitmap _currentPicture;
	    private Bitmap _onPicture ;
	    private Bitmap _offPicture ;
	        
	    /**
	     * Constructor.
	     * @param text The text to be displayed on the button
	     * @param style Combination of field style bits to specify display attributes 
	     */
	    public btnNext(Bitmap on,Bitmap off) 
	    {
	        super(Field.FOCUSABLE);
	        
	        _currentPicture = on;
	        _onPicture =off ;
	        _offPicture = on;
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
	        return _currentPicture.getHeight();
	    }

	    /**
	     * Field implementation.
	     * @see net.rim.device.api.ui.Field#getPreferredWidth()
	     */
	    public int getPreferredWidth() 
	    {
	        return _currentPicture.getWidth();
	    }
	    
	    /**
	     * Field implementation.  Changes the picture when focus is gained.
	     * @see net.rim.device.api.ui.Field#onFocus(int)
	     */
	    protected void onFocus(int direction) 
	    {   this.setBackground(BackgroundFactory.createBitmapBackground(_onPicture));
	        _currentPicture = _onPicture;
	        invalidate();
	    }

	    /**
	     * Field implementation.  Changes picture back when focus is lost.
	     * @see net.rim.device.api.ui.Field#onUnfocus()
	     */
	    protected void onUnfocus() 
	    { this.setBackground(BackgroundFactory.createBitmapBackground(_offPicture));
	        _currentPicture = _offPicture;
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
	        setExtent(_currentPicture.getWidth(),_currentPicture.getHeight() );
	    }

	    /**
	     * Field implementation.
	     * @see net.rim.device.api.ui.Field#paint(Graphics)
	     */
	    protected void paint(Graphics graphics) 
	    {       
	        // First draw the background colour and picture
	
	        graphics.drawBitmap(0, 0, _currentPicture.getWidth(),_currentPicture.getHeight() , _currentPicture, 0, 0);
	        
	       
	      
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

