package mypack;

import screens.*;
import net.rim.device.api.ui.UiApplication;

/**
 * This class extends the UiApplication class, providing a
 * graphical user interface.
 */
public class MyMovieApp extends UiApplication
{
    /**
     * Entry point for application
     * @param args Command line arguments (not used)
     */ 
    public static void main(String[] args)
    {
        // Create a new instance of the application and make the currently
        // running thread the application's event dispatch thread.
        MyMovieApp theApp = new MyMovieApp();       
        theApp.enterEventDispatcher();
    }
    

    /**
     * Creates a new MyMovieApp object
     */
    public MyMovieApp()
    {        
        // Push a screen onto the UI stack for rendering.
    	   pushScreen(new SplashScreen(this));
       //  pushScreen(new Home());
    	//pushScreen(new MovieMeter());
    	
      
    }    
   
}
