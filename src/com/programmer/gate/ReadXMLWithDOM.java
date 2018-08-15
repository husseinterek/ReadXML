package com.programmer.gate;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLWithDOM {

	public static void main(String[] args) throws Exception {
		
		File xmlFile = new File("students.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(xmlFile);
		getStudentById(doc,"id", "2");
		getAllStudents(doc);
		getGraduatedStudents(doc, "graduated", "yes");
		parseWholeXML(doc.getDocumentElement());
	}
	
	private static void getRootElement(Document doc)
	{
		System.out.println(doc.getDocumentElement().getNodeName());
	}
	
	private static void getStudentById(Document doc, String textNodeName, String textNodeValue)
	{
		NodeList studentNodes = doc.getElementsByTagName("student");
		for(int i=0; i<studentNodes.getLength(); i++)
		{
			Node studentNode = studentNodes.item(i);
			if(studentNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element studentElement = (Element) studentNode;
				NodeList textNodes = studentElement.getElementsByTagName(textNodeName);
				if(textNodes.getLength() > 0)
				{
					if(textNodes.item(0).getTextContent().equalsIgnoreCase(textNodeValue))
					{
						System.out.println(textNodes.item(0).getTextContent());
						System.out.println(studentElement.getElementsByTagName("name").item(0).getTextContent());
					}
				}
			}
		}
	}
	
	// Read nodes with specific name
	private static void getAllStudents(Document doc)
	{
		NodeList studentNodes = doc.getElementsByTagName("student");
		for(int i=0; i<studentNodes.getLength(); i++)
		{
			Node studentNode = studentNodes.item(i);
			if(studentNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element studentElement = (Element) studentNode;
				String studentId = studentElement.getElementsByTagName("id").item(0).getTextContent();
				String studentName = studentElement.getElementsByTagName("name").item(0).getTextContent();
				System.out.println("Student Id = " + studentId);
				System.out.println("Student Name = " + studentName);
			}
		}
	}
	
	private static void parseWholeXML(Node startingNode)
	{
		NodeList childNodes = startingNode.getChildNodes();
		for(int i=0; i<childNodes.getLength(); i++)
		{
			Node childNode = childNodes.item(i);
			if(childNode.getNodeType() == Node.ELEMENT_NODE)
			{
				parseWholeXML(childNode);
			}
			else
			{
				// trim() is used to ignore new lines and spaces elements.
				if(!childNode.getTextContent().trim().isEmpty())
				{
					System.out.println(childNode.getTextContent());
				}
			}
		}
	}
	
	// Read nodes having specific attribute
	private static void getGraduatedStudents(Document doc, String attributeName, String attributeValue)
	{
		NodeList studentNodes = doc.getElementsByTagName("student");
		for(int i=0; i<studentNodes.getLength(); i++)
		{
			Node studentNode = studentNodes.item(i);
			if(studentNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element studentElement = (Element) studentNode;
				if(attributeValue.equalsIgnoreCase(studentElement.getAttribute(attributeName)))
				{
					String studentId = studentElement.getElementsByTagName("id").item(0).getTextContent();
					String studentName = studentElement.getElementsByTagName("name").item(0).getTextContent();
					System.out.println("Student Id = " + studentId);
					System.out.println("Student Name = " + studentName);
				}
			}
		}
	}
}
