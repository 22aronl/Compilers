package ast;

import environment.*;
/**
 * Variable
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
     * Evalutates the expression
     * @param env the environemtn
     * @return the evaluated thing
     */
    public int eval(Environment env)
    {
        return env.getVariable(name);
    }
}
