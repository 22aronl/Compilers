package ast;

import environment.*;
import emitter.*;
/**
 * Writeln
 * 
 * @author Aaron Lo
 * @version 10-1-19
 */
public class Writeln extends Statement
{
    private Expression exp;
    /**
     * Constructor
     * @param exp the expression
     */
    public Writeln(Expression exp)
    {
        this.exp = exp;
    }
    
    /**
     * Gets the expression
     * @return the expression
     */
    public Expression getExpression()
    {
        return exp;
    }
    
    /**
     * Exectues the code
     * @param env the environment
     */
    public void exec(Environment env)
    {
        System.out.println(exp.eval(env));
    }
    
    /**
     * The writeln compile
     * @param e the emitter
     */
    public void compile(Emitter e)
    {
        exp.compile(e);
        e.emit("move $a0, $v0");
        e.emit("li $v0, 1");
        e.emit("syscall");
        e.emit("la $a0, newLine");
        e.emit("li $v0, 4");
        e.emit("syscall");
    }
}
