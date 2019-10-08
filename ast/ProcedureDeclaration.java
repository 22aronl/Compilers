package ast;

import environment.*;
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
    /**
     * Constructor for objects of class ProcedureDeclaration
     */
    public ProcedureDeclaration(String name, Statement stmt)
    {
        this.name = name;
        statement = stmt;
    }
    
    public void exec(Environment env)
    {
        
    }
}
