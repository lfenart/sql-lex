package compiler;

import Instruction.DataType;
import Instruction.JoinType;
import Instruction.Node;
import Instruction.NodeAs;
import Instruction.NodeBlock;
import Instruction.NodeBoolean;
import Instruction.NodeColumn;
import Instruction.NodeColumnAlias;
import Instruction.NodeColumnName;
import Instruction.NodeConcat;
import Instruction.NodeCreate;
import Instruction.NodeData;
import Instruction.NodeDelete;
import Instruction.NodeDiv;
import Instruction.NodeDouble;
import Instruction.NodeDrop;
import Instruction.NodeFrom;
import Instruction.NodeFunction;
import Instruction.NodeGroup;
import Instruction.NodeInsert;
import Instruction.NodeInteger;
import Instruction.NodeInto;
import Instruction.NodeJoin;
import Instruction.NodeMinus;
import Instruction.NodeMult;
import Instruction.NodeNot;
import Instruction.NodeNull;
import Instruction.NodeOn;
import Instruction.NodeOperator;
import Instruction.NodeOrder;
import Instruction.NodeOrderBy;
import Instruction.NodePlus;
import Instruction.NodePrimaryKey;
import Instruction.NodeSelect;
import Instruction.NodeSelectExpression;
import Instruction.NodeSet;
import Instruction.NodeTableAlias;
import Instruction.NodeTableExpression;
import Instruction.NodeTable;
import Instruction.NodeText;
import Instruction.NodeType;
import Instruction.NodeUminus;
import Instruction.NodeUpdate;
import Instruction.NodeUplus;
import Instruction.NodeUsing;
import Instruction.NodeValues;
import Instruction.NodeWhere;
import Instruction.NodeWildcard;
import Instruction.Operator;
import Instruction.Order;

public class SQLFactory {

	public Node createNodeAs(Node alias) {
		Node n = new NodeAs();
		n.getChildren().add(alias);
		return n;
	}

	public Node createNodeBlock() {
		return new NodeBlock();
	}

	public Node createNodeBoolean(boolean b) {
		return new NodeBoolean(b);
	}

	public Node createNodeColumn(Node name) {
		Node n = new NodeColumn();
		n.getChildren().add(name);
		return n;
	}

	public Node createNodeColumn(Node alias, Node name) {
		Node n = new NodeColumn();
		n.getChildren().add(alias);
		n.getChildren().add(name);
		return n;
	}

	public Node createNodeColumnAlias(Node name) {
		Node n = new NodeColumnAlias();
		n.getChildren().add(name);
		return n;
	}

	public Node createNodeColumnName(Node name) {
		Node n = new NodeColumnName();
		n.getChildren().add(name);
		return n;
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
		if (nullable != null)
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

	public Node createNodeDouble(Double n) {
		return new NodeDouble(n);
	}

	public Node createNodeDrop(Node table) {
		Node n = new NodeDrop();
		n.getChildren().add(table);
		return n;
	}

	public Node createNodeFrom(Node table,Node as) {
		Node n = new NodeFrom();
		n.getChildren().add(table);
		n.getChildren().add(as);
		return n;
	}

	public Node createNodeFunction(String name) {
		Node n = new NodeFunction(name);
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

	public Node createNodeInteger(Long n) {
		return new NodeInteger(n);
	}

	public Node createNodeInto(Node table) {
		Node n = new NodeInto();
		n.getChildren().add(table);
		return n;
	}

	public Node createNodeJoin(JoinType joinType, Node expression, Node specification) {
		Node n = new NodeJoin(joinType);
		n.getChildren().add(expression);
		n.getChildren().add(specification);
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

	public Node createNodeNull() {
		return new NodeNull();
	}

	public Node createNodeOn(Node on) {
		Node n = new NodeOn();
		n.getChildren().add(on);
		return n;
	}

	public Node createNodeOperator(Operator operator) {
		return new NodeOperator(operator);
	}

	public Node createNodeOrder(Order order) {
		return new NodeOrder(order);
	}

	public Node createNodeOrderBy(Node expression, Node order, Node nulls) {
		Node n = new NodeOrderBy();
		n.getChildren().add(expression);
		n.getChildren().add(order);
		n.getChildren().add(nulls);
		return n;
	}

	public Node createNodePlus(Node s, Node f) {
		Node n = new NodePlus();
		n.getChildren().add(s);
		n.getChildren().add(f);
		return n;
	}

	public Node createNodePrimaryKey(Node primaryKey) {
		Node n = new NodePrimaryKey();
		n.getChildren().add(primaryKey);
		return n;
	}

	public Node createNodeSelect(Node select, Node table, Node where, Node group, Node order) {
		Node n = new NodeSelect();
		n.getChildren().add(select);
		n.getChildren().add(table);
		n.getChildren().add(where);
		n.getChildren().add(group);
		n.getChildren().add(order);
		return n;
	}

	public Node createNodeSelectExpression(Node expression, Node as) {
		Node n = new NodeSelectExpression();
		n.getChildren().add(expression);
		n.getChildren().add(as);
		return n;
	}

	public Node createNodeSet(Node c, Node v) {
		Node n = new NodeSet();
		n.getChildren().add(c);
		n.getChildren().add(v);
		return n;
	}

	public Node createNodeTable(Node name) {
		Node n = new NodeTable();
		n.getChildren().add(name);
		return n;
	}

	public Node createNodeTableAlias(Node alias) {
		Node n = new NodeTableAlias();
		n.getChildren().add(alias);
		return n;
	}

	public Node createNodeTableExpression(String name, Node join) {
		Node n = new NodeTableExpression();
		n.getChildren().add(new NodeText(name));
		n.getChildren().add(join);
		
		return n;
	}

	public Node createNodeText(String str) {
		return new NodeText(str);
	}

	public Node createNodeType(DataType type) {
		return new NodeType(type);
	}

	public Node createNodeType(DataType type, Long size) {
		return new NodeType(type, size);
	}

	public Node createNodeUminus(Node term) {
		Node n = new NodeUminus();
		n.getChildren().add(term);
		return n;
	}

	public Node createNodeUpdate(Node table, Node sets, Node where) {
		Node n = new NodeUpdate();
		n.getChildren().add(table);
		n.getChildren().add(sets);
		n.getChildren().add(where);
		return n;
	}

	public Node createNodeUplus(Node term) {
		Node n = new NodeUplus();
		n.getChildren().add(term);
		return n;
	}

	public Node createNodeUsing(Node using) {
		Node n = new NodeUsing();
		n.getChildren().add(using);
		return n;
	}

	public Node createNodeValues(Node expressions) {
		Node n = new NodeValues();
		n.getChildren().add(expressions);
		return n;
	}

	public Node createNodeWhere(Node expression) {
		Node n = new NodeWhere();
		n.getChildren().add(expression);
		return n;
	}

	public Node createNodeWildcard() {
		return new NodeWildcard();
	}

}