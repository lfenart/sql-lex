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
		  final int nbRacineNoeuds = racineNoeuds.getLength();
		  for (int i = 0; i<nbRacineNoeuds; i++) 
		  {
		    if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) 
		    {
		      final Element create = (Element) racineNoeuds.item(i);
		      System.out.println(create.getNodeName());
		      System.out.println(create.getAttribute("text"));
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
