package com.example.demo.modelo;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

@Column
private String username;

@Column
private String password;

@Column
private boolean enabled;

private String apellido;
private String dni;
private String email;
private String token;
private String telefono;

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(name="authorities_users",
joinColumns=@JoinColumn(name="usuario_id"),
inverseJoinColumns=@JoinColumn(name="authority_id"))
private Set<Authority> authority;

//Getters y Setters
public Long getId() {
	return id;
}

public String getApellido() {
	return apellido;
}

public void setApellido(String apellido) {
	this.apellido = apellido;
}

public String getDni() {
	return dni;
}

public void setDni(String dni) {
	this.dni = dni;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public String getTelefono() {
	return telefono;
}

public void setTelefono(String telefono) {
	this.telefono = telefono;
}

public void setId(Long id) {
	this.id = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}


@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 :id.hashCode());
    return result;
}

public boolean isEnabled() {
	return enabled;
}

public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}

public Set<Authority> getAuthority() {
	return authority;
}

public void setAuthority(Set<Authority> authority) {
	this.authority = authority;
}

@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    User other = (User) obj;
    if (id == null) {
        if (other.id != null)
            return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
}

@Override
public String toString() {
    return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
}

/*
@Override
public String toString() {
	return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
			+ ", apellido=" + apellido + ", dni=" + dni + ", email=" + email + ", token=" + token + ", telefono="
			+ telefono + ", authority=" + authority + "]";
}
	*/


}
