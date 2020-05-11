package Instruction;

public class NodeUminus {

	private int value;

	public NodeUminus(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int result() {
		return -this.value;
	}
}
