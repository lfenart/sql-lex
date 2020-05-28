package exception;

public class DuplicateColumnException extends SemanticError {

	public DuplicateColumnException(String column) {
		this.setMessage("Error: duplicate column name: " + column);
	}

}
