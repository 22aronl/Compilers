package ast;

import java.util.*;
import environment.*;
import emitter.*;

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
    
    @Override
    public void compile(Emitter e)
    {
        for(Statement s: statements)
        {
            s.compile(e);
        }
    }
    
    /**
     * Exectues the code
     * @param env the environment
     */
    public void exec(Environment env) throws SkipException
    {
        for(Statement s : statements)
        {
            if(s instanceof Break)
                throw new BreakException();
            else if(s instanceof Continue)
                throw new ContinueException();
            s.exec(env);
        }
    }
}
