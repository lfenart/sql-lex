package Instruction;

public class Data extends Node {
	private String name;
	private boolean notNull;
	private DataType type;

	public Data(String name, DataType type) {
		this.name = name;
		this.notNull = true;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public DataType getType() {
		return type;
	}

	public void setType(DataType type) {
		this.type = type;
	}

}
