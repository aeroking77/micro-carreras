package com.mx.Carreras.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.Carreras.entidad.Carrera;

public interface CarreraDao extends JpaRepository<Carrera, Integer> {

}
