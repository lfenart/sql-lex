
%%
%caseless
%package compiler
%class Lexer
%public
%line
%column
%cup


%%

[ \t\n\r]		{ }
^#.*			{ }

"select"				{ return getSymbolFactory().newSymbol("SELECT", Sym.SELECT); }
"from"					{ return getSymbolFactory().newSymbol("FROM", Sym.FROM); }
"where"					{ return getSymbolFactory().newSymbol("WHERE", Sym.WHERE); }
"create"				{ return getSymbolFactory().newSymbol("CREATE", Sym.CREATE); }
"table"					{ return getSymbolFactory().newSymbol("TABLE", Sym.TABLE); }
"primary"				{ return getSymbolFactory().newSymbol("PRIMARY", Sym.PRIMARY); }
"key"					{ return getSymbolFactory().newSymbol("KEY", Sym.KEY); }
"delete"				{ return getSymbolFactory().newSymbol("DELETE", Sym.DELETE); }
"update"				{ return getSymbolFactory().newSymbol("UPDATE", Sym.UPDATE); }
"insert"				{ return getSymbolFactory().newSymbol("INSERT", Sym.INSERT); }
"into"					{ return getSymbolFactory().newSymbol("INTO", Sym.INTO); }
"int"					{ return getSymbolFactory().newSymbol("INT", Sym.INT); }
"varchar"				{ return getSymbolFactory().newSymbol("VARCHAR", Sym.VARCHAR); }
"smallint"				{ return getSymbolFactory().newSymbol("SMALLINT", Sym.SMALLINT); }
"tinyint"				{ return getSymbolFactory().newSymbol("TINYINT", Sym.TINYINT); }
"bigint"				{ return getSymbolFactory().newSymbol("BIGINT", Sym.BIGINT); }
"float"					{ return getSymbolFactory().newSymbol("FLOAT", Sym.FLOAT); }
"real"					{ return getSymbolFactory().newSymbol("REAL", Sym.REAL); }
"money"					{ return getSymbolFactory().newSymbol("MONEY", Sym.MONEY); }
"time"					{ return getSymbolFactory().newSymbol("TIME", Sym.TIME); }
"bit"					{ return getSymbolFactory().newSymbol("BIT", Sym.BIT); }
"char"					{ return getSymbolFactory().newSymbol("CHAR", Sym.CHAR); }
"not"					{ return getSymbolFactory().newSymbol("NOT", Sym.NOT); }
"null"					{ return getSymbolFactory().newSymbol("NULL", Sym.NULL); }
"set"					{ return getSymbolFactory().newSymbol("SET", Sym.SET); }
"values"				{ return getSymbolFactory().newSymbol("VALUES", Sym.VALUES); }
"drop"					{ return getSymbolFactory().newSymbol("DROP", Sym.DROP); }
"group"					{ return getSymbolFactory().newSymbol("GROUP", Sym.GROUP); }
"order"					{ return getSymbolFactory().newSymbol("ORDER", Sym.ORDER); }
"by"					{ return getSymbolFactory().newSymbol("BY", Sym.BY); }
"desc"					{ return getSymbolFactory().newSymbol("DESC", Sym.DESC); }
"asc"					{ return getSymbolFactory().newSymbol("ASC", Sym.ASC); }
"nulls"					{ return getSymbolFactory().newSymbol("NULLS", Sym.NULLS); }
"first"					{ return getSymbolFactory().newSymbol("FIRST", Sym.FIRST); }
"last"					{ return getSymbolFactory().newSymbol("LAST", Sym.LAST); }
"on"					{ return getSymbolFactory().newSymbol("ON", Sym.ON); }
"as"					{ return getSymbolFactory().newSymbol("AS", Sym.AS); }
"using"					{ return getSymbolFactory().newSymbol("USING", Sym.USING); }
"inner"					{ return getSymbolFactory().newSymbol("INNER", Sym.INNER); }
"cross"					{ return getSymbolFactory().newSymbol("CROSS", Sym.CROSS); }
"left"					{ return getSymbolFactory().newSymbol("LEFT", Sym.LEFT); }
"right"					{ return getSymbolFactory().newSymbol("RIGHT", Sym.RIGHT); }
"full"					{ return getSymbolFactory().newSymbol("FULL", Sym.FULL); }
"self"					{ return getSymbolFactory().newSymbol("SELF", Sym.SELF); }
"natural"				{ return getSymbolFactory().newSymbol("NATURAL", Sym.NATURAL); }
"union"					{ return getSymbolFactory().newSymbol("UNION", Sym.UNION); }
"join"					{ return getSymbolFactory().newSymbol("JOIN", Sym.JOIN); }

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
","				{ return getSymbolFactory().newSymbol("SEP", Sym.SEP); }
";"				{ return getSymbolFactory().newSymbol("TERM", Sym.TERM); }
"("				{ return getSymbolFactory().newSymbol("OPEN_PARENTHESIS", Sym.OPEN_PARENTHESIS); }
")"				{ return getSymbolFactory().newSymbol("CLOSE_PARENTHESIS", Sym.CLOSE_PARENTHESIS); }
"."				{ return getSymbolFactory().newSymbol("POINT", Sym.POINT); }

-?[0-9]*"."[0-9]+	{ return getSymbolFactory().newSymbol("DOUBLE", Sym.DOUBLE, Double.parseDouble(yytext())); }
-?[0-9]+"."			{ return getSymbolFactory().newSymbol("DOUBLE", Sym.DOUBLE, Double.parseDouble(yytext())); }
-?[0-9]+			{ return getSymbolFactory().newSymbol("INTEGER", Sym.INTEGER, Long.parseLong(yytext())); }

[a-zA-Z][a-zA-Z_0-9]*	{ return getSymbolFactory().newSymbol("ID", Sym.ID, yytext()); }
\'[^']*\'					{ return getSymbolFactory().newSymbol("TEXT", Sym.TEXT, yytext().substring(1, yylength()-1)); }

"--".*	{}
"//".*	{}
"/*".*"*/"	{}

[^]				{ throw new Error("Illegal character <"+yytext()+">"); }
