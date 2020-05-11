package Instruction;

import ui.Visitor;

public class NodeDrop extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitDrop(this);
	}

}
