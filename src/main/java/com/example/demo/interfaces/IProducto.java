package com.example.demo.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modelo.Producto;

@Repository
public interface IProducto extends CrudRepository<Producto, Integer> {

}
