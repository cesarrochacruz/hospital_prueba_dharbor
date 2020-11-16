package com.digitalharbor.hospital.request.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonaRequestDTO {
	
	@JsonProperty(required = true)
	private String nombre;
	
	@JsonProperty(required = true)
	private String apellido;
	
	@JsonProperty(required = true)
	@JsonFormat(pattern="MM/dd/yyyy")
	private LocalDate fechaNacimiento;
	
	@JsonProperty(required = true)
	private String direccion;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
