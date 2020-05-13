package Instruction;

import ui.Visitor;

public class NodeJoin extends Node {
	
	private JoinType type;
	
	public NodeJoin(JoinType type) {
		this.type = type;
	}
	
	public JoinType getType() {
		return this.type;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitJoin(this);
	}

}
