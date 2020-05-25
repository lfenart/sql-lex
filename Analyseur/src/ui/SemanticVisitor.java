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
	private Map<String, Table> currentTables = new HashMap<>();
	private String type;

	public SemanticVisitor() {
		this.tables = new HashMap<>();
	}

	private void visitBinaryOperator(Node n) {
		n.getChildren().get(0).accept(this);
		String type1 = this.type;
		n.getChildren().get(1).accept(this);
		if (!type1.equals(this.type)) {
			throw new SemanticError("Error: wrong type");
		}
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
		this.type = "Number";
	}

	@Override
	public void visitColumn(NodeColumn nodeColumn) {
		this.type = null;
		Node nodeColumnName = nodeColumn.getChildren().get(0);
		NodeText nodeText = (NodeText) nodeColumnName.getChildren().get(0);
		String columnName = nodeText.getValue();
		if (nodeColumn.getChildren().size() == 1) { // pas d'alias
			for (Table t : this.currentTables.values()) {
				int index = t.getColumns().indexOf(new Column(columnName));
				if (index != -1) {					
					Column column = t.getColumns().get(index);
					switch (column.getType()) {
					case "CHAR":
					case "VARCHAR":
						this.type = "Text";
						break;
					default:
						this.type = "Number";
					}
				}
				else 	throw new SemanticError("Error: ambiguous column name: " + columnName);
			}

		} else {
			// TODO
		}
	}

	@Override
	public void visitColumnAlias(NodeColumnAlias nodeColumnAlias) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitColumnName(NodeColumnName nodeColumnName) {
	}

	@Override
	public void visitConcat(NodeConcat nodeConcat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitCreate(NodeCreate nodeCreate) {
		Node nodeTable = nodeCreate.getChildren().get(0);
		NodeText nodeTableName = (NodeText) nodeTable.getChildren().get(0);
		String tableName = nodeTableName.getValue();
		if (tables.containsKey(tableName)) {
			throw new SemanticError("Error: table " + tableName + " already exists");
		}
		List<Column> columns = new ArrayList<Column>();
		List<Node> nodeColumns = nodeCreate.getChildren().get(1).getChildren();
		for (Node n : nodeColumns) {
			String columnName = ((NodeText) n.getChildren().get(0).getChildren().get(0).getChildren().get(0))
					.getValue();
			Column c = new Column(columnName);
			if (columns.contains(c)) {
				throw new SemanticError("Error: duplicate column name: " + c.getName());
			}
			NodeType nodeType = (NodeType) n.getChildren().get(1);
			c.setType(nodeType.getType().name());
			if (nodeType.getSize() != null) {
				c.setTypeSize(nodeType.getSize());
			}

			//probablement à modifier
			if (n.getChildren().size() > 2) {
				c.setNotNull(true);
			}
			columns.add(c);
		}
		Table table = new Table(tableName, columns, null);
		for(Node  n : nodeCreate.getChildren()){
			if(n!=null) {
				if(n.getClass()==NodePrimaryKey.class) {
					table.setPrimaryKey(((NodeText) n.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue());
				}
			}
		}
		this.tables.put(tableName, table);
	}

	@Override
	public void visitData(NodeData nodeData) {

	}

	@Override
	public void visitDelete(NodeDelete nodeDelete) {
		// TODO Auto-generated method stub
		this.testTable(this.getNodeTable(nodeDelete));
		this.visitNode(nodeDelete);

	}

	@Override
	public void visitDiv(NodeDiv nodeDiv) {
		this.visitBinaryOperator(nodeDiv);
	}

	@Override
	public void visitDouble(NodeDouble nodeDouble) {
		this.type = "Number";
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
		this.visitNode(nodeGroup);
	}

	@Override
	public void visitInsert(NodeInsert nodeInsert) {
		this.currentTables = new HashMap<String, Table>();
		String tableName = ((NodeText) nodeInsert.getChildren().get(0).getChildren().get(0).getChildren().get(0))
				.getValue();
		Table table = this.tables.get(tableName);
		List<Node> columns = nodeInsert.getChildren().get(1).getChildren();
		List<String> columnType = new ArrayList<>();
		for (Node column : columns) {
			Node nodeColumnName = column.getChildren().get(0);
			NodeText nodeText = (NodeText) nodeColumnName.getChildren().get(0);
			String columnName = nodeText.getValue();
			if (!table.containsColumn(columnName)) {
				throw new SemanticError("Error: no such column: " + columnName);
			}
			Column c = table.getColumns().get(table.getColumns().indexOf(new Column(columnName)));
			switch (c.getType()) {
			case "VARCHAR":
			case "CHAR":
				columnType.add("Text");
				break;
			default:
				columnType.add("Number");
			}
		}
		List<Node> values = nodeInsert.getChildren().get(2).getChildren().get(0).getChildren();
		if (columns.size() != values.size()) {
			throw new SemanticError("Error: " + values.size() + " values for " + columns.size() + " columns");
		}
		for (int i = 0; i < columns.size(); i++) {
			Node val = values.get(i);
			val.accept(this);
			if (!columnType.get(i).equals(this.type)) {
				throw new SemanticError("Error: wrong type");
			}
		}
	}

	@Override
	public void visitInteger(NodeInteger nodeInteger) {
		this.type = "Number";
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
		this.visitBinaryOperator(nodeMinus);
	}

	@Override
	public void visitMult(NodeMult nodeMult) {
		this.visitBinaryOperator(nodeMult);
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
		this.visitNode(nodeOn);
	}

	@Override
	public void visitOperator(NodeOperator nodeOperator) {
		this.visitBinaryOperator(nodeOperator);
	}

	@Override
	public void visitOrder(NodeOrder nodeOrder) {
		// TODO Auto-generated method stub
		this.visitNode(nodeOrder);
	}

	@Override
	public void visitOrderBy(NodeOrderBy nodeOrderBy) {
		// TODO Auto-generated method stub
		this.visitNode(nodeOrderBy);
	}

	@Override
	public void visitPlus(NodePlus nodePlus) {
		this.visitBinaryOperator(nodePlus);
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
		this.currentTables = new HashMap<String, Table>();
		Node nodeFrom = nodeSelect.getChildren().get(1);
		Node nodeTable = nodeFrom.getChildren().get(0);
		NodeText nodeTableName = (NodeText) nodeTable.getChildren().get(0);
		String tableName = nodeTableName.getValue();
		Table table = this.tables.get(tableName);
		if (table == null) {
			throw new SemanticError("Error: table not found: " + tableName);
		}
		// TODO: pas d'alias pour le moment
		String tableAlias = tableName;
		this.currentTables.put(tableAlias, table);
		Node nodeJoin = nodeTable.getChildren().get(1);
		if (nodeJoin != null) {
			nodeTable = nodeJoin.getChildren().get(0);
			nodeTableName = (NodeText) nodeTable.getChildren().get(0);
			tableName = nodeTableName.getValue();
			table = this.tables.get(tableName);
			if (table == null) {
				throw new SemanticError("Error: table not found: " + tableName);
			}
			// TODO: pas d'alias pour le moment
			tableAlias = tableName;
			this.currentTables.put(tableAlias, table);
			Node nodeOn = nodeJoin.getChildren().get(1);
			if (nodeOn != null) {
				nodeOn.accept(this);
			}
		}
		if (nodeSelect.getChildren().get(0).getChildren().get(0).getClass() == NodeWildcard.class) {
			NodeBlock block = new NodeBlock();
			List<NodeSelectExpression> columns = new ArrayList<NodeSelectExpression>();
			for (int i = 0; i < table.getColumns().size(); i++) {
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
		} else if (nodeSelect.getChildren().get(0).getChildren().get(0).getChildren().get(0)
				.getClass() == NodeFunction.class) {
			int occurences = 0;
			String columnName = ((NodeText) nodeSelect.getChildren().get(0).getChildren().get(0).getChildren().get(0)
					.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue();
			for (Table t : this.currentTables.values()) {
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
		} else {
			List<Node> selectExpressions = nodeSelect.getChildren().get(0).getChildren();
			for (Node expression : selectExpressions) {
				boolean occurence = false;
				String columnName = ((NodeText) expression.getChildren().get(0).getChildren().get(0).getChildren()
						.get(0)).getValue();
				for (Table t : this.currentTables.values()) {
					if (t.containsColumn(columnName)) {
						if (occurence) { // On a déjà trouvé la colonne dans une autre table
							throw new SemanticError("Error: ambiguous column name: " + columnName);
						}
						occurence = true;
					}
				}
				if (!occurence) { // La colonne n'existe dans aucune des tables
					throw new SemanticError("Error: no such column: " + columnName);
				}
			}
			
			for (int i = 2; i < nodeSelect.getChildren().size(); i++) {
				Node n = nodeSelect.getChildren().get(i);
				if (n != null) {
					n.accept(this);
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
		this.visitNode(nodeSet);
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
		this.type = "Text";
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
		this.testTable(this.getNodeTable(nodeUpdate));
		this.visitNode(nodeUpdate);
	}

	@Override
	public void visitUsing(NodeUsing nodeUsing) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitWhere(NodeWhere nodeWhere) {
		this.visitNode(nodeWhere);
	}

	@Override
	public void visitWildcard(NodeWildcard nodeWildcard) {
		// TODO Auto-generated method stub

	}

}
