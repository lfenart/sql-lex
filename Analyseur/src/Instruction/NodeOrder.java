package Instruction;

import ui.Visitor;

public class NodeOrder extends Node {

	private Order order;

	public NodeOrder(Order order) {
		this.order = order;
	}
	
	public Order getOrder() {
		return this.order;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitOrder(this);
	}

}
