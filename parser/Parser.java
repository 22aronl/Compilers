package parser;

import java.util.HashMap;
import scanner.*;

/**
 * This parses the file and returns what is evaluated
 * 
 * @author aaron Lo 
 * @version 10-1-19
 */
public class Parser
{
    private HashMap<String, Integer> map;
    private Scanner sc;
    private String currentToken;
    /**
     * This constructs the parser with the scanner
     * @param sc the scanner
     */
    public Parser(Scanner sc)
    {
        this.sc = sc;
        map = new HashMap<String, Integer>();
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
    private int parseNumber()
    {
        String temp = currentToken;
        eat(currentToken);
        return Integer.parseInt(temp);
    }

    /**
     * This will scan the factor
     * factor -> (factor) | -factor | num
     * @precondition the currentToken is  number
     * @postcondition the number token has been eaten
     * @return the factor
     */
    private int parseFactor()
    {
        if(currentToken.equals("-"))
        {
            eat(currentToken);
            return - parseFactor();
        }
        else if(currentToken.equals("("))
        {
            eat(currentToken);
            int a = parseExpression();
            eat(")");
            return a;
        }
        else if(map.containsKey(currentToken))
        {
            String a = currentToken;
            eat(currentToken);
            return map.get(a);
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
    private int parseTerm()
    {
        int temp = parseFactor();
        while(true)
        {
            if(currentToken.equals("*"))
            {
                eat(currentToken);
                temp *= parseFactor();
            }
            else if(currentToken.equals("/"))
            {
                eat(currentToken);
                temp /= parseFactor();
            }
            else if(currentToken.equals("mod"))
            {
                eat(currentToken);
                temp %= parseFactor();
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
    private int parseExpression()
    {
        int temp = parseTerm();
        while(true)
        {
            if(currentToken.equals("+"))
            {
                eat(currentToken);
                temp += parseTerm();
            }
            else if(currentToken.equals("-"))
            {
                eat(currentToken);
                temp -= parseTerm();
            }
            else
                break;
        }
        return temp;
    }
    
    /**
     * This is a helper to parseStatement that parses things after begin
     */
    private void parseWhileBegin()
    {
        if(currentToken.equals("END"))
        {
            eat("END");
            eat(";");
        }
        else
        {
            parseStatement();
            parseWhileBegin();
        }
    }

    /**
     * This parses statements
     * stmt -> writel(expr) | Begin stmts End
     * stmts -> stmts stmt | e
     */
    public void parseStatement()
    {
        if(currentToken.equals("BEGIN"))
        {
            eat("BEGIN");
            parseWhileBegin();
        }
        else if(currentToken.equals("WRITELN"))
        {
            eat("WRITELN");
            eat("(");
            System.out.println(parseExpression());
            eat(")");
            eat(";");
        }
        else
        {
            String temp = currentToken;
            eat(currentToken);
            eat(":=");
            map.put(temp, parseExpression());
            eat(";");
        }
    }
}
