package com.example.demo.interfaceServices;

import java.util.List;
import java.util.Optional;

import com.example.demo.modelo.User;

public interface IUserService {

	//DECLARO TODOS LOS METODOS DE PRODUCTO
	
	public List<User>listar();
	public Optional<User>listaId(Long id);
	public int save(User p);
	public void delete(Long id);	
}
