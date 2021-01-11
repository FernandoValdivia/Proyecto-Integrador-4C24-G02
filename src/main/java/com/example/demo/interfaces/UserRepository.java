package com.example.demo.interfaces;

import java.util.Optional;

import javax.management.relation.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public Optional<User> findByUsername(String username);
	
}
