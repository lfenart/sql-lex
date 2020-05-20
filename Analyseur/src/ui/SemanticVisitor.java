package ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Instruction.DataType;
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
import Instruction.Operator;
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
		System.out.println("Delete");
		Table table = new Table();
		boolean t=false;
		if(this.tables.containsKey(((NodeText) nodeDelete.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue())) {
			t=true;
			table = this.tables.get(((NodeText) nodeDelete.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue());
		}
		if(t)System.out.println("Table Ok");
		else System.out.println("Table Error");
		
		boolean column=false;
		for(int i=0;i<table.getColumns().size();i++) {
			if(table.getColumns().get(i).getName().equalsIgnoreCase(((NodeText) nodeDelete.getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue()))
				column=true;
		}
		if(column)System.out.println("Column Ok");
		else System.out.println("Column Error");
		//ValueType
		boolean datatype=false;
		for(Column c : table.getColumns()) {
			if(c.getName().equalsIgnoreCase(((NodeText) nodeDelete.getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue()))
			{
				if(nodeDelete.getChildren().get(1).getChildren().get(0).getChildren().get(1).getClass()==NodeInteger.class
						&& c.getType().contains("INT")) {
					datatype=true;
				}
				if(nodeDelete.getChildren().get(1).getChildren().get(0).getChildren().get(1).getClass()==NodeText.class
						&& c.getType().contains("CHAR")) {
					datatype=true;
				}
				break;
			}
		}
		if(!datatype) System.out.println("Type erreur");
		else System.out.println("Type OK");
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
		System.out.println("Drop");
		boolean drop=false;
		if(this.tables.containsKey(((NodeText) nodeDrop.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue()))
			drop=true;
		if(drop)System.out.println("Table Ok");
		else System.out.println("Table Error");
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
		System.out.println("Insert");
		Table table = new Table();
		if(this.tables.containsKey(((NodeText) nodeInsert.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue())) {
			System.out.println("Table OK");
			table = this.tables.get(((NodeText) nodeInsert.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue());
		}
		else System.out.println("Table erreur");
		for(int i=0;i<nodeInsert.getChildren().get(1).getChildren().size();i++) {
			for(int j=0;j<table.getColumns().size();j++) {
				if(table.getColumns().get(j).getName().equalsIgnoreCase(((NodeText) nodeInsert.getChildren().get(1).getChildren().get(i).getChildren().get(0).getChildren().get(0)).getValue())) {
				}
				//else erreur
			}
		}	
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
		
		int nbSelectedColumn = nodeSelect.getChildren().get(0).getChildren().size();
		for(Node n : nodeSelect.getChildren().get(0).getChildren()) {
			for(Column c : table.getColumns()) {
				if(c.getName().equalsIgnoreCase(((NodeText) n.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue()))
					selectedColumns.add(c);	
			}
		}

		if(selectedColumns.size()==nbSelectedColumn) System.out.println("Column OK");
		else System.out.println("Column Error");
		boolean datatype=false;
		for(Column c : table.getColumns()) {
			if(c.getName().equalsIgnoreCase(((NodeText) nodeSelect.getChildren().get(2).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue()))
			{
				if(nodeSelect.getChildren().get(2).getChildren().get(0).getChildren().get(1).getClass()==NodeInteger.class
						&& c.getType().contains("INT")) {
					datatype=true;
				}
				if(nodeSelect.getChildren().get(2).getChildren().get(0).getChildren().get(1).getClass()==NodeText.class
						&& c.getType().contains("CHAR")) {
					datatype=true;
				}
				break;
			}
		}
		if(!datatype) System.out.println("Type erreur");
		else System.out.println("Type OK");
		
		int nbIteration=0;
		for(Map.Entry<String, Table> t : this.tables.entrySet()) {
			for(int j=0;j<selectedColumns.size();j++) {
				for(int k=0;k<t.getValue().getColumns().size();k++) {
					if(t.getValue().getColumns().get(k).getName().equalsIgnoreCase(selectedColumns.get(j).getName()))
						nbIteration++;
				}
			}
		}
		if(nbIteration>1) System.out.println("Select Columns Iteration erreur");
		else System.out.println("Select columns iteration OK");
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
		System.out.println("Update");
		//Table
		Table table = new Table();
		if(this.tables.containsKey(((NodeText) nodeUpdate.getChildren().get(0).getChildren().get(0)).getValue())) {
			System.out.println("Table OK");
			table = this.tables.get(((NodeText) nodeUpdate.getChildren().get(0).getChildren().get(0)).getValue());
		}
		else System.out.println("Table Error");
		////Set
		//Column
		boolean column=false;
		for(int i=0;i<table.getColumns().size();i++) {
			if(table.getColumns().get(i).getName().equalsIgnoreCase(((NodeText) nodeUpdate.getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue()))
				column=true;
		}
		if(column)System.out.println("Set Column Ok");
		else System.out.println("Set Column Error");
		//ValueType
		boolean datatype=false;
		for(Column c : table.getColumns()) {
			if(c.getName().equalsIgnoreCase(((NodeText) nodeUpdate.getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue()))
			{
				if(nodeUpdate.getChildren().get(1).getChildren().get(0).getChildren().get(1).getClass()==NodeInteger.class
						&& c.getType().contains("INT")) {
					datatype=true;
				}
				if(nodeUpdate.getChildren().get(1).getChildren().get(0).getChildren().get(1).getClass()==NodeText.class
						&& c.getType().contains("CHAR")) {
					datatype=true;
				}
				break;
			}
		}
		if(!datatype) System.out.println("Set Type erreur");
		else System.out.println("Set Type OK");
		
		////Where
		//Column
		column=false;
		for(int i=0;i<table.getColumns().size();i++) {
			if(table.getColumns().get(i).getName().equalsIgnoreCase(((NodeText) nodeUpdate.getChildren().get(2).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue()))
				column=true;
		}
		if(column)System.out.println("Where Column Ok");
		else System.out.println("Where Column Error");
		
		datatype=false;
		for(Column c : table.getColumns()) {
			if(c.getName().equalsIgnoreCase(((NodeText) nodeUpdate.getChildren().get(2).getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue()))
			{
				if(nodeUpdate.getChildren().get(2).getChildren().get(0).getChildren().get(1).getClass()==NodeInteger.class
						&& c.getType().contains("INT")) {
					datatype=true;
				}
				if(nodeUpdate.getChildren().get(2).getChildren().get(0).getChildren().get(1).getClass()==NodeText.class
						&& c.getType().contains("CHAR")) {
					datatype=true;
				}
				break;
			}
		}
		if(!datatype) System.out.println("Where Type erreur");
		else System.out.println("Where Type OK");
		
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
