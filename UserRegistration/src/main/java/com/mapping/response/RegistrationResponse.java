package com.mapping.response;

import com.mapping.model.User;

public class RegistrationResponse {

	private Integer status;
	private String message;
	private User user;

	public RegistrationResponse() {
		super();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "RegistrationResponse [status=" + status + ", message=" + message + ", user=" + user + "]";
	}

}
