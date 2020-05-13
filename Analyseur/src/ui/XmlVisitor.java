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
import Instruction.NodeExpression;
import Instruction.NodeFrom;
import Instruction.NodeFunction;
import Instruction.NodeGroup;
import Instruction.NodeInsert;
import Instruction.NodeInteger;
import Instruction.NodeJoin;
import Instruction.NodeMinus;
import Instruction.NodeMult;
import Instruction.NodeNot;
import Instruction.NodeNull;
import Instruction.NodeOn;
import Instruction.NodeOperator;
import Instruction.NodeOrder;
import Instruction.NodeOrderBy;
import Instruction.NodeOrderExpression;
import Instruction.NodePlus;
import Instruction.NodePrimaryKey;
import Instruction.NodeRoot;
import Instruction.NodeSchemaName;
import Instruction.NodeSelect;
import Instruction.NodeSelectExpression;
import Instruction.NodeSet;
import Instruction.NodeTableAlias;
import Instruction.NodeTableExpression;
import Instruction.NodeText;
import Instruction.NodeType;
import Instruction.NodeUminus;
import Instruction.NodeUpdate;
import Instruction.NodeUsing;
import Instruction.NodeWhere;
import Instruction.NodeWildcard;

public class XmlVisitor extends Visitor {
	PrintWriter out;

	public XmlVisitor() throws FileNotFoundException {
		out = new PrintWriter("testXML.xml");
	}

	@Override
	public void visitBlock(NodeBlock nodeBlock) {
		for (Node n : nodeBlock.getChildren()) {
			n.accept(this);
		}
	}

	@Override
	public void visitBoolean(NodeBoolean nodeBoolean) {
		this.out.print("<boolean value=\"" + nodeBoolean.getValue() + "\" />");
	}

	@Override
	public void visitColumn(NodeColumn nodeColumn) {
		this.out.print("<column>");
		this.visitNode(nodeColumn);
		this.out.print("</column>");
	}

	@Override
	public void visitConcat(NodeConcat nodeConcat) {
		this.out.print("<concat>");
		this.visitNode(nodeConcat);
		this.out.print("</concat>");
	}

	@Override
	public void visitCreate(NodeCreate nodeCreate) {
		this.out.print("<create>");
		this.visitNode(nodeCreate);
		this.out.print("</create>");
	}

	@Override
	public void visitData(NodeData nodeData) {
		this.out.print("<data>");
		this.visitNode(nodeData);
		this.out.print("</data>");
	}

	@Override
	public void visitDelete(NodeDelete nodeDelete) {
		this.out.print("<delete>");
		this.visitNode(nodeDelete);
		this.out.print("</delete>");
	}

	@Override
	public void visitDiv(NodeDiv nodeDiv) {
		this.out.print("<div>");
		this.visitNode(nodeDiv);
		this.out.print("</div>");
	}

	@Override
	public void visitDouble(NodeDouble nodeDouble) {
		this.out.print("<double value=\"" + nodeDouble.getValue() + "\" />");
	}

	@Override
	public void visitDrop(NodeDrop nodeDrop) {
		this.out.print("<drop>");
		this.visitNode(nodeDrop);
		this.out.print("</drop>");
	}

	@Override
	public void visitExpression(NodeExpression nodeExpression) {
		// TODO
		this.out.print("<expression>");
		this.visitNode(nodeExpression);
		this.out.print("</expression>");
	}

	@Override
	public void visitGroup(NodeGroup nodeGroup) {
		this.out.print("<groupBy>");
		this.visitNode(nodeGroup);
		this.out.print("</groupBy>");
	}

	@Override
	public void visitInsert(NodeInsert nodeInsert) {
		this.out.print("<insert>");
		this.visitNode(nodeInsert);
		this.out.print("</insert>");
	}

	@Override
	public void visitInteger(NodeInteger nodeInteger) {
		this.out.print("<integer value=\"" + nodeInteger.getValue() + "\" />");
	}

	@Override
	public void visitMinus(NodeMinus nodeMinus) {
		this.out.print("<inus>");
		this.visitNode(nodeMinus);
		this.out.print("</minus>");
	}

	@Override
	public void visitMult(NodeMult nodeMult) {
		this.out.print("<mult>");
		this.visitNode(nodeMult);
		this.out.print("</mult>");
	}

	@Override
	public void visitNot(NodeNot nodeNot) {
		this.out.print("<not>");
		this.visitNode(nodeNot);
		this.out.print("</not>");
	}

	@Override
	public void visitNull(NodeNull nodeNull) {
		this.out.print("<null />");
	}

	@Override
	public void visitOperator(NodeOperator nodeOperator) {
		this.out.print("<" + nodeOperator.getOperator() + ">");
		this.visitNode(nodeOperator);
		this.out.print("</" + nodeOperator.getOperator() + ">");
	}

	@Override
	public void visitOrder(NodeOrder nodeOrder) {
		this.out.print("<order>");
		this.out.print("<" + nodeOrder.getOrder() + "/>");
		this.out.print("</order>");
	}

	@Override
	public void visitOrderBy(NodeOrderBy nodeOrderBy) {
		this.out.print("<orderBy>");
		this.visitNode(nodeOrderBy);
		this.out.print("</orderBy>");
	}

