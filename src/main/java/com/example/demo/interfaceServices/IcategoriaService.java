package com.example.demo.interfaceServices;

import java.util.List;
import java.util.Optional;

import com.example.demo.modelo.Categoria;


public interface IcategoriaService{

	//DECLARO TODOS LOS METODOS DE CATEGORIA
	
		public List<Categoria>listar();
		public Optional<Categoria>listaId(int id);
		public int save(Categoria p);
		public void delete(int id);
}
