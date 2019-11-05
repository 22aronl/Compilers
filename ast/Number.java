package ast;

import environment.*;
import emitter.*;
/**
 * An expression that holds a number
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
    
    /**
     * Sets the compile
     * Sets $v0 as the value
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        e.emit("li $v0, " + value);
    }
}
