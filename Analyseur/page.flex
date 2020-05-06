     
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

"select"		{ return getSymbolFactory().newSymbol("SELECT", Sym.SELECT); }
"from"			{ return getSymbolFactory().newSymbol("FROM", Sym.FROM); }
"where"			{ return getSymbolFactory().newSymbol("WHERE", Sym.WHERE); }
"create"		{ return getSymbolFactory().newSymbol("CREATE", Sym.CREATE); }
"table"			{ return getSymbolFactory().newSymbol("TABLE", Sym.TABLE); }
"primary"		{ return getSymbolFactory().newSymbol("PRIMARY", Sym.PRIMARY); }
"key"			{ return getSymbolFactory().newSymbol("KEY", Sym.KEY); }
"delete"		{ return getSymbolFactory().newSymbol("DELETE", Sym.DELETE); }
"update"		{ return getSymbolFactory().newSymbol("UPDATE", Sym.UPDATE); }
"insert"		{ return getSymbolFactory().newSymbol("INSERT", Sym.INSERT); }
"into"			{ return getSymbolFactory().newSymbol("INTO", Sym.INTO); }
"*"				{ return getSymbolFactory().newSymbol("WILDCARD", Sym.WILDCARD); }
"int"			{ return getSymbolFactory().newSymbol("INT", Sym.INT); }
"varchar"		{ return getSymbolFactory().newSymbol("VARCHAR", Sym.VARCHAR); }
"not"			{ return getSymbolFactory().newSymbol("NOT", Sym.NOT); }
"null"			{ return getSymbolFactory().newSymbol("NULL", Sym.NULL); }
"set"			{ return getSymbolFactory().newSymbol("SET", Sym.SET); }
"values"		{ return getSymbolFactory().newSymbol("VALUES", Sym.VALUES); }
"drop"			{ return getSymbolFactory().newSymbol("DROP", Sym.DROP); }

"'"				{ return getSymbolFactory().newSymbol("QUOTE", Sym.QUOTE); }
","				{ return getSymbolFactory().newSymbol("SEP", Sym.SEP); }
";"				{ return getSymbolFactory().newSymbol("TERM", Sym.TERM); }
"("				{ return getSymbolFactory().newSymbol("OPEN_PARENTHESIS", Sym.OPEN_PARENTHESIS); }
")"				{ return getSymbolFactory().newSymbol("CLOSE_PARENTHESIS", Sym.CLOSE_PARENTHESIS); }

[0-9]+			{ return getSymbolFactory().newSymbol("NUMBER", Sym.NUMBER, Integer.parseInt(yytext())); }

[a-zA-Z][a-zA-Z_0-9]*	{ return getSymbolFactory().newSymbol("ID", Sym.ID, yytext()); }

[^]				{ throw new Error("Illegal character <"+yytext()+">"); }
