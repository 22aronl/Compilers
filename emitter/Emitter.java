package emitter;

import java.io.*;

/**
 * The class emitter that prints statements to the given file name
 * @author Aaro NLo
 * @version 11-4-19
 */
public class Emitter
{
    private PrintWriter out;
    private int nextIf;
    private int nextWhile;

    
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
        emit("sw $v0, ($sp)");
    }

    /**
     * Pops the stack
     * @param reg the emiited reg
     */
    public void emitPop(String reg)
    {
        emit("lw $t0, ($sp)");
        emit("addu $sp, $sp, 4");
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