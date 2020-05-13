package Instruction;

import ui.Visitor;

public class NodeSelectExpression extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitSelectExpression(this);
	}

}
