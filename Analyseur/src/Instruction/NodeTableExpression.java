package Instruction;

import ui.Visitor;

public class NodeTableExpression extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitTable(this);
	}

}
