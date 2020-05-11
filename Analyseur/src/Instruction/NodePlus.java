package Instruction;

public class NodePlus extends Node{

	private int left,right;

	public NodePlus(int left, int right) {
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
		return this.left+this.right;
	}
	
}
