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
import exception.AmbiguousColumnException;
import exception.ColumnNotFoundException;
import exception.DuplicateColumnException;
import exception.DuplicateTableException;
import exception.InsertException;
import exception.SemanticError;
import exception.TableNotFoundException;
import exception.WrongTypeException;

public class SemanticVisitor extends Visitor {

	private enum Type {
		TEXT, NUMBER
	}

	private Map<String, Table> tables = new HashMap<>();
	private Map<String, Table> currentTables;
	private Map<String, Type> currentColumnAliases;
	private Type type;

	private void visitBinaryOperator(Node n) {
		n.getChildren().get(0).accept(this);
		Type type1 = this.type;
		n.getChildren().get(1).accept(this);
		if (!type1.equals(this.type)) {
			throw new WrongTypeException(type1.toString(), this.type.toString());
		}
	}

	public Table testTable(Node n) {
		String tableName = ((NodeText) n.getChildren().get(0)).getValue();
		Table table = this.tables.get(tableName);
		if (table == null) {
			throw new TableNotFoundException(tableName);
		}
		return table;
	}

	public String getName(Node n) {
		return ((NodeText) n.getChildren().get(0).getChildren().get(0)).getValue();
	}

	@Override
	public void visitAs(NodeAs nodeAs) {
	}

