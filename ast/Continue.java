package ast;

import environment.*;
/**
 * Continue
 * 
 * @author Aaron Lo
 * @version 10-3-19
 */
public class Continue extends Statement
{
    /**
     * Executes continue
     * @param env the environment
     * @throws SkipException the skip exception
     */
    public void exec(Environment env) throws SkipException
    {
        throw new ContinueException();
    }
}
