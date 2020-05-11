package Instruction;

import ui.Visitor;

public class NodeSet extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitSet(this);
	}

}
