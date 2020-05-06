     
%%
   
%package compiler
%class Lexer
%public
%line
%column
%cup

%%

[ \t\n\r]		{ }
^#.*			{ }



","				{ return getSymbolFactory().newSymbol ("SEP", Sym.SEP); }
";"				{ return getSymbolFactory().newSymbol ("TERM", Sym.TERM); }

[0-9]+			{ return getSymbolFactory().newSymbol ("NUMBER", Sym.NUMBER, Integer.parseInt(yytext())); }

[a-zA-Z][a-zA-Z_0-9\']*	{ return getSymbolFactory().newSymbol ("ID", Sym.ID, yytext()); }

[^]				{ throw new Error("Illegal character <"+yytext()+">"); }
