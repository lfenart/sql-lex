package Instruction;

public class NodeBoolean extends Node{

	private boolean b;
	
	public NodeBoolean(boolean b) {
		this.setB(b);
	}

	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}
}
