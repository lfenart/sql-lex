package Instruction;

public class NodeMinus {

	private int left,right;

	public NodeMinus(int left, int right) {
		super();
		this.left = left;
		this.right = right;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int result() {
		return this.left-this.right;
	}
	
}
