package compiler;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SQLCompilerApp {

	private CompilerDocument document;

	public SQLCompilerApp () {
		document = new CompilerDocument ();
		document.onOpenDocument();
		this.DataBaseBuilder();
	}
	
	public void DataBaseBuilder() {
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try 
		{
		  final DocumentBuilder builder = factory.newDocumentBuilder();		
		  final Document document= builder.parse(new File("testXML.xml"));
		  final Element racine = document.getDocumentElement();
		  final NodeList racineNoeuds = racine.getChildNodes();
		  final Element create = (Element) racineNoeuds.item(1);
		  final NodeList createNoeuds = create.getChildNodes();
		  final int nbCreateNoeuds = createNoeuds.getLength();
		  for(int i=0;i<nbCreateNoeuds;i++) {
			  if(createNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
				  switch(createNoeuds.item(i).getNodeName()) {
				  case "data" :  final Element column = (Element) createNoeuds.item(i).getChildNodes().item(1).getChildNodes().item(1).getChildNodes().item(1);
				  				 System.out.println("nom de la colonne : " + column.getAttribute("value"));
				  				 break;
				  case "table" : final Element table = (Element) createNoeuds.item(i).getChildNodes().item(1);
				  				 System.out.println("nom de la table : " + table.getAttribute("value"));
				  				 break;
				  case "primaryKey" : final Element primaryKey = (Element) createNoeuds.item(i).getChildNodes().item(1).getChildNodes().item(1).getChildNodes().item(1);
						  			  System.out.println("nom de la clef primaire : " + primaryKey.getAttribute("value"));
						  			  break;
				  }
			  }
		  }
		}
		catch (final ParserConfigurationException e) 
		{
		  e.printStackTrace();
		}
		catch (final SAXException e) 
		{
		  e.printStackTrace();
		}
		catch (final IOException e) 
		{
		  e.printStackTrace();
		}
	}
	
	public static void main (String argv []) {
		SQLCompilerApp app = new SQLCompilerApp ();
	}

}
