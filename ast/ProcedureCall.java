package ast;

import environment.*;
import java.util.*;
/**
 * ProcedureCall
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ProcedureCall extends Expression
{
    private String name;
    private ArrayList<Expression> list;
    public ProcedureCall(String name, ArrayList<Expression> list)
    {
        this.list = list;
        this.name = name;
    }

    public int eval(Environment env)
    {
        ProcedureDeclaration dec = env.getProcedure(name);
        try
        {
            Environment child = new Environment(env);
            ArrayList<Variable> varList = dec.getList();
            for(int i = 0; i < varList.size(); i++)
            {
                Assignment a = new Assignment(varList.get(i).getName(), list.get(i));
                a.exec(child);
            }
            dec.getStatement().exec(child);
        }
        catch(SkipException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}
