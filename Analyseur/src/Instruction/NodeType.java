package Instruction;

public class NodeType extends Node {

	private DataType type;
	private Long size;

	public NodeType(DataType type, Long size) {
		this.type = type;
		this.size = size;
	}

	public NodeType(DataType type) {
		this(type, null);
	}

	public DataType getType() {
		return this.type;
	}

	public Long getSize() {
		return this.size;
	}

}
