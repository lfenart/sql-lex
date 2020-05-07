package Instruction;

public class Create {
	
	private Table table;
	private Data[] columns;
	private Data primaryKey;
	
	public Create(Table table, Data[] columns,Data primaryKey) {
		this.table=table;
		this.columns=columns;
		this.primaryKey=primaryKey;
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
	
	public Data getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Data primaryKey) {
		this.primaryKey = primaryKey;
	}


	
}
