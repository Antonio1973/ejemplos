package proyecto.maven.ejemplos;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

//Crea Xml desde Java object y viceversa
public class JAXBExampleXml {

	public static void main(String[] args) throws Exception {
		
		doJAXBXml();

		  }

	//Create XML from Java object and then vice versa
	public static void doJAXBXml() throws Exception {
		   
		Course course = new Course(1,"Course-15", 15);
		course.setTeacher(new Teacher(1, "Teacher-15"));

		JAXBContext context = JAXBContext.newInstance(Course.class);

		//Marshall Java object to XML
		 Marshaller marshaller = context.createMarshaller();
		 //Set option to format generated XML
		 marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		 StringWriter stringWriter = new StringWriter();
		 //Marshal Course object and write to the StringWriter
		 marshaller.marshal(course, stringWriter);
		 //Get String from the StringWriter
		 String courseXml = stringWriter.getBuffer().toString();
		 stringWriter.close();
		 //Print course XML
		 System.out.println(courseXml);

		 //Now unmarshall courseXML to create Course object
		 Unmarshaller unmarshaller = context.createUnmarshaller();
		 //Create StringReader from courseXml
		 StringReader stringReader = new StringReader(courseXml);
		 //Create StreamSource which will be used by JAXB unmarshaller
		 StreamSource streamSource = new StreamSource(stringReader);
		 Course unmarshalledCourse = unmarshaller.unmarshal(streamSource, Course.class).getValue();
		 System.out.println("-----------------\nUnmarshalled course name - "
		        + unmarshalledCourse.getName());
		 stringReader.close();
	}

}
