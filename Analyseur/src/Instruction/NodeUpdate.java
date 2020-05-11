package Instruction;

public class NodeUpdate extends Node {

	private NodeTable table;
	private NodeWhere where;
	private NodeSet set;
	private String whereValue;

	public NodeUpdate(NodeTable table, NodeWhere where, NodeSet set, String whereValue) {
		this.table = table;
		this.where = where;
		this.set = set;
		this.whereValue = whereValue;
	}

	public NodeTable getTable() {
		return table;
	}

	public void setTable(NodeTable table) {
		this.table = table;
	}

	public NodeWhere getWhere() {
		return where;
	}

	public void setWhere(NodeWhere where) {
		this.where = where;
	}

	public NodeSet getSet() {
		return set;
	}

	public void setSet(NodeSet set) {
		this.set = set;
	}

	public String getWhereValue() {
		return whereValue;
	}

	public void setWhereValue(String whereValue) {
		this.whereValue = whereValue;
	}

}
