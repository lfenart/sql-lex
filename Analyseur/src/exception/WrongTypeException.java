package exception;

public class WrongTypeException extends SemanticError {

	public WrongTypeException(String expectedType, String actualType) {
		this.setMessage("Error: expected type " + expectedType + ", got type " + actualType);
	}

}
