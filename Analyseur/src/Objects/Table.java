package Objects;

import java.util.List;

public class Table {

	private String name, primaryKey;
	private List<Column> columns;
	
	public Table(String name, List<Column> columns, String primaryKey) {
		this.name = name;
		this.columns = columns;
		this.primaryKey = primaryKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	public void addColumn(Column column) {
		this.columns.add(column);
	}
	
	public void removeColumn(Column column) {
		this.columns.remove(column);
	}
	
	
}
