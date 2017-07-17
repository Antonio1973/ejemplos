package proyecto.maven.ejemplos;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.MarshallerProperties;

//Crea JSON desde Java object y viceversa
public class JAXBExampleJson {
	
	public static void main(String[] args) throws Exception {
		
	    doJAXBJson();

	  }
	
	public static void doJAXBJson() throws Exception {

		Course course = new Course(1,"Course-1", 5);
		course.setTeacher(new Teacher(1, "Teacher-1"));

		JAXBContext context = JAXBContext.newInstance(Course.class);

		//Marshal Java object to JSON
		Marshaller marshaller = context.createMarshaller();
		//Set option to format generated JSON
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
		marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);

		StringWriter stringWriter = new StringWriter();
		//Marshal Course object and write to the StringWriter
		marshaller.marshal(course, stringWriter);
		//Get String from the StringWriter
		String courseJson = stringWriter.getBuffer().toString();
		stringWriter.close();
		//Print course JSON
		System.out.println(courseJson);

		//Now, unmarshall courseJson to create Course object
		Unmarshaller unmarshler = context.createUnmarshaller();
		unmarshler.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
		unmarshler.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, true);

		//Create StringReader from courseJson
		StringReader stringReader = new StringReader(courseJson);
		//Create StreamSource which will be used by JAXB unmarshaller
		StreamSource streamSource = new StreamSource(stringReader);
		Course unmarshalledCourse = unmarshler.unmarshal(streamSource, Course.class).getValue();
		System.out.println("-----------------\nUnmarshalled course name - " + unmarshalledCourse.getName());
		stringReader.close();
	
	}

}
