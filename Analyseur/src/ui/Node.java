package ui;

import java.util.ArrayList;

public class Node {
	private Type type;
	private String name;
	private int value;
	private ArrayList<Node> children;
	
	public Node() {
		children = new ArrayList<Node>();
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public ArrayList<Node> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	
	public void visit(Visitor v) {
		v.visitNode(this);
	}
	
}
