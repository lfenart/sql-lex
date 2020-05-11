package Instruction;

import ui.Visitor;

public class NodeCreate extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitCreate(this);
	}

}
