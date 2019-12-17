package com.mapping.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mapping.model.User;

@Repository
public interface RegistrationDao extends JpaRepository<User, Long> {

}
