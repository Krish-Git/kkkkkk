package com.mapping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mapping.model.User;
import com.mapping.repo.RegistrationDao;
import com.mapping.response.RegistrationResponse;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationDao dao;

	RegistrationResponse response = null;
	
	public ResponseEntity<Object> createRegistration(User user) {
		User save = dao.save(user);
		if(save != null) {
			response = new RegistrationResponse();
			response.setStatus(200);
			response.setMessage("successfully created registration");
			response.setUser(user);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		}else {
			response = new RegistrationResponse();
			response.setStatus(422);
			response.setMessage("fail to create registration");
			response.setUser(new User());
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	
	
}
