package mypack;

import java.util.Vector;

class Utils 
{
    Utils()
    {
    }
    
    public static Vector split(String inputstring,String delimiter)
    {
        try
        {
            
        Vector vec = new Vector();
        int i,j;
        boolean done = false;
        
        i = 0;
        j = 0;
        while (!done)
        {
            i = inputstring.indexOf(delimiter,j);
            if (i == -1) 
            {
                // no more delimiters, grab the balance of the field
                if (j < inputstring.length())
                {
                    vec.addElement(inputstring.substring(j));
                }
                else
                {
                    vec.addElement("");
                }
                done = true;
            }
            else
            {
                String x = inputstring.substring(j,i);
               // System.out.println("Found [" + x + "]");
                vec.addElement(x);
                j = i;
                if (j < inputstring.length())
                {
                    j++;
                }
            }
        }
        
        return vec;
        }
        catch (Exception e)
        {
            System.err.println("Error during split [" + e.getMessage() + "]");
            return null;
        }
    }
} 