package Instruction;

public class NodeDelete extends Node {

	private NodeTable from;
	private NodeColumn where;
	private String value;

	public NodeDelete(NodeTable from, NodeColumn where, String value) {
		this.from = from;
		this.where = where;
		this.value = value;
	}

	public NodeTable getFrom() {
		return from;
	}

	public void setFrom(NodeTable from) {
		this.from = from;
	}

	public NodeColumn getWhere() {
		return where;
	}

	public void setWhere(NodeColumn where) {
		this.where = where;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
