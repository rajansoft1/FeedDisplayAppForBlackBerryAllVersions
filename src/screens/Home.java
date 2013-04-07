package screens;


import mypack.*;
import mypack.MyMovieStorage.*;
import net.rim.device.api.system.*;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.container.*;



public class Home extends MainScreen implements FieldChangeListener,KeypadListener
{
    Bitmap bmp;
    Bitmap la,la2,la3,la4,la5,la6;
    TopMenuItem tm1,tm2,tm3,tm4,tm5,tm6,tm7;
    VerticalFieldManager vm;
    HorizontalFieldManager hm,hm2;
    FlowFieldManager fm;
    UrlToImage uti;
    Bitmap thbmp;
	Thread td;
	ShowAll lf1 = new ShowAll("See all news");
	ShowAll lf2 = new ShowAll("See all release dates");
	ShowAll lf3 = new ShowAll("See all collections");
	ShowAll lf4 = new ShowAll("See all stars");
	ShowAll lf5 = new ShowAll("See all movies");
	ShowAll lf6 = new ShowAll("See all songs");
	int scrwidth ;
	Font myFont1;
	int hpos = 46;
	int vpos = 30;
	MyMovieStorage mms;
	MyNews mm;
	MyField3 mf;
	MyField mf2;
	MyField4 mf3;
	MyBoc bc;
	MyMovieCal mc;
	MySong ms;
	MyActor ma;
	MyMovie mmo;
	String[] records;
	String[] records2;
	String[] records3;
	String[] records4;
	String[] records5;
	String[] records6;
	
    
	public Home()
	{
		 super();
		 scrwidth = Display.getWidth();
		 
		 switch(scrwidth)
		 {
		  case 320 : bmp = Bitmap.getBitmapResource("320header2.png");
                     la = Bitmap.getBitmapResource("newstitle320.PNG");
                     la2 = Bitmap.getBitmapResource("mctitle320.png");
                     la3 = Bitmap.getBitmapResource("boctitle320.PNG");
                     la4 = Bitmap.getBitmapResource("startitle320.png");
                     la5 = Bitmap.getBitmapResource("movietitle320.png");
                     la6 = Bitmap.getBitmapResource("musictitle320.png");
                     hpos = 46;
                     vpos = 30;
                     break;
		  case 480 : bmp = Bitmap.getBitmapResource("480header2.png");
                     la = Bitmap.getBitmapResource("newstitle480.PNG");
                     la2 = Bitmap.getBitmapResource("mctitle480.PNG");
                     la3 = Bitmap.getBitmapResource("boctitle480.PNG");
                     la4 = Bitmap.getBitmapResource("startitle480.PNG");
                     la5 = Bitmap.getBitmapResource("movietitle480.PNG");
                     la6 = Bitmap.getBitmapResource("musictitle480.PNG");
                     hpos = 50;
                     vpos = 30;
                     break;
		  default : bmp = Bitmap.getBitmapResource("360header2.png");
		            la = Bitmap.getBitmapResource("newstitle360.png");
		            la2 = Bitmap.getBitmapResource("mctitle360.PNG");
		            la3 = Bitmap.getBitmapResource("boctitle360.png");
		            la4 = Bitmap.getBitmapResource("startitle.png");
                    la5 = Bitmap.getBitmapResource("movietitle.png");
                    la6 = Bitmap.getBitmapResource("musictitle.png");
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
       
        tm5 = new TopMenuItem("Star Meter");
        hm.add(tm5);
        tm5.setChangeListener(this);
        
        tm6 = new TopMenuItem("Movie Meter");
        hm.add(tm6);
        tm6.setChangeListener(this);
       
        tm7 = new TopMenuItem("Music Meter");
        hm.add(tm7);
        tm7.setChangeListener(this);
        
		vm = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH|VerticalFieldManager.VERTICAL_SCROLL);
		
	    vm.setMargin(vpos, 0, 0, 0);
		 try{
			 FontFamily ff1 = FontFamily.forName("BBAlpha Serif");
			 myFont1 = ff1.getFont(Font.PLAIN, 16);
			 
			 setFont(myFont1);
			 lf1.setFont(myFont1);
			 
			 }
			 catch(Exception ex)
			 {
				 System.out.println(ex.getMessage());
			 }
		    
	      	mms = new MyMovieStorage();
		    records = mms.fetchDataItems("N", "etc-news");
		
	        fm.add(vm);
		
		
		
		 if(records!=null)
		 { 
		   for(int i = 0; i < 2; i++)
		  {
		   mm = mms.createMyNews(records[i]);
		   mf = new MyField3(i,"N",mm.getTitle(),mm.getDescription(),mm.getThumbImage());
		  
		   vm.add(mf);
		   mf.setChangeListener(this);
		  }
		 }
		 vm.add(lf1);
		 lf1.setChangeListener(this);
			lf1.setMargin(5, 0, 5, 10);
			
		   vm.add(new BitmapField(la2));
		  
		   records2 = mms.fetchDataItems("C", "movie-calendar");
			 if(records2!=null)
				 { 
				   for(int j = 0; j < 2; j++)
				  {
				   mc = mms.createMyMovieCal(records2[j]);
				   mf3 = new MyField4(j,"C",mc.getTitle(),mc.getDescription(),mc.getTitleImage(),mc.getReleaseDate());
				   vm.add(mf3);
				   mf3.setChangeListener(this);
				  }
				 }
			    vm.add(lf2);
			    lf2.setMargin(5, 0, 5, 10);
			    lf2.setChangeListener(this);
				
				   vm.add(new BitmapField(la3));
				   
				   records3 = mms.fetchDataItems("B", "box-office");
			     if(records3!=null)
				 { 
				   for(int k = 0; k < 2; k++)
				  {
					   bc = mms.createMyBoc(records3[k]);
				        mf = new MyField3(k,"B",bc.getTitle(),bc.getDescription(),bc.getThumbImage());
						   vm.add(mf);
						   mf.setChangeListener(this);
				  }
			    }
			     vm.add(lf3);
                 lf3.setMargin(5, 0, 5, 10);
				 lf3.setChangeListener(this);
				 vm.add(new BitmapField(la4));
				   
				   records4 = mms.fetchDataItems("A", "star-meter");
			     if(records4!=null)
				 { 
				   for(int k = 0; k < 2; k++)
				  {
					   ma = mms.createMyActor(records4[k]);
					//   uti = new UrlToImage(ma.getStarImage(),"T");
					//   thbmp = uti.getbitmap();
				        mf2 = new MyField(ma.getTitle(),ma.getStarImage(),ma.getRankThisWeek(),ma.getRankLastWeek(),ma.getStarRating());
						vm.add(mf2);
						   
				  }
			    }
			     vm.add(lf4);
               lf4.setMargin(5, 0, 5, 10);
				 lf4.setChangeListener(this);
				 vm.add(new BitmapField(la5));
				   
				   records5 = mms.fetchDataItems("M", "movie-meter");
			     if(records5!=null)
				 { 
				   for(int k = 0; k < 2; k++)
				  {
					   mmo = mms.createMyMovie(records5[k]);
					 //  uti = new UrlToImage(mmo.getMovieImage(),"T");
					//   thbmp = uti.getbitmap();
				        mf2 = new MyField(mmo.getTitle(),mmo.getMovieImage(),mmo.getRankThisWeek(),mmo.getRankLastWeek(),mmo.getMovieRating());
						   vm.add(mf2);
						  
				  }
			    }
			     vm.add(lf5);
               lf5.setMargin(5, 0, 5, 10);
				 lf5.setChangeListener(this);
				 vm.add(new BitmapField(la6));
				   
				   records6 = mms.fetchDataItems("S", "music-meter");
			     if(records6!=null)
				 { 
				   for(int k = 0; k < 2; k++)
				  {
					   ms = mms.createMySong(records6[k]);
					//   uti = new UrlToImage(ms.getMusicImage(),"T");
					//   thbmp = uti.getbitmap();
				        mf2 = new MyField(ms.getTitle(),ms.getMusicImage(),ms.getRankThisWeek(),ms.getRankLastWeek(),ms.getMusicRating());
						   vm.add(mf2);
						   
				  }
			    }
			     vm.add(lf6);
               lf6.setMargin(5, 0, 5, 10);
				 lf6.setChangeListener(this);
		  
	}
	public boolean onSavePrompt() 
	   { 
	   return true; 
	   }
	
