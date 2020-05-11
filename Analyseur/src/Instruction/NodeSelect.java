package Instruction;

public class NodeSelect extends Node {

	private NodeTable table;
	private NodeColumn[] columns;
	private NodeGroup group;
	private NodeWhere where;
	private Order orderValue;

	public NodeSelect(NodeTable table, NodeColumn[] columns, NodeGroup group, NodeWhere where, Order orderValue) {
		this.table = table;
		this.columns = columns;
		this.group = group;
		this.where = where;
		this.orderValue = orderValue;
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

	public NodeGroup getGroup() {
		return group;
	}

	public void setGroup(NodeGroup group) {
		this.group = group;
	}

	public NodeWhere getWhere() {
		return where;
	}

	public void setWhere(NodeWhere where) {
		this.where = where;
	}

	public Order getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(Order orderValue) {
		this.orderValue = orderValue;
	}

}
