package Instruction;

import ui.Visitor;

public class NodeExpression extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitExpression(this);
	}

}
