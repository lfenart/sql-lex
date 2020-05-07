package Instruction;

public class Delete {

	private Table from;
	private Data where;
	private String value;

	public Delete(Table from, Data where, String value) {
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

	public Data getWhere() {
		return where;
	}

	public void setWhere(Data where) {
		this.where = where;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
