package mypack;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordFilter;
import javax.microedition.rms.RecordComparator;
import java.util.Vector;

public class MyMovieStorage {
	public RecordStore store;
	 
	public MyMovieStorage() 
	    {
	        try
	        {
	            store = RecordStore.openRecordStore(Guid.recordStoreName,true);
	            store.setMode(RecordStore.AUTHMODE_ANY,true);
	        }
	        catch (Exception e)
	        {
	            System.err.println("error in MyMovieStorage constructor [" + e.getMessage() + "]");
	        }
	    }
	 public boolean closeStore()
	    {
	        try
	        {
	            store.closeRecordStore();
	        }
	        catch (Exception e)
	        {
	            System.err.println("Error closing [" + e.getMessage() + "]"+e.toString());
	        }
	        return true;
	    }
	 public int getNumRecords()
	    {
	        try
	        {
	            return store.getNumRecords();
	        }
	        catch (Exception e)
	        {
	            System.err.println("Error in getNumRecords " + e.getMessage());
	            return 0;
	        }
	    }
	    public byte[] getRecord(int recId)
	    {
	        try
	        {
	            return store.getRecord(recId);
	        }
	        catch (Exception e)
	        {
	            System.err.println("Error in getRecord[" + recId + "] " + e.getMessage());
	            return null;
	        }
	    }
	    public static void zapStore()
	    {int num=0;
	    String str = "dfgdfg";
	        try
	        {
	           // System.out.println("about to delete record store named [" + Guid.recordStoreName + "]");
	            RecordStore.deleteRecordStore(Guid.recordStoreName);
	           // System.out.println("No of Record Stores" + RecordStore.listRecordStores().length);
	            
	        }
	        catch (Exception e)
	        {
	            System.err.println("Error during zapStore [" + e.toString() + "]No Of Records : "+num+" RecordsStore :"+str);
	            
	        }
	    }
	    
	    public void deleteFeed(String fType,String fName)
	    {
	        try
	        {
	           // System.out.println("Deleting " + fName + " feed ....");
	            // enumerate Record Store. 
	            RecordEnumeration records = store.enumerateRecords(new RSSFilter(fType,fName),null,true);
	            // Remove any records where the name is = feedName.
	            while (records.hasNextElement())
	            {
	                // we store the name in the 2nd field
	                int recId = records.nextRecordId();
	                String thisRecord = new String(store.getRecord(recId));
	                System.out.println("Remove this record [" + thisRecord + "]");
	                store.deleteRecord(recId);
	               
	            }
	            System.out.println("All "+fName+" Feeds Deleted !!");
	            
	        }
	        catch (Exception e)
	        {
	        }
	    }
	    public void deleteAllFeeds()
	    {
	        try
	        {
	            System.out.println("Deleting All Feeds ....");
	            // enumerate Record Store. 
	            RecordEnumeration records = store.enumerateRecords(null,null,true);
	            // Remove any records where the name is = feedName.
	            while (records.hasNextElement())
	            {
	                // we store the name in the 2nd field
	                int recId = records.nextRecordId();
	                String thisRecord = new String(store.getRecord(recId));
	                System.out.println("Remove this record [" + thisRecord + "]");
	                    store.deleteRecord(recId);
	               
	            }
	            System.out.println("All Feeds Deleted !!");
	            
	        }
	        catch (Exception e)
	        {
	        }
	    }
	    public RecordEnumeration getFeedList(String type,String name)
	    {

	        RSSFilter feedsFilter = new RSSFilter(type,name);
	        RecordEnumeration feeds = null;

	        try
	        {
	            feeds = store.enumerateRecords(feedsFilter,feedsFilter,false);
	            return feeds;
	        }
	        catch (Exception e)
	        {
	            System.err.println("Error getting FeedList [" + e.getMessage() + "]");
	        }
	        return null;
	    }
	    public static class RSSFilter implements RecordFilter, RecordComparator 
	    {
	        private String _type = "";
	        private String _name = "";
	        
	        RSSFilter(String type,String name)
	        {
	            _type = type;
	            _name = name;
	        }
	        
