package Instruction;

import ui.Visitor;

public class NodeOn extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitOn(this);
	}

}
