package Instruction;

import ui.Visitor;

public class NodeWildcard extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitWildcard(this);
	}

}
