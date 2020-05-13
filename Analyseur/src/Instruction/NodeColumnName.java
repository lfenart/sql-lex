package Instruction;

import ui.Visitor;

public class NodeColumnName extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitColumnName(this);
	}

}
