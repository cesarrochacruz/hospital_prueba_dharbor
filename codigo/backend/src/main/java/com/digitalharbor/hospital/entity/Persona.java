package com.digitalharbor.hospital.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.digitalharbor.hospital.enums.EstadoEnum;

@MappedSuperclass
public class Persona extends Auditoria{
	

	@Column(name="NOMBRE",  length = 100, nullable = false)
	private String nombre;
	
	@Column(name="APELLIDO",  length = 100, nullable = false)
	private String apellido;
	
	@Column(name="FECHA_NACIMIENTO",   nullable = false)
	private LocalDate fechaNacimiento;
	
	@Column(name="DIRECCION", nullable = false)
	private String direccion;
	
	@Column(name="FOTO_PERFIL")
	private String fotoPerfil;
	
	@Column(name="ESTADO", nullable = false)
	private EstadoEnum estado;

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

	public String getFotoPerfil() {
		return fotoPerfil;
	}

	public void setFotoPerfil(String fotoPerfil) {
		this.fotoPerfil = fotoPerfil;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + ", apellido=" + apellido + ", fechaNacimiento=" + fechaNacimiento
				+ ", direccion=" + direccion + ", fotoPerfil=" + fotoPerfil + ", estado=" + estado ;
	}

}
