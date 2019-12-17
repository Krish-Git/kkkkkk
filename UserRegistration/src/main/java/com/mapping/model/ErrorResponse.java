package com.mapping.model;

public class ErrorResponse {

	private Integer status;
	private String message;

	public ErrorResponse() {
		super();
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
		return "ErrorResponse [status=" + status + ", message=" + message + "]";
	}

}
