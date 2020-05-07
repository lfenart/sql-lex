package Instruction;

public class Select {

	private Table table;
	private Column[] columns;
	private Column whereColumn, orderColumn, groupColumn;
	private String whereValue, orderValue;
	
	public Select(Table table, Column[] columns, Column whereColumn, Column orderColumn, Column groupColumn, String whereValue,
			String orderValue) {
		this.table = table;
		this.columns = columns;
		this.whereColumn = whereColumn;
		this.orderColumn = orderColumn;
		this.groupColumn = groupColumn;
		this.whereValue = whereValue;
		this.orderValue = orderValue;
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
	public Column getWhereColumn() {
		return whereColumn;
	}
	public void setWhereColumn(Column whereColumn) {
		this.whereColumn = whereColumn;
	}
	public Column getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(Column orderColumn) {
		this.orderColumn = orderColumn;
	}
	public Column getGroupColumn() {
		return groupColumn;
	}
	public void setGroupColumn(Column groupColumn) {
		this.groupColumn = groupColumn;
	}
	public String getWhereValue() {
		return whereValue;
	}
	public void setWhereValue(String whereValue) {
		this.whereValue = whereValue;
	}
	public String getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(String orderValue) {
		this.orderValue = orderValue;
	}

	
}
