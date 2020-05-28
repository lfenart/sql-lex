package exception;

public class ColumnNotFoundException extends SemanticError {

	public ColumnNotFoundException(String column) {
		this.setMessage("Error: column not found: " + column);
	}

}
