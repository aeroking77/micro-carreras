package com.mx.Carreras.openfeing;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mx.Carreras.dto.AlumnoDto;

@FeignClient(name = "Alumnos",url = "http://localhost:9001", path = "/alumnos")
public interface FeingAlumno {
	@GetMapping("buscarPorCarrera/{numero}")
	public List<AlumnoDto>buscarAlumnos(@PathVariable("numero")int numero);
}
