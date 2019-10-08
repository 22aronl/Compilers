package ast;

import environment.*;
import java.util.*;
/**
 * Write a description of class Program here.
 * 
 * @author Aaron Lo
 * @version 10-7-19
 */
public class Program extends Statement
{
    private List<ProcedureDeclaration> dec;
    private Statement stmt;
    public Program(List<ProcedureDeclaration> dec, Statement stmt)
    {
        this.dec = dec;
        this.stmt = stmt;
    }
    
    public void exec(Environment env)
    {
    }
}
