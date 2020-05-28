package exception;

public class DuplicateTableException extends SemanticError {

	public DuplicateTableException(String table) {
		this.setMessage("Error: table " + table + " already exists");
	}

}
