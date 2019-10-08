package ast;

import environment.*;
/**
 * Write a description of class ProcedureCall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ProcedureCall extends Expression
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class ProcedureCall
     */
    public ProcedureCall()
    {
        // initialise instance variables
        x = 0;
    }
    
    public int eval(Environment env)
    {
        return 1;
    }
}
