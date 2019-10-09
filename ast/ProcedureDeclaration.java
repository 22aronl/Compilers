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
     */
    public ProcedureDeclaration(String name, Statement stmt, ArrayList<Variable> list)
    {
        this.name = name;
        statement = stmt;
        this.list = list;
    }
    
    public void exec(Environment env)
    {
        env.setProcedure(name, this);
    }
    
    public Statement getStatement()
    {
        return statement;
    }
    
    public ArrayList<Variable> getList()
    {
        return list;
    }
}
