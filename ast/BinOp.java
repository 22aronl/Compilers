package ast;

import environment.*;
import emitter.*;
/**
 * Bin Op (Two Expression evaluated from +, - ,*, and /)
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
    
    /**
     * Compiles the binOp into mips file
     * Stores value into $v0
     * @param e the emitter used for mips
     */
    public void compile(Emitter e)
    {
        exp1.compile(e);
        e.emitPush("");
        exp2.compile(e);
        e.emitPop("");
        if(op.equals("+"))
            e.emit("addu $v0, $v0, $t0");
        else if(op.equals("-"))
            e.emit("subu $v0, $v0, $t0");
        else
        {
            if(op.equals("*"))
                e.emit("mult $v0, $t0");
            else
                e.emit("div $t0, $v0");
            e.emit("mflo $v0");
        }
    }
    
    /**
     * Evalutates the expression
     * @param env the environemtn
     * @return the value of the expression
     */
    public int eval(Environment env)
    {
        if(op.equals("*"))
            return exp1.eval(env) * exp2.eval(env);
        else if(op.equals("%"))
            return exp1.eval(env) % exp2.eval(env);
        else if(op.equals("/"))
            return exp1.eval(env) / exp2.eval(env);
        else if(op.equals("+"))
            return exp1.eval(env) + exp2.eval(env);
        else
            return exp1.eval(env) - exp2.eval(env);
    }
}
