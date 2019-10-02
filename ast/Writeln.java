package ast;


/**
 * Writeln
 * 
 * @author Aaron Lo
 * @version 10-1-19
 */
public class Writeln extends Statement
{
    private Expression exp;
    /**
     * Constructor
     * @param exp the expression
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }
}
