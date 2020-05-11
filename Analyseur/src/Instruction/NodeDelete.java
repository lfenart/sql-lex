package Instruction;

import ui.Visitor;

public class NodeDelete extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitDelete(this);
	}

}
