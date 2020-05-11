package compiler;

import java.util.ArrayList;

import Instruction.DataType;
import Instruction.Node;
import Instruction.NodeBlock;
import Instruction.NodeBoolean;
import Instruction.NodeColumn;
import Instruction.NodeConcat;
import Instruction.NodeCreate;
import Instruction.NodeData;
import Instruction.NodeDelete;
import Instruction.NodeDiv;
import Instruction.NodeDouble;
import Instruction.NodeDrop;
import Instruction.NodeGroup;
import Instruction.NodeInsert;
import Instruction.NodeInteger;
import Instruction.NodeMinus;
import Instruction.NodeMult;
import Instruction.NodeNot;
import Instruction.NodeNull;
import Instruction.NodeNumeric;
import Instruction.NodeOperator;
import Instruction.NodeOrder;
import Instruction.NodeOrderBy;
import Instruction.NodeOrderExpression;
import Instruction.NodePlus;
import Instruction.NodePrimaryKey;
import Instruction.NodeSelect;
import Instruction.NodeSet;
import Instruction.NodeTable;
import Instruction.NodeText;
import Instruction.NodeType;
import Instruction.NodeUminus;
import Instruction.NodeUpdate;
import Instruction.NodeWhere;
import Instruction.Operator;
import Instruction.Order;
import ui.SQLVisitor;

public class SQLFactory {

	public SQLVisitor visitor;
	public ArrayList<Node> variables;

	public SQLFactory() {
		visitor = new SQLVisitor();
		variables = new ArrayList<Node>();
	}

	public Node createNodeBoolean(boolean b) {
		return new NodeBoolean(b);
	}

	public Node createNodeNull() {
		return new NodeNull();
	}

	public Node createNodeText(String str) {
		return new NodeText(str);
	}

	public Node createNodeBlock() {
		return new NodeBlock();
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

	public Node createNodeGroup(Node c) {
		Node n = new NodeGroup();
		n.getChildren().add(c);
		return n;
	}

	public Node createNodeInsert(Node table, Node cols, Node terms) {
		Node n = new NodeInsert();
		n.getChildren().add(table);
		n.getChildren().add(cols);
		n.getChildren().add(terms);
		return n;
	}

	public Node createNodeMinus(Node s, Node f) {
		Node n = new NodeMinus();
		n.getChildren().add(s);
		n.getChildren().add(f);
		return n;
	}

	public Node createNodeMult(Node f, Node t) {
		Node n = new NodeMult();
		n.getChildren().add(f);
		n.getChildren().add(t);
		return n;
	}

	public Node createNodeNot() {
		return new NodeNot();
	}

	public Node createNodeOperator(Operator operator) {
		return new NodeOperator(operator);
	}

	public Node createNodeOrder(Order order) {
		return new NodeOrder(order);
	}

	public Node createNodeOrderBy(Node orders) {
		Node n = new NodeOrderBy();
		n.getChildren().add(orders);
		return n;
	}

	public Node createNodePlus(Node s, Node f) {
		Node n = new NodePlus();
		n.getChildren().add(s);
		n.getChildren().add(f);
		return n;
	}

	public Node createNodeSelect(Node selectExpression, Node table, Node where, Node group, Node order) {
		Node n = new NodeSelect();
		n.getChildren().add(selectExpression);
		n.getChildren().add(table);
		n.getChildren().add(where);
		n.getChildren().add(group);
		n.getChildren().add(order);
		return n;
	}

	public Node createNodeSet(Node c, Node v) {
		Node n = new NodeSet();
		n.getChildren().add(c);
		n.getChildren().add(v);
		return n;
	}

	public Node createNodeTable(String name) {
		Node n = new NodeTable();
		n.getChildren().add(new NodeText(name));
		return n;
	}

	public Node createNodeType(DataType type) {
		return new NodeType(type);
	}

	public Node createNodeType(DataType type, Long size) {
		return new NodeType(type, size);
	}

	public Node createNodeUminus() {
		return new NodeUminus();
	}

	public Node createNodeUpdate(Node table, Node sets, Node where) {
		Node n = new NodeUpdate();
		n.getChildren().add(table);
		n.getChildren().add(sets);
		n.getChildren().add(where);
		return n;
	}

	public Node createNodeWhere(Node expression) {
		Node n = new NodeWhere();
		n.getChildren().add(expression);
		return n;
	}

	public Node createNodePrimaryKey(Node primaryKey) {
		Node n = new NodePrimaryKey();
		n.getChildren().add(primaryKey);
		return n;
	}

	public Node createNodeColumn(String name) {
		Node n = new NodeColumn();
		n.getChildren().add(new NodeText(name));
		return n;
	}

	public Node createNodeOrderExpression(Node expression, Node order, Node nulls) {
		Node n = new NodeOrderExpression();
		n.getChildren().add(expression);
		n.getChildren().add(order);
		n.getChildren().add(nulls);
		return n;
	}

	public Node createNodeDouble(Double n) {
		return new NodeDouble(n);
	}

	public Node createNodeInteger(Long n) {
		return new NodeInteger(n);
	}

}