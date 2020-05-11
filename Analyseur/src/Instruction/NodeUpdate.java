package Instruction;

import ui.Visitor;

public class NodeUpdate extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitUpdate(this);
	}

}
