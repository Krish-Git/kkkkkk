package com.mapping.controller;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapping.model.ErrorResponse;
import com.mapping.model.User;
import com.mapping.service.RegistrationServiceImpl;
import com.mapping.util.Validation;

@RestController
@RequestMapping("/reg")
public class RegistrationResource {

	@Autowired
	private RegistrationServiceImpl service;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	ErrorResponse error = null;

	@PostMapping("/save")
	public ResponseEntity<Object> createRegistration(@RequestBody User user) {
		try {
			logger.info("data received into controller");
			error = new ErrorResponse();
			for (Field field : user.getClass().getDeclaredFields()) {
				user.setId(5L);
				field.setAccessible(true);
				String obj = field.get(user).toString();
				if (obj == null || obj.trim().isEmpty()) {
					logger.error("request object is null");
					error.setStatus(422);
					error.setMessage(field.getName() + " is missing, please provide " + field.getName());
					return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
				}
			}
			if (!Validation.isNameValidation(user.getFirstName())) {
				error.setStatus(422);
				error.setMessage("please provide alphabets only");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (!Validation.isNameValidation(user.getLastName())) {
				error.setStatus(422);
				error.setMessage("please provide alphabets only");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (Validation.isValidDate(user.getDateOfBirth())) {
				error.setStatus(422);
				error.setMessage("please provide valid date of birth.");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (user.getMobileNum().chars().allMatch(x -> Character.isDigit(x)) == false) {
				error.setMessage("enter number only");
				error.setStatus(422);
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (user.getMobileNum().length() != 10) {
				error.setMessage("mobile number must be 10 numbers");
				error.setStatus(422);
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (!Validation.isValidGender(user.getGender())) {
				error.setStatus(422);
				error.setMessage("please provide the valid gender");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (!Validation.isValidateEmail(user.getEmail())) {
				error.setStatus(422);
				error.setMessage("please provide the valid email");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			if (!Validation.isValidatePassword(user.getPassword())) {
				error.setStatus(422);
				error.setMessage("password must have 8 characters and above. Use characters,symbols and numbers");
				return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
			}
			return service.createRegistration(user);
		} catch (IllegalAccessException e) {
			error.setStatus(422);
			error.setMessage("Illegal access exception");
			return new ResponseEntity<Object>(error, HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			logger.error("fail to create registration");
			error.setStatus(409);
			error.setMessage("fail to create Registration");
			return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		}
	}

}
