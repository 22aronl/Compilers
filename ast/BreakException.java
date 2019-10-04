package ast;


/**
 * The break exception
 * 
 * @author Aaron Lo
 * @version 10-3-19
 */
public class BreakException extends SkipException
{
    /**
     * Contructs break Exception
     */
    public BreakException()
    {
        super();
    }
    
    /**
     * Contructs break exception with a reason
     * @param reason the reason for the exception
     */
    public BreakException(String reason)
    {
        super(reason);
    }
}
