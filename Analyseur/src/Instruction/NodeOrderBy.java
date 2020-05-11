package Instruction;

public class NodeOrderBy extends Node{

	private NodeColumn column;
	private Order order;
	
	public NodeOrderBy(NodeColumn column, Order order) {
		this.column = column;
		this.order = order;
	}

	public NodeColumn getColumn() {
		return column;
	}

	public void setColumn(NodeColumn column) {
		this.column = column;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
