package com.mx.Carreras.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mx.Carreras.dao.CarreraDao;
import com.mx.Carreras.dto.AlumnoDto;
import com.mx.Carreras.dto.MateriaDto;
import com.mx.Carreras.dto.Respuesta;
import com.mx.Carreras.entidad.Carrera;
import com.mx.Carreras.openfeing.FeingAlumno;
import com.mx.Carreras.openfeing.FeingMateria;

@Service
public class CarreraServicio {
    final CarreraDao carreraDao;
    final FeingAlumno feingAlumno;
    final FeingMateria feingMateria;

    CarreraServicio(CarreraDao carreraDao,FeingAlumno feingAlumno,FeingMateria feingMateria) {
        this.carreraDao = carreraDao;
        this.feingAlumno = feingAlumno;
        this.feingMateria = feingMateria;
    }
    
    public ResponseEntity<?> mostrar(){
        if(carreraDao.findAll().isEmpty()) {
            return ResponseEntity.noContent().build();
        }	
        return ResponseEntity.ok(carreraDao.findAll());
    }
    
    public Respuesta guardar(Carrera carrera) {
        if(carreraDao.existsById(carrera.getNumero())) {
           return new Respuesta("La carrera no se agrego por que ya existe", false, carrera.getNumero());
        }
        for(Carrera c:carreraDao.findAll()) {
            if(carrera.getCarrera().equalsIgnoreCase(c.getCarrera())) {
                return new Respuesta("La carrera no se agrego por que ya existe", false, c);
            }
        }
        carreraDao.save(carrera);
        return new Respuesta("La carrera ha sido agregada", true, carrera);
    }
    
    public Respuesta editar(Carrera carrera) {
        if(carreraDao.existsById(carrera.getNumero())) {
            for(Carrera c:carreraDao.findAll()) {
                if(carrera.getNumero() != c.getNumero() && carrera.getCarrera().equalsIgnoreCase(c.getCarrera())) {
                    return new Respuesta("La carrera no se edito por que ya existe", false, c);	
                }
            }
            carreraDao.save(carrera);
            return new Respuesta("La carrera ha sido editada", true, carrera);
        }
        return new Respuesta("La carrera que tratas de editar no existe", false, carrera.getNumero());
    }
    
    public Respuesta eliminar(int numero) {
        Carrera carrera = carreraDao.findById(numero).orElse(null);
    	if(carrera == null) {
            return new Respuesta("La carrera que tratas de eliminar no existe", false, numero);
        }
    	List<AlumnoDto> alumnos = feingAlumno.buscarAlumnos(numero);
    	if(alumnos != null) {
    	    return new Respuesta("La carrera no se puede dar de baja por que tiene alumnos", false, alumnos);	
    	}
    	List<MateriaDto>materias = feingMateria.buscarMaterias(numero);
    	if(materias != null) {
    		return new Respuesta("La carrera no se puede dar de baja por que tiene materias", false, materias);
    	}
    	Respuesta rs = new Respuesta("La carrera ha sido eliminada", true, carrera);
    	carreraDao.delete(carrera);
    	return rs;
    }
    
    public ResponseEntity<?> buscar(int numero) {
    	Carrera carrera = carreraDao.findById(numero).orElse(null);
    	if(carrera == null) {
    	    return ResponseEntity.noContent().build();
    	}
    	return ResponseEntity.ok(carrera);
    }
    
    public ResponseEntity<?> buscarAlumnos(int numero){
    	List<AlumnoDto>alumnos = feingAlumno.buscarAlumnos(numero);
    	if(alumnos == null) {
    		return ResponseEntity.noContent().build();
    	}
    	return ResponseEntity.ok(alumnos);
    }
    
    public ResponseEntity<?> buscarMaterias(int numero){
    	List<MateriaDto>materias = feingMateria.buscarMaterias(numero);
    	if(materias == null) {
    		return ResponseEntity.noContent().build();
    	}
    	return ResponseEntity.ok(materias);
    }
}
