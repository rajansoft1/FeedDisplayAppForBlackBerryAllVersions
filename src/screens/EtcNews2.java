package screens;

import mypack.*;
import mypack.MyMovieStorage.MyNews;
import net.rim.device.api.system.*;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.*;



public class EtcNews2 extends MainScreen implements FieldChangeListener
{
    MyMovieStorage mms;
    MyNews mm;
    BitmapField thbmp;
	Bitmap la,bmp;
	String _img;
	//String str = "Veer";
	//String str1 = "Veer Pratap Singh (Salman Khan) is a Pindari Prince and the son of the great Pindari warrior, Prithvi Singh (Mithun Chakraborty), who was known for his great battles to free India from British rule. Veer wishes to continue his father's legacy by leading a movement of Pindaris against the British in order to free both the Rajasthani Kingdom of Madhavghar and the rest of India from the great colonial power. Veer receives the help of his younger brother, Punya Singh (Sohail Khan) in gathering together an army. However Veer finds opposition from the King of Madhavghar , Gyanendra singh (Jackie Shroff), who sees Veer as a threat to Madhavghar and his rule and orders for Veer to be killed. Veer and Punya along with their supporters go into hiding within the Thar Desert of Rajasthan, while singh makes an alliance with the British Governor of Rajasthan, James Fraser (Tim James Lawrence), saying that Madhavghar will support the British in crushing the Pindari movement and eliminating Veer.";
	int f1=30;
	int f2=22;
	String[] records;
	
	RichTextField head,body;
	Font font,font2;
	FontFamily ff1;
	
    VerticalFieldManager vm;
    HorizontalFieldManager hm,hm2,hm3;
    FlowFieldManager fm;
    UrlToImage uti;
    UrlToImage2 uti2;
    TopMenuItem tm1,tm2,tm3,tm4,tm5,tm6,tm7;
    int bpos = 143;
    int pos1 = 10;
    int pos2 = 100;
    int _index;
    Thread td;
	int scrwidth;
	Font myFont1;
	
	btnNext bnext = new btnNext(Bitmap.getBitmapResource("nextNews.png"),Bitmap.getBitmapResource("nextNews2.png"));
	btnBack bback = new btnBack(Bitmap.getBitmapResource("goBack.png"),Bitmap.getBitmapResource("goBack2.png"));
	btnPrevious bprev = new btnPrevious(Bitmap.getBitmapResource("prevNews.png"),Bitmap.getBitmapResource("prevNews2.png"));
	
	int hpos = 46;
	int vpos = 30;
    
	//public EtcNews2()
	public EtcNews2(String[] record,int index)
	{
		 super();
		 scrwidth = Display.getWidth();
		 mms = new MyMovieStorage();
		 mm = mms.createMyNews(record[index]);
		 _index = index;
		 records = record;
		 _img = mm.getInnerImage();
		 switch(scrwidth)
		 {
		  case 320 : bmp = Bitmap.getBitmapResource("320header2.png");
                     la = Bitmap.getBitmapResource("newstitle320.PNG");
                     bprev.setMargin(5, 0, 10, 9);
           		     bback.setMargin(5, 0, 10, 30);
           		     bnext.setMargin(5, 0, 10, 40);
           		     bpos = 60;
           		     f1=28;
           		     f2=19;
           		     pos1 = 8;   
           		     hpos = 46;
                     vpos = 30;
                     break;
		  case 480 : bmp = Bitmap.getBitmapResource("480header2.png");
                     la = Bitmap.getBitmapResource("newstitle480.PNG");
                     bprev.setMargin(5, 0, 10, 9);
           		     bback.setMargin(5, 0, 10, 110);
           		     bnext.setMargin(5, 9, 10, 120);
           		     bpos = 140;
           		     f1=32;
        		     f2=24;
        		     pos1=12;
        		     hpos = 50;
                     vpos = 30;
                     break;
		  default :  bmp = Bitmap.getBitmapResource("360header2.png");
                     la = Bitmap.getBitmapResource("newstitle360.png");
		             bprev.setMargin(5, 0, 10, 9);
		  		     bback.setMargin(5, 0, 10, 50);
		  		     bnext.setMargin(5, 0, 10, 60);
		  		     bpos = 80;
		  		     f1=30;
         		     f2=20;
         		     pos1 = 10;
         		     pos2 = 80;
         		     hpos = 46;
                     vpos = 30;
		             break;
		 }
		 
		 try{
		     ff1 = FontFamily.forName("BBAlpha Serif");
			 font = ff1.getFont(Font.PLAIN, f1);
			 font2 = ff1.getFont(Font.PLAIN, f2);
		    }
			   catch(Exception ex)
			   {
				 System.out.println(ex.getMessage());
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
        tm2.setFocus();
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
        tm7.setChangeListener(this);
		vm = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH|VerticalFieldManager.VERTICAL_SCROLL);
	    
		 try{
			 ff1 = FontFamily.forName("BBAlpha Serif");
			 font = ff1.getFont(Font.PLAIN, f1);
			 font2 = ff1.getFont(Font.PLAIN, f2);
			
			 }
			 catch(Exception ex)
			 {
				 System.out.println(ex.getMessage());
			 }
		
			 head = new RichTextField(mm.getTitle())
				{
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						 
					      graphics.setColor(Color.BLUE);
					      
					      super.paint(graphics);
					     }
				};
				
