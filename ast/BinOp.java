package ast;


/**
 * Bin Op
 * 
 * @author Aaron Lo
 * @version 10-1-19
 */
public class BinOp extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;

    /**
     * Constructor for objects of class BinOp
     * @param op the op
     * @param exp1 first expression
     * @param exp2 second expression
     */
    public BinOp(String op, Expression exp1, Expression exp2)
    {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
}
