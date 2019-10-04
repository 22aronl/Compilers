package ast;

import environment.*;
/**
 * While Loop
 * 
 * @author AAron Lo
 * @version 10-3-19
 */
public class While extends Statement
{
    private Condition condition;
    private Statement statement;

    /**
     * Constructor for objects of class While
     * @param condition the condition
     * @param statement the statement
     */
    public While(Condition condition, Statement statement)
    {
        this.condition = condition;
        this.statement = statement;
    }

    /**
     * The executable
     * @param env the enivroment
     */
    public void exec(Environment env)
    {
        while(condition.eval(env) == 1)
        {
            try
            {
                statement.exec(env);
            }
            catch(BreakException e)
            {
                break;
            }
            catch(SkipException e)
            {
                //
            }
        }
    }
}
