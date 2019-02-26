package com.meta.analyze;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.meta.dto.Log;
import com.meta.dto.LogData;
import com.meta.dto.LogTimeData;
import com.meta.dto.Taskname;
import com.meta.inputOutput.FileWrite;
 
public class LogAnalyze {
	//파일1,파일2를 생성하기 위한 클래스변수
	FileWrite fw = new FileWrite();
	/*
	 * 로그파일의 줄번호와 해당 줄의 텍스트를 각각 key값 Log객체로 맵에 담아왔다.
	 * foreach문과 contain메서드를 이용하여 추출 키워드를 담고 있는 Log객체를 ThreadNum과 LineNum와 함께 생성하여 새로운 리스트에 담아준다.
	 * ThreadNum으로 정렬한 후 LineNum로 정렬하면  ThreadNum가 낮은 순 으로 우선 정렬되고 Log기록에 빨리 나온 순으로 재정렬된다.
	 * 새로 정렬된 리스트는 for문을 이용하여 추출 키워드를 모두 가지고있는 경우에만 LogData list에 담아준다.
	 */
	public void analyzeLogFile(Map<Integer, Log> result) {
				
		List<Log> newLogList = new ArrayList<Log>();
		
		for(int key : result.keySet()) {
				//해당 조건을 만족할 경우 threadId 값과 함께 Log정보, 로그파일에서 몇번째 줄인지에 대한 정보를  newLogList에 담는다.
				if(result.get(key).getLine().contains("##galileo_bean start")) {
					result.get(key).setThreadNum(Integer.parseInt(result.get(key).getLine().substring(58, 66)));
					Log log = new Log(result.get(key).getLine(),result.get(key).getThreadNum(),result.get(key).getLineNum()); 
					newLogList.add(log);
				}
				if(result.get(key).getLine().contains("##galileo_bean end")) {
					result.get(key).setThreadNum(Integer.parseInt(result.get(key).getLine().substring(58, 66)));
					Log log = new Log(result.get(key).getLine(),result.get(key).getThreadNum(),result.get(key).getLineNum());
					newLogList.add(log);
				}
				if(result.get(key).getLine().contains("ESB_TRAN_ID :")) {
					result.get(key).setThreadNum(Integer.parseInt(result.get(key).getLine().substring(58, 66)));
					Log log = new Log(result.get(key).getLine(),result.get(key).getThreadNum(),result.get(key).getLineNum());
					newLogList.add(log);
				}
				if(result.get(key).getLine().contains("Content-Length:")) {
					result.get(key).setThreadNum(Integer.parseInt(result.get(key).getLine().substring(58, 66)));
					Log log = new Log(result.get(key).getLine(),result.get(key).getThreadNum(),result.get(key).getLineNum());
					newLogList.add(log);
				} 
				if(result.get(key).getLine().contains("#galileo call time")) {
					result.get(key).setThreadNum(Integer.parseInt(result.get(key).getLine().substring(58, 66)));
					Log log = new Log(result.get(key).getLine(),result.get(key).getThreadNum(),result.get(key).getLineNum());
					newLogList.add(log);
				}
				if(result.get(key).getLine().contains("StopWatch")){
		               Taskname taskname = new Taskname();
		               result.get(key).setThreadNum(Integer.parseInt(result.get(key).getLine().substring(58, 66)));
		               //StopWatch를 포함한 로그를 만나게되면 현재에서 다음 4번째줄에 taskName데이터가 나오는데 필요한 데이터 정보인지 확인하는 절차를 거친다.
		               if(result.get(key+4).getLine().contains("Before Marshalling")){
		                  taskname.setBefore(result.get(key+4).getLine());
		               }else{
		                  taskname.setBefore(null);
		               }
		               if(result.get(key+4).getLine().contains("Marshalling")){
		                  taskname.setMarshalling(result.get(key+5).getLine());
		               }else{
		                  taskname.setMarshalling(null);
		               }
		               if(result.get(key+6).getLine().contains("Invoking galileo")){
		                  taskname.setInvoking(result.get(key+6).getLine());
		               }else{
		                  taskname.setInvoking(null);
		               }
		               if(result.get(key+7).getLine().contains("Unmarshalling")){
		                  taskname.setUnmarshalling(result.get(key+7).getLine());
		               }else{
		                  taskname.setUnmarshalling(null);
		               }
		               Log log = new Log(result.get(key).getLine(),result.get(key).getThreadNum(),taskname,result.get(key).getLineNum());
		               //
		               if(taskname.getBefore()!=null && taskname.getMarshalling()!=null && taskname.getInvoking()!=null && taskname.getUnmarshalling()!=null)
		               {
		               newLogList.add(log);
		               }
		            }
			}

		newLogList.sort(Comparator.comparing(Log::getThreadNum).thenComparing(Log::getLineNum));
		//6개의 값이 들어온지 체크하기위한 변수
		int tempNum=0;
		
		LogData logData = null;
		
		List<LogData> logDataList = new ArrayList<LogData>();
			//추출키워드가 포함되어있는 정렬된 데이터를 for문으로 값이 모두 들어있나 확인한다.
			for(int i = 0 ; i < newLogList.size(); i++) {
					//tempNum이 0일 경우 LogData객체를 생성한다.
					if(tempNum==0) {
						logData = new LogData();
					}
					//Start를 만날 경우 tempNum은 0으로 초기화되고, logData의 값을 셋팅 후 tempNum은 하나 증가한다.
					if(newLogList.get(i).getLine().contains("##galileo_bean start.")) {
						tempNum = 0;
						logData.setGalileo_start(newLogList.get(i).getLine().substring(1, 18));
						tempNum++;
					}//ESB_TRAN_ID를 만날 경우 logData의 값을 셋팅 후 tempNum은 하나 증가한다.
					if(newLogList.get(i).getLine().contains("ESB_TRAN_ID :")) {
						logData.setEsb_trans_id(newLogList.get(i).getLine().substring(111));
						tempNum++;
					}//Content-Length:를 만날 경우 logData의 값을 셋팅 후 tempNum은 하나 증가한다.
					if(newLogList.get(i).getLine().contains("Content-Length:")) {
						logData.setContent_length(newLogList.get(i).getLine().substring(124));
						tempNum++;
					}//#galileo call time 를 만날 경우 logData의 값을 셋팅 후 tempNum은 하나 증가한다.
					if(newLogList.get(i).getLine().contains("#galileo call time")) {
						logData.setGalileo_call(newLogList.get(i).getLine().substring(131).replace(" ms", ""));
						tempNum++;
					}//StopWatch 를 만날 경우 logData의 값을 셋팅 후 tempNum은 하나 증가한다.
					if(newLogList.get(i).getLine().contains("StopWatch")) {
							logData.setBefore(newLogList.get(i).getTaskName().getBefore().substring(0, 5));
							logData.setMarsharlling(newLogList.get(i).getTaskName().getMarshalling().substring(0, 5));
							logData.setInvoking(newLogList.get(i).getTaskName().getInvoking().substring(0, 5));
							logData.setUnmarshalling(newLogList.get(i).getTaskName().getUnmarshalling().substring(0, 5));
							tempNum++;
					}//galileo_bean end를 만날 경우 logData의 값을 셋팅 후 tempNum은 하나 증가한다.
					if(newLogList.get(i).getLine().contains("galileo_bean end")) {
							logData.setGalileo_end(newLogList.get(i).getLine().substring(1, 18));
							tempNum++;
					}
					//모든 데이터를 포함할 경우 tempNum은 6이되고 set된 logData를 리스트 객체에 담을수 있다.
					//tempNum이 6이 아닌경우 start를 다시 만나 0부터 시작하며 logData를 다시 세팅한다.
					//start부터 다음 start까지는 ID, calltime, 4개의 task, end중  데이터가 소실 될 수는 있어도 2개이상 들어있지 않다고 가정했다.
					//리스트에 담은 후에는 tempNum을 0으로 초기화하여 새로운 객체를 생성할수 있게 한다.
					if(tempNum==6 && newLogList.get(i).getLine().contains("galileo_bean end")) {
						logDataList.add(logData);
						tempNum = 0;
					}
			}
			fw.WriteFile(logDataList);
}
	/*
	 * FILE1의 추출된 데이터를 ArrayList에 담아준 후 시작시간 순으로 정렬하고
	 * 년월일 시분까지 잘라내어 동일한 경우 리스트에 담고 분이 달라지면 맵에 담는다.
	 * 맵의 키값마다 동일한 시간의 데이터들이 리스트형태로 들어가 있으므로 필요한 데이터를 계산한다.
	 * @param Map<Integer, LogData> result1
	 * @return void
	 * */
	public void AnalyzeLogTime(Map<Integer, LogData> result1) {
		
			Iterator<Integer> it = result1.keySet().iterator();
			ArrayList<LogData> logDataArr = new ArrayList<LogData>();
			
			while(it.hasNext()) {
				int key = it.next();
				logDataArr.add((result1.get(key)));
			}
			
			logDataArr.sort(Comparator.comparing(LogData::getGalileo_start));
			int temp = 0;
			HashMap<Integer, Object> map = new HashMap<Integer, Object>();
			ArrayList<LogData> logtime = new ArrayList<LogData>();
			String time = "";
			for(int i = 0 ; i < logDataArr.size(); i++) {
				
				//time변수와 i번째 시작시간이 같을 경우 logtime 리스트에 LogData를 추가.
				if(time.equals(logDataArr.get(i).getGalileo_start().substring(0, 14))) {
					logtime.add(logDataArr.get(i));
				}else{
				//time변수와 i번째 시작시간이 다를경우 새로운 LogData 객체 생성 
				//time 변수는 바뀐 시간으로 셋팅
				//map 에 키값과 같은시간동안 담긴 리스트를 삽입
				//새로운 logtime리스트에 바뀐 시간 데이터를 삽입
				//키값으로 쓸 temp값 증가
					logtime = new ArrayList<LogData>();
					time = logDataArr.get(i).getGalileo_start().substring(0, 14);
					map.put(temp, logtime);
					logtime.add(logDataArr.get(i));
					temp++;
				}
			}
			
			ArrayList<LogTimeData> arr = new ArrayList<LogTimeData>();
			
			//키값을 이용해 map에 담긴  리스트를 꺼낸후
			//calculate메서드에 담아 필요한 데이터를 정제후
			//return 받은 LogTimeData 를 리스트에 추가한다.
			for(int i = 0 ; i < map.size(); i++) {
				
				ArrayList<LogData> log = (ArrayList<LogData>) map.get(i);
				LogTimeData ltd1;
				try {
					ltd1 = calculate(log);
					arr.add(ltd1);
				} catch (ParseException e) {
					e.printStackTrace();
				}				
			}
			fw.WriteFile2(arr);
	}
	
