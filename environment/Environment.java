package environment;

import java.util.*;
import ast.*;
/**
 *Enviroment
 * 
 * @author Aaron Lo
 * @version 10-1-19
 */
public class Environment
{
    private Map<String, Integer> varMap;
    private Map<String, ProcedureDeclaration> procedureMap;
    /**
     * Constructor for objects of class Environment
     */
    public Environment()
    {
        varMap = new HashMap<String, Integer>();
        procedureMap = new HashMap<String, ProcedureDeclaration>();
    }
    
    /**
     * Sets the variable
     * @param variable the string
     * @param value the value
     */
    public void setVariable(String variable, int value)
    {
        varMap.put(variable, value);
    }
    
    /**
     * Gets the variable
     * @param variable the variable
     * @return the int of variable
     */
    public int getVariable(String variable)
    {
        return varMap.get(variable);
    }
    
    public void setProcedure(String name, ProcedureDeclaration dec)
    {
        procedureMap.put(name, dec);
    }
    
    public ProcedureDeclaration getProcedure(String name)
    {
        return procedureMap.get(name);
    }
}