	@Override
	public void visitOrderExpression(NodeOrderExpression nodeOrderExpression) {
		this.out.print("<orderExpression>");
		this.visitNode(nodeOrderExpression);
		this.out.print("</orderExpression>");
	}

	@Override
	public void visitPlus(NodePlus nodePlus) {
		this.out.print("<plus>");
		this.visitNode(nodePlus);
		this.out.print("</plus>");
	}

	@Override
	public void visitPrimary(NodePrimaryKey nodePrimaryKey) {
		this.out.print("<primaryKey>");
		this.visitNode(nodePrimaryKey);
		this.out.print("</primaryKey>");
	}

	@Override
	public void visitRoot(NodeRoot nodeRoot) {
		this.out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		this.out.print("<root>");
		this.visitNode(nodeRoot);
		this.out.print("</root>");
		this.out.flush();
	}

	@Override
	public void visitSelect(NodeSelect nodeSelect) {
		this.out.print("<select>");
		this.visitNode(nodeSelect);
		this.out.print("</select>");
	}

	@Override
	public void visitSet(NodeSet nodeSet) {
		this.out.print("<set>");
		this.visitNode(nodeSet);
		this.out.print("</set>");
	}

	@Override
	public void visitTable(NodeTableExpression nodeTable) {
		this.out.print("<table>");
		this.visitNode(nodeTable);
		this.out.print("</table>");
	}

	@Override
	public void visitText(NodeText nodeText) {
		this.out.print("<text value=\"" + nodeText.getValue() + "\" />");
	}

	@Override
	public void visitType(NodeType nodeType) {
		this.out.print("<type>");
		this.out.print("<" + nodeType.getType());
		Long size = nodeType.getSize();
		if (size != null) {
			this.out.print(" size=\"" + size + "\" ");
		}
		this.out.print(" /></type>");
	}

	@Override
	public void visitUminus(NodeUminus nodeUminus) {
		this.out.print("<uminus>");
		this.visitNode(nodeUminus);
		this.out.print("</uminus>");
	}

	@Override
	public void visitUpdate(NodeUpdate nodeUpdate) {
		this.out.print("<update>");
		this.visitNode(nodeUpdate);
		this.out.print("</update>");
	}

	@Override
	public void visitWhere(NodeWhere nodeWhere) {
		this.out.print("<where>");
		this.visitNode(nodeWhere);
		this.out.print("</where>");
	}

	@Override
	public void visitFunction(NodeFunction nodeFunction) {
		this.out.print("<" + nodeFunction.getName().toLowerCase() + ">");
		this.visitNode(nodeFunction);
		this.out.print("</" + nodeFunction.getName().toLowerCase() + ">");
	}

	@Override
	public void visitJoin(NodeJoin nodeJoin) {
		this.out.print("<join");
		switch (nodeJoin.getType()) {
		case CROSS:
			this.out.print(" value=\"cross\"");
			break;
		case FULL:
			this.out.print(" value=\"full\"");
			break;
		case INNER:
			this.out.print(" value=\"inner\"");
			break;
		case LEFT:
			this.out.print(" value=\"left\"");
			break;
		case NATURAL:
			this.out.print(" value=\"natural\"");
			break;
		case RIGHT:
			this.out.print(" value=\"right\"");
			break;
		case SELF:
			this.out.print(" value=\"self\"");
			break;
		case UNION:
			this.out.print(" value=\"union\"");
			break;
		default:
			break;
		}
		this.out.print(">");
		this.visitNode(nodeJoin);
		this.out.print("</join>");
	}

	@Override
	public void visitOn(NodeOn nodeOn) {
		this.out.print("<on>");
		this.visitNode(nodeOn);
		this.out.print("</on>");
	}

	@Override
	public void visitUsing(NodeUsing nodeUsing) {
		this.out.print("<using>");
		this.visitNode(nodeUsing);
		this.out.print("</using>");
	}

	@Override
	public void visitColumnName(NodeColumnName nodeColumnName) {
		this.out.print("<columnName>");
		this.visitNode(nodeColumnName);
		this.out.print("</columnName>");
	}

	@Override
	public void visitSchemaName(NodeSchemaName nodeSchemaName) {
		this.out.print("<schemaName>");
		this.visitNode(nodeSchemaName);
		this.out.print("</schemaName>");
	}

	@Override
	public void visitTableAlias(NodeTableAlias nodeTableAlias) {
		this.out.print("<tableAlias>");
		this.visitNode(nodeTableAlias);
		this.out.print("</tableAlias>");
	}

	@Override
	public void visitWildcard(NodeWildcard nodeWildcard) {
		this.out.print("<wildcard />");
	}

	@Override
	public void visitSelectExpression(NodeSelectExpression nodeSelectExpression) {
		this.out.print("<selectExpression>");
		this.visitNode(nodeSelectExpression);
		this.out.print("</selectExpression>");
	}

	@Override
	public void visitAs(NodeAs nodeAs) {
		this.out.print("<as>");
		this.visitNode(nodeAs);
		this.out.print("</as>");
	}

	@Override
	public void visitColumnAlias(NodeColumnAlias nodeColumnAlias) {
		this.out.print("<columnAlias>");
		this.visitNode(nodeColumnAlias);
		this.out.print("</columnAlias>");
	}

	@Override
	public void visitFrom(NodeFrom nodeFrom) {
		this.out.print("<from>");
		this.visitNode(nodeFrom);
		this.out.print("</from>");
	}

}
