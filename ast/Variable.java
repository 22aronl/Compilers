package ast;

import environment.*;
import emitter.*;
/**
 * Variable name
 * 
 * @author Aaron Lo
 * @version 10-1-19
 */
public class Variable extends Expression
{
    private String name;
    /**
     * The variable name
     * @param name the name
     */
    public Variable(String name)
    {
        this.name = name;
    }

    /**
     * This gets the name
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * The compile for Variable
     * Stores the variable as $v0
     * @param e emitter 
     */
    public void compile(Emitter e)
    {
        if(e.isLocalVariable(name))
        {
            int k = e.getOffset(name);
            e.emit("addu $t0, $sp, " + k);
            e.emit("lw $v0, ($t0)");
        }
        else
        {
            e.emit("la $t0, var" + name);
            e.emit("lw $v0, ($t0)");
        }
    }

    /**
     * Evalutates the expression
     * @param env the environemtn
     * @return the evaluated thing
     */
    public int eval(Environment env)
    {
        return env.getVariable(name);
    }
}
