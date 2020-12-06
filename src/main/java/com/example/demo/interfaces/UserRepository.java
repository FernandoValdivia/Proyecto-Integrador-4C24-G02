package com.example.demo.interfaces;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.modelo.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public Optional<User> findByUsername(String username);
}
