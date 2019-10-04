package ast;


/**
 * The skip exception
 * 
 * @author Aaron Lo
 * @version 10-3-19
 */
public class SkipException extends Exception
{
    /**
     * The skip exception
     */
    public SkipException()
    {
        super();
    }
    
    /**
     * The skip exception 
     * @param reason the reason
     */
    public SkipException(String reason)
    {
        super(reason);
    }
}
