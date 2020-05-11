package compiler;

import java.util.ArrayList;

import Instruction.Create;
import Instruction.DataType;
import Instruction.Node;
import Instruction.Operator;
import Instruction.Order;
import ui.SQLVisitor;
import ui.Type;

public class SQLFactory {

	public SQLVisitor visitor;
	public ArrayList<Node> variables;

	public SQLFactory() {
		visitor = new SQLVisitor();
		variables = new ArrayList<Node>();
	}

	public Node createNodeBoolean(boolean b) {
		return null;
	}

	public Node createNodeNull() {
		return null;
	}

	public Node createNodeNumeric(Node n) {
		return null;
	}

	public Node createNodeText(String str) {
		return null;
	}
	
	public Node createNodeBlock() {
		return null;
	}
	
	public Node createNodeColumn(String s) {
		return null;
	}
	
	public Node createNodeConcat(Node n1, Node n2) {
		Node n = new NodeConcat();
		n.getChildren().add(n1);
		n.getChildren().add(n2);
		return n;
	}

	public Node createNodeCreate(Node table, Node datas, Node primaryKey) {
		Node n = new NodeCreate();
		n.getChildren().add(table);
		n.getChildren().add(datas);
		n.getChildren().add(primaryKey);
		return n;
	}

	public Node createNodeData(Node column, Node type, Node nullable) {
		Node n = new NodeData();
		n.getChildren().add(column);
		n.getChildren().add(type);
		n.getChildren().add(nullable);
		return n;
	}

	public Node createNodeDelete(Node table, Node where) {
		Node n = new NodeDelete();
		n.getChildren().add(table);
		n.getChildren().add(where);
		return n;
	}

	public Node createNodeDiv(Node f, Node t) {
		Node n = new NodeDiv();
		n.getChildren().add(f);
		n.getChildren().add(t);
		return n;
	}

	public Node createNodeDrop(Node table) {
		Node n = new NodeDrop();
		n.getChildren().add(table);
		return n;
	}

	public Node createNodeGroupBy(Node c) {
		Node n = new NodeGroupBy();
		n.getChildren().add(c);
		return n;
	}

	public Node createNodeInsert(Node cols, Node vs) {
		Node n = new NodeInsert();
		
	}

	public Node createNodeMinus(Node s, Node f) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeMult(Node f, Node t) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeNot() {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeNumber(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeOperator(Operator greater) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeOrder(Order desc) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeOrderBy(Node c, Node o) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodePlus(Node s, Node f) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeSelect(Node cols, Node t, Node w, Node g, Node o) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeSet(Node c, Node v) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeTable(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeType(DataType i) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeType(DataType varchar, Integer n) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeUminus() {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeUpdate(Node t, Node w) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createNodeWhere(Node c) {
		// TODO Auto-generated method stub
		return null;
	}

	public Node createPrimaryKey(Node n) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}