	        public boolean matches( byte[] recordData )
	        {
	            try
	            {
	                String oneRec = new String(recordData);
	                Vector v1 = Utils.split(new String(recordData),"|");
	                
	                
	                String recordType = (String) v1.elementAt(0);
	                String recordName = (String) v1.elementAt(1);
	                   
	                   
	                   
	                if (_name != null)   
	                {
	                    if (recordName.trim().equalsIgnoreCase(_name) && recordType.equalsIgnoreCase(_type))
	                    {
	                      //  System.out.println("Found Matching Record 1");
	                        return true;
	                    }
	                }
	                else
	                {
	                    // just matching type
	                    if (recordType.equalsIgnoreCase(_type))
	                    {
	                    //   System.out.println("Found Matching Record 2");
	                        return true;
	                    }
	                }
	            }
	            catch (Exception e)
	            {
	                System.out.println(e);
	                e.printStackTrace();
	            }
	            return false;
	        }
	        
	        
	        public int compare(byte[] rec1, byte[] rec2)
	        {
	            int comp = 0;
	            
	            try
	            {

	                String first = new String(rec1);
	                String second = new String(rec2);
	               // Vector v1 = Utils.split(first,"|");
	              //  Vector v2 = Utils.split(second,"|");
	               // if (_type.equals("H"))
	               // {
	                    //compare name field
	                 //   String r1 = ((String) v1.elementAt(1)).toUpperCase();
	                 //   String r2 = ((String) v2.elementAt(1)).toUpperCase();
	                    comp = first.compareTo(second);
	               // }
	               // else
	             //   {
	                    // compare title field
	              //      String r1 = ((String) v1.elementAt(2)).toUpperCase();
	              //      String r2 = ((String) v2.elementAt(2)).toUpperCase();
	              //      comp = r1.compareTo(r2);
	              //  }
	                
	            }
	            catch (Exception e)
	            {                
	            }
	            
	            if(comp < 0)
	            {
	                return PRECEDES;
	            }
	            else if( comp == 0 )
	            {
	                return EQUIVALENT;
	            }
	            else
	            {
	                return FOLLOWS;
	            }
	        }
	     }
	    public class MyBoc
	     {
	    	 private String _name = "";
	    	 private String _nextid = "0";
	    	 private String _previd = "0";
	    	 private String _movieid = "";
	    	 private String _title = "";
	         private String _reldate = "";
	         private String _description = "";
	         private String _director = "";
	         private String _producer = "";
	         private String _starcast = "";
	         private String _dboc = "";
	         private String _iboc = "";
	         private String _tboc = "";
	         private String _thumbimage = "";
	         private String _innerimage = "";
	         private String _rank = "";
	         private String _rate = "";
	         
	         MyBoc()
	         {
	         }
	         
	         MyBoc(String name,String movieid,String title, String reldate,String description,String director,String producer,String starcast,String dboc,String iboc,String tboc,String thumbimage,String innerimage,String rank,String rate)
	         {
	        	 _name = name;
	        	 _movieid = movieid;
		    	 _title = title;
		         _reldate = reldate;
		         _description = description;
		         _director = director;
		         _producer = producer;
		         _starcast = starcast;
		         _dboc = dboc;
		         _iboc = iboc;
		         _tboc = tboc;
		         _thumbimage = thumbimage;
		         _innerimage = innerimage;
		         _rank = rank;
		         _rate = rate;
	         }
	         public String getNextId()
	         {
	             return _nextid;
	         }
	         public String getPrevId()
	         {
	             return _previd;
	         }
	         public String getMovieId()
	         {
	             return _movieid;
	         }
	         public String getName()
	         {
	             return _name;
	         }
	         public String getRelDate()
	         {
	             return _reldate;
	         }
	         public String getTitle()
	         {
	             return _title;
	         }
	         public String getDescription()
	         {
	             return _description;
	         }
	         public String getDirector()
		     {
			     return _director;
			 }
	         public String getProducer()
		     {
		         return _producer;
		     }
	         public String getStarcast()
		     {
		         return _starcast;
		     }
	         public String getDomesticBOC()
		     {
		         return _dboc;
		     }
	         public String getInternationalBOC()
		     {
		         return _iboc;
		     }
	         public String getTotalBOC()
		     {
		         return _tboc;
		     }
	         public String getThumbImage()
	         {
	             return _thumbimage;
	         }
	         public String getInnerImage()
	         {
	             return _innerimage;
	         }
	         public String getMeterRating()
	         {
	             return _rate;
	         }
	         public String getMeterRank()
	         {
	             return _rank;
	         }
	         public void setNextId(String nextid)
	         {
	             _nextid = nextid;
	         }
	         public void setPrevId(String previd)
	         {
	             _previd = previd;
	         }
	         public void setMovieId(String movieid)
	         {
	             _movieid = movieid;
	         }
	         public void setName(String name)
		     {
		          _name = name;
		     }
	         public void setRelDate(String reldate)
	         {
	             _reldate = reldate;
	         }
	         public void setTitle(String title)
	         {
	             _title = title;
	         }
	         public void setDescription(String description)
	         {
	             _description = description;
	         }
	         public void setDirector(String director)
	         {
	             _director = director;
	         }
	         public void setProducer(String producer)
	         {
	             _producer = producer;
	         }
	         public void setStarcast(String starcast)
	         {
	             _starcast = starcast;
	         }
	         public void setDomesticBOC(String dboc)
	         {
	             _dboc = dboc;
	         }
	         public void setInternationalBOC(String iboc)
	         {
	             _iboc = iboc;
	         }
	         public void setTotalBOC(String tboc)
	         {
	             _tboc = tboc;
	         }
	         public void setThumbImage(String thumbimage)
	         {
	             _thumbimage = thumbimage;
	         }
	         public void setInnerImage(String innerimage)
	         {
	             _innerimage = innerimage;
	         }
	         public void setMeterRating(String rating)
	         {
	             _rate = rating;
	         }
	        
