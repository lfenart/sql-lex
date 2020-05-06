package compiler;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java_cup.runtime.SimpleSymbolFactory;
import java_cup.runtime.SymbolFactory;


public class CompilerDocument {

	private SQLContext context;
	
	public SQLContext getContext() {
		return context;
	}

	public void setContext(SQLContext context) {
		this.context = context;
	}

	public void onOpenDocument() {
			try {
		    	SymbolFactory csf = new SimpleSymbolFactory ();
		    	Lexer l = new Lexer(new FileReader("test.txt"));
		    	l.setSymbolFactory(csf);
		    	Parser p = new Parser(l, csf);
				context = new SQLContext();
				p.setContext(context);
		    	p.parse();      
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				context.addError("FileNotFoundException");
			} catch (Exception e) {
				e.printStackTrace();
				context.addError("FileNotFoundException");
			}
	}
}
