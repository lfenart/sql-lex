package Instruction;

public class NodeWhere extends Node {

	private NodeExpression expression;

	public NodeWhere(NodeExpression expression) {
		this.expression = expression;
	}

	public NodeExpression getExpression() {
		return expression;
	}

	public void setExpression(NodeExpression expression) {
		this.expression = expression;
	}

}
