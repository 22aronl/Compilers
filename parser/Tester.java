package parser;

import java.io.FileInputStream;
import java.io.IOException;
import scanner.*;
import emitter.*;
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
        for(int i = 3; i < 4; i++)
        {
            Scanner sc = new Scanner(new FileInputStream("parser/parserTest9 ("+i+").txt"));
            Parser p = new Parser(sc);
            Environment env = new Environment();
            p.parseProgram().compile("codeGenTest"+i+".txt");
            System.out.println("Test For"+ i + "  :)");
        }
    }
}
