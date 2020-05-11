package Instruction;

import ui.Visitor;

public class NodeSelect extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitSelect(this);
	}

}
