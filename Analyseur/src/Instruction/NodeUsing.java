package Instruction;

import ui.Visitor;

public class NodeUsing extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitUsing(this);
	}

}
