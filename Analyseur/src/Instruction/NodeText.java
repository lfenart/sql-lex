package Instruction;

import ui.Visitor;

public class NodeText extends Node {

	private String value;

	public NodeText(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitText(this);
	}

}
