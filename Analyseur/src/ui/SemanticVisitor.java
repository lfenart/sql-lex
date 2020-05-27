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

	private enum Type {
		TEXT, NUMBER
	}

	private Map<String, Table> tables;
	private Map<String, Table> currentTables = new HashMap<>();
	private Type type;

	public SemanticVisitor() {
		this.tables = new HashMap<>();
	}

	private void visitBinaryOperator(Node n) {
		n.getChildren().get(0).accept(this);
		Type type1 = this.type;
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
		if (n.getClass() == NodeSelect.class) {
			result = this.getNodeTable(n.getChildren().get(1));
		} else if (n.getClass() != NodeTable.class && n.getClass() != NodeTableExpression.class) {
			result = this.getNodeTable(n.getChildren().get(0));
		} else {
			result = n;
		}
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
		for (Node n : nodeBlock.getChildren()) {
			if (n != null) {
				n.accept(this);
			}
		}
	}

	@Override
	public void visitBoolean(NodeBoolean nodeBoolean) {
		this.type = Type.NUMBER;
	}

	@Override
	public void visitColumn(NodeColumn nodeColumn) {
		this.type = null;
		Node nodeColumnName = nodeColumn.getChildren().get(0);
		NodeText nodeText = (NodeText) nodeColumnName.getChildren().get(0);
		String columnName = nodeText.getValue();
		Node tableAlias = nodeColumn.getChildren().get(1);
		if (tableAlias == null) { // pas d'alias
			boolean occurence = false;
			for (Table t : this.currentTables.values()) {
				int index = t.getColumns().indexOf(new Column(columnName));
				if (index != -1) {
					if (occurence) {
						throw new SemanticError("Error: ambiguous column name: " + columnName);
					}
					occurence = true;
					Column column = t.getColumns().get(index);
					switch (column.getType()) {
					case "CHAR":
					case "VARCHAR":
						this.type = Type.TEXT;
						break;
					default:
						this.type = Type.NUMBER;
					}
				}
			}

		} else {
			Table t = this.currentTables.get(((NodeText) tableAlias.getChildren().get(0)).getValue());
			int index = t.getColumns().indexOf(new Column(columnName));
			if (index != -1) {
				Column column = t.getColumns().get(index);
				switch (column.getType()) {
				case "CHAR":
				case "VARCHAR":
					this.type = Type.TEXT;
					break;
				default:
					this.type = Type.NUMBER;
				}
			}
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

			// probablement � modifier
			if (n.getChildren().size() > 2) {
				c.setNotNull(true);
			}
			columns.add(c);
		}
		Table table = new Table(tableName, columns, null);
		for (Node n : nodeCreate.getChildren()) {
			if (n != null) {
				if (n.getClass() == NodePrimaryKey.class) {
					table.setPrimaryKey(
							((NodeText) n.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue());
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
		this.type = Type.NUMBER;
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
		List<Type> columnType = new ArrayList<>();
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
				columnType.add(Type.TEXT);
				break;
			default:
				columnType.add(Type.NUMBER);
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
		this.type = Type.NUMBER;
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
			e.printStackTrace();
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
		String tableAlias = tableName;
		Node nodeAs = nodeTable.getChildren().get(1);
		if (nodeAs != null) {
			Node nodeTableAlias = nodeAs.getChildren().get(0);
			NodeText nodeText = (NodeText) nodeTableAlias.getChildren().get(0);
			tableAlias = nodeText.getValue();
		}
		this.currentTables.put(tableAlias, table);
		Node nodeJoin = nodeTable.getChildren().get(2);
		if (nodeJoin != null) {
			nodeTable = nodeJoin.getChildren().get(0);
			nodeTableName = (NodeText) nodeTable.getChildren().get(0);
			tableName = nodeTableName.getValue();
			table = this.tables.get(tableName);
			if (table == null) {
				throw new SemanticError("Error: table not found: " + tableName);
			}
			tableAlias = tableName;
			nodeAs = nodeTable.getChildren().get(1);
			if (nodeAs != null) {
				Node nodeTableAlias = nodeAs.getChildren().get(0);
				NodeText nodeText = (NodeText) nodeTableAlias.getChildren().get(0);
				tableAlias = nodeText.getValue();
			}
			this.currentTables.put(tableAlias, table);
			Node nodeOn = nodeJoin.getChildren().get(1);
			if (nodeOn != null) {
				nodeOn.accept(this);
			}
		}
		if (nodeSelect.getChildren().get(0).getChildren().get(0) instanceof NodeWildcard) {
			NodeBlock block = new NodeBlock();
			List<NodeSelectExpression> columns = new ArrayList<NodeSelectExpression>();
			for (String key : this.currentTables.keySet()) {
				Table t = this.currentTables.get(key);
				for (int i = 0; i < t.getColumns().size(); i++) {
					NodeSelectExpression selectExpression = new NodeSelectExpression();
					NodeColumn column = new NodeColumn();
					NodeColumnName columnName = new NodeColumnName();
					NodeText text = new NodeText(t.getColumns().get(i).getName());
					columnName.getChildren().add(text);
					column.getChildren().add(columnName);
					text = new NodeText(key);
					Node alias = new NodeTableAlias();
					alias.getChildren().add(text);
					column.getChildren().add(alias);
					selectExpression.getChildren().add(column);
					columns.add(selectExpression);
				}
			}
			block.getChildren().addAll(columns);
			nodeSelect.getChildren().set(0, block);
			this.visitSelect(nodeSelect);
		} else if (nodeSelect.getChildren().get(0).getChildren().get(0).getChildren().get(0) instanceof NodeFunction) {
			boolean occurence = false;
			String columnName = ((NodeText) nodeSelect.getChildren().get(0).getChildren().get(0).getChildren().get(0)
					.getChildren().get(0).getChildren().get(0).getChildren().get(0)).getValue();
			for (Table t : this.currentTables.values()) {
				if (t.containsColumn(columnName)) {
					if (occurence) {
						throw new SemanticError("Error: ambiguous column name: " + columnName);
					}
					occurence = true;
				}
			}
			if (!occurence) {
				throw new SemanticError("Error: no such column: " + columnName);
			}
		} else {
			List<Node> selectExpressions = nodeSelect.getChildren().get(0).getChildren();
			for (Node expression : selectExpressions) {
				boolean occurence = false;
				Node column = expression.getChildren().get(0);
				String columnName = ((NodeText) column.getChildren().get(0).getChildren().get(0)).getValue();
				Node alias = column.getChildren().get(1);
				if (alias == null) {
					for (Table t : this.currentTables.values()) {
						if (t.containsColumn(columnName)) {
							if (occurence) { // On a d�j� trouv� la colonne dans une autre table
								throw new SemanticError("Error: ambiguous column name: " + columnName);
							}
							occurence = true;
						}
					}
				} else {
					Table t = this.currentTables.get(((NodeText) alias.getChildren().get(0)).getValue());
					if (t == null) {
						throw new SemanticError(
								"Error: no such table: " + ((NodeText) alias.getChildren().get(0)).getValue());
					}
					occurence = t.containsColumn(columnName);
				}
				if (!occurence) { // La colonne n'existe dans aucune des tables
					throw new SemanticError("Error: no such column: " + columnName);
				}
			}
			if (nodeSelect.getChildren().get(0).getChildren().get(0).getChildren().size() > 1) {
				if (nodeSelect.getChildren().get(0).getChildren().get(0).getChildren().get(1) instanceof NodeAs) {
					String columnAlias = (((NodeText) nodeSelect.getChildren().get(0).getChildren().get(0).getChildren()
							.get(1).getChildren().get(0).getChildren().get(0)).getValue());
					Node column = nodeSelect.getChildren().get(0).getChildren().get(0).getChildren().get(0);
					if (nodeSelect.getChildren().get(2) != null) {
						for (int i = 0; i < nodeSelect.getChildren().get(2).getChildren().get(0).getChildren()
								.size(); i++) {
							if (nodeSelect.getChildren().get(2).getChildren().get(0).getChildren()
									.get(i) instanceof NodeColumn) {
								nodeSelect.getChildren().get(2).getChildren().get(0).getChildren().set(i, column);
							}
						}
					}
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
		this.visitBinaryOperator(nodeSet);
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
		this.type = Type.TEXT;
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
