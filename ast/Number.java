package ast;

import environment.*;
/**
 * The Number
 * 
 * @author Aaron Lo
 * @version 10 -1 -19
 */
public class Number extends Expression
{
    private int value;
    /**
     * Contructs number
     * @param value the value
     */
    public Number(int value)
    {
        this.value = value;
    }
    
    /**
     * Evalutates the expression
     * @param env the environemtn
     * @return the evaluated thing
     */
    public int eval(Environment env)
    {
        return value;
    }
}
