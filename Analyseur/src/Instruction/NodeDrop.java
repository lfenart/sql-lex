package Instruction;

public class NodeDrop extends Node {

	private NodeTable table;

	public NodeDrop(NodeTable table) {
		this.setTable(table);
	}

	public NodeTable getTable() {
		return table;
	}

	public void setTable(NodeTable table) {
		this.table = table;
	}

}
