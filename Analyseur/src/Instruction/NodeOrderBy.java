package Instruction;

import ui.Visitor;

public class NodeOrderBy extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitOrderBy(this);
	}

}
