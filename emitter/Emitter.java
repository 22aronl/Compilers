package emitter;

import java.io.*;

public class Emitter
{
    private PrintWriter out;
    private int nextIf;
    private int nextWhile;

    //creates an emitter for writing to a new file with given name
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
    
    public int nextLabelID()
    {
        return ++nextIf;
    }
    
    public int nextWhileLabelID()
    {
        return ++nextWhile;
    }

    //prints one line of code to file (with non-labels indented)
    public void emit(String code)
    {
        if (!code.endsWith(":"))
            code = "\t" + code;
        out.println(code);
    }

    public void emitPush(String reg)
    {
        emit("subu $sp, $sp, 4");
        emit("sw $v0, ($sp)");
    }

    public void emitPop(String reg)
    {
        emit("lw $t0, ($sp)");
        emit("addu $sp, $sp, 4");
    }

    //closes the file.  should be called after all calls to emit.
    public void close()
    {
        out.close();
    }
}