package parser;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
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
            throw new IllegalArgumentException("Does Not Match the Given Token in PARSER :" + 
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

    /**
     * Checks if it is number
     * @return true if it is a number; otherwise false
     */
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
     * Parses the condition
     * @return the condition
     */
    private Condition parseCondition()
    {
        Expression exp1 = parseExpression();
        String relop =  currentToken;
        eat(currentToken);
        return new Condition(relop, exp1, parseExpression());
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
     * Parses the assingment
     * @return the assignment
     */
    public Assignment parseAssignment()
    {
        String temp = currentToken;
        eat(currentToken);
        eat(":=");
        Expression e = parseExpression();
        return new Assignment(temp, e);
    }

    public Program parseProgram()
    {
        List<ProcedureDeclaration> list = new ArrayList<ProcedureDeclaration>();
        while(currentToken.equals("PROCEDURE"))
        {
            eat("PROCEDURE");
            String name = currentToken + "()";
            eat(currentToken);
            eat("()");
            eat(";");
            list.add(new ProcedureDeclaration(name, parseStatement()));
        }
        eat(".");
        Statement stmt = parseStatement();
        return new Program(list, stmt);
    }

    /**
     * This parses statements
     * stmt -> writel(expr) | Begin stmts End
     * stmts -> stmts stmt | e
     * @return the statement
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
        else if(currentToken.equals("IF"))
        {
            eat("IF");
            Condition condition = parseCondition();
            eat("THEN");
            Statement stmt = parseStatement();
            if(currentToken.equals("ELSE"))
            {
                eat("ELSE");
                Statement stmt2 = parseStatement();
                return new If(condition, stmt, stmt2);
            }
            else
                return new If(condition, stmt);
        }
        else if(currentToken.equals("WHILE"))
        {
            eat("WHILE");
            Condition condition = parseCondition();
            eat("DO");
            return new While(condition, parseStatement());
        }
        else if(currentToken.equals("CONTINUE"))
        {
            eat("CONTINUE");
            eat(";");
            return new Continue();
        }
        else if(currentToken.equals("BREAK"))
        {
            eat("BREAK");
            eat(";");
            return new Break();
        }
        else if(currentToken.equals("FOR"))
        {
            eat("FOR");
            Assignment a = (Assignment)parseAssignment();
            eat("DO");
            ast.Expression n = parseExpression();
            eat("THEN");
            return new For(a, n, parseStatement());
        }
        else
        {
            Assignment a = parseAssignment();
            eat(";");
            return a;
        }
    }
}
