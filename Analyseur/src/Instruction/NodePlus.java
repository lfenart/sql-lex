package Instruction;

import ui.Visitor;

public class NodePlus extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitPlus(this);
	}

}
