package compiler;

import java.util.ArrayList;

import ui.Node;
import ui.SQLVisitor;
import ui.Type;

public class SQLFactory {

	public SQLVisitor visitor;
	public ArrayList<Node> variables;
	
	public SQLFactory() {
		visitor = new SQLVisitor();
		variables=new ArrayList<Node>();
	}
	
	public Node addNode(Node n ){
		Node retour = null;
    	for(Node node : variables) {
    		if(node.getName().equalsIgnoreCase(n.getName())) {
    			retour = node;
    		}
    	}
    		
    	if(retour==null) return n;
    	else return retour;
	}
	

}