package Instruction;

public class NodeSet extends Node {

	private NodeColumn column;
	private String value;

	public NodeSet(NodeColumn column, String value) {
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
