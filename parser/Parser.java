package parser;

import java.util.HashMap;
import java.util.ArrayList;
import scanner.*;
import ast.*;

/**
 * This parses the file and returns what is evaluated
 * 
 * @author aaron Lo 
 * @version 10-1-19
 */
public class Parser
{
    private HashMap<String, Expression> map;
    private Scanner sc;
    private String currentToken;
    /**
     * This constructs the parser with the scanner
     * @param sc the scanner
     */
    public Parser(Scanner sc)
    {
        this.sc = sc;
        map = new HashMap<String, Expression>();
        try
        {
            currentToken = this.sc.nextToken();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * This eats the next token of scanner
     * @param s checks if it is equals
     * @throws IllegalArgumentException if teh current token != s
     */
    private void eat(String s)
    {
        if(!s.equals(currentToken))
            throw new IllegalArgumentException("Does Not Match the Given Token in PARSER" + 
                        s + " " + currentToken);

        try
        {
            currentToken = sc.nextToken();
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }

    /**
     * This will scan the number - > rules of num
     * @precondition the currentToken is  number
     * @postcondition the number token has been eaten
     * @return the value of the parsed Integer
     */
    private ast.Number parseNumber()
    {
        String temp = currentToken;
        eat(currentToken);
        return new ast.Number(Integer.parseInt(temp));
    }
    
    public boolean isNumber()
    {
        try
        {
            Integer.parseInt(currentToken);
            return false;
        }
        catch(Exception e)
        {
            return true;
        }
    }

    /**
     * This will scan the factor
     * factor -> (factor) | -factor | num
     * @precondition the currentToken is  number
     * @postcondition the number token has been eaten
     * @return the factor
     */
    private Expression parseFactor()
    {
        if(currentToken.equals("-"))
        {
            eat(currentToken);
            return new BinOp("*", new ast.Number(-1), parseFactor());
        }
        else if(currentToken.equals("("))
        {
            eat(currentToken);
            Expression a = parseExpression();
            eat(")");
            return a;
        }
        else if(isNumber())
        {
            String a = currentToken;
            eat(currentToken);
            return new Variable(a);
        }
        else
        {
            return parseNumber();
        }
    }

    /**
     * This scans the term
     * term -> factor whileterm
     * whileterm -> * factor whileterm | / factor whileterm | e
     * factor -> (term) | -factor | num
     * @return the final term
     */
    private Expression parseTerm()
    {
        Expression temp = parseFactor();
        while(true)
        {
            if(currentToken.equals("*"))
            {
                eat(currentToken);
                temp =  new BinOp("*", temp, parseFactor());
            }
            else if(currentToken.equals("/"))
            {
                eat(currentToken);
                temp =  new BinOp("/", temp, parseFactor());
            }
            else if(currentToken.equals("mod"))
            {
                eat(currentToken);
                temp =  new BinOp("%", temp, parseFactor());
            }
            else
                break;
        }
        return temp;
    }

    /**
     * This parses the expressions
     *  expr -> expr + term | expr - term | term
     */
    private Expression parseExpression()
    {
        Expression temp = parseTerm();
        while(true)
        {
            if(currentToken.equals("+"))
            {
                eat(currentToken);
                temp = new BinOp("+", temp, parseTerm());
            }
            else if(currentToken.equals("-"))
            {
                eat(currentToken);
                temp = new BinOp("-", temp, parseTerm());
            }
            else
                break;
        }
        return temp;
    }
    
    /**
     * This is a helper to parseStatement that parses things after begin
     * @param ar the arraylist
     */
    private void parseWhileBegin(ArrayList<Statement> ar)
    {
        if(currentToken.equals("END"))
        {
            eat("END");
            eat(";");
        }
        else
        {
            ar.add(parseStatement());
            parseWhileBegin(ar);
        }
    }

    /**
     * This parses statements
     * stmt -> writel(expr) | Begin stmts End
     * stmts -> stmts stmt | e
     */
    public Statement parseStatement()
    {
        if(currentToken.equals("BEGIN"))
        {
            eat("BEGIN");
            ArrayList<Statement> ar = new ArrayList<Statement>();
            parseWhileBegin(ar);
            return new Block(ar);
        }
        else if(currentToken.equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            Expression e = parseExpression();
            eat(")");
            eat(";");
            return new Writeln(e);
        }
        else
        {
            String temp = currentToken;
            eat(currentToken);
            eat(":=");
            Expression e = parseExpression();
            eat(";");
            return new Assignment(temp, e);
        }
    }
}
