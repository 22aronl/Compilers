package ast;

import environment.*;
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
    /**
     * Constructor for objects of class ProcedureDeclaration
     * @param name the name of the declaration
     * @param stmt the stmt of the declaration
     * @param list the list of the variables
     */
    public ProcedureDeclaration(String name, Statement stmt, ArrayList<Variable> list)
    {
        this.name = name;
        statement = stmt;
        this.list = list;
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
