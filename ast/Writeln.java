package ast;

import environment.*;
/**
 * Writeln
 * 
 * @author Aaron Lo
 * @version 10-1-19
 */
public class Writeln extends Statement
{
    private Expression exp;
    /**
     * Constructor
     * @param exp the expression
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }
    
    /**
     * Gets the expression
     * @return the expression
     */
    public Expression getExpression()
    {
        return exp;
    }
    
    /**
     * Exectues the code
     * @param env the environment
     */
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }
}