	         public void setMeterRank(String rank)
	         {
	             _rank = rank;
	         }
	         
	         public String toString()
	         {
	             String ret = "B";
	             
	             ret += "|" + _name;
	             ret += "|" + _movieid;
	             ret += "|" + _reldate;
	             ret += "|" + _title;
	             ret += "|" + _description;
	             ret += "|" + _director;
	             ret += "|" + _producer;
	             ret += "|" + _starcast;
	             ret += "|" + _dboc;
	             ret += "|" + _iboc;
	             ret += "|" + _tboc; 
	             ret += "|" + _thumbimage;
	             ret += "|" + _innerimage;
	             ret += "|" + _rate;
	             ret += "|" + _rank;
	             
	             
	             return ret;
	         }
	      }
	    public class MyMovieCal
	     {
	    	 private String _nextid = "0";
	    	 private String _previd = "0";
	    	 private String _name = "";
	    	 private String _releasedate = "";
	    	 private String _movieid = "";
	         private String _title = "";
	         private String _description = "";
	         private String _director = "";
	         private String _producer = "";
	         private String _starcast = "";
	         private String _movietype = "";
	         private String _titleimage = "";
	         private String _bigtitleimage = "";
	         private String _searchkeywords = "";
	         
	         MyMovieCal()
	         {
	         }
	         
	         MyMovieCal(String name,String releasedate, String movieid, String title, String description,String director,String producer,String starcast,String movietype,String titleimage,String bigtitleimage,String searchkeywords)
	         {
	        	 _name = name;
	        	 _releasedate = releasedate;
	             _movieid = movieid;
	             _title = title;
	             _description = description;
	             _movieid = movieid;
	             _director = director;
	             _producer = producer;
	             _starcast = starcast;
	             _movietype = movietype;
	             _titleimage = titleimage;
	             _bigtitleimage = bigtitleimage;
	             _searchkeywords = searchkeywords;
	             
	         }
	         public String getNextId()
	         {
	             return _nextid;
	         }
	         public String getPrevId()
	         {
	             return _previd;
	         }
	         public String getName()
	         {
	             return _name;
	         }
	         public String getReleaseDate()
	         {
	             return _releasedate;
	         }
	         public String getMovieId()
	         {
	             return _movieid;
	         }
	         public String getTitle()
	         {
	             return _title;
	         }
	         public String getDescription()
	         {
	             return _description;
	         }
	         public String getDirector()
	         {
	             return _director;
	         }
	         public String getProducer()
	         {
	             return _producer;
	         }
	         
