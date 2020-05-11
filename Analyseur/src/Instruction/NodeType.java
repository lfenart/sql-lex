package Instruction;

public class NodeType extends Node {

	private DataType type;
	private Integer size;

	public NodeType(DataType type, Integer size) {
		this.type = type;
		this.size = size;
	}

	public DataType getType() {
		return this.type;
	}

	public int getSize() {
		return this.size;
	}

}
