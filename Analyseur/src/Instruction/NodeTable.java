package Instruction;

import ui.Visitor;

public class NodeTable extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitTable(this);
	}

}
