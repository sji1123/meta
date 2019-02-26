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
			//로그파일 분석할 클래스
			LogAnalyze analyzeFile = new LogAnalyze();
			
			//로그파일을 한줄씩 읽어 맵형태로 저장
			Map<Integer, Log> result = readFile.read("galileo.log");
			
			//저장된 맵형태를 분석
			analyzeFile.analyzeLogFile(result);
			
			Map<Integer, LogData> result1 = readFile.ReadFormat("FILE1.txt");
			
			analyzeFile.AnalyzeLogTime(result1);
			long end = System.currentTimeMillis();

			long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

			System.out.println("used memory is " + used/1024+ " KB");
			System.out.println( "실행 시간 : " + ( end - start ) );

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
