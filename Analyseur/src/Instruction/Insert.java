package Instruction;

public class Insert extends Node {
	private Table table;
	private Column[] columns;
	private String[] values;

	public Insert(Table table, Column[] columns, String[] values) {
		this.table = table;
		this.columns = columns;
		this.values = values;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Column[] getColumns() {
		return columns;
	}

	public void setColumns(Column[] columns) {
		this.columns = columns;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}
}
