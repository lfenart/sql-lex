package Instruction;

public class NodeDiv extends Node{

	private int numerator,denominator;

	public NodeDiv(int left, int right) {
		this.numerator = left;
		this.denominator = right;
	}

	public int getLeft() {
		return numerator;
	}

	public void setLeft(int left) {
		this.numerator = left;
	}

	public int getRight() {
		return denominator;
	}

	public void setRight(int right) {
		this.denominator = right;
	}
	
	public int result() {
		if(denominator!=0) return numerator/denominator;
		else return 0;
	}
	
}
