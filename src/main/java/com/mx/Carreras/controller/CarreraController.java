package com.mx.Carreras.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Carreras.dto.Respuesta;
import com.mx.Carreras.entidad.Carrera;
import com.mx.Carreras.service.CarreraServicio;

@RestController
@RequestMapping("carreras")
@CrossOrigin
public class CarreraController {
    final CarreraServicio service;

    CarreraController(CarreraServicio service) {
        this.service = service;
    }
    
    @GetMapping("listar")
    public ResponseEntity<?> mostrar(){
       return service.mostrar();
    }
    
    @PostMapping("guardar")
    public Respuesta guardar(@RequestBody Carrera carrera) {
    	return service.guardar(carrera);
    }
    
    @PostMapping("editar")
    public Respuesta editar(@RequestBody Carrera carrera) {
    	return service.editar(carrera);
    }
    
    @GetMapping("eliminar/{numero}")
    public Respuesta eliminar(@PathVariable("numero")int numero) {
    	return service.eliminar(numero);
    }
    
    @GetMapping("buscar/{numero}")
    public ResponseEntity<?> buscar(@PathVariable("numero")int numero) {
    	return service.buscar(numero);
    }
    
    @GetMapping("buscarAlumnos/{numero}")
    public ResponseEntity<?> buscarAlumnos(@PathVariable("numero")int numero){
    	return service.buscarAlumnos(numero);
    }
    
    @GetMapping("buscarMaterias/{numero}")
    public ResponseEntity<?> buscarMaterias(@PathVariable("numero")int numero){
    	return service.buscarMaterias(numero);
    }
}
