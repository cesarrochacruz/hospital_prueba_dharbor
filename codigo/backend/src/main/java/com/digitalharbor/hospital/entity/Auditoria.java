package com.digitalharbor.hospital.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Auditoria {
	
	@Column(name="CREADO_EN", nullable = false)
	private LocalDateTime creadoEn;
	
	@Column(name="ACTUALIZADO_EN")
	private LocalDateTime actualizadoEn;
	
	@Column(name="CREADO_POR",  length = 50, nullable = false)
	private String creadoPor;
	
	@Column(name="ACTUALIZADO_POR",  length = 50)
	private String actualizadoPor;
	
	public LocalDateTime getCreadoEn() {
		return creadoEn;
	}
	public void setCreadoEn(LocalDateTime creadoEn) {
		this.creadoEn = creadoEn;
	}
	public LocalDateTime getActualizadoEn() {
		return actualizadoEn;
	}
	public void setActualizadoEn(LocalDateTime actualizadoEn) {
		this.actualizadoEn = actualizadoEn;
	}
	public String getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}
	public String getActualizadoPor() {
		return actualizadoPor;
	}
	public void setActualizadoPor(String actualizadoPor) {
		this.actualizadoPor = actualizadoPor;
	}
	

}
