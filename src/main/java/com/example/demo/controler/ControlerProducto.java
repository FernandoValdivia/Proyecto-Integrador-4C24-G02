package com.example.demo.controler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.interfaceServices.IproductoService;
import com.example.demo.modelo.Producto;

@Controller  
@RequestMapping
public class ControlerProducto {

	@Autowired
	private IproductoService service;
	
	@GetMapping("/index")
	public String menu(Model model) {
		return "index";//vista index (Pagina principal)
	}
	
	@GetMapping("/admin/listar")
	public String listar(Model model) {
		List<Producto>productos = service.listar();
		model.addAttribute("productos",productos);
		return "listaProductos";//en la vista index envio la lista de productos
	}

	//muestro formulario de registro
	@GetMapping("/admin/new")
	public String agregar(Model model) {
		model.addAttribute("producto", new Producto());
		return "registroProducto";
	}
	
	//GUARDO REGISTRO NUEVO
	@PostMapping("save")
	public String save(@Validated Producto p, Model model) {
		service.save(p);
		return "redirect:/admin/listar";
	}

	//metodo para ACTUALIZO DATOS
	@GetMapping("/admin/editar/{id}")
	public String editar(@PathVariable int id, Model modelo) { //@pathVariable es para el parametro que le paso (ID)
		Optional<Producto> producto = service.listaId(id);//trae los datos de persona (id).
		modelo.addAttribute("producto",producto);
		return "registroProducto";//envio al mismo formulario de Registro pero con los datos de la persona ID
	}
	
	@GetMapping("/admin/eliminar/{id}")
	public String delete(Model modelo, @PathVariable int id) {
		service.delete(id);
		return "redirect:/admin/listar";	
	}
	
}
