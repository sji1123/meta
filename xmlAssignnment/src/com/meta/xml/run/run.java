package com.meta.xml.run;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.meta.xml.dto.F_TB;
import com.meta.xml.dto.P_TB;
import com.meta.xml.write.writeXml;

public class run {
	
	private final static Logger LOG = Logger.getGlobal();
	
	public static void main(String[] args)  {
		LOG.info("시작");
		long startTime = System.currentTimeMillis();
		try {
		File file = new File("C:\\Users\\meta\\Desktop\\1.XML 파일 분석\\T_BASEFILE_TB.xml");
		DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuild;		
			docBuild = docBuildFact.newDocumentBuilder();
			Document doc = docBuild.parse(file);
			doc.getDocumentElement().normalize();
			
		NodeList fileList = doc.getElementsByTagName("FILE_ID");
	
		for(int i = 0 ; i < fileList.getLength(); i++) {
			LOG.info("=========="+i+"번째 파일========");
			String fFileName = "C:\\Users\\meta\\Desktop\\1.XML 파일 분석\\F_"+i+"_TB.xml";
			String pFileName = "C:\\Users\\meta\\Desktop\\1.XML 파일 분석\\P_"+i+"_TB.xml";
			
			ArrayList<F_TB> farr = new ArrayList<F_TB>();
			HashSet<F_TB> farr2 = new HashSet<F_TB>();
			ArrayList<P_TB> parr = new ArrayList<P_TB>();
			try {
			File fFile = new File(fFileName);
			File pFile = new File(pFileName);
			
			Document fdoc = docBuild.parse(fFile);
			Document pdoc = docBuild.parse(pFile);
			
			fdoc.getDocumentElement().normalize();
			pdoc.getDocumentElement().normalize();
			NodeList fList = fdoc.getElementsByTagName("ROW");
			NodeList pList = pdoc.getElementsByTagName("ROW");
			
			ArrayList<String> arr = new ArrayList<String>();
			
			for(int j=0 ; j < fList.getLength(); j++) {
		
				String rate = fdoc.getElementsByTagName("SIMILAR_RATE").item(j).getTextContent();
				int parseRate = Integer.parseInt(rate);
				
				if(parseRate/100 >= 15) {
					
					String rowid = fdoc.getElementsByTagName("ROWID").item(j).getTextContent()+"";
					String volume = fdoc.getElementsByTagName("VOLUME").item(j).getTextContent()+"";
					String file_name = fdoc.getElementsByTagName("FILE_NAME").item(j).getTextContent()+"";
					String release_name = fdoc.getElementsByTagName("RELEASE_NAME").item(j).getTextContent()+"";
					String similar_rate = fdoc.getElementsByTagName("SIMILAR_RATE").item(j).getTextContent()+"";
					String file_path = fdoc.getElementsByTagName("FILE_PATH").item(j).getTextContent()+"";
			        String pId = fdoc.getElementsByTagName("P_ID").item(j).getTextContent()+"";
					String exclusion = fdoc.getElementsByTagName("EXCLUSION").item(j).getTextContent()+"";
					String comment = fdoc.getElementsByTagName("COMMENT").item(j).getTextContent()+"";
					
					if(!pId.equals("")) {
					F_TB ftb = new F_TB(rowid,volume,file_name,release_name,similar_rate,file_path,pId,exclusion,comment);
					arr.add(pId);
					farr.add(ftb);
					}
				}
			}
			HashSet<String> arr2 = new HashSet<String>(arr);
			ArrayList<String> arr3 = new ArrayList<String>(arr2);
		
			for(int k=0 ; k < pList.getLength(); k++) {
				String pPID = pdoc.getElementsByTagName("P_ID").item(k).getTextContent();
				for(int l = 0 ; l < arr3.size(); l++) {
					if(pPID.equals(arr3.get(l))) {
						String licenseId = pdoc.getElementsByTagName("LICENSE_ID").item(k).getTextContent(); 
						if(!licenseId.equals("")) {
							P_TB ptb = new P_TB(pPID, licenseId);
							parr.add(ptb);
						}
					}
				}
			}
			for(int n = 0 ; n < farr.size(); n++) {
				for(int m = 0 ; m < parr.size(); m++) {
					if(farr.get(n).getP_ID().equals(parr.get(m).getP_ID())) {
						farr.get(n).setCOMMENT(parr.get(m).getLICENSE_ID());
						if(!farr.get(n).getCOMMENT().equals("")) {
							F_TB ftb= farr.get(n); 
							farr2.add(ftb);
						}
					}
				}
			}
			String newFileName = "T_"+i+"_TB.xml";
			writeXml write = new writeXml();
			if(parr.size()!=0) {
				write.writexml(farr2, newFileName);
			}
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	long endTime = System.currentTimeMillis();
	LOG.info((endTime-startTime)/1000+"초 걸림");
	LOG.info("끝");
}
}