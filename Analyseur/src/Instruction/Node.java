package Instruction;

import java.util.ArrayList;
import java.util.List;

import ui.Visitor;

public abstract class Node {

	private List<Node> children = new ArrayList<Node>();

	public List<Node> getChildren() {
		return this.children;
	}

	public abstract void accept(Visitor visitor);

}
