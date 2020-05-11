package Instruction;

public class NodePrimaryKey extends Node{

	private NodeColumn primaryKey;

	public NodePrimaryKey(NodeColumn primaryKey) {
		this.primaryKey = primaryKey;
	}

	public NodeColumn getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(NodeColumn primaryKey) {
		this.primaryKey = primaryKey;
	}
}
