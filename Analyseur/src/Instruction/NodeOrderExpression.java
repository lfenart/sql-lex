package Instruction;

import ui.Visitor;

public class NodeOrderExpression extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitOrderExpression(this);
	}

}
