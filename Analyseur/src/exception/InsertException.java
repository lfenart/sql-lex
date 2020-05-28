package exception;

public class InsertException extends SemanticError {

	public InsertException(int valuesSize, int columnsSize) {
		this.setMessage("Error: " + valuesSize + " values for " + columnsSize + " columns");
	}

}
