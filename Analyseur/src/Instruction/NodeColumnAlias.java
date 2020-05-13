package Instruction;

import ui.Visitor;

public class NodeColumnAlias extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitColumnAlias(this);
	}

}