			    body = new RichTextField(mm.getDescription())
			    {
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						 
					      graphics.setColor(Color.DARKSLATEGRAY);
					      
					      super.paint(graphics);
					     }
				};
				
				head.setFont(font);
			    head.setMargin(15, 0, 0, pos1);
			    body.setMargin(10, 10, 20, pos1);
			    body.setFont(font2);
				//uti = new UrlToImage(mm.getInnerImage(),"I");
			    thbmp = new BitmapField(Bitmap.getBitmapResource("blank193.PNG"));
			    thbmp.setMargin(40,0,0,bpos);
			    thbmp.setSpace(10, 10);
			    
	            vm.add(thbmp);
			    vm.add(head);
			    vm.add(body);
			   
			    fm.add(vm);
			    
		        hm3 = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH);
		 
		        hm3.add(bprev);
		        hm3.add(bback);
		        hm3.add(bnext);
		  
		        bback.setChangeListener(this);
		        bprev.setChangeListener(this);
		        bnext.setChangeListener(this);
		        fm.add(hm3);
		        
       		 td = new Thread(new LoadThumb());
		         td.start();
	}
	public boolean onSavePrompt() 
	{ 
	return true; 
	}
	
	public void fieldChanged(Field field, int context) 
    {
      
        
		 if (field instanceof TopMenuItem) {
        	 TopMenuItem tm = (TopMenuItem) field;
        	 if(tm._text=="Box Office Collections")
        	 {
        	   this.getUiEngine().pushScreen(new BoxOffice());
        	 }
        	 else if(tm._text=="Movie Calendar")
        	 {
        	   this.getUiEngine().pushScreen(new MovieCalendar());
        	 }
        	 else if(tm._text=="Home")
        	 {
        	   this.getUiEngine().pushScreen(new Home());
        	 }
        	 else if(tm._text=="Star Meter")
        	 {
        	   this.getUiEngine().pushScreen(new StarMeter());
        	 }
        	 else if(tm._text=="Movie Meter")
        	 {
        	   this.getUiEngine().pushScreen(new MovieMeter());
        	 }
        	 else if(tm._text=="Music Meter")
        	 {
        	   this.getUiEngine().pushScreen(new MusicMeter());
         }
        	 this.getUiEngine().popScreen(this);
        	
        }  
        else if (field instanceof btnBack) {
       	 this.getUiEngine().popScreen(this);
       }
        else if (field instanceof btnNext) {
        	
        	if((_index+1) < records.length)
      	  {
        		 mm = mms.createMyNews(records[_index+1]);
        		 head.setText(mm.getTitle());
        		 body.setText(mm.getDescription());
        		 _img = mm.getInnerImage();
        		 td = new Thread(new LoadThumb());
		         td.start();
        		// uti2 = new UrlToImage2(mm.getInnerImage(),"I");
         		// thbmp.setImage(uti2.getbitmap());
         		  
      		  _index++;
      	  }
        	
       	
       }
        else if (field instanceof btnPrevious) {
       	 
        	if((_index-1) >= 0)
      	  {
        		 mm = mms.createMyNews(records[_index-1]);
        		 head.setText(mm.getTitle());
        		 body.setText(mm.getDescription());
        		 _img = mm.getInnerImage();
        		 td = new Thread(new LoadThumb());
		         td.start();

        		// uti2 = new UrlToImage2(mm.getInnerImage(),"I");
         		// thbmp.setImage(uti2.getbitmap());
      	  _index--;
      	  }
       }
       
       
    }
	 private class LoadThumb implements Runnable 
	    {
	 	  public void run() 
	 	  {
	 		  UrlToImage2 uti = new UrlToImage2(_img,"I");
	 		  thbmp.setImage(uti.getbitmap());
	 		  
	 	  }
	    }

	
  
}


