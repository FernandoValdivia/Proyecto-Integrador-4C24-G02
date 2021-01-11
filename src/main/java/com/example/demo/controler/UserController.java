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

import com.example.demo.interfaceServices.IUserService;
import com.example.demo.modelo.User;

@Controller
@RequestMapping
public class UserController {
	
	@Autowired
	private IUserService service;
	
	@GetMapping("/admin/listaUsuarios")
	public String listaUsers(Model model) {
		List<User>usuarios = service.listar();
		model.addAttribute("usuarios",usuarios);
		return "listaUsuario";
	}
	
	@GetMapping("/admin/nuevoUser")
	public String agregar(Model model) {
		model.addAttribute("usuario", new User());
		model.addAttribute("titulo", "Nuevo Usuario");
		return "registroUsuario";
	}
	
	@PostMapping("admin/saveUsuario")
	public String save(@Validated User p, Model model) {
		service.save(p);
		return "redirect:/admin/listaUsuarios";
	}

	//metodo para ACTUALIZO DATOS
	@GetMapping("/admin/editarUsuario/{id}")
	public String editar(@PathVariable Long id, Model modelo) { 
		Optional<User> usuario = service.listaId(id);
		modelo.addAttribute("usuario",usuario);
		modelo.addAttribute("titulo", "Actualizar Usuario");
		return "registroUsuario";
	}
	
	@GetMapping("/admin/eliminarUsuario/{id}")
	public String delete(Model modelo, @PathVariable Long id) {
		service.delete(id);
		return "redirect:/admin/listarCategoria";	
	}

}
