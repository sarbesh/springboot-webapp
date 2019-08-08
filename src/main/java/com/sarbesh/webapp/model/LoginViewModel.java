package com.sarbesh.webapp.model;

public class LoginViewModel {
    private long Id;
    private String password;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}