package mypack;

import net.rim.device.api.ui.*;
import net.rim.device.api.ui.container.*;
import net.rim.device.api.system.*;
import java.util.*;
import screens.*;

public class SplashScreen extends MainScreen {
   Application ui;
   private Timer timer = new Timer();
   private  Bitmap _bitmap;
   AnimatedGIFField gf;
   int hpos = 200;
   int vpos = 230;
   Thread td;
   public SplashScreen(UiApplication _ui) {
      super(Field.USE_ALL_HEIGHT | Field.USE_ALL_WIDTH);
      ui = _ui;
      
      switch(Display.getWidth())
      {  case 320 :   _bitmap = Bitmap.getBitmapResource("splash320x240.png");
                     hpos = 115;
                     vpos = 180;
                    break;
    	 case 480 :   _bitmap = Bitmap.getBitmapResource("splash480x360.png");
    	            hpos = 200;
    	            vpos = 280;
                    break;
    	  default :  hpos = 130;
                     
    		         if(Display.getHeight()==400)
    	            {
    		          _bitmap = Bitmap.getBitmapResource("splash360x400.png");
    		          vpos = 315;
    	            }
    	            else
    	            {
    	                  _bitmap = Bitmap.getBitmapResource("splash.png");
    	                  vpos = 360;
    	            }
    		         break;
      
      }
      VerticalFieldManager vm = new VerticalFieldManager(VerticalFieldManager.USE_ALL_HEIGHT | VerticalFieldManager.USE_ALL_WIDTH)
      {
    	  public void paint(Graphics graphics)
		    {
		      //Draw the background image and then call paint.
			 
		      graphics.drawBitmap(0, 0, _bitmap.getWidth(),_bitmap.getHeight(), _bitmap, 0, 0);
		      super.paint(graphics);
		     }
      };
      add(vm);
      gf = new AnimatedGIFField(300,0);
      gf.setMargin(vpos, 0, 0, hpos);
      vm.add(gf);
          DownloadThread2 dThread = new DownloadThread2();
          td = new Thread(dThread);
          td.start();
          //DownloadThread dThread = new DownloadThread();
	      //td = new Thread(dThread);
	     // td.start();
	      timer.schedule(new CountDown(),18000);
     
    		  
   }
   public void dismiss()
   {
	   timer.cancel();
	   this.getUiEngine().pushScreen(new Home());
       this.getUiEngine().popScreen(this);
      
   }
   
   private class CountDown extends TimerTask 
   {
	   public void run()
	   {
		 // dismiss();
	     DismissThread dThread = new DismissThread();
	     ui.invokeLater(dThread);
	    // DismissThread2 dThread2 = new DismissThread2();
	    // ui.invokeLater(dThread2);
	   }
	 }
   private class DismissThread implements Runnable 
   {
	  public void run() 
	  {  
	     // DismissThread2 dThread2 = new DismissThread2();
	      
	     //ui.invokeAndWait(dThread2);
		  dismiss();
	  }
   }
   private class DownloadThread2 implements Runnable 
   {
	  public void run() 
	  {  int nway = 0;
	     String uid = null;
	     PrepareApp prp = new PrepareApp();
	     nway = prp.getNetWay();
	      uid = prp.getUID();
          if(nway!=0)
          {
		   MyMovieComms mc = new MyMovieComms(nway,uid);
		   System.out.println("### # # ##@@@@@@@ @ @@ %% % % Downloaded DATA @ @$@#$ @#$ @#$");
          }
	  }
   }
   private class DownloadThread implements Runnable {
		  public void run() 
		  {
			  
			  
			  int nway = 0;
			  String uid = null;
			  PrepareApp prp = new PrepareApp();
			  nway = prp.getNetWay();
			  uid = prp.getUID();
			  if(nway != 0)
			  {
				  MyMovieComms2 mc = new MyMovieComms2(nway,0,uid,"http://mocms.etc.in/rss/movie-meter.xml");
				  MyMovieComms2 mc1 = new MyMovieComms2(nway,1,uid,"http://mocms.etc.in/rss/music-meter.xml");
				  MyMovieComms2 mc2 = new MyMovieComms2(nway,2,uid,"http://mocms.etc.in/rss/star-meter.xml");
				  MyMovieComms2 mc3 = new MyMovieComms2(nway,3,uid,"http://mocms.etc.in/rss/etc-news.xml");
				  MyMovieComms2 mc4 = new MyMovieComms2(nway,4,uid,"http://mocms.etc.in/rss/movie-calendar.xml");
				  MyMovieComms2 mc5 = new MyMovieComms2(nway,5,uid,"http://mocms.etc.in/rss/box-office-collection.xml");
				  
				  Thread td1 = new Thread(mc);
				  Thread td2 = new Thread(mc1);
				  Thread td3 = new Thread(mc2);
				  Thread td4 = new Thread(mc3);
				  Thread td5 = new Thread(mc4);
				  Thread td6 = new Thread(mc5);
				 
				  td1.start();
				  td2.start();
				  td3.start();
				  td4.start();
				  td5.start();
				  td6.start();
			  }
			  
		  }
	   }
   

    
} 