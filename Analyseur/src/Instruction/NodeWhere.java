package Instruction;

import ui.Visitor;

public class NodeWhere extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitWhere(this);
	}

}