	/*
	 * 시작시간,처리건수, 평균소요시간, 최소시간, 최대시간, 평균사이즈, 최소사이즈, 최대사이즈를 구하는 메소드
	 * @param ArrayList<LogData> log
	 * @return LogTimeData
	 * */
	private LogTimeData calculate(ArrayList<LogData> log) throws ParseException {
		String startTime = log.get(0).getGalileo_start()+"";
		String completed = log.size()+"";
		String endTime = "";
		String avgTime = "";
		String minTime = "";
		String maxTime = "";
		String avgSize = "";
		String minSize = "";
		String maxSize = "";
		
		long spendTime[] = new long[log.size()];
		
		for(int i = 0 ; i < log.size(); i++) {
			startTime = log.get(i).getGalileo_start();
			endTime = log.get(i).getGalileo_end();
			SimpleDateFormat dt = new SimpleDateFormat("yy.mm.dd hh:mm:ss");
			Date start = dt.parse(startTime);
			Date end = dt.parse(endTime);			
			spendTime[i] += end.getTime() - start.getTime();
		}
		
		long sum = 0;
		for(int j = 0 ; j < spendTime.length; j++) {
			sum += spendTime[j];
		}
		
		//최소시간, 최대시간을 추출하기 위해 오름차순으로 정렬
		for (int i = 0; i < spendTime.length; i++) { 
	            for(int t = 0; t < spendTime.length - 1; t++) {
	                if (spendTime[t] > spendTime[t + 1]) {
	                    long temp = spendTime[t];
	                    spendTime[t] = spendTime[t+1];
	                    spendTime[t + 1] = temp;
	                }
	            }
		 }
		minTime = spendTime[0]+"";
		maxTime = spendTime[log.size()-1]+"";
		avgTime = (long) (sum/log.size())+"";
		
		int contentLength[] = new int[log.size()];
		int contentSum = 0;
		
		for(int i = 0 ; i < log.size(); i++) {
			contentLength[i] += Integer.parseInt(log.get(i).getContent_length());
			contentSum += Integer.parseInt(log.get(i).getContent_length());
		}
		//최소사이즈, 최대사이즈를 구하기위해 배열을 오름차순 정렬
		for (int i = 0; i < contentLength.length; i++) { 
            for(int t = 0; t < contentLength.length - 1; t++) { 
                if (contentLength[t] > contentLength[t + 1]) {
                    int temp = contentLength[t];
                    contentLength[t] = contentLength[t+1];
                    contentLength[t + 1] = temp;
                }
            }
		}
		avgSize = contentSum / log.size() +"";
		minSize = contentLength[0]+"";
		maxSize = contentLength[log.size()-1]+"";
		
		LogTimeData ltd = new LogTimeData(startTime,completed,avgTime,minTime,maxTime,avgSize,minSize,maxSize);
		return ltd;
	}
}