	         public String getStarcast()
	         {
	        	 return _starcast;
	         }
	         public String getMovieType()
	         {
	        	 return _movietype;
	         }
	         public String getTitleImage()
	         {
	        	 return _titleimage;
	         }
	         public String getBigTitleImage()
	         {
	        	 return _bigtitleimage;
	         }
	         public String getSearchKeywords()
	         {
	        	 return _searchkeywords;
	         }
	         public void setNextId(String nextid)
	         {
	             _nextid = nextid;
	         }
	         public void setPrevId(String previd)
	         {
	             _previd = previd;
	         }
	         public void setName(String name)
	         {
	             _name = name;
	         }
	         public void setReleaseDate(String releasedate)
	         {
	             _releasedate = releasedate;
	         }
	         public void setMovieId(String movieid)
	         {
	             _movieid = movieid;
	         }
	         public void setTitle(String title)
	         {
	             _title = title;
	         }
	         public void setDescription(String description)
	         {
	             _description = description;
	         }
	         public void setDirector(String director)
	         {
	             _director = director;
	         }
	         public void setProducer(String producer)
	         {
	             _producer = producer;
	         }
	         public void setStarcast(String starcast)
	         {
	             _starcast = starcast;
	         }
	         public void setMovietype(String movietype)
	         {
	             _movietype = movietype;
	         }
	         public void setTitleImage(String titleimage)
	         {
	             _titleimage = titleimage;
	         }
	         public void setBigTitleImage(String bigtitleimage)
	         {
	             _bigtitleimage = bigtitleimage;
	         }
	         public void setSearchKeywords(String searchkeywords)
	         {
	             _searchkeywords = searchkeywords;
	         }
	         public String toString()
	         {
	             String ret = "C";
	             
	             ret += "|" + _name;
	             ret += "|" + _releasedate;
	             ret += "|" + _movieid;
	             ret += "|" + _title;
	             ret += "|" + _description;
	             ret += "|" + _director;
	             ret += "|" + _producer;
	             ret += "|" + _starcast;
	             ret += "|" + _movietype;
	             ret += "|" + _titleimage;
	             ret += "|" + _bigtitleimage;
	             ret += "|" + _searchkeywords;
	             
	             return ret;
	         }
	      }
	     public class MyActor
	     {
	    	 private String _name = "";
	    	 private String _rankthisweek = "";
	    	 private String _title = "";
	         private String _starimage = "";
	         private String _starrating = "";
	         private String _ranklastweek = "";
	         
	         MyActor()
	         {
	         }
	         
	         MyActor(String name,String rankthisweek,String title, String starimage,String starrating,String ranklastweek)
	         {
	        	 _name = name;
	        	 _rankthisweek = rankthisweek;
	             _title = title;
	             _starimage = starimage;
	             _starrating = starrating;
	             _ranklastweek = ranklastweek;
	         }
	         public String getRankThisWeek()
	         {
	             return _rankthisweek;
	         }
	         public String getTitle()
	         {
	             return _title;
	         }
	         
	         public String getName()
	         {
	             return _name;
	         }
	         public String getStarImage()
	         {
	             return _starimage;
	         }
	         public String getStarRating()
	         {
	             return _starrating;
	         }
	         public String getRankLastWeek()
	         {
	             return _ranklastweek;
	         }
	         
	          public void setRankThisWeek(String rankthisweek)
	         {
	             _rankthisweek = rankthisweek;
	         }
	         public void setTitle(String title)
	         {
	             _title = title;
	         }
	         public void setStarImage(String starimage)
	         {
	             _starimage = starimage;
	         }
	         public void setStarRating(String starrating)
	         {
	             _starrating = starrating;
	         }
	         public void setName(String name)
	         {
	             _name = name;
	         }
	         public void setRankLastWeek(String ranklastweek)
	         {
	             _ranklastweek = ranklastweek;
	         }
	         
	         public String toString()
	         {
	             String ret = "A";
	             
	             ret += "|" + _name;
	             ret += "|" + _rankthisweek;
	             ret += "|" + _title;
	             ret += "|" + _starimage;
	             ret += "|" + _starrating;
	             ret += "|" + _ranklastweek;
	             
	             
	             return ret;
	         }
	      }
	     
	     public class MySong
	     {
	    	 private String _name = "";
	    	 private String _rankthisweek = "";
	    	 private String _title = "";
	         private String _musicimage = "";
	         private String _musicrating = "";
	         private String _ranklastweek = "";
	         
	         MySong()
	         {
	         }
	         
