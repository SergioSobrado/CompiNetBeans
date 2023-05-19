package compilador;
import java_cup.runtime.Symbol;
%%
%class LexicoCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char

%{
    private Symbol symbol(int type, Object value){
        return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
        return new Symbol(type, yyline, yycolumn);
    }
%}
espacios = [ ,\t,\r,\n]+
Variables = [a-z,A-Z]{1}[a-zA-Z0-9\\-]*
%%
"(" {return new Symbol(sym.ParentIzq, yychar, yyline, yytext());}
")" {return new Symbol(sym.ParentDer, yychar, yyline, yytext());}
"[" {return new Symbol(sym.CorchIzq, yychar, yyline, yytext());}
"]" {return new Symbol(sym.CorchDer, yychar, yyline, yytext());}
"While"|"until" {return new Symbol(sym.Mientras, yychar, yyline, yytext());}
{espacios} {/*Ignorar*/} 
{Variables} {return new Symbol(sym.Variables, yychar, yyline, yytext());}