	@Override
	public void visitBlock(NodeBlock nodeBlock) {
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
		String columnName = this.getName(nodeColumn);
		Node tableAlias = nodeColumn.getChildren().get(1);
		if (tableAlias == null) { // pas d'alias
			boolean occurence = false;
			for (Table t : this.currentTables.values()) {
				int index = t.getColumns().indexOf(new Column(columnName));
				if (index != -1) {
					if (occurence) {
						throw new AmbiguousColumnException(columnName);
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
			if (!occurence) {
				this.type = this.currentColumnAliases.get(columnName);
				if (this.type == null) {
					throw new ColumnNotFoundException(columnName);
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
			} else {
				throw new ColumnNotFoundException(columnName);
			}
		}
	}

	@Override
	public void visitColumnAlias(NodeColumnAlias nodeColumnAlias) {
	}

	@Override
	public void visitColumnName(NodeColumnName nodeColumnName) {
	}

	@Override
	public void visitConcat(NodeConcat nodeConcat) {
	}

	@Override
	public void visitCreate(NodeCreate nodeCreate) {
		String tableName = this.getName(nodeCreate);
		if (tables.containsKey(tableName)) {
			throw new DuplicateTableException(tableName);
		}
		List<Column> columns = new ArrayList<Column>();
		List<Node> nodeColumns = nodeCreate.getChildren().get(1).getChildren();
		for (Node n : nodeColumns) {
			String columnName = this.getName(n.getChildren().get(0));
			Column c = new Column(columnName);
			if (columns.contains(c)) {
				throw new DuplicateColumnException(columnName);
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
		boolean verifiedPrimaryKey = false;
		Node primaryKey = null;
		String primaryKeyValue = "";
		for (Node n : nodeCreate.getChildren()) {
			if (n != null) {
				if (n.getClass() == NodePrimaryKey.class) {
					primaryKey = n;
				}
			}
		}
		if (primaryKey != null) {
			primaryKeyValue = this.getName(primaryKey.getChildren().get(0));
			for (Column c : columns) {
				if (primaryKeyValue.equalsIgnoreCase(c.getName())) {
					table.setPrimaryKey(primaryKeyValue);
					verifiedPrimaryKey = true;
				}
			}
			if (!verifiedPrimaryKey) {
				throw new ColumnNotFoundException(primaryKeyValue);
			}
		}
		this.tables.put(tableName, table);
	}

	@Override
	public void visitData(NodeData nodeData) {

	}

	@Override
	public void visitDelete(NodeDelete nodeDelete) {
		Node from = nodeDelete.getChildren().get(0);
		Node table = from.getChildren().get(0);
		this.testTable(table);
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
		this.testTable(nodeDrop.getChildren().get(0).getChildren().get(0));
	}

	@Override
	public void visitFrom(NodeFrom nodeFrom) {
	}

	@Override
	public void visitFunction(NodeFunction nodeFunction) {
	}

	@Override
	public void visitGroup(NodeGroup nodeGroup) {
		this.visitNode(nodeGroup);
	}

	@Override
	public void visitInsert(NodeInsert nodeInsert) {
		this.currentTables = new HashMap<String, Table>();
		String tableName = this.getName(nodeInsert.getChildren().get(0));
		Table table = this.tables.get(tableName);
		List<Node> columns = nodeInsert.getChildren().get(1).getChildren();
		List<Type> columnType = new ArrayList<>();
		for (Node column : columns) {
			String columnName = this.getName(column);
			if (!table.containsColumn(columnName)) {
				throw new ColumnNotFoundException(columnName);
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
			throw new InsertException(values.size(), columns.size());
		}
		for (int i = 0; i < columns.size(); i++) {
			Node val = values.get(i);
			val.accept(this);
			if (!columnType.get(i).equals(this.type)) {
				throw new WrongTypeException(columnType.get(i).toString(), this.type.toString());
			}
		}
	}

	@Override
	public void visitInteger(NodeInteger nodeInteger) {
		this.type = Type.NUMBER;
	}

	@Override
	public void visitInto(NodeInto nodeInto) {
	}

	@Override
	public void visitJoin(NodeJoin nodeJoin) {
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
	}

	@Override
	public void visitNodeValues(NodeValues nodeValues) {
	}

	@Override
	public void visitNot(NodeNot nodeNot) {
	}

	@Override
	public void visitNull(NodeNull nodeNull) {
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
		this.visitNode(nodeOrder);
	}

	@Override
	public void visitOrderBy(NodeOrderBy nodeOrderBy) {
		this.visitNode(nodeOrderBy);
	}

	@Override
	public void visitPlus(NodePlus nodePlus) {
		this.visitBinaryOperator(nodePlus);
	}

	@Override
	public void visitPrimary(NodePrimaryKey nodePrimaryKey) {
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
		this.currentColumnAliases = new HashMap<String, Type>();
		Node nodeSelectExpressions = nodeSelect.getChildren().get(0);
		Node nodeFrom = nodeSelect.getChildren().get(1);
		Node nodeTable = nodeFrom.getChildren().get(0);
		String tableName = this.getName(nodeFrom);
		Table table = this.tables.get(tableName);
		if (table == null) {
			throw new TableNotFoundException(tableName);
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
			tableName = this.getName(nodeJoin);
			table = this.tables.get(tableName);
			if (table == null) {
				throw new TableNotFoundException(tableName);
			}
			tableAlias = tableName;
			nodeAs = nodeTable.getChildren().get(1);
			if (nodeAs != null) {
				tableAlias = this.getName(nodeAs);
			}
			this.currentTables.put(tableAlias, table);
			Node nodeOn = nodeJoin.getChildren().get(1);
			if (nodeOn != null) {
				nodeOn.accept(this);
			}
		}
		if (nodeSelectExpressions.getChildren().get(0) instanceof NodeWildcard) {
			// Transformation d'arbre : SELECT * => SELECT col1, col2,...
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
		} else if (nodeSelectExpressions.getChildren().get(0).getChildren().get(0) instanceof NodeFunction) {
			boolean occurence = false;
			Node column = nodeSelect.getChildren().get(0).getChildren().get(0).getChildren().get(0).getChildren()
					.get(0);
			String columnName = this.getName(column);
			for (Table t : this.currentTables.values()) {
				if (t.containsColumn(columnName)) {
					if (occurence) {
						throw new AmbiguousColumnException(columnName);
					}
					occurence = true;
				}
			}
			if (!occurence) {
				throw new ColumnNotFoundException(columnName);
			}
		} else {
			List<Node> selectExpressions = nodeSelectExpressions.getChildren();
			for (Node expression : selectExpressions) {
				boolean occurence = false;
				Node column = expression.getChildren().get(0);
				String columnName = this.getName(column);
				Node alias = column.getChildren().get(1);
				if (alias == null) {
					for (Table t : this.currentTables.values()) {
						if (t.containsColumn(columnName)) {
							if (occurence) { // On a d�j� trouv� la colonne dans une autre table
								throw new AmbiguousColumnException(columnName);
							}
							occurence = true;
							table = t;
						}
					}
				} else {
					String name = ((NodeText) alias.getChildren().get(0)).getValue();
					table = this.currentTables.get(name);
					if (table == null) {
						throw new TableNotFoundException(name);
					}
					occurence = table.containsColumn(columnName);
				}
				if (!occurence) { // La colonne n'existe dans aucune des tables
					throw new ColumnNotFoundException(columnName);
				}
				if (expression.getChildren().size() > 1) {
					if (expression.getChildren().get(1) instanceof NodeAs) {
						String columnAlias = this.getName(expression.getChildren().get(1));
						int index = table.getColumns().indexOf(new Column(columnName));
						switch (table.getColumns().get(index).getType()) {
						case "CHAR":
						case "VARCHAR":
							this.currentColumnAliases.put(columnAlias, Type.TEXT);
							break;
						default:
							this.currentColumnAliases.put(columnAlias, Type.NUMBER);
						}
					}
				}
			}
			this.visitNode(nodeSelect);
		}
	}

	@Override
	public void visitSelectExpression(NodeSelectExpression nodeSelectExpression) {
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
	}

	@Override
	public void visitTableAlias(NodeTableAlias nodeTableAlias) {
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
	}

	@Override
	public void visitUpdate(NodeUpdate nodeUpdate) {
		Node table = nodeUpdate.getChildren().get(0);
		this.testTable(table);
		this.visitNode(nodeUpdate);
	}

	@Override
	public void visitUsing(NodeUsing nodeUsing) {
	}

	@Override
	public void visitWhere(NodeWhere nodeWhere) {
		this.visitNode(nodeWhere);
	}

	@Override
	public void visitWildcard(NodeWildcard nodeWildcard) {
	}

}
