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
     * Gets the expressison of the assingment
     * @return the xpression assingment holds
     */
    public Expression getExpression()
    {
        return exp;
    }

    /**
     * Sets the expression of assingment
     * @param s the expression
     */
    public void setExpression(Expression s)
    {
        exp= s;
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
        if(e.isLocalVariable(var))
        {
            int k = e.getOffset(var);
            e.emit("addu $t0, $sp, "+k);
            e.emit("sw $v0, ($t0)");
        }
        else
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
