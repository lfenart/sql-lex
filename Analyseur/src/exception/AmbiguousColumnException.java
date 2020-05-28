package exception;

public class AmbiguousColumnException extends SemanticError {

	public AmbiguousColumnException(String column) {
		this.setMessage("Error: ambiguous column name: " + column);
	}

}
