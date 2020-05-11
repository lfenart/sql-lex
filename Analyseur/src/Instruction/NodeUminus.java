package Instruction;

import ui.Visitor;

public class NodeUminus extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitUminus(this);
	}

}
