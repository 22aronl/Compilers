package ast;

import environment.*;
import emitter.*;
/**
 * The if statement
 * If the condition is true, the statement is executed
 * 
 * @author Aaron Lo
 * @version 10-3-19
 */
public class If extends Statement
{
    private Condition condition;
    private Statement exp1;
    private Statement exp2;
    /**
     * The if statement
     * @param con the condition
     * @param exp1 the statement for the if
     */
    public If(Condition con, Statement exp1)
    {
        this.condition = con;
        this.exp1 = exp1;
    }

    /**
     * The if statement with else
     * @param con the condition
     * @param exp1 the statement for the if
     * @param exp2 called if con is false
     */
    public If(Condition con, Statement exp1, Statement exp2)
    {
        this.condition = con;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    /**
     * The if compiler
     * if condition is valid this will run the exp1
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        String cur = "endif"+ e.nextLabelID();
        condition.compile(e, cur);
        exp1.compile(e);
        e.emit(cur + ":");
    }

    /**
     * Runs the if statemnt
     * @param env the environment
     * @throws SkipException the skip exception
     */
    public void exec(Environment env) throws SkipException
    {
        try
        {
            if(condition.eval(env) == 1)
                exp1.exec(env);
            else if(exp2 != null)
                exp2.exec(env);
        }
        catch(BreakException e)
        {
            throw new BreakException();
        }
        catch(ContinueException e)
        {
            throw new ContinueException();
        }
    }
}
