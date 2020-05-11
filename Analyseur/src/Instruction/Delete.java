package Instruction;

public class Delete {

	private Table from;
	private Column where;
	private String value;

	public Delete(Table from, Column where, String value) {
		this.from=from;
		this.where=where;
		this.value=value;
	}

	public Table getFrom() {
		return from;
	}

	public void setFrom(Table from) {
		this.from = from;
	}

	public Column getWhere() {
		return where;
	}

	public void setWhere(Column where) {
		this.where = where;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
