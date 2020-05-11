package Instruction;

public class NodeExpression extends Node {

	private NodeColumn column;
	private String value;

	public NodeExpression(NodeColumn column, String value) {
		super();
		this.column = column;
		this.value = value;
	}

	public NodeColumn getColumn() {
		return column;
	}

	public void setColumn(NodeColumn column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
