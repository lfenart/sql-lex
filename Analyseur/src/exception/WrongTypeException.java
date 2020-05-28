package exception;

public class WrongTypeException extends SemanticError {

	public WrongTypeException() {
		this.setMessage("Error: wrong type");
	}

}
