package com.mapping.service;

import org.springframework.http.ResponseEntity;

import com.mapping.model.User;

public interface RegistrationService {

	public ResponseEntity<Object> createRegistration(User user);
}
