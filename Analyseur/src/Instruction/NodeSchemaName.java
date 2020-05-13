package Instruction;

import ui.Visitor;

public class NodeSchemaName extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitSchemaName(this);
	}

}
