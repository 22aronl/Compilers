package environment;

import java.util.*;

/**
 *Enviroment
 * 
 * @author Aaron Lo
 * @version 10-1-19
 */
public class Environment
{
    private Map<String, Integer> map;
    /**
     * Constructor for objects of class Environment
     */
    public Environment()
    {
        map = new HashMap<String, Integer>();
    }
    
    /**
     * Sets the variable
     * @param variable the string
     * @param value the value
     */
    public void setVariable(String variable, int value)
    {
        map.put(variable, value);
    }
    
    /**
     * Gets the variable
     * @param variable the variable
     * @return the int of variable
     */
    public int getVariable(String variable)
    {
        return map.get(variable);
    }
}
