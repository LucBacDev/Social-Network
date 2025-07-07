package com.doan.MyWechat.com.Common;

public enum UserStatus {
	ONLINE("online"),OFFLINE("offline");
	
	private String value;
	UserStatus(String value) {
		this.value=value;
	}
	
	public String getValue() {
		return this.value;
	}
}