	         MySong(String name,String rankthisweek,String title, String musicimage,String musicrating,String ranklastweek)
	         {
	        	 _name = name;
	        	 _rankthisweek = rankthisweek;
	             _title = title;
	             _musicimage = musicimage;
	             _musicrating = musicrating;
	             _ranklastweek = ranklastweek;
	         }
	         public String getRankThisWeek()
	         {
	             return _rankthisweek;
	         }
	         public String getTitle()
	         {
	             return _title;
	         }
	         public String getName()
	         {
	             return _name;
	         }
	         public String getMusicImage()
	         {
	             return _musicimage;
	         }
	         public String getMusicRating()
	         {
	             return _musicrating;
	         }
	         
	         public String getRankLastWeek()
	         {
	             return _ranklastweek;
	         }
	         
	          public void setRankThisWeek(String rankthisweek)
	         {
	             _rankthisweek = rankthisweek;
	         }
	          public void setName(String name)
		     {
		             _name = name;
		     }
	         public void setTitle(String title)
	         {
	             _title = title;
	         }
	         public void setMusicImage(String musicimage)
	         {
	             _musicimage = musicimage;
	         }
	         public void setMusicRating(String musicrating)
	         {
	             _musicrating = musicrating;
	         }
	         public void setRankLastWeek(String ranklastweek)
	         {
	             _ranklastweek = ranklastweek;
	         }
	         
	         public String toString()
	         {
	             String ret = "S";
	             
	             ret += "|" + _name;
	             ret += "|" + _rankthisweek;
	             ret += "|" + _title;
	             ret += "|" + _musicimage;
	             ret += "|" + _musicrating;
	             ret += "|" + _ranklastweek;
	             
	             
	             return ret;
	         }
	      }
	     public class MyMovie
	     {
	    	 private String _name = "";
	    	 private String _rankthisweek = "";
	    	 private String _title = "";
	         private String _movieimage = "";
	         private String _movierating = "";
	         private String _ranklastweek = "";
	         
	         MyMovie()
	         {
	         }
	         
	         MyMovie(String name,String rankthisweek,String title, String movieimage,String movierating,String ranklastweek)
	         {
	        	 _name = name;
	        	 _rankthisweek = rankthisweek;
	             _title = title;
	             _movieimage = movieimage;
	             _movierating = movierating;
	             _ranklastweek = ranklastweek;
	         }
	         public String getName()
	         {
	             return _name;
	         }
	         public String getRankThisWeek()
	         {
	             return _rankthisweek;
	         }
	         public String getTitle()
	         {
	             return _title;
	         }
	         
	         public String getMovieImage()
	         {
	             return _movieimage;
	         }
	         public String getMovieRating()
	         {
	             return _movierating;
	         }
	         
	         public String getRankLastWeek()
	         {
	             return _ranklastweek;
	         }
	         
	          public void setRankThisWeek(String rankthisweek)
	         {
	             _rankthisweek = rankthisweek;
	         }
	          public void setName(String name)
		         {
		             _name = name;
		         }
	         public void setTitle(String title)
	         {
	             _title = title;
	         }
	         public void setMovieImage(String movieimage)
	         {
	             _movieimage = movieimage;
	         }
	         public void setMovieRating(String movierating)
	         {
	             _movierating = movierating;
	         }
	         public void setRankLastWeek(String ranklastweek)
	         {
	             _ranklastweek = ranklastweek;
	         }
	         
	         public String toString()
	         {
	             String ret = "M";
	             
	             ret += "|" + _name;
	             ret += "|" + _rankthisweek;
	             ret += "|" + _title;
	             ret += "|" + _movieimage;
	             ret += "|" + _movierating;
	             ret += "|" + _ranklastweek;
	             
	             
	             return ret;
	         }
	      }
	     public class MyNews
	     {
	    	 private String _nextid = "0";
	    	 private String _previd = "0";
	    	 private String _name = "";
	    	 private String _newsid = "";
	    	 private String _title = "";
	         private String _description = "";
	         private String _thumbimage = "";
	         private String _innerimage = "";
	         
	         MyNews()
	         {
	         }
	         
	         MyNews(String name,String newsid,String title, String description,String thumbimage,String innerimage)
	         {
	        	 _name = name;
	        	 _newsid = newsid;
	             _title = title;
	             _description = description;
	             _thumbimage = thumbimage;
	             _innerimage = innerimage;
	         }
	         
