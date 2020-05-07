package Instruction;

public class Table {

	private String name;
	private Column[] columns;
	private Column primaryKey;
	
	public Table(String name, Column[] columns,Column primaryKey) {
		this.name=name;
		this.columns=columns;
		this.primaryKey=primaryKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		name = name;
	}

	public Column[] getColumns() {
		return columns;
	}

	public void setColumns(Column[] columns) {
		columns = columns;
	}
	
	public Column getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Column primaryKey) {
		this.primaryKey = primaryKey;
	}
}
