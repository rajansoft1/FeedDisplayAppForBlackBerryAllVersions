package mypack;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.decor.BackgroundFactory;

//A field that displays an animated GIF.

public class AnimatedGIFField extends Field
{
    private Bitmap _image;     //The image to draw.
    private int _currentFrame;          //The current frame in
                                        //the animation sequence.
    private int _width=100;                 //The width of the image
                                        //(background frame).
    private int _height=100;                //The height of the image
                                       // (background frame).
    private AnimatorThread _animatorThread;
    int nloops = 100; 
    int alpha = 0; 
    int scrwidth = Display.getWidth();

    public AnimatedGIFField(int loops,int alpha)
    {
        //Call super to setup the field with the specified style.
        //The image is passed in as well for the field to
        //configure its required size.
        super();
        nloops = loops;
        scrwidth = Display.getWidth();
        //Store the image and it's dimensions.
        if(scrwidth==320)
        {
        _image = Bitmap.getBitmapResource("Frames0.png");
        }
        else
        {
        _image = Bitmap.getBitmapResource("Frame0.png");
        }
        this.setBackground(BackgroundFactory.createSolidTransparentBackground(Color.BLACK, alpha));
        //Start the animation thread.
        _animatorThread = new AnimatorThread(this);
        _animatorThread.start();
    }

    protected void paint(Graphics graphics)
    {
        //Call super.paint. This will draw the first background
        //frame and handle any required focus drawing.

        //Don't redraw the background if this is the first frame.
       
            //Draw the animation frame.
    	   if(scrwidth==320)
    	   {
        	_image = Bitmap.getBitmapResource("Frame"+_currentFrame+".png");
            graphics.drawBitmap(25,25,_image.getWidth(), _image.getHeight(), _image, 0, 0);
    	   }
    	   else
    	   {
    	    _image = Bitmap.getBitmapResource("Frames"+_currentFrame+".png");
            graphics.drawBitmap(18,18,_image.getWidth(), _image.getHeight(), _image, 0, 0);
    	   }
        
    }
    protected void layout(int width, int height) 
    {
        setExtent(80,80);
        
    }
    //Stop the animation thread when the screen the field is on is
    //popped off of the display stack.
    protected void onUndisplay()
    {
        _animatorThread.stop();
        super.onUndisplay();
    }


    //A thread to handle the animation.
    private class AnimatorThread extends Thread
    {
        private AnimatedGIFField _theField;
        private boolean _keepGoing = true;
        private int _totalFrames;     //The total number of
                                       // frames in the image.
        private int _loopCount;       //The number of times the
                                     //animation has looped (completed).
        private int _totalLoops;      //The number of times the animation should loop (set in the image).

        public AnimatorThread(AnimatedGIFField theField)
        {
            _theField = theField;
            _totalFrames = 8;
            _totalLoops = nloops;

        }

        public synchronized void stop()
        {
            _keepGoing = false;
        }

        public void run()
        {
            while(_keepGoing)
            {
                //Invalidate the field so that it is redrawn.
                UiApplication.getUiApplication().invokeAndWait(new Runnable()
                {
                    public void run()
                    {
                        _theField.invalidate();
                    }
                });

                try
                {
                    //Sleep for the current frame delay before
                    //the next frame is drawn.
                    sleep(50);
                }
                catch (InterruptedException iex)
                {} //Couldn't sleep.

                //Increment the frame.
                ++_currentFrame;

                if (_currentFrame == _totalFrames)
                {
                    //Reset back to frame 0 if we have reached the end.
                    _currentFrame = 0;

                    ++_loopCount;

                    //Check if the animation should continue.
                    if (_loopCount == _totalLoops)
                    {
                        _keepGoing = false;
                    }
                }
            }
        }
    }
}