	         public String getNextId()
	         {
	             return _nextid;
	         }
	         public String getPrevId()
	         {
	             return _previd;
	         }
	         public String getNewsId()
	         {
	             return _newsid;
	         }
	         public String getTitle()
	         {
	             return _title;
	         }
	         
	         public String getName()
	         {
	             return _name;
	         }
	         public String getThumbImage()
	         {
	             return _thumbimage;
	         }
	         public String getInnerImage()
	         {
	             return _innerimage;
	         }
	         public String getDescription()
	         {
	             return _description;
	         }
	         public void setNextId(String nextid)
	         {
	             _nextid = nextid;
	         }
	         public void setPrevId(String previd)
	         {
	             _previd = previd;
	         }
	         
	          public void setNewsId(String newsid)
	         {
	             _newsid = newsid;
	         }
	         public void setTitle(String title)
	         {
	             _title = title;
	         }
	         public void setThumbImage(String thumbimage)
	         {
	             _thumbimage = thumbimage;
	         }
	         public void setInnerImage(String innerimage)
	         {
	             _innerimage = innerimage;
	         }
	         public void setName(String name)
	         {
	             _name = name;
	         }
	         public void setDescription(String description)
	         {
	             _description = description;
	         }
	         
	         public String toString()
	         {
	             String ret = "N";
	             
	             ret += "|" + _name;
	             ret += "|" + _newsid;
	             ret += "|" + _title;
	             ret += "|" + _description;
	             ret += "|" + _thumbimage;
	             ret += "|" + _innerimage;
	             
	             
	             return ret;
	         }
	      }
	    
	     public class MyRssFeed
	     {
	         private String _name;
	         private String _url;
	         
	         MyRssFeed()
	         {
	         }
	     
	     
	         MyRssFeed(String name,String url)
	         {
	             _name = name;
	             _url = url;
	         }
	     
	        
	         public String getName()
	         {
	             return _name;
	         }
	         
	         public String getUrl()
	         {
	             return _url;
	         }
	          
	          
	         public void setName(String name)
	         {
	             _name = name;
	         } 
	         
	         public void setUrl(String url)
	         {
	             _url = url;
	         }
	        
	         public String toString()
	         {
	             String ret = "H|";
	             ret += _name;
	             ret += "|" + _url;
	             return ret;
	         }
	     }
	     
	     public MyBoc createMyBoc()
	     {
	        return new MyBoc();
	     }
	     public  MyBoc createMyBoc(String name,String movieid,String title, String reldate,String description,String director,String producer,String starcast,String dboc,String iboc,String tboc,String thumbimage,String innerimage,String rank,String rate)
	     {
	         return new MyBoc(name,movieid,title,reldate,description,director,producer,starcast,dboc,iboc,tboc,thumbimage,innerimage,rank,rate);
	         
	     }
	     
	     public MyBoc createMyBoc(byte [] recordData)
	     {
	         return createMyBoc(new String(recordData));
	     }
	     
	     
	     public  MyBoc createMyBoc(String recordData)
	     {
	         Vector v = Utils.split(recordData,"|");
	         MyBoc ret = new MyBoc();
	         ret.setName((String) v.elementAt(1));
	         ret.setMovieId((String) v.elementAt(2));
	         ret.setRelDate((String) v.elementAt(3));
	         ret.setTitle((String) v.elementAt(4));
	         ret.setDescription((String) v.elementAt(5));
	         ret.setDirector((String) v.elementAt(6));
	         ret.setProducer((String) v.elementAt(7));
	         ret.setStarcast((String) v.elementAt(8));
	         ret.setDomesticBOC((String) v.elementAt(9));
	         ret.setInternationalBOC((String) v.elementAt(10));
	         ret.setTotalBOC((String) v.elementAt(11));	       
	         ret.setThumbImage((String) v.elementAt(12));
	         ret.setInnerImage((String) v.elementAt(13));
	         ret.setMeterRating((String) v.elementAt(14));
	         ret.setMeterRank((String) v.elementAt(15));
	         return ret;
	     }
	     
