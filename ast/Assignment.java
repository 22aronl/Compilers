package ast;


/**
 * Assingment
 * 
 * @author AAron Lo
 * @version 10-1-19
 */
public class Assignment extends Statement
{
    private String var;
    private Expression exp;

    /**
     * Constructor for objects of class Assignment
     * @param var the variable
     * @param exp the expression
     */
    public Assignment(String var, Expression exp)
    {
        this.var = var;
        this.exp = exp;
    }
}
