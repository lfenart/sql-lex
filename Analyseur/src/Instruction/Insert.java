package Instruction;

public class Insert {
	private Table table;
	private Data[] columns;
	private String[] values;
	
	public Insert(Table table, Data[] columns, String[] values) {
		this.table=table;
		this.columns=columns;
		this.values=values;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Data[] getColumns() {
		return columns;
	}

	public void setColumns(Data[] columns) {
		this.columns = columns;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}
}
