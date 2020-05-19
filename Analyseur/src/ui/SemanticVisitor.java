package ui;

import java.util.HashMap;
import java.util.Map;

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
import Instruction.NodeRoot;
import Instruction.NodeSelect;
import Instruction.NodeSelectExpression;
import Instruction.NodeSet;
import Instruction.NodeTable;
import Instruction.NodeTableAlias;
import Instruction.NodeTableExpression;
import Instruction.NodeText;
import Instruction.NodeType;
import Instruction.NodeUminus;
import Instruction.NodeUpdate;
import Instruction.NodeUplus;
import Instruction.NodeUsing;
import Instruction.NodeValues;
import Instruction.NodeWhere;
import Instruction.NodeWildcard;
import Objects.Table;

public class SemanticVisitor extends Visitor {

	private Map<String, Table> tables;
	private Table table;
	
	public SemanticVisitor() {
		this.tables = new HashMap<>();
		this.table = new Table();
	}

	@Override
	public void visitAs(NodeAs nodeAs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitBlock(NodeBlock nodeBlock) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitBoolean(NodeBoolean nodeBoolean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitColumn(NodeColumn nodeColumn) {
		// TODO Auto-generated method stub
		nodeColumn.getChildren().get(0).accept(this);
	}

	@Override
	public void visitColumnAlias(NodeColumnAlias nodeColumnAlias) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitColumnName(NodeColumnName nodeColumnName) {
		// TODO Auto-generated method stub
		System.out.println("Column name : " + ((NodeText) nodeColumnName.getChildren().get(0)).getValue());
	}

	@Override
	public void visitConcat(NodeConcat nodeConcat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitCreate(NodeCreate nodeCreate) {
		System.out.println("Creation");
		for(Node n : nodeCreate.getChildren())
			if(n!=null)
				n.accept(this);
		this.tables.put(this.table.getName(), table);
	}

	@Override
	public void visitData(NodeData nodeData) {
		// TODO Auto-generated method stub
		for(Node d : nodeData.getChildren())
			d.accept(this);

	}

	@Override
	public void visitDelete(NodeDelete nodeDelete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitDiv(NodeDiv nodeDiv) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitDouble(NodeDouble nodeDouble) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitDrop(NodeDrop nodeDrop) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitFrom(NodeFrom nodeFrom) {
		String tableName = ((NodeText) nodeFrom.getChildren().get(0).getChildren().get(0)).getValue();
		this.table = this.tables.get(tableName);
	}

	@Override
	public void visitFunction(NodeFunction nodeFunction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitGroup(NodeGroup nodeGroup) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitInsert(NodeInsert nodeInsert) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitInteger(NodeInteger nodeInteger) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitInto(NodeInto nodeInto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitJoin(NodeJoin nodeJoin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitMinus(NodeMinus nodeMinus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitMult(NodeMult nodeMult) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitNodeUplus(NodeUplus nodeUplus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitNodeValues(NodeValues nodeValues) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitNot(NodeNot nodeNot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitNull(NodeNull nodeNull) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitOn(NodeOn nodeOn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitOperator(NodeOperator nodeOperator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitOrder(NodeOrder nodeOrder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitOrderBy(NodeOrderBy nodeOrderBy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitPlus(NodePlus nodePlus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitPrimary(NodePrimaryKey nodePrimaryKey) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitRoot(NodeRoot nodeRoot) {
		this.visitNode(nodeRoot);
	}

	@Override
	public void visitSelect(NodeSelect nodeSelect) {
		System.out.println("Select");
		for(Node n : nodeSelect.getChildren())
			if(n!=null)
				n.accept(this);
		if (this.table == null) {
			System.out.println("Select Error");
		} else {
			System.out.println("Select Ok");
		}
	}

	@Override
	public void visitSelectExpression(NodeSelectExpression nodeSelectExpression) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitSet(NodeSet nodeSet) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitTable(NodeTable nodeTableName) {
		// TODO Auto-generated method stub
		this.table.setName(((NodeText) nodeTableName.getChildren().get(0)).getValue());
	}

	@Override
	public void visitTable(NodeTableExpression nodeTable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitTableAlias(NodeTableAlias nodeTableAlias) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitText(NodeText nodeText) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitType(NodeType nodeType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitUminus(NodeUminus nodeUminus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitUpdate(NodeUpdate nodeUpdate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitUsing(NodeUsing nodeUsing) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitWhere(NodeWhere nodeWhere) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitWildcard(NodeWildcard nodeWildcard) {
		// TODO Auto-generated method stub

	}

}
