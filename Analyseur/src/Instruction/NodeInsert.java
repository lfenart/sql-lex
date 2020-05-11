package Instruction;

import ui.Visitor;

public class NodeInsert extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitInsert(this);
	}

}
