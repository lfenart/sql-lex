package Instruction;

import ui.Visitor;

public class NodeBlock extends Node {


	@Override
	public void accept(Visitor visitor) {
		visitor.visitBlock(this);
	}
}
