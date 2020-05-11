package Instruction;

import ui.Visitor;

public class NodeNull extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitNull(this);
	}

}
