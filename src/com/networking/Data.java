package com.networking;

import java.io.Serializable;

public class Data implements Serializable {
	
	private int header;
	private String body;
	
	public Data(int header, String body) {
		this.header = header;
		this.body = body;
	}
	
	public int getHeader() {
		return header;
	}
	public void setHeader(int header) {
		this.header = header;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}

}
