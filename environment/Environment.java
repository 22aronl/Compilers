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
    private Environment parent;
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
    
    public Environment(Environment p)
    {
        varMap = new HashMap<String, Integer>();
        procedureMap = new HashMap<String, ProcedureDeclaration>();
        parent = p;
    }
    
    
    /**
     * Sets the variable
     * @param variable the string
     * @param value the value
     */
    public void setVariable(String variable, int value)
    {
        if(varMap.containsKey(variable) || parent == null)
            varMap.put(variable, value);
        else
            parent.setVariable(variable, value);
    }
    
    public void declareVariable(String variable, int value)
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
        if(varMap.containsKey(variable))
            return varMap.get(variable);
        
        if(parent == null)
            return 0;
            
        return parent.getVariable(variable);
    }
    
    public void setProcedure(String name, ProcedureDeclaration dec)
    {
        if(parent == null)
            procedureMap.put(name, dec);
        else
            parent.setProcedure(name, dec);
    }
    
    public ProcedureDeclaration getProcedure(String name)
    {
        if(parent == null)
            return procedureMap.get(name);
        else
            return parent.getProcedure(name);
    }
}
