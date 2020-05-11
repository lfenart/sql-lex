package Instruction;

import ui.Visitor;

public class NodeBlock extends Node {

	public NodeBlock() {

	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitBlock(this);
	}
}
