package com.programmer.gate;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
	
	private List<Student> students = null;
	private Student student = null;
	private String elementValue;
	
	@Override
	public void startDocument() throws SAXException {
		students = new ArrayList<Student>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equalsIgnoreCase("student")) {
			student = new Student();
			
			if(attributes.getLength() > 0)
			{
				String graduated = attributes.getValue("graduated");
				student.setGraduated(Boolean.valueOf(graduated));
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("student")) {
			students.add(student);
		}
		
		if (qName.equalsIgnoreCase("id")) {
			student.setId(Integer.valueOf(elementValue));
		}
		
		if (qName.equalsIgnoreCase("name")) {
			student.setName(elementValue);
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		elementValue = new String(ch, start, length);
	}

	public List<Student> getStudents() {
		return students;
	}
}
