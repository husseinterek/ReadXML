package com.programmer.gate;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.xml.sax.SAXException;

public class ReadXMLWithSTAX {

	public static void main(String[] args) throws ParserConfigurationException, SAXException {
		try
		{
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("students.xml"));
			
			List<Student> students = new ArrayList<Student>();
			Student student = null;
			String elementValue = null;
			while(eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				switch(event.getEventType()) {
					case XMLStreamConstants.START_ELEMENT:
					{
						StartElement startElement = event.asStartElement();
		                String qName = startElement.getName().getLocalPart();
		                if (qName.equalsIgnoreCase("student")) {
		                	student = new Student();
		                	Iterator<Attribute> attributes = startElement.getAttributes();
		                	if(attributes.hasNext())
		                	{
		                		Attribute attr = attributes.next();
		        				String graduated = attr.getValue();
		        				student.setGraduated(Boolean.valueOf(graduated));
		        			}
		                }
						break;
					}
					case XMLStreamConstants.CHARACTERS:
					{
						Characters characters = event.asCharacters();
						elementValue = characters.getData();
						break;
					}
					case XMLStreamConstants.END_ELEMENT:
					{
						EndElement endElement = event.asEndElement();
						String qName = endElement.getName().getLocalPart();
						if (qName.equalsIgnoreCase("student")) {
							students.add(student);
						}
						if (qName.equalsIgnoreCase("id")) {
							student.setId(Integer.valueOf(elementValue));
						}
						
						if (qName.equalsIgnoreCase("name")) {
							student.setName(elementValue);
						}
						break;
					}
				}
			}

			for(Student s : students)
			{
				System.out.println("Student Id = " + s.getId());
				System.out.println("Student Name = " + s.getName());
				System.out.println("Is student graduated? " + s.isGraduated());
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
