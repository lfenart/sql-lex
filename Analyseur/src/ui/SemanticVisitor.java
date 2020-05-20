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

	public Table testTable(Node n) {
		Table table = null;
		if (this.tables.containsKey(((NodeText) n.getChildren().get(0)).getValue())) {
			table = this.tables.get(((NodeText) n.getChildren().get(0)).getValue());
		}
		if (table == null)
			throw new SemanticError("Error: table not found: " + ((NodeText) n.getChildren().get(0)).getValue());
		return table;
	}

	public Node getNodeTable(Node n) {
		Node result = null;
		if (n.getClass() == NodeSelect.class)
			result = this.getNodeTable(n.getChildren().get(1));
		else if (n.getClass() != NodeTable.class && n.getClass() != NodeTableExpression.class)
			result = this.getNodeTable(n.getChildren().get(0));
		else
			result = n;
		return result;
	}

	public void testColumnInTable(Table table, String column) {
		if (!table.containsColumn(column)) {
			throw new SemanticError("Error: no such column: " + column);
		}
	}

	public String getColumnName(Node n) {
		String result = null;
		if (n.getClass() == NodeDelete.class)
			result = this.getColumnName(n.getChildren().get(1));
		else if (n.getClass() == NodeText.class)
			result = ((NodeText) n).getValue();
		else
			result = this.getColumnName(n.getChildren().get(0));
		return result;
	}

	@Override
	public void visitAs(NodeAs nodeAs) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitBlock(NodeBlock nodeBlock) {
		// TODO Auto-generated method stub
		for (Node n : nodeBlock.getChildren())
			if (n != null)
				n.accept(this);
	}

	@Override
	public void visitBoolean(NodeBoolean nodeBoolean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitColumn(NodeColumn nodeColumn) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitColumnAlias(NodeColumnAlias nodeColumnAlias) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitColumnName(NodeColumnName nodeColumnName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitConcat(NodeConcat nodeConcat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitCreate(NodeCreate nodeCreate) {
		List<Column> columns = new ArrayList<Column>();
		Table table = new Table();
		for (Node n : nodeCreate.getChildren().get(1).getChildren()) {
			Column c = new Column();
			c.setName(((NodeText) n.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue());
			c.setType(((NodeType) n.getChildren().get(1)).getType().name());
			if (((NodeType) n.getChildren().get(1)).getSize() != null) {
				c.setTypeSize(((NodeType) n.getChildren().get(1)).getSize());
			}
			if (n.getChildren().size() > 2) {
				c.setNotNull(true);
			}
			if (columns.contains(c)) {
				throw new SemanticError("Error: duplicate column name: " + c.getName());
			} else {
				columns.add(c);
			}
		}
		table.setName(((NodeText) nodeCreate.getChildren().get(0).getChildren().get(0)).getValue());
		table.setColumns(columns);
		this.tables.put(table.getName(), table);
	}

	@Override
	public void visitData(NodeData nodeData) {

	}

	@Override
	public void visitDelete(NodeDelete nodeDelete) {
		// TODO Auto-generated method stub
		Table table = this.testTable(this.getNodeTable(nodeDelete));
		this.testColumnInTable(table, this.getColumnName(nodeDelete));
		boolean datatype = false;
		for (Column c : table.getColumns()) {
			if (c.getName().equalsIgnoreCase(((NodeText) nodeDelete.getChildren().get(1).getChildren().get(0)
					.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue())) {
				if (nodeDelete.getChildren().get(1).getChildren().get(0).getChildren().get(0).getChildren().get(0)
						.getChildren().get(0).getClass() == NodeText.class && c.getType().contains("CHAR")) {
					datatype = true;
				}
				if (nodeDelete.getChildren().get(1).getChildren().get(0).getChildren().get(1)
						.getClass() == NodeInteger.class && c.getType().contains("INT")) {
					datatype = true;
				}
				break;
			}
		}
		if (!datatype)
			throw new SemanticError("Type error : "+ ((NodeText) nodeDelete.getChildren().get(1).getChildren().get(0) 
										.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue());

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
		String tableName = ((NodeText) nodeInsert.getChildren().get(0).getChildren().get(0).getChildren().get(0))
				.getValue();
		Table table = this.tables.get(tableName);
		List<Node> columns = nodeInsert.getChildren().get(1).getChildren();
		for (Node column : columns) {
			String columnName = ((NodeText) column.getChildren().get(0).getChildren().get(0)).getValue();
			if (!table.containsColumn(columnName)) {
				throw new SemanticError("Error: no such column: " + columnName);
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
		try {
			this.visitNode(nodeRoot);
		} catch (SemanticError e) {
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void visitSelect(NodeSelect nodeSelect) {
		Node nodeFrom = nodeSelect.getChildren().get(1);
		Node nodeTable = nodeFrom.getChildren().get(0);
		NodeText nodeTableName = (NodeText) nodeTable.getChildren().get(0);
		String tableName = nodeTableName.getValue();
		Table table = this.tables.get(tableName);
		if (table == null) {
			throw new SemanticError("Error: table not found: " + tableName);
		}
		List<Table> tables = new ArrayList<>();
		tables.add(table);
		Node nodeJoin = nodeTable.getChildren().get(1);
		if (nodeJoin != null) {
			nodeTable = nodeJoin.getChildren().get(0);
			nodeTableName = (NodeText) nodeTable.getChildren().get(0);
			tableName = nodeTableName.getValue();
			table = this.tables.get(tableName);
			if (table == null) {
				throw new SemanticError("Error: table not found: " + tableName);
			}
			tables.add(table);
		}
		if(nodeSelect.getChildren().get(0).getChildren().get(0).getClass()==NodeWildcard.class) {
			NodeBlock block = new NodeBlock();
			List<NodeSelectExpression> columns = new ArrayList<NodeSelectExpression>();
			for(int i=0;i<table.getColumns().size();i++) {
				NodeSelectExpression selectExpression = new NodeSelectExpression();
				NodeColumn column = new NodeColumn();
				NodeColumnName columnName = new NodeColumnName();
				NodeText text = new NodeText(table.getColumns().get(i).getName()); 
				columnName.getChildren().add(text);
				column.getChildren().add(columnName);
				selectExpression.getChildren().add(column);
				columns.add(selectExpression);
			}
			block.getChildren().addAll(columns);
			nodeSelect.getChildren().set(0, block);
			this.visitSelect(nodeSelect);
		}
		else if(nodeSelect.getChildren().get(0).getChildren().get(0).getChildren().get(0).getClass()==NodeFunction.class) {
			int occurences=0;
			String columnName = ((NodeText) nodeSelect.getChildren().get(0).getChildren().get(0).getChildren().get(0)
											.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue();
			for(Table t : tables) {
				if(t.containsColumn(columnName)) {
					occurences++;
				}
			}
			if (occurences == 0) {
				throw new SemanticError("Error: no such column: " + columnName);
			}
			if (occurences > 1) {
				throw new SemanticError("Error: ambiguous column name: " + columnName);
			}
		}
		else {
			List<Node> selectExpressions = nodeSelect.getChildren().get(0).getChildren();
			for (Node expression : selectExpressions) {
				int occurences=0;
				String columnName = ((NodeText) expression.getChildren().get(0).getChildren().get(0).getChildren().get(0))
						.getValue();
				for (Table t : tables) {
					if (t.containsColumn(columnName)) {
						occurences++;
					}
				}
				if (occurences == 0) {
					throw new SemanticError("Error: no such column: " + columnName);
				}
				if (occurences > 1) {
					throw new SemanticError("Error: ambiguous column name: " + columnName);
				}
			}
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
	}

	@Override
	public void visitUminus(NodeUminus nodeUminus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitUpdate(NodeUpdate nodeUpdate) {
		// TODO Auto-generated method stub
		Table table = this.testTable(this.getNodeTable(nodeUpdate));
		boolean column = false;
		for (int i = 0; i < table.getColumns().size(); i++) {
			if (table.getColumns().get(i).getName().equalsIgnoreCase(((NodeText) nodeUpdate.getChildren().get(1)
					.getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue()))
				column = true;
		}
		if (!column)
			throw new SemanticError("Error: no such column: " + ((NodeText) nodeUpdate.getChildren().get(1)
					.getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue());
		boolean datatype = false;
		for (Column c : table.getColumns()) {
			if (c.getName().equalsIgnoreCase(((NodeText) nodeUpdate.getChildren().get(1).getChildren().get(0)
					.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue())) {
				if (nodeUpdate.getChildren().get(1).getChildren().get(0).getChildren().get(1)
						.getClass() == NodeInteger.class && c.getType().contains("INT")) {
					datatype = true;
				}
				if (nodeUpdate.getChildren().get(1).getChildren().get(0).getChildren().get(1)
						.getClass() == NodeText.class && c.getType().contains("CHAR")) {
					datatype = true;
				}
				break;
			}
		}
		if (!datatype)
			throw new SemanticError("Type error : "+ ((NodeText) nodeUpdate.getChildren().get(1).getChildren().get(0) 
										.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue());

		column = false;
		for (int i = 0; i < table.getColumns().size(); i++) {
			if (table.getColumns().get(i).getName().equalsIgnoreCase(((NodeText) nodeUpdate.getChildren().get(2)
					.getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue()))
				column = true;
		}
		if (!column)
			throw new SemanticError("Error: no such column: " + ((NodeText) nodeUpdate.getChildren().get(1)
					.getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue());

		datatype = false;
		for (Column c : table.getColumns()) {
			if (c.getName().equalsIgnoreCase(((NodeText) nodeUpdate.getChildren().get(2).getChildren().get(0)
					.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue())) {
				if (nodeUpdate.getChildren().get(2).getChildren().get(0).getChildren().get(1)
						.getClass() == NodeInteger.class && c.getType().contains("INT")) {
					datatype = true;
				}
				if (nodeUpdate.getChildren().get(2).getChildren().get(0).getChildren().get(1)
						.getClass() == NodeText.class && c.getType().contains("CHAR")) {
					datatype = true;
				}
				break;
			}
		}
		if (!datatype)
			throw new SemanticError("Type error : "+ ((NodeText) nodeUpdate.getChildren().get(1).getChildren().get(0) 
										.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue());

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
