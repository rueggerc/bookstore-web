package com.rueggerllc.bookstore.enums;

public enum RequestResultState {
	PASS("pass"), FAIL("fail"), UNKNOWN(null);
	
	public static final String SESSION_KEY = "requestResultState";
	private String value;
	
	private RequestResultState(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public static RequestResultState fromString(String text) {
		if (text != null) {
			for (RequestResultState r : RequestResultState.values()) {
				if (text.equalsIgnoreCase(r.value)) {
					return r;
				}
			}
		}
		return UNKNOWN;
	}
	

}
