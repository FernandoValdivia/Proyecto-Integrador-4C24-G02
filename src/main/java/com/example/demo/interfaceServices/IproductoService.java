package com.example.demo.interfaceServices;

import java.util.List;
import java.util.Optional;

import com.example.demo.modelo.Producto;


public interface IproductoService {

	//DECLARO TODOS LOS METODOS DE PRODUCTO
	
	public List<Producto>listar();//Metodo LISTAR PERSONA
	public Optional<Producto>listaId(int id);//metodo de tipo OPTIONAL (lista x ID)
	public int save(Producto p);
	public void delete(int id);
	
}
