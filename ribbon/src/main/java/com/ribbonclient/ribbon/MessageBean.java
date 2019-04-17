package com.ribbonclient.ribbon;

public class MessageBean {

	private int id;
	private String value;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public MessageBean(int id, String value) {
		super();

		this.id = id;
		this.value = value;
	}
	public MessageBean() {
		super();
	}



}
