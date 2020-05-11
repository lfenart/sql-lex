package Instruction;

public class NodeOperator extends Node{

	private Operator op;

	public NodeOperator(Operator op) {
		this.op = op;
	}

	public Operator getOp() {
		return op;
	}

	public void setOp(Operator op) {
		this.op = op;
	}
	
	
}
