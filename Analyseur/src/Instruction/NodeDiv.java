package Instruction;

import ui.Visitor;

public class NodeDiv extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitDiv(this);
	}

}
