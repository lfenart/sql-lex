package Instruction;

public class NodeOperator extends Node {

	private Operator operator;

	public NodeOperator(Operator operator) {
		this.operator = operator;
	}
	
	public Operator getOperator() {
		return this.operator;
	}

}
