package ast;

import emitter.*;
import environment.*;
import parser.*;
import java.util.*;
import java.io.*;
/**
 * a program -> This  holds a list of variables. procedures and the statement
 * 
 * @author Aaron Lo
 * @version 10-7-19
 */
public class Program extends Statement
{
    private List<Assignment> stringList;
    private List<ProcedureDeclaration> dec;
    private Statement stmt;
    /**
     * This contructs the program
     * @param dec the list of declarations
     * @param stmt the main method
     * @param stringList the list of string variables
     */
    public Program(List<Assignment> stringList, List<ProcedureDeclaration> dec, Statement stmt)
    {
        this.dec = dec;
        this.stmt = stmt;
        this.stringList = stringList;
    }

    /**
     * The compile method for hte program
     * The program has all things needed for a mips file 
     * @param fileName the directed filename
     */
    public void compile(String fileName)
    {
        Emitter e = new Emitter(fileName);
        e.emit(".data");
        e.emit("newLine:\t.asciiz \"\\n\"");
        for(Assignment s: stringList)
        {
            int value = s.getExpression() == null ? 0 : ((Number)s.getExpression()).getValue();
            e.emit("var" + s.getVariable() + ":\t.word\t" + value);
        }

        e.emit(".text");
        e.emit(".globl main");
        e.emit("main:");

        stmt.compile(e);

        e.emit("li $v0 10");
        e.emit("syscall");
        for(ProcedureDeclaration d : dec)
            d.compile(e);
    }

    /**
     * this executes the program
     * @param env the environmetn
     */
    public void exec(Environment env)
    {
        for(Assignment a: stringList)
        {
            if(a.getExpression() == null)
            {
                a.setExpression(new Number(0));
                a.exec(env);
            }
            else
                a.exec(env);
        }

        for(ProcedureDeclaration d : dec)
            d.exec(env);

        try
        {
            stmt.exec(env);
        }
        catch(BreakException e)
        {
            e.printStackTrace();
        }
        catch(SkipException e)
        {
            e.printStackTrace();
        }
    }
}
