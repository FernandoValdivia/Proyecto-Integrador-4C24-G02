package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.interfaceServices.IproductoService;
import com.example.demo.interfaces.IProducto;
import com.example.demo.modelo.Producto;


@Service
public class ProductoService implements IproductoService{//LLamada de los metodos DECLARADOS en la Interfaz producto servicio

	@Autowired
	private IProducto data;//variable de tipo Interfacz producto (esta contienen los metodos del crud de libreria)

	@Override
	public List<Producto> listar() {
		return (List<Producto>)data.findAll();//metodo "findAll() me lista todos los datos. Este metodo es propio de la "libreria" CrudRepository "
	}
	
	@Override
	public Optional<Producto> listaId(int id) {
		return data.findById(id);
	}

	@Override
	public int save(Producto p) {
		int resp=0;
		Producto persona = data.save(p);//inserto
		if(!persona.equals(null)) {
			resp=1;
		}
		return resp;
	}

	@Override
	public void delete(int id) {
		data.deleteById(id);	
	}
	
}
