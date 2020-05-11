package Instruction;

public class NodeCreate extends Node {

	private NodeTable table;
	private NodeData[] datas;
	private NodeData primaryKey;

	public NodeCreate(NodeTable table, NodeData[] columns, NodeData primaryKey) {
		this.table = table;
		this.datas = columns;
		this.primaryKey = primaryKey;
	}

	public NodeTable getTable() {
		return table;
	}

	public void setTable(NodeTable table) {
		this.table = table;
	}

	public NodeData[] getColumns() {
		return datas;
	}

	public void setColumns(NodeData[] columns) {
		this.datas = columns;
	}

	public NodeData getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(NodeData primaryKey) {
		this.primaryKey = primaryKey;
	}

}
