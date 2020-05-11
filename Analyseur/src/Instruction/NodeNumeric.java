package Instruction;

public class NodeNumeric extends Node{

	private float value;

	public NodeNumeric(float value) {
		this.value = value;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
	
	
}
