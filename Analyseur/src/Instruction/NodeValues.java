package Instruction;

import ui.Visitor;

public class NodeValues extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitNodeValues(this);
	}

}
