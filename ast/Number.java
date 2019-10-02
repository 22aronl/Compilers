package ast;


/**
 * The Number
 * 
 * @author Aaron Lo
 * @version 10 -1 -19
 */
public class Number extends Expression
{
    private int value;
    /**
     * Contructs number
     * @param value the value
     */
    public Number(int value)
    {
        this.value = value;
    }
}
