package ui;

<<<<<<< HEAD
import java.io.FileNotFoundException;
import java.io.PrintWriter;
=======
import java.util.HashMap;
import java.util.Map;
>>>>>>> lfenart

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
<<<<<<< HEAD

public class SemanticVisitor extends Visitor{

	PrintWriter out;

	public SemanticVisitor() throws FileNotFoundException {
		out = new PrintWriter(System.out);
	}

	@Override
	public void visitAs(NodeAs nodeAs) {
		this.out.println("<as>");
		this.visitNode(nodeAs);
		this.out.println("</as>");
=======
import symbol.Table;

public class SemanticVisitor extends Visitor {

	private Map<String, Table> tables = new HashMap<>();
	private Table table;

	@Override
	public void visitAs(NodeAs nodeAs) {
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitBlock(NodeBlock nodeBlock) {
<<<<<<< HEAD
		for (Node n : nodeBlock.getChildren()) {
			n.accept(this);
		}
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitBoolean(NodeBoolean nodeBoolean) {
<<<<<<< HEAD
		this.out.println("<boolean value=\"" + nodeBoolean.getValue() + "\" />");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitColumn(NodeColumn nodeColumn) {
<<<<<<< HEAD
		this.out.println("<column>");
		this.visitNode(nodeColumn);
		this.out.println("</column>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitColumnAlias(NodeColumnAlias nodeColumnAlias) {
<<<<<<< HEAD
		this.out.println("<columnAlias>");
		this.visitNode(nodeColumnAlias);
		this.out.println("</columnAlias>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitColumnName(NodeColumnName nodeColumnName) {
<<<<<<< HEAD
		this.out.println("<columnName>");
		this.visitNode(nodeColumnName);
		this.out.println("</columnName>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitConcat(NodeConcat nodeConcat) {
<<<<<<< HEAD
		this.out.println("<concat>");
		this.visitNode(nodeConcat);
		this.out.println("</concat>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitCreate(NodeCreate nodeCreate) {
<<<<<<< HEAD
		this.out.println("<create>");
		this.visitNode(nodeCreate);
		this.out.println("</create>");
=======
		Node table = nodeCreate.getChildren().get(0);
		Node datas = nodeCreate.getChildren().get(1);
		Node primaryKey = nodeCreate.getChildren().get(2);
		this.tables.put(((NodeText) table.getChildren().get(0)).getValue(), new Table());
>>>>>>> lfenart
	}

	@Override
	public void visitData(NodeData nodeData) {
<<<<<<< HEAD
		this.out.println("<data>");
		this.visitNode(nodeData);
		this.out.println("</data>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitDelete(NodeDelete nodeDelete) {
<<<<<<< HEAD
		this.out.println("<delete>");
		this.visitNode(nodeDelete);
		this.out.println("</delete>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitDiv(NodeDiv nodeDiv) {
<<<<<<< HEAD
		this.out.println("<div>");
		this.visitNode(nodeDiv);
		this.out.println("</div>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitDouble(NodeDouble nodeDouble) {
<<<<<<< HEAD
		this.out.println("<double value=\"" + nodeDouble.getValue() + "\" />");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitDrop(NodeDrop nodeDrop) {
<<<<<<< HEAD
		this.out.println("<drop>");
		this.visitNode(nodeDrop);
		this.out.println("</drop>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitFrom(NodeFrom nodeFrom) {
<<<<<<< HEAD
		this.out.println("<from>");
		this.visitNode(nodeFrom);
		this.out.println("</from>");
=======
		String tableName = ((NodeText) nodeFrom.getChildren().get(0).getChildren().get(0)).getValue();
		this.table = this.tables.get(tableName);
>>>>>>> lfenart
	}

	@Override
	public void visitFunction(NodeFunction nodeFunction) {
<<<<<<< HEAD
		this.out.println("<" + nodeFunction.getName() + ">");
		this.visitNode(nodeFunction);
		this.out.println("</" + nodeFunction.getName() + ">");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitGroup(NodeGroup nodeGroup) {
<<<<<<< HEAD
		this.out.println("<groupBy>");
		this.visitNode(nodeGroup);
		this.out.println("</groupBy>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitInsert(NodeInsert nodeInsert) {
<<<<<<< HEAD
		this.out.println("<insert>");
		this.visitNode(nodeInsert);
		this.out.println("</insert>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitInteger(NodeInteger nodeInteger) {
<<<<<<< HEAD
		this.out.println("<integer value=\"" + nodeInteger.getValue() + "\" />");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitInto(NodeInto nodeInto) {
<<<<<<< HEAD
		this.out.println("<into>");
		this.visitNode(nodeInto);
		this.out.println("</into>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitJoin(NodeJoin nodeJoin) {
<<<<<<< HEAD
		this.out.println("<join value=\"" + nodeJoin.getType() + "\">");
		this.visitNode(nodeJoin);
		this.out.println("</join>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitMinus(NodeMinus nodeMinus) {
<<<<<<< HEAD
		this.out.println("<inus>");
		this.visitNode(nodeMinus);
		this.out.println("</minus>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitMult(NodeMult nodeMult) {
<<<<<<< HEAD
		this.out.println("<mult>");
		this.visitNode(nodeMult);
		this.out.println("</mult>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitNodeUplus(NodeUplus nodeUplus) {
<<<<<<< HEAD
		this.out.println("<unaryPlus>");
		this.visitNode(nodeUplus);
		this.out.println("</unaryPlus>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitNodeValues(NodeValues nodeValues) {
<<<<<<< HEAD
		this.out.println("<values>");
		this.visitNode(nodeValues);
		this.out.println("</values>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitNot(NodeNot nodeNot) {
<<<<<<< HEAD
		this.out.println("<not>");
		this.visitNode(nodeNot);
		this.out.println("</not>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitNull(NodeNull nodeNull) {
<<<<<<< HEAD
		this.out.println("<null />");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitOn(NodeOn nodeOn) {
<<<<<<< HEAD
		this.out.println("<on>");
		this.visitNode(nodeOn);
		this.out.println("</on>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitOperator(NodeOperator nodeOperator) {
<<<<<<< HEAD
		this.out.println("<" + nodeOperator.getOperator() + ">");
		this.visitNode(nodeOperator);
		this.out.println("</" + nodeOperator.getOperator() + ">");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitOrder(NodeOrder nodeOrder) {
<<<<<<< HEAD
		this.out.println("<order>");
		this.out.println("<" + nodeOrder.getOrder() + "/>");
		this.out.println("</order>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitOrderBy(NodeOrderBy nodeOrderBy) {
<<<<<<< HEAD
		this.out.println("<orderBy>");
		this.visitNode(nodeOrderBy);
		this.out.println("</orderBy>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitPlus(NodePlus nodePlus) {
<<<<<<< HEAD
		this.out.println("<plus>");
		this.visitNode(nodePlus);
		this.out.println("</plus>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitPrimary(NodePrimaryKey nodePrimaryKey) {
<<<<<<< HEAD
		this.out.println("<primaryKey>");
		this.visitNode(nodePrimaryKey);
		this.out.println("</primaryKey>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitRoot(NodeRoot nodeRoot) {
<<<<<<< HEAD
		this.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		this.out.println("<root>");
		this.visitNode(nodeRoot);
		this.out.println("</root>");
		this.out.flush();
=======
		this.visitNode(nodeRoot);
>>>>>>> lfenart
	}

	@Override
	public void visitSelect(NodeSelect nodeSelect) {
<<<<<<< HEAD
		this.out.println("<select>");
		this.visitNode(nodeSelect);
		this.out.println("</select>");
=======
		Node select = nodeSelect.getChildren().get(0);
		Node from = nodeSelect.getChildren().get(1);
		Node where = nodeSelect.getChildren().get(2);
		Node group = nodeSelect.getChildren().get(3);
		Node order = nodeSelect.getChildren().get(4);
		from.accept(this);
		if (this.table == null) {
			System.out.println("Select Error");
		} else {
			System.out.println("Select Ok");
		}
>>>>>>> lfenart
	}

	@Override
	public void visitSelectExpression(NodeSelectExpression nodeSelectExpression) {
<<<<<<< HEAD
		this.out.println("<selectExpression>");
		this.visitNode(nodeSelectExpression);
		this.out.println("</selectExpression>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitSet(NodeSet nodeSet) {
<<<<<<< HEAD
		this.out.println("<set>");
		this.visitNode(nodeSet);
		this.out.println("</set>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitTable(NodeTable nodeTableName) {
<<<<<<< HEAD
		this.out.println("<table>");
		this.visitNode(nodeTableName);
		this.out.println("</table>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitTable(NodeTableExpression nodeTable) {
<<<<<<< HEAD
		this.out.println("<table>");
		this.visitNode(nodeTable);
		this.out.println("</table>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitTableAlias(NodeTableAlias nodeTableAlias) {
<<<<<<< HEAD
		this.out.println("<tableAlias>");
		this.visitNode(nodeTableAlias);
		this.out.println("</tableAlias>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitText(NodeText nodeText) {
<<<<<<< HEAD
		this.out.println("<text value=\"" + nodeText.getValue() + "\" />");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitType(NodeType nodeType) {
<<<<<<< HEAD
		this.out.println("<type>");
		this.out.println("<" + nodeType.getType());
		Long size = nodeType.getSize();
		if (size != null) {
			this.out.println(" size=\"" + size + "\" ");
		}
		this.out.println(" /></type>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitUminus(NodeUminus nodeUminus) {
<<<<<<< HEAD
		this.out.println("<uminus>");
		this.visitNode(nodeUminus);
		this.out.println("</uminus>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitUpdate(NodeUpdate nodeUpdate) {
<<<<<<< HEAD
		this.out.println("<update>");
		this.visitNode(nodeUpdate);
		this.out.println("</update>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitUsing(NodeUsing nodeUsing) {
<<<<<<< HEAD
		this.out.println("<using>");
		this.visitNode(nodeUsing);
		this.out.println("</using>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitWhere(NodeWhere nodeWhere) {
<<<<<<< HEAD
		this.out.println("<where>");
		this.visitNode(nodeWhere);
		this.out.println("</where>");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

	@Override
	public void visitWildcard(NodeWildcard nodeWildcard) {
<<<<<<< HEAD
		this.out.println("<wildcard />");
=======
		// TODO Auto-generated method stub

>>>>>>> lfenart
	}

}
