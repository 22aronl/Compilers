package ast;

import environment.*;
import emitter.*;
/**
 * Abstract class Statement - statment
 * 
 * @author Aaron Lo
 * @version 10- 1- 19
 */
public abstract class Statement
{
    /**
     * Exectues the code
     * @param env the environment
     */
    public abstract void exec(Environment env) throws SkipException;
    
    /**
     * The compile method for statement
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        throw new RuntimeException("Implement me!!!");
    }
    
}
