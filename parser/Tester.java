package parser;

import java.io.FileInputStream;
import java.io.IOException;
import scanner.*;
import ast.*;
import environment.*;

/**
 * The tester
 * 
 * @author AAron Lo
 * @version 10-3-19
 */
public class Tester
{
    /**
     * The tester
     * @param args the arguments
     */
    public static void main(String[] args) throws IOException, SkipException
    {
        for(int i = 6; i < 7; i++)
        {
            Scanner sc = new Scanner(new FileInputStream("parser/parserTest" + i +".txt"));
            Parser p = new Parser(sc);
            Environment e = new Environment();
            while(sc.hasNext())
                p.parseProgram().exec(e);
            System.out.println("^^&&^^");
        }
    }
}
