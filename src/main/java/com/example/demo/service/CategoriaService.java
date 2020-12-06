package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaceServices.IcategoriaService;
import com.example.demo.interfaces.ICategoria;
import com.example.demo.modelo.Categoria;

@Service
public class CategoriaService implements IcategoriaService{

	@Autowired
	private ICategoria data;
	
	@Override
	public List<Categoria> listar() {
		return (List<Categoria>)data.findAll();
	}

	@Override
	public Optional<Categoria> listaId(int id) {
		return data.findById(id);
	}

	@Override
	public int save(Categoria c) {
		int resp=0;
		Categoria categoria = data.save(c);
		if(!categoria.equals(null)) {
			resp=1;
		}
		return resp;
	}

	@Override
	public void delete(int id) {
		data.deleteById(id);	
	}

	
}
