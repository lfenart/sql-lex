package Instruction;

public class Drop extends Node {

	private Table table;

	public Drop(Table table) {
		this.setTable(table);
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

}
