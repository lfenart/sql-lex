     
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

"select"|"SELECT"		{ return getSymbolFactory().newSymbol("SELECT", Sym.SELECT); }
"from"|"FROM"			{ return getSymbolFactory().newSymbol("FROM", Sym.FROM); }
"where"|"WHERE"			{ return getSymbolFactory().newSymbol("WHERE", Sym.WHERE); }
"create"|"CREATE"		{ return getSymbolFactory().newSymbol("CREATE", Sym.CREATE); }
"table"|"TABLE"			{ return getSymbolFactory().newSymbol("TABLE", Sym.TABLE); }
"primary"|"PRIMARY"		{ return getSymbolFactory().newSymbol("PRIMARY", Sym.PRIMARY); }
"key"|"KEY"			{ return getSymbolFactory().newSymbol("KEY", Sym.KEY); }
"delete"|"DELETE"		{ return getSymbolFactory().newSymbol("DELETE", Sym.DELETE); }
"update"|"UPDATE"		{ return getSymbolFactory().newSymbol("UPDATE", Sym.UPDATE); }
"insert"|"INSERT"		{ return getSymbolFactory().newSymbol("INSERT", Sym.INSERT); }
"into"|"INTO"			{ return getSymbolFactory().newSymbol("INTO", Sym.INTO); }
"int"|"INT"				{ return getSymbolFactory().newSymbol("INT", Sym.INT); }
"varchar"|"VARCHAR"		{ return getSymbolFactory().newSymbol("VARCHAR", Sym.VARCHAR); }
"not"|"NOT"			{ return getSymbolFactory().newSymbol("NOT", Sym.NOT); }
"null"|"NULL"			{ return getSymbolFactory().newSymbol("NULL", Sym.NULL); }
"set"|"SET"			{ return getSymbolFactory().newSymbol("SET", Sym.SET); }
"values"|"VALUES"		{ return getSymbolFactory().newSymbol("VALUES", Sym.VALUES); }
"drop"|"DROP"			{ return getSymbolFactory().newSymbol("DROP", Sym.DROP); }
"group"|"GROUP"			{ return getSymbolFactory().newSymbol("GROUP", Sym.GROUP); }
"order"|"ORDER"			{ return getSymbolFactory().newSymbol("ORDER", Sym.ORDER); }
"by"|"BY"           { return getSymbolFactory().newSymbol("BY", Sym.BY); }
"desc"|"DESC"    { return getSymbolFactory().newSymbol("DESC", Sym.DESC); }
"asc"|"ASC"    { return getSymbolFactory().newSymbol("ASC", Sym.ASC); }

[ ]				{ return getSymbolFactory().newSymbol("SPACE", Sym.SPACE); }
"="				{ return getSymbolFactory().newSymbol("EQUAL", Sym.EQUAL); }
"*"				{ return getSymbolFactory().newSymbol("WILDCARD", Sym.WILDCARD); }
"'"				{ return getSymbolFactory().newSymbol("QUOTE", Sym.QUOTE); }
","				{ return getSymbolFactory().newSymbol("SEP", Sym.SEP); }
";"				{ return getSymbolFactory().newSymbol("TERM", Sym.TERM); }
"("				{ return getSymbolFactory().newSymbol("OPEN_PARENTHESIS", Sym.OPEN_PARENTHESIS); }
")"				{ return getSymbolFactory().newSymbol("CLOSE_PARENTHESIS", Sym.CLOSE_PARENTHESIS); }

[0-9]+			{ return getSymbolFactory().newSymbol("NUMBER", Sym.NUMBER, Integer.parseInt(yytext())); }

[a-zA-Z][a-zA-Z_0-9יטךאגפûשחמ]*	{ return getSymbolFactory().newSymbol("ID", Sym.ID, yytext()); }

[^]				{ throw new Error("Illegal character <"+yytext()+">"); }
