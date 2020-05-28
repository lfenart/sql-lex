package exception;

public abstract class SemanticError extends RuntimeException {

	private String message;

	protected void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}

}
