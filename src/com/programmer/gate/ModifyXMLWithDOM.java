package com.programmer.gate;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ModifyXMLWithDOM {

	public static void main(String[] args) throws Exception {
		
		File xmlFile = new File("students.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(xmlFile);
		
		setGraduatedStudent(doc, 2);
		modifyStudentFirstName(doc,2,"Alexa");
		setStudentLastName(doc,1,"Terek");
		removeStudentLastName(doc,1);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("students.xml"));
		transformer.transform(source, result);
	}

	private static void setGraduatedStudent(Document doc, int id) {
		
		NodeList students = doc.getElementsByTagName("student");
		for(int i=0; i < students.getLength();i++)
		{
			Element studentNode = (Element) students.item(i);
			int studentId = Integer.valueOf(studentNode.getElementsByTagName("id").item(0).getTextContent());
			if(studentId == id)
			{
				studentNode.setAttribute("graduated", "true");
			}
		}
	}
	
	private static void modifyStudentFirstName(Document doc, int id, String updatedFirstName) {
		
		NodeList students = doc.getElementsByTagName("student");
		for(int i=0; i < students.getLength();i++)
		{
			Element studentNode = (Element) students.item(i);
			int studentId = Integer.valueOf(studentNode.getElementsByTagName("id").item(0).getTextContent());
			if(studentId == id)
			{
				Element studentName = (Element) studentNode.getElementsByTagName("name").item(0);
				studentName.setTextContent(updatedFirstName);
			}
		}
	}
	
	private static void setStudentLastName(Document doc, int id, String lastName) {
		
		NodeList students = doc.getElementsByTagName("student");
		for(int i=0; i < students.getLength();i++)
		{
			Element studentNode = (Element) students.item(i);
			int studentId = Integer.valueOf(studentNode.getElementsByTagName("id").item(0).getTextContent());
			if(studentId == id)
			{
				Element lastNameElement = doc.createElement("lastName");
				lastNameElement.setTextContent(lastName);
				studentNode.appendChild(lastNameElement);
			}
		}
	}
	
	private static void removeStudentLastName(Document doc, int id) {
		
		NodeList students = doc.getElementsByTagName("student");
		for(int i=0; i < students.getLength();i++)
		{
			Element studentNode = (Element) students.item(i);
			int studentId = Integer.valueOf(studentNode.getElementsByTagName("id").item(0).getTextContent());
			if(studentId == id)
			{
				Element studentLastName = (Element) studentNode.getElementsByTagName("lastName").item(0);
				studentNode.removeChild(studentLastName);
			}
		}
	}
	
}
