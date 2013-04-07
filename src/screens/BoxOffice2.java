package screens;

import java.util.Vector;

import mypack.*;
import mypack.MyMovieStorage.MyBoc;
import net.rim.device.api.system.*;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.container.*;



public class BoxOffice2 extends MainScreen implements FieldChangeListener
{
    MyMovieStorage mms;
    MyBoc mm;
    Thread td;
    BitmapField thbmp,orbmp,mmbmp;
	Bitmap la,bmp,div,bmp2;
//	String str = "Veer";
	
//	String director = "Anil Sharma";
	String producer = "Vijay Galani, Sunil A. Lulla";
	String starcast = "Salman Khan, Sohail Khan, Mithun Chakraborty, Jackie Shroff, Zarine Khan, Lisa Lazarus, Gita Soto, Neena Gupta, Aryan Vaid, Bharat Dabholkar, Bunny Anand, Shahbaaz Khan,Puru Raaj Kumar, Tim Laurence, William Chubb, Rajesh Vivek, Ashok Samarth, Vinay Apte, Yogesh Suri, Surendra Singh, Yuri Suri, Roy Brongsheet";
//	String reldate = "2010-01-22";
//	String sdom = "192,100,000";
//	String sint = "100,100,000";
//	String stot = "292,200,000";
//	String over = "4";
//	String meter = "5";
	String _img;
	Vector prod,stars;
	String[] records;
	int f1=30;
	int f2=22;
	BOC bc;
	RichTextField head,dir,pro,star,rdate,dir1,pro1,star1,rdate1,boc,body;
	LabelField sr,mr,rdom,rint,rtot;
	Font font,font2;
	FontFamily ff1;
	
    VerticalFieldManager vm;
    HorizontalFieldManager hm,hm2,hm3,hm4,hm5,hm6,hm7,hm8,hm9;
    FlowFieldManager fm;
    UrlToImage uti;
    UrlToImage2 uti2;
    TopMenuItem tm1,tm2,tm3,tm4,tm5,tm6,tm7;
    int bpos = 143;
    int pos1 = 10;
    int pos2 = 100;
    int _index;
    
	int scrwidth;
	Font myFont1;
	
