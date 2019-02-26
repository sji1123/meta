package com.meta.dto;
  
public class LogData {
	private String galileo_start;
	private String esb_trans_id;
	private String content_length;
	private String galileo_call;
	private String before;
	private String marsharlling;
	private String invoking;
	private String unmarshalling;
	private String galileo_end;
	
	public LogData() {};
	
	public LogData(String galileo_start, String esb_trans_id, String content_length, String galileo_call, String before,
			String marsharlling, String invoking, String unmarshalling, String galileo_end) {
		super();
		this.galileo_start = galileo_start;
		this.esb_trans_id = esb_trans_id;
		this.content_length = content_length;
		this.galileo_call = galileo_call;
		this.before = before;
		this.marsharlling = marsharlling;
		this.invoking = invoking;
		this.unmarshalling = unmarshalling;
		this.galileo_end = galileo_end;
	}

	public String getGalileo_start() {
		return galileo_start;
	}

	public void setGalileo_start(String galileo_start) {
		this.galileo_start = galileo_start;
	}

	public String getEsb_trans_id() {
		return esb_trans_id;
	}

	public void setEsb_trans_id(String esb_trans_id) {
		this.esb_trans_id = esb_trans_id;
	}

	public String getContent_length() {
		return content_length;
	}

	public void setContent_length(String content_length) {
		this.content_length = content_length;
	}

	public String getGalileo_call() {
		return galileo_call;
	}

	public void setGalileo_call(String galileo_call) {
		this.galileo_call = galileo_call;
	}

	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	public String getMarsharlling() {
		return marsharlling;
	}

	public void setMarsharlling(String marsharlling) {
		this.marsharlling = marsharlling;
	}

	public String getInvoking() {
		return invoking;
	}
	public void setInvoking(String invoking) {
		this.invoking = invoking;
	}

	public String getUnmarshalling() {
		return unmarshalling;
	}

	public void setUnmarshalling(String unmarshalling) {
		this.unmarshalling = unmarshalling;
	}

	public String getGalileo_end() {
		return galileo_end;
	}

	public void setGalileo_end(String galileo_end) {
		this.galileo_end = galileo_end;
	}

	@Override
	public String toString() {
		return "LogData [galileo_start=" + galileo_start + ", esb_trans_id=" + esb_trans_id + ", content_length="
				+ content_length + ", galileo_call=" + galileo_call + ", before=" + before + ", marsharlling="
				+ marsharlling + ", invoking=" + invoking + ", unmarshalling=" + unmarshalling + ", galileo_end="
				+ galileo_end + "]";
	}
	
}
