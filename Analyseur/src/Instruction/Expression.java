package Instruction;

public class Expression {

	private Column column;
	private String value;
	
	public Expression(Column column, String value) {
		super();
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
