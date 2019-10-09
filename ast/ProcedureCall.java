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
    private String name;
    public ProcedureCall(String name)
    {
        this.name = name;
    }

    public int eval(Environment env)
    {
        ProcedureDeclaration dec = env.getProcedure(name);
        try
        {
            dec.getStatement().exec(env);
        }
        catch(SkipException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
