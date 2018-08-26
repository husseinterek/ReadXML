package com.programmer.gate;

import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ReadXMLWithSAX {

	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		try
		{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			SAXHandler saxHandler = new SAXHandler();
			saxParser.parse("students.xml", saxHandler);
			
			List<Student> students = saxHandler.getStudents();
			for(Student student : students)
			{
				System.out.println("Student Id = " + student.getId());
				System.out.println("Student Name = " + student.getName());
				System.out.println("Is student graduated? " + student.isGraduated());
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
