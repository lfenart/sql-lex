package ui;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
	}

	@Override
	public void visitBlock(NodeBlock nodeBlock) {
		for (Node n : nodeBlock.getChildren()) {
			n.accept(this);
		}
	}

	@Override
	public void visitBoolean(NodeBoolean nodeBoolean) {
		this.out.println("<boolean value=\"" + nodeBoolean.getValue() + "\" />");
	}

	@Override
	public void visitColumn(NodeColumn nodeColumn) {
		this.out.println("<column>");
		this.visitNode(nodeColumn);
		this.out.println("</column>");
	}

	@Override
	public void visitColumnAlias(NodeColumnAlias nodeColumnAlias) {
		this.out.println("<columnAlias>");
		this.visitNode(nodeColumnAlias);
		this.out.println("</columnAlias>");
	}

	@Override
	public void visitColumnName(NodeColumnName nodeColumnName) {
		this.out.println("<columnName>");
		this.visitNode(nodeColumnName);
		this.out.println("</columnName>");
	}

	@Override
	public void visitConcat(NodeConcat nodeConcat) {
		this.out.println("<concat>");
		this.visitNode(nodeConcat);
		this.out.println("</concat>");
	}

	@Override
	public void visitCreate(NodeCreate nodeCreate) {
		this.out.println("<create>");
		this.visitNode(nodeCreate);
		this.out.println("</create>");
	}

	@Override
	public void visitData(NodeData nodeData) {
		this.out.println("<data>");
		this.visitNode(nodeData);
		this.out.println("</data>");
	}

	@Override
	public void visitDelete(NodeDelete nodeDelete) {
		this.out.println("<delete>");
		this.visitNode(nodeDelete);
		this.out.println("</delete>");
	}

	@Override
	public void visitDiv(NodeDiv nodeDiv) {
		this.out.println("<div>");
		this.visitNode(nodeDiv);
		this.out.println("</div>");
	}

	@Override
	public void visitDouble(NodeDouble nodeDouble) {
		this.out.println("<double value=\"" + nodeDouble.getValue() + "\" />");
	}

	@Override
	public void visitDrop(NodeDrop nodeDrop) {
		this.out.println("<drop>");
		this.visitNode(nodeDrop);
		this.out.println("</drop>");
	}

	@Override
	public void visitFrom(NodeFrom nodeFrom) {
		this.out.println("<from>");
		this.visitNode(nodeFrom);
		this.out.println("</from>");
	}

	@Override
	public void visitFunction(NodeFunction nodeFunction) {
		this.out.println("<" + nodeFunction.getName() + ">");
		this.visitNode(nodeFunction);
		this.out.println("</" + nodeFunction.getName() + ">");
	}

	@Override
	public void visitGroup(NodeGroup nodeGroup) {
		this.out.println("<groupBy>");
		this.visitNode(nodeGroup);
		this.out.println("</groupBy>");
	}

	@Override
	public void visitInsert(NodeInsert nodeInsert) {
		this.out.println("<insert>");
		this.visitNode(nodeInsert);
		this.out.println("</insert>");
	}

	@Override
	public void visitInteger(NodeInteger nodeInteger) {
		this.out.println("<integer value=\"" + nodeInteger.getValue() + "\" />");
	}

	@Override
	public void visitInto(NodeInto nodeInto) {
		this.out.println("<into>");
		this.visitNode(nodeInto);
		this.out.println("</into>");
	}

	@Override
	public void visitJoin(NodeJoin nodeJoin) {
		this.out.println("<join value=\"" + nodeJoin.getType() + "\">");
		this.visitNode(nodeJoin);
		this.out.println("</join>");
	}

	@Override
	public void visitMinus(NodeMinus nodeMinus) {
		this.out.println("<inus>");
		this.visitNode(nodeMinus);
		this.out.println("</minus>");
	}

	@Override
	public void visitMult(NodeMult nodeMult) {
		this.out.println("<mult>");
		this.visitNode(nodeMult);
		this.out.println("</mult>");
	}

	@Override
	public void visitNodeUplus(NodeUplus nodeUplus) {
		this.out.println("<unaryPlus>");
		this.visitNode(nodeUplus);
		this.out.println("</unaryPlus>");
	}

	@Override
	public void visitNodeValues(NodeValues nodeValues) {
		this.out.println("<values>");
		this.visitNode(nodeValues);
		this.out.println("</values>");
	}

	@Override
	public void visitNot(NodeNot nodeNot) {
		this.out.println("<not>");
		this.visitNode(nodeNot);
		this.out.println("</not>");
	}

	@Override
	public void visitNull(NodeNull nodeNull) {
		this.out.println("<null />");
	}

	@Override
	public void visitOn(NodeOn nodeOn) {
		this.out.println("<on>");
		this.visitNode(nodeOn);
		this.out.println("</on>");
	}

	@Override
	public void visitOperator(NodeOperator nodeOperator) {
		this.out.println("<" + nodeOperator.getOperator() + ">");
		this.visitNode(nodeOperator);
		this.out.println("</" + nodeOperator.getOperator() + ">");
	}

	@Override
	public void visitOrder(NodeOrder nodeOrder) {
		this.out.println("<order>");
		this.out.println("<" + nodeOrder.getOrder() + "/>");
		this.out.println("</order>");
	}

	@Override
	public void visitOrderBy(NodeOrderBy nodeOrderBy) {
		this.out.println("<orderBy>");
		this.visitNode(nodeOrderBy);
		this.out.println("</orderBy>");
	}

	@Override
	public void visitPlus(NodePlus nodePlus) {
		this.out.println("<plus>");
		this.visitNode(nodePlus);
		this.out.println("</plus>");
	}

	@Override
	public void visitPrimary(NodePrimaryKey nodePrimaryKey) {
		this.out.println("<primaryKey>");
		this.visitNode(nodePrimaryKey);
		this.out.println("</primaryKey>");
	}

	@Override
	public void visitRoot(NodeRoot nodeRoot) {
		this.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		this.out.println("<root>");
		this.visitNode(nodeRoot);
		this.out.println("</root>");
		this.out.flush();
	}

	@Override
	public void visitSelect(NodeSelect nodeSelect) {
		this.out.println("<select>");
		this.visitNode(nodeSelect);
		this.out.println("</select>");
	}

	@Override
	public void visitSelectExpression(NodeSelectExpression nodeSelectExpression) {
		this.out.println("<selectExpression>");
		this.visitNode(nodeSelectExpression);
		this.out.println("</selectExpression>");
	}

	@Override
	public void visitSet(NodeSet nodeSet) {
		this.out.println("<set>");
		this.visitNode(nodeSet);
		this.out.println("</set>");
	}

	@Override
	public void visitTable(NodeTable nodeTableName) {
		this.out.println("<table>");
		this.visitNode(nodeTableName);
		this.out.println("</table>");
	}

	@Override
	public void visitTable(NodeTableExpression nodeTable) {
		this.out.println("<table>");
		this.visitNode(nodeTable);
		this.out.println("</table>");
	}

	@Override
	public void visitTableAlias(NodeTableAlias nodeTableAlias) {
		this.out.println("<tableAlias>");
		this.visitNode(nodeTableAlias);
		this.out.println("</tableAlias>");
	}

	@Override
	public void visitText(NodeText nodeText) {
		this.out.println("<text value=\"" + nodeText.getValue() + "\" />");
	}

	@Override
	public void visitType(NodeType nodeType) {
		this.out.println("<type>");
		this.out.println("<" + nodeType.getType());
		Long size = nodeType.getSize();
		if (size != null) {
			this.out.println(" size=\"" + size + "\" ");
		}
		this.out.println(" /></type>");
	}

	@Override
	public void visitUminus(NodeUminus nodeUminus) {
		this.out.println("<uminus>");
		this.visitNode(nodeUminus);
		this.out.println("</uminus>");
	}

	@Override
	public void visitUpdate(NodeUpdate nodeUpdate) {
		this.out.println("<update>");
		this.visitNode(nodeUpdate);
		this.out.println("</update>");
	}

	@Override
	public void visitUsing(NodeUsing nodeUsing) {
		this.out.println("<using>");
		this.visitNode(nodeUsing);
		this.out.println("</using>");
	}

	@Override
	public void visitWhere(NodeWhere nodeWhere) {
		this.out.println("<where>");
		this.visitNode(nodeWhere);
		this.out.println("</where>");
	}

	@Override
	public void visitWildcard(NodeWildcard nodeWildcard) {
		this.out.println("<wildcard />");
	}

}
