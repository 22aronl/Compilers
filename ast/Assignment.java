package ast;

import environment.*;
import emitter.*;
/**
 * This assigns the exp into the variable
 * 
 * @author Aaron Lo
 * @version 10-1-19
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;

    /**
     * Constructor for objects of class Assignment
     * @param var the variable
     * @param exp the expression
     */
    public Assignment(String var, Expression exp)
    {
        this.var = var;
        this.exp = exp;
    }
    
    /**
     * Exectues the code
     * @param env the environment
     */
    public void exec(Environment env)
    {
        env.setVariable(var, exp.eval(env));
    }
    
    /**
     * Compiles to code the mips file
     * Stores what was in $v0 into the the variable
     * @param e the emitter used for compiling
     */
    public void compile(Emitter e)
    {
        exp.compile(e);
        e.emit("sw $v0, var" + var);
    }
    
    /**
     * Gets the variable name
     * @return the variable name
     */
    public String getVariable()
    {
        return var;
    }
}
