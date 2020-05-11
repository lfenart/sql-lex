package Instruction;

public class Set extends Node {

	private Column column;
	private String value;

	public Set(Column column, String value) {
		this.column = column;
		this.value = value;
	}

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
