package exception;

public class TableNotFoundException extends SemanticError {

	public TableNotFoundException(String table) {
		this.setMessage("Error: table not found: " + table);
	}

}
