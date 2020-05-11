package Instruction;

public class Update extends Node {

	private Table table;
	private Where where;
	private Set set;
	private String whereValue;

	public Update(Table table, Where where, Set set, String whereValue) {
		this.table = table;
		this.where = where;
		this.set = set;
		this.whereValue = whereValue;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Where getWhere() {
		return where;
	}

	public void setWhere(Where where) {
		this.where = where;
	}

	public Set getSet() {
		return set;
	}

	public void setSet(Set set) {
		this.set = set;
	}

	public String getWhereValue() {
		return whereValue;
	}

	public void setWhereValue(String whereValue) {
		this.whereValue = whereValue;
	}

}
