     
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

"select"|"SELECT"				{ return getSymbolFactory().newSymbol("SELECT", Sym.SELECT); }
"select"|"SELECT"[ \t\n\r]+\*	{ return getSymbolFactory().newSymbol("SELECT_ALL", Sym.SELECT_ALL); }
"from"|"FROM"					{ return getSymbolFactory().newSymbol("FROM", Sym.FROM); }
"where"|"WHERE"					{ return getSymbolFactory().newSymbol("WHERE", Sym.WHERE); }
"create"|"CREATE"				{ return getSymbolFactory().newSymbol("CREATE", Sym.CREATE); }
"table"|"TABLE"					{ return getSymbolFactory().newSymbol("TABLE", Sym.TABLE); }
"primary"|"PRIMARY"				{ return getSymbolFactory().newSymbol("PRIMARY", Sym.PRIMARY); }
"key"|"KEY"						{ return getSymbolFactory().newSymbol("KEY", Sym.KEY); }
"delete"|"DELETE"				{ return getSymbolFactory().newSymbol("DELETE", Sym.DELETE); }
"update"|"UPDATE"				{ return getSymbolFactory().newSymbol("UPDATE", Sym.UPDATE); }
"insert"|"INSERT"				{ return getSymbolFactory().newSymbol("INSERT", Sym.INSERT); }
"into"|"INTO"					{ return getSymbolFactory().newSymbol("INTO", Sym.INTO); }
"int"|"INT"						{ return getSymbolFactory().newSymbol("INT", Sym.INT); }
"varchar"|"VARCHAR"				{ return getSymbolFactory().newSymbol("VARCHAR", Sym.VARCHAR); }
"not"|"NOT"						{ return getSymbolFactory().newSymbol("NOT", Sym.NOT); }
"null"|"NULL"					{ return getSymbolFactory().newSymbol("NULL", Sym.NULL); }
"set"|"SET"						{ return getSymbolFactory().newSymbol("SET", Sym.SET); }
"values"|"VALUES"				{ return getSymbolFactory().newSymbol("VALUES", Sym.VALUES); }
"drop"|"DROP"					{ return getSymbolFactory().newSymbol("DROP", Sym.DROP); }
"group"|"GROUP"					{ return getSymbolFactory().newSymbol("GROUP", Sym.GROUP); }
"order"|"ORDER"					{ return getSymbolFactory().newSymbol("ORDER", Sym.ORDER); }
"by"|"BY"						{ return getSymbolFactory().newSymbol("BY", Sym.BY); }
"desc"|"DESC"					{ return getSymbolFactory().newSymbol("DESC", Sym.DESC); }
"asc"|"ASC"						{ return getSymbolFactory().newSymbol("ASC", Sym.ASC); }

"="				{ return getSymbolFactory().newSymbol("EQUAL", Sym.EQUAL); }
"<>"|"!="		{ return getSymbolFactory().newSymbol("NOT_EQ", Sym.NOT_EQ); }
"<"				{ return getSymbolFactory().newSymbol("LESS", Sym.LESS); }
">"				{ return getSymbolFactory().newSymbol("GREATER", Sym.GREATER); }
"<="			{ return getSymbolFactory().newSymbol("LESS_OR_EQ", Sym.LESS_OR_EQ); }
">="			{ return getSymbolFactory().newSymbol("GREATER_OR_EQ", Sym.GREATER_OR_EQ); }
"+"				{ return getSymbolFactory().newSymbol("PLUS", Sym.PLUS); }
"-"				{ return getSymbolFactory().newSymbol("MINUS", Sym.MINUS); }
"*"				{ return getSymbolFactory().newSymbol("MULT", Sym.MULT); }
"/"				{ return getSymbolFactory().newSymbol("DIV", Sym.DIV); }
"'"				{ return getSymbolFactory().newSymbol("QUOTE", Sym.QUOTE); }
","				{ return getSymbolFactory().newSymbol("SEP", Sym.SEP); }
";"				{ return getSymbolFactory().newSymbol("TERM", Sym.TERM); }
"("				{ return getSymbolFactory().newSymbol("OPEN_PARENTHESIS", Sym.OPEN_PARENTHESIS); }
")"				{ return getSymbolFactory().newSymbol("CLOSE_PARENTHESIS", Sym.CLOSE_PARENTHESIS); }

[0-9]+			{ return getSymbolFactory().newSymbol("NUMBER", Sym.NUMBER, Integer.parseInt(yytext())); }

[a-zA-Z][a-zA-Z_0-9]*	{ return getSymbolFactory().newSymbol("ID", Sym.ID, yytext()); }
\'.*\'					{ return getSymbolFactory().newSymbol("TEXT", Sym.TEXT, yytext().substring(1, yylength()-1)); }

[^]				{ throw new Error("Illegal character <"+yytext()+">"); }