	     public MyMovieCal createMyMovieCal()
	     {
	        return new MyMovieCal();
	     }
	     public  MyMovieCal createMyMovieCal(String name,String releasedate,String movieid,String title,String description,String director,String producer, String starcast,String movietype,String titleimage,String bigtitleimage,String searchkeywords)
	     {
	         return new MyMovieCal(name,releasedate,movieid,title,description,director,producer,starcast,movietype,titleimage,bigtitleimage,searchkeywords);
	         
	     }
	     
	     public MyMovieCal createMyMovieCal(byte [] recordData)
	     {
	         return createMyMovieCal(new String(recordData));
	     }
	     
	     
	     public  MyMovieCal createMyMovieCal(String recordData)
	     {
	         Vector v = Utils.split(recordData,"|");
	         MyMovieCal ret = new MyMovieCal();
	         ret.setName((String) v.elementAt(1));
	         ret.setReleaseDate((String) v.elementAt(2));
	         ret.setMovieId((String) v.elementAt(3));
	         ret.setTitle((String) v.elementAt(4));
	         ret.setDescription((String) v.elementAt(5));
	         ret.setDirector((String) v.elementAt(6));
	         ret.setProducer((String) v.elementAt(7));
	         ret.setStarcast((String) v.elementAt(8));
	         ret.setMovietype((String) v.elementAt(9));
	         ret.setTitleImage((String) v.elementAt(10));
	         ret.setBigTitleImage((String) v.elementAt(11));	       
	         ret.setSearchKeywords((String) v.elementAt(12));
	         
	         return ret;
	     }
	     public MyNews createMyNews()
	     {
	        return new MyNews();
	     }
	     public  MyNews createMyNews(String name,String newsid,String title,String description,String thumbimage, String innerimage)
	     {
	         return new MyNews(name,newsid,title,description,thumbimage,innerimage);
	         
	     }
	     
	     public MyNews createMyNews(byte [] recordData)
	     {
	         return createMyNews(new String(recordData));
	     }
	     
	     
	     public  MyNews createMyNews(String recordData)
	     {
	         Vector v = Utils.split(recordData,"|");
	         MyNews ret = new MyNews();
	         ret.setName((String) v.elementAt(1));
	         ret.setNewsId((String) v.elementAt(2));
	         ret.setTitle((String) v.elementAt(3));
	         ret.setDescription((String) v.elementAt(4));
	         ret.setThumbImage((String) v.elementAt(5));
	         ret.setInnerImage((String) v.elementAt(6));
	         
	         return ret;
	     }
	     
	    
	     public MyMovie createMyMovie()
	     {
	        return new MyMovie();
	     }
	     public  MyMovie createMyMovie(String name,String rankthisweek,String title,String movieimage,String movierating,String ranklastweek)
	     {
	         return new MyMovie(name,rankthisweek,title,movieimage,movierating,ranklastweek);
	         
	     }
	     
	     public MyMovie createMyMovie(byte [] recordData)
	     {
	         return createMyMovie(new String(recordData));
	     }
	     
	     
	     public  MyMovie createMyMovie(String recordData)
	     {
	         Vector v = Utils.split(recordData,"|");
	         MyMovie ret = new MyMovie();
	         ret.setName((String) v.elementAt(1));
	         ret.setRankThisWeek((String) v.elementAt(2));
	         ret.setTitle((String) v.elementAt(3));
	         ret.setMovieImage((String) v.elementAt(4));
	         ret.setMovieRating((String) v.elementAt(5));
	         ret.setRankLastWeek((String) v.elementAt(6));
	         
	         return ret;
	     }
	     public MySong createMySong()
	     {
	        return new MySong();
	     }
	     public  MySong createMySong(String name,String rankthisweek,String title,String musicimage,String musicrating,String ranklastweek)
	     {
	         return new MySong(name,rankthisweek,title,musicimage,musicrating,ranklastweek);
	         
	     }
	     
	     public MySong createMySong(byte [] recordData)
	     {
	         return createMySong(new String(recordData));
	     }
	     
	     
	     public  MySong createMySong(String recordData)
	     {
	         Vector v = Utils.split(recordData,"|");
	         MySong ret = new MySong();
	         ret.setName((String) v.elementAt(1));
	         ret.setRankThisWeek((String) v.elementAt(2));
	         ret.setTitle((String) v.elementAt(3));
	         ret.setMusicImage((String) v.elementAt(4));
	         ret.setMusicRating((String) v.elementAt(5));
	         ret.setRankLastWeek((String) v.elementAt(6));
	         
	         
	         return ret;
	     }
	     public MyActor createMyActor()
	     {
	        return new MyActor();
	     }
	     public  MyActor createMyActor(String name,String rankthisweek,String title,String starimage,String starrating,String ranklastweek)
	     {
	         return new MyActor(name,rankthisweek,title,starimage,starrating,ranklastweek);
	         
	     }
	     
