package ast;

import environment.*;
import emitter.*;
import java.util.*;
/**
 * The procedure
 * 
 * @author Aaron Lo
 * @version 1-7-19
 */
public class ProcedureDeclaration extends Statement
{
    private Statement statement;
    private String name;
    private ArrayList<Variable> list;
    private ArrayList<String> stringList;
    /**
     * Constructor for objects of class ProcedureDeclaration
     * @param name the name of the declaration
     * @param stmt the stmt of the declaration
     * @param list the list of the variables
     * @param stringList the string list of local variables
     */
    public ProcedureDeclaration(String name, Statement stmt, ArrayList<Variable> list, 
            ArrayList<String> stringList)
    {
        this.name = name;
        statement = stmt;
        this.list = list;
        this.stringList = stringList;
    }

    /**
     * Gest the name of the procedure
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the variable list of the procedure
     * @return the arraylist of these variables
     */
    public ArrayList<String> getVariableList()
    {
        return stringList;
    }

    /**
     * This executes the procedure
     * @param env the environemtn
     */
    public void exec(Environment env)
    {
        env.setProcedure(name, this);
    }

    /**
     * Compiles the proceudre Declaration
     * @param e the emitter
     */
    public void compile(Emitter e)
    {

        e.setProcedureContext(this);
        e.emit("proc" + name  + ":");
        e.emit("li $s0, 0");
        e.emitPush("$s0");
        e.emitPush("$ra");
        for(String s : stringList)
        {
            e.emit("li $v0, 0");
            e.emitPush("$v0");
        }

        statement.compile(e);

        e.emit("addu $sp, $sp, "+(e.getStackHeight()-2)*4);
        e.emitPop("$ra");
        e.emitPop("$s4");
        e.emit("jr $ra");
        e.clearProcedureContext();
    }

    /**
     * this gets the statement
     * @return the statement
     */
    public Statement getStatement()
    {
        return statement;
    }

    /**
     * This gets the list of variables
     * @return the list of variables
     */
    public ArrayList<Variable> getList()
    {
        return list;
    }
}
