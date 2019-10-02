package ast;

import java.util.*;

/**
 * Block
 * 
 * @author Aaron Lo
 * @version 10-1-19
 */
public class Block extends Statement
{
    private List<Statement> statements;
    /**
     * Constructor for objects of class Block
     * @param statements the statements
     */
    public Block(ArrayList<Statement> statements)
    {
        this.statements = statements;
    }
}
