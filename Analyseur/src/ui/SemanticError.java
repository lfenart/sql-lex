package ui;

public class SemanticError extends RuntimeException {

	private String message;

	public SemanticError(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}
