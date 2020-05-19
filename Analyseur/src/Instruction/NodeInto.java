package Instruction;

import ui.Visitor;

public class NodeInto extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitInto(this);
	}

}
