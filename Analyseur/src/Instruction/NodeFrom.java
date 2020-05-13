package Instruction;

import ui.Visitor;

public class NodeFrom extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitFrom(this);
	}

}
