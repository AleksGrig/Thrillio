package com.aurelius.thrillio.constants;

public enum UserType {

	USER("user"), EDITOR("editor"), CHIEF_EDITOR("chief_editor");

	private UserType(String user) {
		this.user = user;
	}

	private String user;

	public String getUser() {
		return user;
	}
}
