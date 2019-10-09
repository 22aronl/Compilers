package ast;

import environment.*;
/**
 * ProcedureCall
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
            Environment child = new Environment(env);
            dec.getStatement().exec(child);
        }
        catch(SkipException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
