package jflex;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.*;
import java.util.*;

/**
 *  This is the tester of scanner that allows you to scan a document
 * 
 * @author AAron L, Shonack ghosh, michael tran
 * @version 9-6-19
 */
public class ScannerTester
{
    private static HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
    /**
     * The tester: this will scan the inputed document and ask you 
     *  for each word you wish to find
     *      then it will output all instances of that word and the line number
     *          EXIT - to exit
     * @param args the the argument
     * @throws IOException if the file is not found
     */
    public static void main(String[] args) throws IOException
    {
        ScannerC sc = new ScannerC(new FileReader("jflex/a.txt"));
        while(!sc.zzAtEOF)
        {
            String pl = sc.nextToken();
            int o = pl.indexOf(",");
            if(o == -1)
                continue;
            String k = pl.substring(0, o);
            if(map.containsKey(k.toLowerCase()))
            {
                map.get(k.toLowerCase()).add(pl);
            }
            else
            {
                ArrayList<String> list = new ArrayList<String>();
                list.add(pl);
                map.put(k.toLowerCase(), list);
                
            }
        }
        
        java.util.Scanner s = new java.util.Scanner(System.in);
        System.out.println("What word would you like to find");
        
        String k  = s.next();
        while(true)
        {
            if(k.equals("EXIT"))
                break;
            else if(!map.containsKey(k))
                System.out.println("No matches");
            else
            {
                ArrayList<String> p = map.get(k);
                for(String l : p)
                {
                    System.out.println(l);
                }
            }
            System.out.println("What word would you like to find");
            k  = s.next();
        }
    }
}
