package Instruction;

import ui.Visitor;

public class NodeFunction extends Node{

	private String name;

	public NodeFunction(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitFunction(this);
	}
	
}
