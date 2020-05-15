package compiler;

public class SQLCompilerApp {

	private CompilerDocument document;

	public SQLCompilerApp() {
		document = new CompilerDocument();
		document.onOpenDocument();
	}

	public static void main(String argv[]) {
		SQLCompilerApp app = new SQLCompilerApp();
	}

}
