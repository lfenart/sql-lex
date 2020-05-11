package Instruction;

public class NodeOrder extends Node {

	private Order order;

	public NodeOrder(Order order) {
		this.order = order;
	}
	
	public Order getOrder() {
		return this.order;
	}

}
