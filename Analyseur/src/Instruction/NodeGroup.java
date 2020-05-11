package Instruction;

import ui.Visitor;

public class NodeGroup extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitGroup(this);
	}

}
