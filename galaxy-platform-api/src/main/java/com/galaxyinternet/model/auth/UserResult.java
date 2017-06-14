package com.galaxyinternet.model.auth;

import com.galaxyinternet.model.user.User;

public class UserResult {
	private boolean success;
	private String errorCode;
	private String message;
	private User value;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getValue() {
		return value;
	}

	public void setValue(User value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "AuthResult [success=" + success + ", errorCode=" + errorCode + ", message=" + message + ", value="
				+ value + "]";
	}
	
}
