package Instruction;

import ui.Visitor;

public class NodeAs extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitAs(this);
	}

}