	btnNext bnext = new btnNext(Bitmap.getBitmapResource("nextMov.png"),Bitmap.getBitmapResource("nextMov2.png"));
	btnBack bback = new btnBack(Bitmap.getBitmapResource("goBack.png"),Bitmap.getBitmapResource("goBack2.png"));
	btnPrevious bprev = new btnPrevious(Bitmap.getBitmapResource("prevMov.png"),Bitmap.getBitmapResource("prevMov2.png"));
	int hpos = 46;
	int vpos = 30;
	
    
	//public BoxOffice2()
	public BoxOffice2(String[] record,int index)
	{
		 super();
		 scrwidth = Display.getWidth();
		 mms = new MyMovieStorage();
		 mm = mms.createMyBoc(record[index]);
		 _index = index;
		 records = record;
		 _img = mm.getInnerImage();
		 switch(scrwidth)
		 {
		  case 320 : bmp = Bitmap.getBitmapResource("320header2.png");
                     la = Bitmap.getBitmapResource("boctitle320.PNG");
                     div = Bitmap.getBitmapResource("divider320.png");
                    
                     bprev.setMargin(5, 0, 10, 9);
           		     bback.setMargin(5, 0, 10, 30);
           		     bnext.setMargin(5, 0, 10, 40);
           		     bpos = 60;
           		     f1=28;
           		     f2=19;
           		     pos1 = 8;
           		     pos2 = 60;
           		     hpos = 46;
                     vpos = 30;
                     break;
		  case 480 : bmp = Bitmap.getBitmapResource("480header2.png");
                     la = Bitmap.getBitmapResource("boctitle480.PNG");
                     div = Bitmap.getBitmapResource("divider480.png");
                     
                     bprev.setMargin(5, 0, 10, 9);
           		     bback.setMargin(5, 0, 10, 110);
           		     bnext.setMargin(5, 9, 10, 120);
           		     bpos = 140;
           		     f1=32;
        		     f2=24;
        		     pos1=12;
        		     pos2=100;
        		     hpos = 50;
                     vpos = 30;
                     break;
		  default :  bmp = Bitmap.getBitmapResource("360header2.png");
                     la = Bitmap.getBitmapResource("boctitle360.png");
                     div = Bitmap.getBitmapResource("divider360.png");
                     
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
		 switch(Integer.valueOf(mm.getMeterRank()).intValue())
   		 {
   		   case 1 : orbmp = new BitmapField(Bitmap.getBitmapResource("onestar.png"));
                    break;
   		   case 2 : orbmp = new BitmapField(Bitmap.getBitmapResource("twostar.png"));
                    break;
   		   case 3 : orbmp = new BitmapField(Bitmap.getBitmapResource("threestar.png"));
                    break;
           case 4 : orbmp = new BitmapField(Bitmap.getBitmapResource("fourstar.png"));
                    break;
           case 5 : orbmp = new BitmapField(Bitmap.getBitmapResource("fivestar.png"));
                    break;
   		   default : orbmp = new BitmapField(Bitmap.getBitmapResource("nostar.png"));
 	                 break;
   		 }
		 switch(Integer.valueOf(mm.getMeterRating()).intValue())
   		 {
   		   case 1 : mmbmp = new BitmapField(Bitmap.getBitmapResource("onestar.png"));
                    break;
   		   case 2 : mmbmp = new BitmapField(Bitmap.getBitmapResource("twostar.png"));
                    break;
   		   case 3 : mmbmp = new BitmapField(Bitmap.getBitmapResource("threestar.png"));
                    break;
           case 4 : mmbmp = new BitmapField(Bitmap.getBitmapResource("fourstar.png"));
                    break;
           case 5 : mmbmp = new BitmapField(Bitmap.getBitmapResource("fivestar.png"));
                    break;
   		   default : mmbmp = new BitmapField(Bitmap.getBitmapResource("nostar.png"));
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
			   producer = splitstar(mm.getProducer());
			   starcast = splitstar(mm.getStarcast());
		
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
        tm4.setFocus();
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
	    
		
		
			 head = new RichTextField(mm.getTitle())
				{
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						 
					      graphics.setColor(Color.BLUE);
					      
					      super.paint(graphics);
					     }
				};
				dir = new RichTextField("Director :")
				{
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						 
					      graphics.setColor(Color.BLUE);
					      
					      super.paint(graphics);
					     }
				};
				pro = new RichTextField("Producer :")
				{
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						 
					      graphics.setColor(Color.BLUE);
					      
					      super.paint(graphics);
					     }
				};
				star = new RichTextField("Cast :")
				{
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						 
					      graphics.setColor(Color.BLUE);
					      
					      super.paint(graphics);
					     }
				};
				rdate = new RichTextField("Release date :")
				{
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						 
					      graphics.setColor(Color.BLUE);
					      
					      super.paint(graphics);
					     }
				};
			  //  body = new RichTextField(mm.getDescription())
			   // {
				//	 public void paint(Graphics graphics)
				//	    {
					      //Draw the background image and then call paint.
						 
				///	      graphics.setColor(Color.DARKSLATEGRAY);
					      
				//	      super.paint(graphics);
					//     }
			//	};
				dir1 = new RichTextField(mm.getDirector())
			    {
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						 
					      graphics.setColor(Color.DARKSLATEGRAY);
					      
					      super.paint(graphics);
					     }
				};
				pro1 = new RichTextField(producer)
			    {
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						  
					      graphics.setColor(Color.DARKSLATEGRAY);
					      
					      super.paint(graphics);
					     }
				};
				rdate1 = new RichTextField(mm.getRelDate())
			    {
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						 
					      graphics.setColor(Color.DARKSLATEGRAY);
					      
					      super.paint(graphics);
					     }
				};
				star1 = new RichTextField(starcast)
			    {
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						 
					      graphics.setColor(Color.DARKSLATEGRAY);
					      
					      super.paint(graphics);
					     }
				};
				boc = new RichTextField("Box Office Collections")
			   {
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						 
					      graphics.setColor(Color.BLUE);
					      
					      super.paint(graphics);
					     }
				};
				bc = new BOC(mm.getDomesticBOC(),mm.getInternationalBOC(),mm.getTotalBOC());
			//	rdom = new LabelField(mm.getDomesticBOC())
			//    {
			//		 public void paint(Graphics graphics)
			//		    {
					      //Draw the background image and then call paint.
						 
			//		      graphics.setColor(Color.DARKSLATEGRAY);
					      
			//		      super.paint(graphics);
			//		     }
			//	};
			//	rint = new LabelField(mm.getInternationalBOC())
			//    {
			//		 public void paint(Graphics graphics)
			//		    {
					      //Draw the background image and then call paint.
						 
			//		      graphics.setColor(Color.DARKSLATEGRAY);
					      
			//		      super.paint(graphics);
			//		     }
			//	};
			//	rtot = new LabelField(mm.getTotalBOC())
			//    {
			//		 public void paint(Graphics graphics)
			//		    {
					      //Draw the background image and then call paint.
						 
			//		      graphics.setColor(Color.DARKSLATEGRAY);
					      
			//		      super.paint(graphics);
			//		     }
			//	};
				mr = new LabelField("Movie Meter : ")
			    {
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						 
					      graphics.setColor(Color.BLUE);
					      
					      super.paint(graphics);
					     }
				};
				sr = new LabelField("Overall Rating : ")
			    {
					 public void paint(Graphics graphics)
					    {
					      //Draw the background image and then call paint.
						 
					      graphics.setColor(Color.BLUE);
					      
					      super.paint(graphics);
					     }
				};
				
				head.setFont(font);
			    head.setMargin(15, 0, 0, pos1);
			    
			  //  body.setFont(font2); 
			    dir.setFont(font2);
			    dir1.setFont(font2);
			    pro.setFont(font2);
			    pro1.setFont(font2);
			    star.setFont(font2);
			    star1.setFont(font2);
			    rdate.setFont(font2);
			    rdate1.setFont(font2);
			    boc.setFont(font2);
		   //   rdom.setFont(font2);
			//	rint.setFont(font2);
		   //   rtot.setFont(font2);
			    mr.setFont(font2);
			    sr.setFont(font2);
			//	uti = new UrlToImage(mm.getInnerImage(),"I");
		  //    bmp2 = uti.getbitmap();
			    bmp2 = Bitmap.getBitmapResource("blank193.PNG");
			    thbmp = new BitmapField(bmp2);
			    thbmp.setMargin(40,0,0,bpos);
			    thbmp.setSpace(10, 10);
			//  ibf = new BitmapField(ibmp);
			 // tbf = new BitmapField(tbmp);
		   //   dbf = new BitmapField(dbmp);
			//    dbf.setMargin(2, 0, 10, pos1);
			//    ibf.setMargin(2, 0, 10, 10);
			//    tbf.setMargin(2, 0, 10, 10);
			    dir.setMargin(15,0,0,pos1);
			    dir1.setMargin(0,0,0,pos2);
                pro.setMargin(10,0,0,pos1);
			    pro1.setMargin(0,0,0,pos2);
			    star.setMargin(10,0,0,pos1);
			    star1.setMargin(0,0,0,pos2);
			    rdate.setMargin(10,0,0,pos1);
			    rdate1.setMargin(0,0,0,pos2);
			  //  body.setMargin(10, 10, 15, pos1);
			    boc.setMargin(10, 0, 5, pos1);
			    
			    hm6 = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH);
			    hm7 = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH);
			   
			    vm.add(thbmp);
			    vm.add(head);
			    vm.add(dir);
			    vm.add(dir1);
			    vm.add(pro);
			    vm.add(pro1);
			    vm.add(star);
			    vm.add(star1);
			    vm.add(rdate);
			    vm.add(rdate1);
			   // vm.add(body);
			    vm.add(boc);
			    vm.add(bc);
			    fm.add(vm);
			    
			    if(scrwidth==480)
			    {
			 //    dbf.setMargin(2, 0, 10, pos1);
			//	 ibf.setMargin(2, 0, 10, 10);
			//	 tbf.setMargin(2, 0, 10, 10);
				 
				// rdom.setMargin(5, 0, 10, pos1);
				// rint.setMargin(5, 0, 10, 30);
				// rtot.setMargin(5, 0, 10, 30);
				 
				 sr.setMargin(10, 0, 0, pos1);
				 orbmp.setMargin(15, 0, 0, 5);

				 mr.setMargin(10, 0, 20, pos1);
				 mmbmp.setMargin(15, 0, 20, 20);
				 
			    // hm4.add(dbf);
			   //  hm4.add(ibf);
			   //  hm4.add(tbf);
			     
			  //   hm5.add(rdom);
			   //  hm5.add(rint);
			   //  hm5.add(rtot);
			     
			     hm6.add(sr);
			     hm6.add(orbmp);
			     hm7.add(mr);
			     hm7.add(mmbmp);
			     
			    
			    }
			    else if(scrwidth==360)
			    {
			   //  hm8 = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH);
			   //  hm9 = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH);

			     //dbf.setMargin(2, 0, 5, pos1);
				// ibf.setMargin(2, 0, 5, 12);
				// tbf.setMargin(2, 0, 5, 10);
				 
				// rdom.setMargin(5, 0, 10, pos1+15);
				// rint.setMargin(5, 0, 10, 70);
				// rtot.setMargin(5, 0, 10, 120);
				 
				 sr.setMargin(10, 0, 0, pos1);
				 orbmp.setMargin(13, 0, 0, 5);

				 mr.setMargin(10, 0, 20, pos1);
				 mmbmp.setMargin(10, 0, 20, 20);
				 
			    // hm4.add(dbf);
			   //  hm4.add(ibf);
			     
			   //  hm5.add(rdom);
			    // hm5.add(rint);
			     
			   //  hm8.add(tbf);
			    // hm9.add(rtot);
			     
			     hm6.add(sr);
			     hm6.add(orbmp);
			     hm7.add(mr);
			     hm7.add(mmbmp);
			     
			     //fm.add(hm4);
				// fm.add(hm5);
				// fm.add(hm8);
				// fm.add(hm9);
			    }
			    else
				    {
			    	// hm8 = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH);
				    // hm9 = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH);

				   //  dbf.setMargin(2, 0, 5, pos1);
					// ibf.setMargin(2, 0, 5, 16);
					// tbf.setMargin(2, 0, 5, 10);
					 
					// rdom.setMargin(5, 0, 10, pos1+15);
					// rint.setMargin(5, 0, 10, 65);
					// rtot.setMargin(5, 0, 10, 110);
					 
					 sr.setMargin(10, 0, 0, pos1);
					 orbmp.setMargin(13, 0, 0, 5);

					 mr.setMargin(10, 0, 20, pos1);
					 mmbmp.setMargin(10, 0, 20, 20);
					 
				    // hm4.add(dbf);
				    // hm4.add(ibf);
				     
				    // hm5.add(rdom);
				   // hm5.add(rint);
				     
				   //  hm8.add(tbf);
				    // hm9.add(rtot);
				     
				     hm6.add(sr);
				     hm6.add(orbmp);
				     hm7.add(mr);
				     hm7.add(mmbmp);
				     
				    // fm.add(hm4);
					// fm.add(hm5);
					// fm.add(hm8);
					// fm.add(hm9);
				    }
			   
			    fm.add(new BitmapField(div));
			    fm.add(hm6);
			    fm.add(hm7);
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
	private String splitstar (String text) 
	{
	   boolean flag=true;
	   int sp = 0;
	   int ep = 0;
	   String str="";
	   while(flag)
	   {
		   if(text.indexOf(",")!=-1)
		   {
		   ep = text.indexOf(",");
		   str += text.substring(sp,ep).trim()+"\n";
		   text = text.substring(ep+1);
		   }
		   else
		   {
			   str += text.substring(sp).trim();
			   flag=false;
		   }
		   
	   }
	   return str;
	}
	public void fieldChanged(Field field, int context) 
    {
      
        
		 if (field instanceof TopMenuItem) {
        	 TopMenuItem tm = (TopMenuItem) field;
        	 if(tm._text=="ETC News")
        	 {
        	   this.getUiEngine().pushScreen(new EtcNews());
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
      	  {       mm = mms.createMyBoc(records[_index+1]);
        		  head.setText(mm.getTitle());
        		//  body.setText(mm.getDescription());
        		  dir1.setText(mm.getDirector());
        		  pro1.setText(splitstar(mm.getProducer()));
        		  star1.setText(splitstar(mm.getStarcast()));
        		  rdate1.setText(mm.getRelDate());
        		  _img = mm.getInnerImage();
        		  td = new Thread(new LoadThumb());
  		          td.start();
      		      bc.setText(mm.getDomesticBOC(),mm.getInternationalBOC(),mm.getTotalBOC());
      		     
      		    switch(Integer.valueOf(mm.getMeterRank()).intValue())
      	   		 {
      	   		   case 1 : orbmp.setImage(EncodedImage.getEncodedImageResource("onestar.png"));
      	                    break;
      	   		   case 2 : orbmp.setImage(EncodedImage.getEncodedImageResource("twostar.png"));
      	                    break;
      	   		   case 3 : orbmp.setImage(EncodedImage.getEncodedImageResource("threestar.png"));
      	                    break;
      	           case 4 : orbmp.setImage(EncodedImage.getEncodedImageResource("fourstar.png"));
      	                    break;
      	           case 5 : orbmp.setImage(EncodedImage.getEncodedImageResource("fivestar.png"));
      	                    break;
      	   		   default : orbmp.setImage(EncodedImage.getEncodedImageResource("nostar.png"));
      	 	                 break;
      	   		 }
      			 switch(Integer.valueOf(mm.getMeterRating()).intValue())
      	   		 {
      	   		   case 1 : mmbmp.setImage(EncodedImage.getEncodedImageResource("onestar.png"));
      	                    break;
      	   		   case 2 : mmbmp.setImage(EncodedImage.getEncodedImageResource("twostar.png"));
      	                    break;
      	   		   case 3 : mmbmp.setImage(EncodedImage.getEncodedImageResource("threestar.png"));
      	                    break;
      	           case 4 : mmbmp.setImage(EncodedImage.getEncodedImageResource("fourstar.png"));
      	                    break;
      	           case 5 : mmbmp.setImage(EncodedImage.getEncodedImageResource("fivestar.png"));
      	                    break;
      	   		   default : mmbmp.setImage(EncodedImage.getEncodedImageResource("nostar.png"));
      	 	                 break;
      	   		 }
      		  _index++;
      	  }
        	
       	
       }
        else if (field instanceof btnPrevious) {
       	 
        	if((_index-1) >= 0)
      	  {   
        		 mm = mms.createMyBoc(records[_index-1]);
       		     head.setText(mm.getTitle());
       		  //   body.setText(mm.getDescription());
       		     dir1.setText(mm.getDirector());
       		     pro1.setText(splitstar(mm.getProducer()));
       		     star1.setText(splitstar(mm.getStarcast()));
       		     rdate1.setText(mm.getRelDate());
       		     _img = mm.getInnerImage();
       		     td = new Thread(new LoadThumb());
		         td.start();
     		     bc.setText(mm.getDomesticBOC(),mm.getInternationalBOC(),mm.getTotalBOC());
     		    switch(Integer.valueOf(mm.getMeterRank()).intValue())
     	   		 {
     	   		   case 1 : orbmp.setImage(EncodedImage.getEncodedImageResource("onestar.png"));
     	                    break;
     	   		   case 2 : orbmp.setImage(EncodedImage.getEncodedImageResource("twostar.png"));
     	                    break;
     	   		   case 3 : orbmp.setImage(EncodedImage.getEncodedImageResource("threestar.png"));
     	                    break;
     	           case 4 : orbmp.setImage(EncodedImage.getEncodedImageResource("fourstar.png"));
     	                    break;
     	           case 5 : orbmp.setImage(EncodedImage.getEncodedImageResource("fivestar.png"));
     	                    break;
     	   		   default : orbmp.setImage(EncodedImage.getEncodedImageResource("nostar.png"));
     	 	                 break;
     	   		 }
     			 switch(Integer.valueOf(mm.getMeterRating()).intValue())
     	   		 {
     	   		   case 1 : mmbmp.setImage(EncodedImage.getEncodedImageResource("onestar.png"));
     	                    break;
     	   		   case 2 : mmbmp.setImage(EncodedImage.getEncodedImageResource("twostar.png"));
     	                    break;
     	   		   case 3 : mmbmp.setImage(EncodedImage.getEncodedImageResource("threestar.png"));
     	                    break;
     	           case 4 : mmbmp.setImage(EncodedImage.getEncodedImageResource("fourstar.png"));
     	                    break;
     	           case 5 : mmbmp.setImage(EncodedImage.getEncodedImageResource("fivestar.png"));
     	                    break;
     	   		   default : mmbmp.setImage(EncodedImage.getEncodedImageResource("nostar.png"));
     	 	                 break;
     	   		 }
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


