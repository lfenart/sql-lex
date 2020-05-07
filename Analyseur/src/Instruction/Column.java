package Instruction;

public class Column {
	private String name;
	private boolean notNull;
	private ColumnType type;
	
	public Column(String name, ColumnType type) {
		this.name=name;
		notNull=true;
		this.type=type;
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

	public ColumnType getType() {
		return type;
	}

	public void setType(ColumnType type) {
		this.type = type;
	}
	
	
	
}