	public void showMoM()
	   { 
		this.getUiEngine().pushScreen(new MovieMeter());  
	   }
	public void showSoM()
	   { 
		this.getUiEngine().pushScreen(new StarMeter());  
	   }
	public void showMuM()
	   { 
		this.getUiEngine().pushScreen(new MusicMeter());  
	   }
	public void showBoC()
	   { 
		this.getUiEngine().pushScreen(new BoxOffice());  
	   }
	public void showMoC()
	   { 
		this.getUiEngine().pushScreen(new MovieCalendar());  
	   }
	public void showNews()
	   { 
		this.getUiEngine().pushScreen(new EtcNews());  
	   }
	public void showHome()
	   { 
		this.getUiEngine().pushScreen(new Home());  
	   }
	public void fieldChanged(Field field, int context) 
    {
		 
        if (field instanceof TopMenuItem) {
        	 TopMenuItem tm = (TopMenuItem) field;
        	 if(tm._text=="ETC News")
        	 {
        	   showNews();
        	 }
        	 else if(tm._text=="Movie Calendar")
        	 {   
        	   showMoC();
        	 }
        	 else if(tm._text=="Box Office Collections")
        	 {
        	   showBoC();
        	 }
        	 else if(tm._text=="Star Meter")
        	 {
        	  showSoM();
        	 }
        	 else if(tm._text=="Movie Meter")
        	 {
        	  showMoM();
        	 }
        	 else if(tm._text=="Music Meter")
        	 {
        	  showMuM();
        	 }
        	 
        	
        }else if (field instanceof ShowAll) {
       	 
           ShowAll lf = (ShowAll) field;
            if(lf._text=="See all news")
            {
        	 showNews();
            }
            else if(lf._text=="See all release dates")
            {
           	 showMoC();
            }
            else if(lf._text=="See all collections")
            {
           	 showBoC();
            }
            else if(lf._text=="See all stars")
            {
           	 showSoM();
            }
            else if(lf._text=="See all movies")
            {
           	 showMoM();
            }
            else if(lf._text=="See all songs")
            {
           	 showMuM();
            }
       	
       } 
        else if (field instanceof MyField3) {
          	 
            MyField3 mf = (MyField3) field;
             if(mf.ftype == "N")
             {
            	 this.getUiEngine().pushScreen(new EtcNews2(records,mf.indexno));
             }else if(mf.ftype == "B")
             {
            	 this.getUiEngine().pushScreen(new BoxOffice2(records3,mf.indexno));
             }
             
        	 
        } 
        else if (field instanceof MyField4) {
         	 
            MyField4 mf = (MyField4) field;
            
            this.getUiEngine().pushScreen(new MovieCalendar2(records2,mf.indexno));
            
             
        	 
        } 
        
       
    }
	
  


}