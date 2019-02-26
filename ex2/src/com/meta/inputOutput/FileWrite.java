package com.meta.inputOutput;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.meta.dto.LogData;
import com.meta.dto.LogTimeData;

public class FileWrite {
	   
	public void WriteFile(List<LogData> newLogData) {
		
		FileWriter fw = null;
		BufferedWriter bw =null;
		try {
			fw = new FileWriter("FILE1.txt");
			bw = new BufferedWriter(fw);
			
			for(int i = 0 ; i < newLogData.size(); i++) {
				
				bw.write(newLogData.get(i).getGalileo_start()+",");
				bw.write(newLogData.get(i).getGalileo_end()+",");
				bw.write(newLogData.get(i).getEsb_trans_id()+",");
				bw.write(newLogData.get(i).getContent_length()+",");
				bw.write(newLogData.get(i).getGalileo_call()+",");
				bw.write(newLogData.get(i).getBefore()+",");
				bw.write(newLogData.get(i).getMarsharlling()+",");
				bw.write(newLogData.get(i).getInvoking()+",");
				bw.write(newLogData.get(i).getUnmarshalling());
				bw.newLine();
	
			}
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void WriteFile2(ArrayList<LogTimeData> arr) {
		FileWriter fw = null;
		BufferedWriter bw =null;
		try {
			fw = new FileWriter("FIlE2.txt");
			bw = new BufferedWriter(fw);
			
			for(int i = 0 ; i < arr.size(); i++) {
				
				bw.write(arr.get(i).getTime()+",");
				bw.write(arr.get(i).getCompleted()+",");
				bw.write(arr.get(i).getAvgTime()+",");
				bw.write(arr.get(i).getMinTime()+",");
				bw.write(arr.get(i).getMaxTime()+",");
				bw.write(arr.get(i).getAvgSize()+",");
				bw.write(arr.get(i).getMinSize()+",");
				bw.write(arr.get(i).getMaxSize());
				bw.newLine();
	
			}
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
