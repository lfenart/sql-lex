package Instruction;

import ui.Visitor;

public class NodeConcat extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitConcat(this);
	}

}
