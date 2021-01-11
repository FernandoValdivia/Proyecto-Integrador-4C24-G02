package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.service.UserDetailsServiceImpl;

//Archivo de configuarcion y habilito el webSecurity
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    //Necesario para evitar que la seguridad se aplique a los resources
    //Como los css, imagenes y javascripts
    String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/images/**","/js/**","/layer/**"
    };
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
	        .antMatchers(resources).permitAll()  
	        .antMatchers("/","/index").permitAll() // todos tienen permiso
	        .antMatchers("/admin*").access("hasRole('ADMIN')") //
	        .antMatchers("/user*").access("hasRole('USER')") //or hasRole('ADMIN')
                .anyRequest().authenticated()//cualquier otra URL q no este especificada arriba para acceder a ellas deben de estar AUTENTIFICADO
                .and()
            .formLogin() //configuro el formulario de LOGIN
                .loginPage("/login")
                .permitAll() //permito acceso a todos 
                .defaultSuccessUrl("/user")
                .failureUrl("/login?error=true") // si el login no es correcto envio msj en la vista(en la vista muestro msj a traves de failureURL..)
                .usernameParameter("username") // nombre del campo del formulario en vista
                .passwordParameter("password") // "  "  " ..
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout"); // una vez deslogueado te envia al "ruta login"
    }
    
    BCryptPasswordEncoder bCryptPasswordEncoder;
    //Crea el encriptador de contraseñas	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		//El numero 4 representa que tan fuerte quieres la encriptacion.
		//Se puede en un rango entre 4 y 31. 
		//Si no pones un numero el programa utilizara uno aleatoriamente cada vez
		//que inicies la aplicacion, por lo cual tus contrasenas encriptadas no funcionaran bien
        return bCryptPasswordEncoder;
    }
	
    @Autowired
    UserDetailsServiceImpl userDetailsService;
	
    //Registra el service para usuarios y el encriptador de contrasena
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
    }
    
}
