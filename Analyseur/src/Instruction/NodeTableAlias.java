package Instruction;

import ui.Visitor;

public class NodeTableAlias extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitTableAlias(this);
	}

}
