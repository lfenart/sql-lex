package Instruction;

public class Update {
	private Table table;
	private String setValue;
	private Column whereColumn,setColumn;
	private String whereValue;
	
	public Update(Table table, String setvalue, Column whereColumn, Column setColumn, String whereValue) {
		this.table=table;
		this.setValue=setvalue;
		this.whereColumn=whereColumn;
		this.setColumn=setColumn;
		this.whereValue=whereValue;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public String getSetValue() {
		return setValue;
	}

	public void setSetValue(String setValue) {
		this.setValue = setValue;
	}

	public Column getWhereColumn() {
		return whereColumn;
	}

	public void setWhereColumn(Column whereColumn) {
		this.whereColumn = whereColumn;
	}

	public Column getSetColumn() {
		return setColumn;
	}

	public void setSetColumn(Column setColumn) {
		this.setColumn = setColumn;
	}

	public String getWhereValue() {
		return whereValue;
	}

	public void setWhereValue(String whereValue) {
		this.whereValue = whereValue;
	}
	
}
