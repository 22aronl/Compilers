package ast;


/**
 * Variable
 * 
 * @author Aaron Lo
 * @version 10-1-19
 */
public class Variable extends Expression
{
    private String name;
    /**
     * The variable name
     * @param name the name
     */
    public Variable(String name)
    {
        this.name = name;
    }
}
