     
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

"select" | "SELECT"		{ return getSymbolFactory().newSymbol("SELECT", Sym.SELECT); }
"from" | "FROM"			{ return getSymbolFactory().newSymbol("FROM", Sym.FROM); }
"where"	| "WHERE"		{ return getSymbolFactory().newSymbol("WHERE", Sym.WHERE); }
"create table" | "CREATE TABLE"	{ return getSymbolFactory().newSymbol("CREATE_TABLE", Sym.CREATE_TABLE); }
"primary key" | "PRIMARY KEY"		{ return getSymbolFactory().newSymbol("PRIMARY_KEY", Sym.PRIMARY_KEY); }
"delete" | "DELETE"			{ return getSymbolFactory().newSymbol("DELETE", Sym.DELETE); }
"update" | "UPDATE"			{ return getSymbolFactory().newSymbol("UPDATE", Sym.UPDATE); }
"insert into" | "INSERT INTO"		{ return getSymbolFactory().newSymbol("INSERT_INTO", Sym.INSERT_INTO); }
"*" 					{ return getSymbolFactory().newSymbol("WILDCARD", Sym.WILDCARD); }
"int" | "INT"				{ return getSymbolFactory().newSymbol("INT", Sym.INT); }
"varchar" | "VARCHAR"			{ return getSymbolFactory().newSymbol("VARCHAR", Sym.VARCHAR); }
"not null" | "NOT NULL"			{ return getSymbolFactory().newSymbol("NOT_NULL", Sym.NOT_NULL); }
"set" | "SET"				{ return getSymbolFactory().newSymbol("SET", Sym.SET); }
"values" | "VALUES"			{ return getSymbolFactory().newSymbol("VALUES", Sym.VALUES); }
"group by" | "GROUP BY"			{ return getSymbolFactory().newSymbol("GROUP_BY", Sym.GROUP_BY); }
"order by" | "ORDER BY"			{ return getSymbolFactory().newSymbol("ORDER_BY", Sym.ORDER_BY); }


"'"				{ return getSymbolFactory().newSymbol("QUOTE", Sym.QUOTE); }
","				{ return getSymbolFactory().newSymbol("SEP", Sym.SEP); }
";"				{ return getSymbolFactory().newSymbol("TERM", Sym.TERM); }
"("				{ return getSymbolFactory().newSymbol("OPEN_PARENTHESIS", Sym.OPEN_PARENTHESIS); }
")"				{ return getSymbolFactory().newSymbol("CLOSE_PARENTHESIS", Sym.CLOSE_PARENTHESIS); }

[0-9]+			{ return getSymbolFactory().newSymbol("NUMBER", Sym.NUMBER, Integer.parseInt(yytext())); }

[a-zA-Z][a-zA-Z_0-9]*	{ return getSymbolFactory().newSymbol("ID", Sym.ID, yytext()); }

[^]				{ throw new Error("Illegal character <"+yytext()+">"); }
