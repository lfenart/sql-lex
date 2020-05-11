package Instruction;

public abstract class NodeSelectExpression extends Node {

	private String name;

	public NodeSelectExpression(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
