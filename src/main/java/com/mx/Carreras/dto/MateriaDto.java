package com.mx.Carreras.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MateriaDto {
	private int nrc;
	private String materia;
	private int creditos;
	private String nivel;
	private int numeroCarrera;
}
