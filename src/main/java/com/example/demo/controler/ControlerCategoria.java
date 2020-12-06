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

import com.example.demo.interfaceServices.IcategoriaService;
import com.example.demo.modelo.Categoria;

@Controller
@RequestMapping
public class ControlerCategoria {

	@Autowired
	private IcategoriaService service;
	
	
	@GetMapping("/admin/listarCategoria")
	public String listar(Model model) {
		List<Categoria>categorias = service.listar();
		model.addAttribute("categorias",categorias);
		return "listaCategoria";
	}

	@GetMapping("/admin/newCategoria")
	public String agregar(Model model) {
		model.addAttribute("categoria", new Categoria());
		return "registroCategoria";
	}
	
	@PostMapping("admin/saveCategoria")
	public String save(@Validated Categoria p, Model model) {
		service.save(p);
		return "redirect:/admin/listarCategoria";
	}

	//metodo para ACTUALIZO DATOS
	@GetMapping("/admin/editarCategoria/{id}")
	public String editar(@PathVariable int id, Model modelo) { //@pathVariable es para el parametro que le paso (ID)
		Optional<Categoria> categoria = service.listaId(id);//trae los datos de persona (id).
		modelo.addAttribute("categoria",categoria);
		return "registroCategoria";//envio al mismo formulario de Registro pero con los datos de la persona ID
	}
	
	@GetMapping("/admin/eliminarCategoria/{id}")
	public String delete(Model modelo, @PathVariable int id) {
		service.delete(id);
		return "redirect:/admin/listarCategoria";	
	}
}
