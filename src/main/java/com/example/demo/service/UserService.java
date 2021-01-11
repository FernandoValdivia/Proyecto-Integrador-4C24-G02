package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaceServices.IUserService;
import com.example.demo.interfaces.UserRepository;
import com.example.demo.modelo.Producto;
import com.example.demo.modelo.User;



@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository data;

	@Override
	public List<User> listar() {
		return (List<User>)data.findAll();
	}

	@Override
	public Optional<User> listaId(Long id) {
		return data.findById(id);
	}

	@Override
	public int save(User p) {
		int resp=0;
		User usuario = data.save(p);//inserto
		if(!usuario.equals(null)) {
			resp=1;
		}
		return resp;
	}

	@Override
	public void delete(Long id) {
		data.deleteById(id);
	}
	
}
