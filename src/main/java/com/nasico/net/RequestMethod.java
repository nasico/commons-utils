package com.nasico.net;

public enum RequestMethod {
	
	GET("GET"),POST("POST");
	
	private String method;
	RequestMethod(String method){
		this.method = method;
	}
	public String getMethod() {
		return method;
	}
	
}
