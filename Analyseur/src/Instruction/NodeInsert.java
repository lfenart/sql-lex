package Instruction;

public class NodeInsert extends Node {
	private NodeTable table;
	private NodeColumn[] columns;
	private String[] values;

	public NodeInsert(NodeTable table, NodeColumn[] columns, String[] values) {
		this.table = table;
		this.columns = columns;
		this.values = values;
	}

	public NodeTable getTable() {
		return table;
	}

	public void setTable(NodeTable table) {
		this.table = table;
	}

	public NodeColumn[] getColumns() {
		return columns;
	}

	public void setColumns(NodeColumn[] columns) {
		this.columns = columns;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}
}
