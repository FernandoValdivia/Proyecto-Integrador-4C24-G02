package com.example.demo.controler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	// CONTROLADORES PARA SESION (LOGIN)
	
	@GetMapping({"/","/login"})
	public String index() {
		return "login";
	}
	
	@GetMapping("/menu")
	public String hello() {
		return "menuS";
	}
	
	@GetMapping("/user")
	public String user() {
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
