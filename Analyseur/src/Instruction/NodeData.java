package Instruction;

import ui.Visitor;

public class NodeData extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitData(this);
	}

}
