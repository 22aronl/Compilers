package ast;


/**
 * The continue exception
 * 
 * @author Aaonr Lo
 * @version 10-3-19
 */
public class ContinueException extends SkipException
{
    /**
     * The continue exception
     */
    public ContinueException()
    {
        super();
    }
    
    /**
     * The continue exception
     * @param reason the reason
     */
    public ContinueException(String reason)
    {
        super(reason);
    }
}
