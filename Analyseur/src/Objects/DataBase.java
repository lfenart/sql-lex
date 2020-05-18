package Objects;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

	private List<Table> tables;

	public DataBase() {
		this.tables = new ArrayList<Table>();
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTables(List<Table> tables) {
		this.tables = tables;
	}
	
	public void addTable(Table table) {
		this.tables.add(table);
	}
	
	public void removeTable(Table table) {
		this.tables.remove(table);
	}
}
