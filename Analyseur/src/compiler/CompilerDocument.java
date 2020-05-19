package compiler;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java_cup.runtime.SimpleSymbolFactory;
import java_cup.runtime.SymbolFactory;
import ui.Visitor;
import ui.XmlVisitor;

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
			SymbolFactory csf = new SimpleSymbolFactory();
			Lexer l = new Lexer(translate(new FileReader("test.txt")));
			l.setSymbolFactory(csf);
			Parser p = new Parser(l, csf);
			context = new SQLContext();
			p.setContext(context);
			p.parse();
			Visitor visitor = new XmlVisitor();
			context.getRoot().accept(visitor);
		} catch (Exception e) {
			e.printStackTrace();
			context.addError("FileNotFoundException");
		}
	}

	public static FileReader translate(FileReader fileReader) throws IOException {
		int data = fileReader.read();
		String src = "";
		while (data != -1) {
			src += (char) data;
			data = fileReader.read();
		}
		StringBuffer result = new StringBuffer();
		if (src != null && src.length() != 0) {
			int index = -1;
			char c = (char) 0;
			String chars = "אגהיטךכמןפצשûüח";
			String replace = "aaaeeeeiioouuuc";
			for (int i = 0; i < src.length(); i++) {
				c = src.charAt(i);
				if ((index = chars.indexOf(c)) != -1)
					result.append(replace.charAt(index));
				else
					result.append(c);
			}
		}
		FileWriter out = new FileWriter("testTranslated.txt");
		out.write(result.toString());
		out.close();
		return new FileReader("testTranslated.txt");
	}
}
