package Instruction;

import ui.Visitor;

public class NodeBoolean extends Node {

	private Boolean value;

	public NodeBoolean(Boolean value) {
		this.value = value;
	}
	
	public Boolean getValue() {
		return this.value;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitBoolean(this);
	}
}
