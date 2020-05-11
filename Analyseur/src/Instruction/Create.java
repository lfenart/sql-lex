package Instruction;

public class Create {
	
	private Table table;
	private Data[] datas;
	private Data primaryKey;
	
	public Create(Table table, Data[] columns,Data primaryKey) {
		this.table=table;
		this.datas=columns;
		this.primaryKey=primaryKey;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Data[] getColumns() {
		return datas;
	}

	public void setColumns(Data[] columns) {
		this.datas = columns;
	}
	
	public Data getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Data primaryKey) {
		this.primaryKey = primaryKey;
	}


	
}
