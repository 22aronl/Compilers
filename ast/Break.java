package ast;

import environment.*;
/**
 * The break
 * 
 * @author Aaron Lo
 * @version 10-3-19
 */
public class Break extends Statement
{
    /**
     * Constructor for objects of class Break
     */
    public Break()
    {
    }
    
    /**
     * Executes the block
     * @param env the environment
     * @throws BreakException breaks out of the place
     */
    public void exec(Environment env) throws BreakException
    {
        throw new BreakException();
    }
}
