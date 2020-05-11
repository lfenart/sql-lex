package Instruction;

import ui.Visitor;

public class NodeNot extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitNot(this);
	}

}
