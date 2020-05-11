package Instruction;

import ui.Visitor;

public class NodePrimaryKey extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitPrimary(this);
	}

}
