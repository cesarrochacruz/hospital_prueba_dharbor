package com.digitalharbor.hospital.request.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NotaVisitaRequestDTO {
	
	
	@JsonProperty(required = true)
	private String descripcion;
	
	@JsonProperty(required = true)
	@JsonFormat(pattern="MM/dd/yyyy HH:mm:ss")
	private LocalDateTime fecha;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	
	

}