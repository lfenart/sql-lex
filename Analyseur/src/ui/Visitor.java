package ui;

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
import Instruction.NodeTableAlias;
import Instruction.NodeTableExpression;
import Instruction.NodeTable;
import Instruction.NodeText;
import Instruction.NodeType;
import Instruction.NodeUminus;
import Instruction.NodeUpdate;
import Instruction.NodeUplus;
import Instruction.NodeUsing;
import Instruction.NodeValues;
import Instruction.NodeWhere;
import Instruction.NodeWildcard;

public abstract class Visitor {

	public void visitNode(Node node) {
		for (Node n : node.getChildren()) {
			if (n != null) {
				n.accept(this);
			}
		}
	}

	public abstract void visitAs(NodeAs nodeAs);

	public abstract void visitBlock(NodeBlock nodeBlock);

	public abstract void visitBoolean(NodeBoolean nodeBoolean);

	public abstract void visitColumn(NodeColumn nodeColumn);

	public abstract void visitColumnAlias(NodeColumnAlias nodeColumnAlias);

	public abstract void visitColumnName(NodeColumnName nodeColumnName);

	public abstract void visitConcat(NodeConcat nodeConcat);

	public abstract void visitCreate(NodeCreate nodeCreate);

	public abstract void visitData(NodeData nodeData);

	public abstract void visitDelete(NodeDelete nodeDelete);

	public abstract void visitDiv(NodeDiv nodeDiv);

	public abstract void visitDouble(NodeDouble nodeDouble);

	public abstract void visitDrop(NodeDrop nodeDrop);

	public abstract void visitFrom(NodeFrom nodeFrom);

	public abstract void visitFunction(NodeFunction nodeFunction);

	public abstract void visitGroup(NodeGroup nodeGroup);

	public abstract void visitInsert(NodeInsert nodeInsert);

	public abstract void visitInteger(NodeInteger nodeInteger);

	public abstract void visitInto(NodeInto nodeInto);

	public abstract void visitJoin(NodeJoin nodeJoin);

	public abstract void visitMinus(NodeMinus nodeMinus);

	public abstract void visitMult(NodeMult nodeMult);

	public abstract void visitNodeUplus(NodeUplus nodeUplus);

	public abstract void visitNodeValues(NodeValues nodeValues);

	public abstract void visitNot(NodeNot nodeNot);

	public abstract void visitNull(NodeNull nodeNull);

	public abstract void visitOn(NodeOn nodeOn);

	public abstract void visitOperator(NodeOperator nodeOperator);

	public abstract void visitOrder(NodeOrder nodeOrder);

	public abstract void visitOrderBy(NodeOrderBy nodeOrderBy);

	public abstract void visitPlus(NodePlus nodePlus);

	public abstract void visitPrimary(NodePrimaryKey nodePrimaryKey);

	public abstract void visitRoot(NodeRoot nodeRoot);

	public abstract void visitSelect(NodeSelect nodeSelect);

	public abstract void visitSelectExpression(NodeSelectExpression nodeSelectExpression);

	public abstract void visitSet(NodeSet nodeSet);

	public abstract void visitTable(NodeTable nodeTableName);

	public abstract void visitTable(NodeTableExpression nodeTable);

	public abstract void visitTableAlias(NodeTableAlias nodeTableAlias);

	public abstract void visitText(NodeText nodeText);

	public abstract void visitType(NodeType nodeType);

	public abstract void visitUminus(NodeUminus nodeUminus);

	public abstract void visitUpdate(NodeUpdate nodeUpdate);

	public abstract void visitUsing(NodeUsing nodeUsing);

	public abstract void visitWhere(NodeWhere nodeWhere);

	public abstract void visitWildcard(NodeWildcard nodeWildcard);
}
