package emitter;

import ast.*;
import java.io.*;
import java.util.*;

/**
 * The class emitter that prints statements to the given file name
 * @author Aaro NLo
 * @version 11-4-19
 */
public class Emitter
{
    private PrintWriter out;
    private ProcedureDeclaration current;
    private int nextIf;
    private int nextWhile;
    private int excessStackHeight = 0;

    /**
     * creates an emitter for writing to a new file with given name
     * @param outputFileName the output file name
     */
    public Emitter(String outputFileName)
    {
        try
        {
            out = new PrintWriter(new FileWriter(outputFileName), true);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the next lavel for if ids
     * @return the label
     */
    public int nextLabelID()
    {
        return ++nextIf;
    }

    /**
     * Gets the next label for while ids
     * @return the next label
     */
    public int nextWhileLabelID()
    {
        return ++nextWhile;
    }

    /**
     * prints one line of code to file (with non-labels indented)
     * @param code the string to be emitteed
     */
    public void emit(String code)
    {
        if (!code.endsWith(":"))
            code = "\t" + code;
        out.println(code);
    }

    /**
     * Pushs the stack
     * @param reg the emitted string
     */
    public void emitPush(String reg)
    {
        emit("subu $sp, $sp, 4");
        emit("sw " + reg + ", ($sp)");
        excessStackHeight++;
    }

    /**
     * Pops the stack
     * @param reg the emiited reg
     */
    public void emitPop(String reg)
    {
        emit("lw " + reg+ ", ($sp)");
        emit("addu $sp, $sp, 4");
        excessStackHeight--;
    }

    public boolean isLocalVariable(String varName)
    {
        if(current == null)
            return false;
        for(Variable v : current.getList())
            if(v.getName().equals(varName))
                return true;
        if(varName.equals(current.getName()))
            return true;
        for(String s : current.getVariableList())
            if(s.equals(varName))
                return true;
        return false;
    }

    public int getStackHeight()
    {
        return excessStackHeight;
    }

    public int getOffset(String localVarName)
    {
        ArrayList<Variable> list = current.getList();
        for(int i = list.size() -1; i >=0; i--)
        {
            if(list.get(i).getName().equals(localVarName))
                return (list.size() - i -1) * 4 + excessStackHeight*4;
        }
        if(localVarName.equals(current.getName()))
            return (excessStackHeight-1) * 4;
        ArrayList<String> l = current.getVariableList();
        for(int i = 0; i < l.size(); i++)
            if(l.get(i).equals(localVarName))
                return (i)*4 + (excessStackHeight - l.size() + i -1)*4;

        return -1;
    }

    public void setProcedureContext(ProcedureDeclaration proc)
    {
        current = proc;
        excessStackHeight=0;
    }

    public void clearProcedureContext()
    {
        current = null;
    }

    /**
     * closes the file.  should be called after all calls to emit.
     * 
     */
    public void close()
    {
        out.close();
    }
}