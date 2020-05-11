package Instruction;

import ui.Visitor;

public class NodeOperator extends Node {

	private Operator operator;

	public NodeOperator(Operator operator) {
		this.operator = operator;
	}
	
	public Operator getOperator() {
		return this.operator;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitOperator(this);
	}

}
