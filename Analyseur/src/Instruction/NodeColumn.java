package Instruction;

import ui.Visitor;

public class NodeColumn extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitColumn(this);
	}

}
