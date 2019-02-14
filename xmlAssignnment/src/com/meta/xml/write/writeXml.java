package com.meta.xml.write;

import java.util.ArrayList;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.meta.xml.dto.F_TB;

public class writeXml {
	
	public void writexml(HashSet<F_TB> ftb, String fileName) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
		        .newInstance();
		    DocumentBuilder documentBuilder;
				documentBuilder = documentBuilderFactory.newDocumentBuilder();
				Document doc = documentBuilder.newDocument();
			
				Element rootElement = doc.createElement("TABLE");
				doc.appendChild(rootElement);
				Element child1 = doc.createElement("ROWS");
				rootElement.appendChild(child1);
				Element child2 = doc.createElement("ROW");
				
				for(F_TB f : ftb) {
					
					child1.appendChild(child2);
					Element ROWID = doc.createElement("ROWID");
					child2.appendChild(ROWID).setTextContent(f.getROWID());
		            Element VOLUME = doc.createElement("VOLUME");
		            child2.appendChild(VOLUME).setTextContent(f.getVOLUME());
		            Element FILE_NAME = doc.createElement("FILE_NAME");
		            child2.appendChild(FILE_NAME).setTextContent(f.getFILE_NAME());
		            Element RELEASE_NAME = doc.createElement("RELEASE_NAME");
		            child2.appendChild(RELEASE_NAME).setTextContent(f.getRELEASE_NAME());
		            Element SIMILAR_RATE = doc.createElement("SIMILAR_RATE");
		            child2.appendChild(SIMILAR_RATE).setTextContent(f.getSIMILAR_RATE());
		            Element FILE_PATH = doc.createElement("FILE_PATH");
		            child2.appendChild(FILE_PATH).setTextContent(f.getFILE_PATH());
		            Element P_ID = doc.createElement("P_ID");
		            child2.appendChild(P_ID).setTextContent(f.getP_ID());
		            Element EXCLUSION = doc.createElement("EXCLUSION");
		            child2.appendChild(EXCLUSION).setTextContent(f.getEXCLUSION());
		            Element COMMENT = doc.createElement("COMMENT");
		            child2.appendChild(COMMENT).setTextContent(f.getCOMMENT());
					
				}
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
			    Transformer transformer = transformerFactory.newTransformer();
			    DOMSource source = new DOMSource(doc);

			    StreamResult result = new StreamResult(fileName);
			    transformer.transform(source, result);
		
		} catch (ParserConfigurationException e) {
				e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
				e.printStackTrace();
		}
	}
}
