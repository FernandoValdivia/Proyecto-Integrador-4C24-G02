package com.example.demo.controler;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.modelo.Categoria;

@Controller
public class AppController {

	// CONTROLADORES PARA SESION (LOGIN)
	
	@GetMapping({"/","/login"})
	public String index() {
		return "login";
	}
	
	/*@GetMapping("/menu")
	public String hello() {
		return "menuS";
	}*/
	
	@GetMapping("/user")
	public String user(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		model.addAttribute("auth", auth);
		return "user";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}
	@GetMapping("/about")
	public String about() {
		return "about";
	}
}
