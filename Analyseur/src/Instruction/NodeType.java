package Instruction;

public class NodeType extends Node{

	private DataType type;
	private int value;
	
	public NodeType(DataType type, int value) {
		this.type = type;
		this.value = value;
	}
	
	public DataType getType() {
		return type;
	}
	public void setType(DataType type) {
		this.type = type;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
}
