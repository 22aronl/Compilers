package ast;

import environment.*;
import emitter.*;
/**
 * The condition
 * 
 * @author Aaron Lo
 * @version 10-3-19
 */
public class Condition extends Expression
{
    private String op;
    private Expression exp1;
    private Expression exp2;

    /**
     * Constructor for objects of class Condition
     * @param op the operator
     * @param exp1 the expression 1 on the left
     * @param exp2 the expression 2 on the right
     */
    public Condition(String op, Expression exp1, Expression exp2)
    {
        this.op = op;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    public void compile(Emitter e, String targetLabel)
    {
        exp1.compile(e);
        e.emit("move $t0, $v0");
        exp2.compile(e);
        e.emit("move $t1, $v0");
        if(op.equals("<>"))
            e.emit("beq $t0, $t1, " + targetLabel);
        else if(op.equals("="))
            e.emit("bne $t0, $t1, " + targetLabel);
        else if(op.equals("<"))
            e.emit("bge $t0, $t1, " + targetLabel);
        else if(op.equals(">"))
            e.emit("ble $t0, $t1, " + targetLabel);
        else if(op.equals("<="))
            e.emit("bgt $t0, $t1, " + targetLabel);
        else
            e.emit("blt $t0, $t1, " + targetLabel);
    }

    /**
     * Exectues the code
     * @param env the environment
     * @return 1 if true; -1 for false
     */
    public int eval(Environment env)
    {
        if(op.equals("="))
            return exp1.eval(env) == exp2.eval(env) ? 1 : -1;
        else if(op.equals("<>"))
            return exp1.eval(env) != exp2.eval(env) ? 1 : -1;
        else if(op.equals("<"))
            return exp1.eval(env) < exp2.eval(env) ? 1 : -1;
        else if(op.equals(">"))
            return exp1.eval(env) > exp2.eval(env) ? 1 : -1;
        else if(op.equals("<="))
            return exp1.eval(env) <= exp2.eval(env) ? 1 : -1;  
        else
            return exp1.eval(env) >= exp2.eval(env) ? 1 : -1;
    }
}
