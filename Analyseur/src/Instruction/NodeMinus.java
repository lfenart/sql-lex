package Instruction;

import ui.Visitor;

public class NodeMinus extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitMinus(this);
	}

}
