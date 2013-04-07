package screens;


import mypack.*;
import mypack.MyMovieStorage.MySong;
import net.rim.device.api.system.*;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.container.*;



public class MusicMeter extends MainScreen implements FieldChangeListener
{
    Bitmap bmp;
    Bitmap la;
    VerticalFieldManager vm;
    HorizontalFieldManager hm,hm2,hm3;
    FlowFieldManager fm;
    TopMenuItem tm1,tm2,tm3,tm4,tm5,tm6,tm7;
    UrlToImage uti;
    Bitmap thbmp;
	Bitmap rating;
	int scrwidth ;
	Font myFont1;
	int tr=0;
	int rr=0;
	int vr=0;
	MyMovieStorage mms;
	MySong mm;
	String[] records;
	
	//LoadNextItems lni = new LoadNextItems(Bitmap.getBitmapResource("load5songs.png"),Bitmap.getBitmapResource("load5songs2.png"));
	
	    int hpos = 46;
	    int vpos = 30;
    
    
	public MusicMeter()
	{
		 super();
		 scrwidth = Display.getWidth();
		 
		 switch(scrwidth)
		 {
		  case 320 : bmp = Bitmap.getBitmapResource("320header2.png");
                     la = Bitmap.getBitmapResource("musictitle320.png");
                     hpos = 46;
                     vpos = 30;
                     break;
		  case 480 : bmp = Bitmap.getBitmapResource("480header2.png");
                     la = Bitmap.getBitmapResource("musictitle480.PNG");
                     hpos = 50;
                     vpos = 30;
                     break;
		  default : bmp = Bitmap.getBitmapResource("360header2.png");
		            la = Bitmap.getBitmapResource("musictitle.png");
		            hpos = 46;
                    vpos = 30;
		            break;
		 }
		  fm = new FlowFieldManager(FlowFieldManager.USE_ALL_WIDTH|FlowFieldManager.VERTICAL_SCROLL)
		 {
			 public void paint(Graphics graphics)
			    {
			      //Draw the background image and then call paint.
				 
			      graphics.drawBitmap(0, 0, bmp.getWidth(),bmp.getHeight(), bmp, 0, 0);
			      graphics.drawBitmap(0, bmp.getHeight(), la.getWidth(),la.getHeight(), la, 0, 0);
			      
			      super.paint(graphics);
			     }
		 };
		add(fm);
		hm2 = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH);
		fm.add(hm2);
		hm = new HorizontalFieldManager(HorizontalFieldManager.HORIZONTAL_SCROLL);
		 hm.setMargin(hpos,30,0,30);
		hm2.add(hm);
		tm1 = new TopMenuItem("Home");
        hm.add(tm1);
        tm1.setChangeListener(this);
        
        tm2 = new TopMenuItem("ETC News");
        hm.add(tm2);
        tm2.setChangeListener(this);
       
        tm3 = new TopMenuItem("Movie Calendar");
        hm.add(tm3);
        tm3.setChangeListener(this);
        
        tm4 = new TopMenuItem("Box Office Collections");
        hm.add(tm4);
        tm4.setChangeListener(this);
       
        tm5 =  new TopMenuItem("Star Meter");
        hm.add(tm5);
        tm5.setChangeListener(this);
        
        tm6 = new TopMenuItem("Movie Meter");
        hm.add(tm6);
        tm6.setChangeListener(this);
       
