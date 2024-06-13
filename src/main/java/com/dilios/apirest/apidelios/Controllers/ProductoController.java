package com.dilios.apirest.apidelios.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dilios.apirest.apidelios.Repositories.ProductoRepositorio;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dilios.apirest.apidelios.Entities.Producto;


@RestController
@RequestMapping("/productos")
public class ProductoController {

    //va y sbe a que repositorio corresponde lo que vamos a solicitar y hace una instancia
    @Autowired
    private ProductoRepositorio productoRepository;

    @GetMapping
    public List<Producto> obtenerProductos(){
        return productoRepository.findAll();//me trae todos los productos que traiga este repositorio
    }

    @GetMapping("/{id}")
    public Producto obteneProductoPorId(@PathVariable Long id){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID:  " + id));
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto){ //recibe un RequestBody json las caracteristicas que debe tener son las que estan en la entidad
                                     // este caso producto(campos nombre y precio) si tiene mas campos se deben poner todas,salvo que
                                     // no sean obligatorias
        return productoRepository.save(producto);//graba y devuelve un producto
    }
    @PutMapping("/{id}")
    public Producto actualizaProducto(@PathVariable Long id, @RequestBody Producto detallesProducto ){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID:  " + id));

        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productoRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String borrarProducto(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID:  " + id));

        productoRepository.delete(producto);
        return "El producto con el ID: " + id + " fue eliminado correctamente";

    }

    
}
