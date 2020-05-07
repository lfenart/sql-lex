package Instruction;

public class Select {

	private Table table;
	private Data[] columns;
	private Data whereColumn, orderColumn, groupColumn;
	private String whereValue, orderValue;
	
	public Select(Table table, Data[] columns, Data whereColumn, Data orderColumn, Data groupColumn, String whereValue,
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
	public Data[] getColumns() {
		return columns;
	}
	public void setColumns(Data[] columns) {
		this.columns = columns;
	}
	public Data getWhereColumn() {
		return whereColumn;
	}
	public void setWhereColumn(Data whereColumn) {
		this.whereColumn = whereColumn;
	}
	public Data getOrderColumn() {
		return orderColumn;
	}
	public void setOrderColumn(Data orderColumn) {
		this.orderColumn = orderColumn;
	}
	public Data getGroupColumn() {
		return groupColumn;
	}
	public void setGroupColumn(Data groupColumn) {
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
