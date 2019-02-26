package com.meta.dto;

public class Taskname {
	 
	private String before;
	private String marshalling;
	private String invoking;
	private String unmarshalling;
	
	public Taskname() {};

	public Taskname(String before, String marshalling, String invoking, String unmarshalling) {
		super();
		this.before = before;
		this.marshalling = marshalling;
		this.invoking = invoking;
		this.unmarshalling = unmarshalling;
	}
	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	public String getMarshalling() {
		return marshalling;
	}

	public void setMarshalling(String marshalling) {
		this.marshalling = marshalling;
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
	@Override
	public String toString() {
		return "Taskname [before=" + before + ", marshalling=" + marshalling + ", invoking=" + invoking + ", unmarshalling="
				+ unmarshalling;
	}
}
