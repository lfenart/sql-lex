package Instruction;

import ui.Visitor;

public class NodeRoot extends Node {

	@Override
	public void accept(Visitor visitor) {
		visitor.visitRoot(this);
	}

}
