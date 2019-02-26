package com.meta.dto;
  
public class LogTimeData {
	private String time;
	private String completed;
	private String avgTime;
	private String minTime;
	private String maxTime;
	private String avgSize;
	private String minSize;
	private String maxSize;
	
	public LogTimeData() {}
	
	public LogTimeData(String time, String completed, String avgTime, String minTime, String maxTime, String avgSize,
			String minSize, String maxSize) {
		super();
		this.time = time;
		this.completed = completed;
		this.avgTime = avgTime;
		this.minTime = minTime;
		this.maxTime = maxTime;
		this.avgSize = avgSize;
		this.minSize = minSize;
		this.maxSize = maxSize;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
	}

	public String getMinTime() {
		return minTime;
	}

	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}

	public String getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(String maxTime) {
		this.maxTime = maxTime;
	}

	public String getAvgSize() {
		return avgSize;
	}

	public void setAvgSize(String avgSize) {
		this.avgSize = avgSize;
	}

	public String getMinSize() {
		return minSize;
	}

	public void setMinSize(String minSize) {
		this.minSize = minSize;
	}

	public String getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	public String toString() {
		return "LogTimeData [time=" + time + ", completed=" + completed + ", avgTime=" + avgTime + ", minTime="
				+ minTime + ", maxTime=" + maxTime + ", avgSize=" + avgSize + ", minSize=" + minSize + ", maxSize="
				+ maxSize + "]";
	};
	
	
}
