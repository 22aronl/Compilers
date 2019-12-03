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
        for(int i = 10; i < 13; i++)
        {
            String file = "parser/parserTestProc";
            Scanner sc = new Scanner(new FileInputStream(file));
            Parser p = new Parser(sc);
            Environment env = new Environment();
            Program pro = p.parseProgram();
            pro.compile("codeGenTest" + i + ".txt");
            //pro.exec(env);
            System.out.println("Test For"+ i + "  :)");
        }
    }
}
