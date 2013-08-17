package com.antwei.meetingdoc.entity;

public class Doc {

	private String name;
	private String date;
	private String path;
	
	public Doc(String name, String date, String path) {
		this.name = name;
		this.date = date;
		this.path = path;
	}

	public Doc() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	
	
	
}