	     public MyActor createMyActor(byte [] recordData)
	     {
	         return createMyActor(new String(recordData));
	     }
	     
	     
	     public  MyActor createMyActor(String recordData)
	     {
	         Vector v = Utils.split(recordData,"|");
	         MyActor ret = new MyActor();
	         ret.setName((String) v.elementAt(1));
	         ret.setRankThisWeek((String) v.elementAt(2));
	         ret.setTitle((String) v.elementAt(3));
	         ret.setStarImage((String) v.elementAt(4));
	         ret.setStarRating((String) v.elementAt(5));
	         ret.setRankLastWeek((String) v.elementAt(6));
	         
	         
	         return ret;
	     }
	     public int addFeedRecord(MyRssFeed feed)
	     {
	         return addRecord(feed.toString().getBytes());
	     }
	     public int addNewsRecord(MyNews feed)
	     {
	         return addRecord(feed.toString().getBytes());
	     }
	     public int addBocRecord(MyBoc feed)
	     {
	         return addRecord(feed.toString().getBytes());
	     }
	     public int addMovieRecord (MyMovie item)
	     {
	         return addRecord(item.toString().getBytes());
	     }
	     public int addSongRecord (MySong item)
	     {
	         return addRecord(item.toString().getBytes());
	     }
	     public int addActorRecord (MyActor item)
	     {
	         return addRecord(item.toString().getBytes());
	     }
	     public int addMovieCalRecord (MyMovieCal item)
	     {
	         return addRecord(item.toString().getBytes());
	     }
	     public int addRecord(byte [] recordData)
	     {
	          int recPosition = 0;
	         try
	         {
	             recPosition = store.addRecord(recordData,0,recordData.length);
	         }
	         catch (Exception ee)
	         {
	             System.err.println("Failed to addRecord [" + ee.getMessage() + "]");
	         }
	         return recPosition;
	     }
	     public RecordEnumeration getFeedItems(String feedname)
	     {

	         RSSFilter feedsFilter = new RSSFilter("D",feedname);
	         RecordEnumeration titles = null;

	         try
	         {
	             titles = store.enumerateRecords(feedsFilter,feedsFilter,false);
	             return titles;
	             
	         }
	         catch (Exception e)
	         {
	             System.err.println("Error getting FeedTitles [" + e.getMessage() + "]");
	         }
	         return null;
	     }
	     public MyRssFeed createFeed(String name,String url)
	     {
	         return new MyRssFeed(name,url);
	     }
	     
	     public MyRssFeed createFeed(byte[] recordData)
	     {
	         return createFeed(new String(recordData));
	     }
	     public MyRssFeed createFeed(String recordData)
	     {
	         Vector v = Utils.split(recordData,"|");
	         MyRssFeed feed = new MyRssFeed();
	         feed.setName((String) v.elementAt(1));
	         feed.setUrl((String)v.elementAt(2));
	         return feed;
	         
	     }
	     public String[] fetchDataItems(String ftype,String feedName)
	     {   RecordEnumeration records;
	         String[] str =null;
	     
	         int cid=0;
	         int pid=0;
	         int nrecords = 0;
	         int i=0;
	    	 try{
	    	 records = store.enumerateRecords(new RSSFilter(ftype,feedName),null,true);
	    	 nrecords = records.numRecords();
	    	  if(nrecords > 0)
	    	  {
	    		 str = new String[nrecords];
	             for(i = 0;i<nrecords;i++)
	            {   
	        	//   cid = records.nextRecordId();
	        	   
	        
	        	//   str[i] = new String(store.getRecord(cid));
	            	 str[i] = new String(records.nextRecord());
	        	  
	            }
	    	  } 
	           
	                // we store the name in the 2nd field
	    	 
	    	 }
	    	 catch(Exception ex)
	    	 {
	    		 ex.printStackTrace();
	    	 }
	    	return str;
	    	 
	    	  
	     }
}

