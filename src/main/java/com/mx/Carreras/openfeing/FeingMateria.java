package com.mx.Carreras.openfeing;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mx.Carreras.dto.MateriaDto;

@FeignClient(name = "Materias", url = "http://localhost:9002",path = "/materias")
public interface FeingMateria {
	@GetMapping("buscarPorCarrera/{numero}")
	public List<MateriaDto> buscarMaterias(@PathVariable("numero")int numero);
}
