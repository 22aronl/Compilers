package ast;

import environment.*;
import java.util.*;
/**
 * ProcedureCall
 * 
 * @author Aaron Lo
 * @version 10-9-19
 */
public class ProcedureCall extends Expression
{
    private String name;
    private ArrayList<Expression> list;
    
    /**
     * Constructs procedure call
     * @param name the name of the procedure
     * @param list the arraylist of the parameters
     */
    public ProcedureCall(String name, ArrayList<Expression> list)
    {
        this.list = list;
        this.name = name;
    }

    /**
     * This evalutates the procedure
     * @param env the environment in which it operates
     * @return if the thing has a return type
     */
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
        catch(BreakException e)
        {
            e.printStackTrace();
        }
        catch(SkipException e)
        {
            e.printStackTrace();
        }
        return child.getVariable(name);
    }
}
