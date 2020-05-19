package Instruction;

import ui.Visitor;

public class NodeUplus extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitNodeUplus(this);
	}

}
