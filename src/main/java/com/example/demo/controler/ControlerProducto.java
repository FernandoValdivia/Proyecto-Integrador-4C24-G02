package com.example.demo.controler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.interfaceServices.IcategoriaService;
import com.example.demo.interfaceServices.IproductoService;
import com.example.demo.modelo.Producto;


@Controller  
@RequestMapping
public class ControlerProducto {

	@Autowired
	private IproductoService service;
	
	@Autowired
	private IcategoriaService categoriaService;
	
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
		model.addAttribute("categorias", categoriaService.listar());
		return "registroProducto";
	}
	
	//GUARDO REGISTRO NUEVO
	@PostMapping("/admin/save")
	public String save(@Validated @ModelAttribute Producto p, Model model, @RequestParam("file") MultipartFile imagen, RedirectAttributes attribute) { //file es el nombre del campo y con MultipartFile le asigno un nombre
		
		if(!imagen.isEmpty()) {
			Path directorioImagenes = Paths.get("src/main/resources/static/images"); //ruta relativa hacia la carpeta "images" del recurso estatico
			String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
			
			try {
				
				//Obtengo los bytes de la imagen
				byte[] bytesImg = imagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "/"+imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytesImg);
				
				p.setImagen(imagen.getOriginalFilename());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		service.save(p);
		attribute.addFlashAttribute("success", "producto guardado con exito");
		return "redirect:/admin/listar";
	}

	//metodo para VER DETALLES DATOS
	@GetMapping("/admin/detalleProducto/{id}")
	public String detalleProducto(@PathVariable int id, Model modelo, RedirectAttributes attribute) { //@pathVariable es para el parametro que le paso (ID)
	
		Optional<Producto> producto = service.listaId(id);//trae los datos de persona (id).
		modelo.addAttribute("titulo", "Detalle del producto: "+ producto.get().getNombre());
		modelo.addAttribute("producto",producto);
		return "detalleProducto";//envio al mismo formulario de Registro pero con los datos de la persona ID
	}
	
	//metodo para ACTUALIZO DATOS
	@GetMapping("/admin/editar/{id}")
	public String editar(@PathVariable int id, Model modelo) { //
					
		Optional<Producto> producto = service.listaId(id);
		modelo.addAttribute("producto",producto);
		return "registroProducto";//
	}
	
	@GetMapping("/admin/eliminar/{id}")
	public String delete(Model modelo, @PathVariable int id) {
		service.delete(id);
		return "redirect:/admin/listar";	
	}
	
}
