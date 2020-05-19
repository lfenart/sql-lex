package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import Objects.Column;
import Objects.Table;

public class SemanticVisitor extends Visitor {

	private Map<String, Table> tables;
	
	public SemanticVisitor() {
		this.tables = new HashMap<>();
	}

	@Override
	public void visitAs(NodeAs nodeAs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitBlock(NodeBlock nodeBlock) {
		// TODO Auto-generated method stub
		for(Node n : nodeBlock.getChildren())
			if(n!=null)
				n.accept(this);
	}

	@Override
	public void visitBoolean(NodeBoolean nodeBoolean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitColumn(NodeColumn nodeColumn) {
		// TODO Auto-generated method stub
		for(Node n : nodeColumn.getChildren())
			if(n!=null)
				n.accept(this);
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
		List<Column> columns = new ArrayList<Column>();
		Table table = new Table();
		//Columns
		for(Node n : nodeCreate.getChildren().get(1).getChildren()) {
			Column c = new Column();
			//Noms
			c.setName(((NodeText) n.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue());
			//Type
			c.setType(((NodeType) n.getChildren().get(1)).getType().name());
			if(((NodeType) n.getChildren().get(1)).getSize()!=null) {
				c.setTypeSize(((NodeType) n.getChildren().get(1)).getSize());
			}
			//NotNull
			if(n.getChildren().size()>2) {
				c.setNotNull(true);
			}
			columns.add(c);
		}
		//PrimaryKey
		if(nodeCreate.getChildren().size()>2) {
			table.setPrimaryKey(((NodeText) nodeCreate.getChildren().get(2).getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue());
		}
		//Table
		table.setName(((NodeText) nodeCreate.getChildren().get(0).getChildren().get(0)).getValue());
		table.setColumns(columns);
		this.tables.put(table.getName(), table);
	}

	@Override
	public void visitData(NodeData nodeData) {
		// TODO Auto-generated method stub
		for(Node d : nodeData.getChildren())
			if(d!=null)
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
		//this.table = this.tables.get(tableName);
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
		System.out.println("not null");
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
		System.out.println("Primary key : ");
		nodePrimaryKey.getChildren().get(0).accept(this);
	}

	@Override
	public void visitRoot(NodeRoot nodeRoot) {
		this.visitNode(nodeRoot);
	}

	@Override
	public void visitSelect(NodeSelect nodeSelect) {
		System.out.println("Select");
		Table table = new Table();
		List<Column> selectedColumns = new ArrayList<Column>();
		if (this.tables.containsKey(((NodeText) nodeSelect.getChildren().get(1).getChildren().get(0).getChildren().get(0)).getValue())) {
			System.out.println("Table OK");
			table=this.tables.get(((NodeText) nodeSelect.getChildren().get(1).getChildren().get(0).getChildren().get(0)).getValue());
		} else {
			System.out.println("Table Error");
		}
		
		int inColumn = 0;
		int nbSelectedColumn = nodeSelect.getChildren().get(0).getChildren().size();
		for(Column c : table.getColumns())
			if(c.getName().equalsIgnoreCase(((NodeText) nodeSelect.getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue())) {
				inColumn++;
				selectedColumns.add(c);
				continue;
			}
		if(inColumn==nbSelectedColumn) System.out.println("Column OK");
		else System.out.println("Column Error");
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
		System.out.println("nom de la table : " + ((NodeText) nodeTableName.getChildren().get(0)).getValue());
		//this.table.setName(((NodeText) nodeTableName.getChildren().get(0)).getValue());
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
		System.out.println("Column Type : " + nodeType.getType());
		if(nodeType.getSize()!=null)
			System.out.println("Size : " + nodeType.getSize());
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