        tm7 = new TopMenuItem("Music Meter");
        hm.add(tm7);
        tm7.setFocus();
        tm7.setChangeListener(this);
		vm = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH|VerticalFieldManager.VERTICAL_SCROLL);
	    vm.setMargin(vpos, 0, 0, 0);
		 try{
			 FontFamily ff1 = FontFamily.forName("BBAlpha Serif");
			 myFont1 = ff1.getFont(Font.PLAIN, 18);
			 
			 setFont(myFont1);
			
			 }
			 catch(Exception ex)
			 {
				 System.out.println(ex.getMessage());
			 }
		
	      	mms = new MyMovieStorage();
		    records = mms.fetchDataItems("S", "music-meter");
		
	        fm.add(vm);
		
	        //  uti = new UrlToImage("http://115.119.191.164/4e117a8eec82f_Veer_60x60.jpg");
			//thbmp = uti.getbitmap();
		   // thbmp = Bitmap.getBitmapResource("zelda.jpg");
		 if(records!=null)
		 { 
			 tr = records.length;
			 if(tr >= 10)
			 {
		       for(int i = 0; i < 10; i++)
		       {
		        mm = mms.createMySong(records[i]);
		       // uti = new UrlToImage(mm.getMusicImage(),"T");
		      //  thbmp = uti.getbitmap();
		        vm.add(new MyField(mm.getTitle(),mm.getMusicImage(),mm.getRankThisWeek(),mm.getRankLastWeek(),mm.getMusicRating()));
		        // vm.add(new MyField(title[i],thbmp,crank[i],prank[i],rate[i]));
		       }
		       vr=10;
		       rr = tr - vr;
		     }
			 else
	 		  {
	 			for(int i = 0; i < tr; i++)
	   		    {
					 mm = mms.createMySong(records[i]);
	 		        //uti = new UrlToImage(mm.getMusicImage(),"T");
	 		        //thbmp = uti.getbitmap();
	 		        vm.add(new MyField(mm.getTitle(),mm.getMusicImage(),mm.getRankThisWeek(),mm.getRankLastWeek(),mm.getMusicRating()));
	 		        // vm.add(new MyField(title[i],thbmp,crank[i],prank[i],rate[i]));
	   		    }
	 			 vr=tr;
	 			 rr=0;
	   		  }
		 }
	//	   hm3 = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH);
	//	   int left = (scrwidth/3);
	//	 lni.setMargin(5, 0, 5, left);
	//	 lni.setChangeListener(this);
	//	 hm3.add(lni);
	//	 fm.add(hm3);
	}
	public boolean onSavePrompt() 
	   { 
	   return true; 
	   }
	public void fieldChanged(Field field, int context) 
    {
      
        
		 if (field instanceof TopMenuItem) {
        	 TopMenuItem tm = (TopMenuItem) field;
        	 if(tm._text=="Home")
        	 {
        	   this.getUiEngine().pushScreen(new Home());
        	 }
        	 else if(tm._text=="Movie Calendar")
        	 {
        	   this.getUiEngine().pushScreen(new MovieCalendar());
        	 }
        	 else if(tm._text=="Box Office Collections")
        	 {
        	   this.getUiEngine().pushScreen(new BoxOffice());
        	 }
        	 else if(tm._text=="Star Meter")
        	 {
        	   this.getUiEngine().pushScreen(new StarMeter());
        	 }
        	 else if(tm._text=="Movie Meter")
        	 {
        	   this.getUiEngine().pushScreen(new MovieMeter());
        	 }
        	 else if(tm._text=="ETC News")
        	 {
        	   this.getUiEngine().pushScreen(new EtcNews());
        	 }
        	 this.getUiEngine().popScreen(this);
        	
        }
   //     else if(field instanceof LoadNextItems)
    //    {
    //    
    //    	if(rr != 0)
    // 	   {
     //		    if(rr >= 5)
     //			{
     //		       for(int i = vr; i < (vr+5); i++)
     //		       {

    //    		   mm = mms.createMySong(records[i]); 
     //   		   uti = new UrlToImage(mm.getMusicImage(),"T");
     //   		   thbmp = uti.getbitmap();
     //   		   vm.add(new MyField(mm.getTitle(),thbmp,mm.getRankThisWeek(),mm.getRankLastWeek(),mm.getMusicRating()));
        		   //   vm.add(new MyField(title[i],thbmp,crank[i],prank[i],rate[i]));
     //   		  }
     //		       vr+=5;
  	//	           rr=tr-vr;
    //			}
    //			else
    //			{
    //			   for(int i = vr; i < rr; i++)
      //		       {

     //      		    mm = mms.createMySong(records[i]); 
     //      		    uti = new UrlToImage(mm.getMusicImage(),"T");
     //      		    thbmp = uti.getbitmap();
     //      		    vm.add(new MyField(mm.getTitle(),thbmp,mm.getRankThisWeek(),mm.getRankLastWeek(),mm.getMusicRating()));
           		    //   vm.add(new MyField(title[i],thbmp,crank[i],prank[i],rate[i]));
           		   
    //    		   }
 	//		       vr=tr;
  	//	           rr=0;
     //   	   }
        	
    //    	}
   //     }
    }
       
 }

	
  


