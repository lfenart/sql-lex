package Instruction;

import ui.Visitor;

public class NodeJoin extends Node{

	private String name;

	public NodeJoin(String name) {
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
		// TODO Auto-generated method stub
		visitor.visitJoin(this);
	}
	
}
