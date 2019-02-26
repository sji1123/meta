package com.meta.run;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.meta.analyze.LogAnalyze;
import com.meta.dto.Log;
import com.meta.dto.LogData;
import com.meta.inputOutput.FileRead;
import com.meta.inputOutput.FileRead;

public class Main {
 
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
 
		FileRead readFile = new FileRead();
		try {
			//�α����� �м��� Ŭ����
			LogAnalyze analyzeFile = new LogAnalyze();
			
			//�α������� ���پ� �о� �����·� ����
			Map<Integer, Log> result = readFile.read("galileo.log");
			
			//����� �����¸� �м�
			analyzeFile.analyzeLogFile(result);
			
			Map<Integer, LogData> result1 = readFile.ReadFormat("FILE1.txt");
			
			analyzeFile.AnalyzeLogTime(result1);
			long end = System.currentTimeMillis();

			long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

			System.out.println("used memory is " + used/1024+ " KB");
			System.out.println( "���� �ð� : " + ( end - start ) );

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
