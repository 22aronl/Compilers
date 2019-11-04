package ast;

import environment.*;
import emitter.*;
/**
 * Abstract class Expression an Expression
 * 
 * @author Aaron Lo
 * @version 10-1-19
 */
public abstract class Expression
{
    /**
     * Evalutates the expression
     * @param env the environemtn
     * @return the evaluated number
     */
    public abstract int eval(Environment env);
    
    /**
     * The compile method
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        throw new RuntimeException("Implement me!!!");
    }
}
