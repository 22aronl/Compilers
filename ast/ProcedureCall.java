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
        Environment child = new Environment(env);
        try
        {
            if(list != null)
            {
                ArrayList<Variable> varList = dec.getList();
                for(int i = 0; i < varList.size(); i++)
                {
                    child.declareVariable(varList.get(i).getName(), list.get(i).eval(child));
                }
            }
            dec.getStatement().exec(child);
        }
        catch(SkipException e)
        {
            e.printStackTrace();
        }
        return child.getVariable(name);
    }
}
