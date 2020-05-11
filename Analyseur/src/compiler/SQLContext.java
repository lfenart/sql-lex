package compiler;

import java.util.ArrayList;
import java.util.List;

import Instruction.Node;
import Instruction.NodeRoot;

public class SQLContext {
	private List<String> errors;
	private SQLFactory factory = null;
	private Node root = new NodeRoot();

	public SQLContext() {
		super();
	}

	public boolean hasErrors() {
		if (errors == null)
			return false;
		if (errors.isEmpty())
			return false;
		return true;
	}

	public List<String> getErrors() {
		if (errors == null)
			this.errors = new ArrayList<String>();
		return errors;
	}

	public void addError(String text) {
		getErrors().add(text);
	}

	public SQLFactory getFactory() {
		if (factory == null)
			factory = new SQLFactory();
		return factory;
	}

	public void addInstruction(Node n) {
		this.root.getChildren().add(n);
	}

	public Node getRoot() {
		return this.root;
	}

}
