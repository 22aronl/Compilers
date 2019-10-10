package ast;

import environment.*;
import java.util.*;
/**
 * program
 * 
 * @author Aaron Lo
 * @version 10-7-19
 */
public class Program extends Statement
{
    private List<ProcedureDeclaration> dec;
    private Statement stmt;
    /**
     * This contructs the program
     * @param dec the list of declarations
     * @param stmt the main method
     */
    public Program(List<ProcedureDeclaration> dec, Statement stmt)
    {
        this.dec = dec;
        this.stmt = stmt;
    }

    /**
     * this executes the program
     * @param env the environmetn
     */
    public void exec(Environment env)
    {
        for(ProcedureDeclaration d : dec)
            d.exec(env);
            
        try
        {
            stmt.exec(env);
        }
        catch(BreakException e)
        {
            e.printStackTrace();
        }
        catch(SkipException e)
        {
            e.printStackTrace();
        }
    }
}
