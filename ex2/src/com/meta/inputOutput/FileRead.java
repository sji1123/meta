package com.meta.inputOutput;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.meta.dto.Log;
import com.meta.dto.LogData;
 
public class FileRead {

	public Map<Integer, Log> read(String filePath) throws IOException{
		FileReader fileReader = new FileReader(filePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String line ="";
		//�α������� �ٴ����� ����� �� ��ü����
		Map<Integer, Log> logMap = new HashMap<Integer, Log>();
		//���� Ű��
		int lineNum = 1;
		
		while((line = bufferedReader.readLine()) != null) {
			//���� �о �αװ�ü�� ������ ����
			Log log = new Log(line, lineNum);
			//�α� ��ü�� Ű���� �Բ� �ʿ� ����
			logMap.put(lineNum, log);
			
			lineNum++;
		}
		bufferedReader.close();
		fileReader.close();
		return logMap;
	}
	
	public Map<Integer, LogData> ReadFormat(String filePath) throws IOException{
		FileReader fileReader = new FileReader(filePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<LogData> logDataArr= new ArrayList<LogData>();

		String line = "";
		Map<Integer, LogData> logDataMap = new HashMap<Integer, LogData>();
		
		int lineNum = 1;
		
		while((line = bufferedReader.readLine()) != null) {
				String[] cut = line.split(",");
				String start = cut[0];
				String end = cut[1];
				String id = cut[2];
				String length = cut[3];
				String call = cut[4];
				String before = cut[5];
				String marshalling = cut[6];
				String invoking = cut[7];
				String unmarshalling = cut[8];
			
				LogData logData = new LogData(start, id, length, call, before,
											marshalling, invoking , unmarshalling, end);
				logDataMap.put(lineNum, logData);
				lineNum++;
			}
		return logDataMap;
	}
}
