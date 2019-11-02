package ast;

import emitter.*;
import environment.*;
import parser.*;
import java.util.*;
import java.io.*;
/**
 * program
 * 
 * @author Aaron Lo
 * @version 10-7-19
 */
public class Program extends Statement
{
    private List<String> stringList;
    private List<ProcedureDeclaration> dec;
    private Statement stmt;
    /**
     * This contructs the program
     * @param dec the list of declarations
     * @param stmt the main method
     */
    public Program(List<String> stringList, List<ProcedureDeclaration> dec, Statement stmt)
    {
        this.dec = dec;
        this.stmt = stmt;
        this.stringList = stringList;
    }

    public void compile(String fileName)
    {
        Emitter e = new Emitter(fileName);
        e.emit(".data");
        e.emit("newLine:\t.asciiz \"\\n\"");
        for(String s: stringList)
        {
            e.emit("var" + s + ":\t.word\t" + 0);
        }

        e.emit(".text");
        e.emit(".globl main");
        e.emit("main:");

        stmt.compile(e);

        e.emit("li $v0 10");
        e.emit("syscall");
    }

    /**
     * this executes the program
     * @param env the environmetn
     */
    public void exec(Environment env)
    {
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
