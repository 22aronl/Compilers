package parser;

import java.io.FileInputStream;
import java.io.IOException;
import scanner.*;

/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tester
{
    public static void main(String[] args) throws IOException
    {
        for(int i = 0; i < 5; i++)
        {
            Scanner sc = new Scanner(new FileInputStream("parser/parserTest" + i +".txt"));
            Parser p = new Parser(sc);
            while(sc.hasNext())
            p.parseStatement();
            System.out.println("^^&&^^");
        }
    }
}
