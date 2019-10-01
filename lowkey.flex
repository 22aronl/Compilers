
/**
* This file defines a simple lexer for the compilers course 2014-2015
*
* @author  Aaron Lo
* @version 5/12/2017
* 
*/
import java.io.*;

%%
/* lexical functions */
/* specify that the class will be called Scanner and the function to get the next
 * token is called nextToken.  
 */
%class ScannerC
%unicode
%line
%column
%public
%function nextToken
/*  return String objects - the actual lexemes */
/*  returns the String "END: at end of file */
%type String
%eofval{
return "END";
%eofval}
LineTerminator = \r|\n|\r\n
WhiteSpace = {LineTerminator} | [ \t\f]

/**
 * Pattern definitions
 */
 
Number = \d+ | ['('\d+')'];
Letter = [:letter:]+ | ['('[:letter:]')']; 

 
%%
/**
 * lexical rules
 */
{Number} {}
{Letter} {return yytext() + ", Line" + yyline;}
{WhiteSpace}		{}
.			{ /* do nothing */ }
