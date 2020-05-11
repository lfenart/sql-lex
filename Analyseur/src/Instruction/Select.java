package Instruction;

public class Select {

	private Table table;
	private Column[] columns;
	private Group group;
	private Where where;
	private Order orderValue;
	
	public Select(Table table, Column[] columns, Group group, Where where, Order orderValue) {
		this.table = table;
		this.columns = columns;
		this.group = group;
		this.where = where;
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
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Where getWhere() {
		return where;
	}
	public void setWhere(Where where) {
		this.where = where;
	}
	public Order getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(Order orderValue) {
		this.orderValue = orderValue;
	}
	

	
}
