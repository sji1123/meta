package com.meta.dto;
 
public class Log {
	
	private String line;
	private int lineNum;
	private int threadNum;
	private Taskname taskName;
	
	public Log() {}

	public Log(String line, int lineNum) {
		super();
		this.line = line;
		this.lineNum = lineNum;
	}
	public Log(String line, int threadNum, Taskname taskName , int lineNum) {
		super();
		this.line = line;
		this.threadNum = threadNum;
		this.taskName = taskName;
		this.lineNum = lineNum;
	}
	public Log(String line, Taskname taskName) {
		super();
		this.line = line;
		this.taskName = taskName;
	}

	public Log(String line, int threadNum , int lineNum) {
		super();
		this.line = line;
		this.threadNum = threadNum;
		this.lineNum = lineNum;
	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public int getLineNum() {
		return lineNum;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public Taskname getTaskName() {
		return taskName;
	}
	
	public void setTaskName(Taskname taskName) {
		this.taskName = taskName;
	}
	
	@Override
	public String toString() {
		return "Log [line=" + line + ", lineNum=" + lineNum + ", threadNum=" + threadNum + ", taskName=" + taskName
				+ "]";
	};
	
	
}
