package Instruction;

import ui.Visitor;

public class NodeMult extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitMult(this);
	}

}
