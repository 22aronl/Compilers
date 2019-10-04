package ast;

import environment.*;
/**
 * The for class
 * 
 * @author Aaron Lo
 * @version 10-3-19
 */
public class For extends Statement
{
    private Assignment condition;
    private Number exp;
    private Statement statement;
    /**
     * The For constuctor
     * @param condition the Assignment
     * @param exp the number to go up to
     * @param statement the statement to execute
     */
    public For(Assignment condition, Number exp, Statement statement)
    {
        this.condition = condition;
        this.exp = exp;
        this.statement = statement;
    }

    /**
     * Executes the for loop
     * @param env the enviroment in which it runs
     */
    public void exec(Environment env)
    {
        condition.exec(env);
        for(int i = env.getVariable(condition.getVariable()); i < exp.eval(env); i++)
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
