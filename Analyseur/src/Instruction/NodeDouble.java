package Instruction;

import ui.Visitor;

public class NodeDouble extends NodeNumeric {

	private Double value;

	public NodeDouble(Double value) {
		this.value = value;
	}

	public Double getValue() {
		return this.value;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitDouble(this);
	}

}
