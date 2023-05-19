package compilador;
import static compilador.Token.*;
%%
%class AnalizadorLexico
%type Token

%{
public String Lexema;
%}
espacios = [ ,\t,\r,\n]+
Variables = [a-z,A-Z]{1}[a-zA-Z0-9\\-]*
%%
"(" {return ParentIzq;}
")" {return ParentDer;}
"[" {return CorchIzq;}
"]" {return CorchDer;}
"While"|"until" {Lexema= yytext(); return Mientras;}
{espacios} {/*Ignorar*/} 
{Variables} {Lexema = yytext(); return Variables;}




