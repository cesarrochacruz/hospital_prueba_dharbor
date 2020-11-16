package com.digitalharbor.hospital.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.digitalharbor.hospital.enums.EstadoEnum;

@Entity
@Table(name = "NOTAS_VISITAS")
public class NotaVisita extends Auditoria{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="NOTA_VISITA_ID")
	private Long notaVisitaId;
	
	@Column(name="DESCRIPCION",  nullable = false)
	private String descripcion;
	
	@Column(name="FECHA",   nullable = false)
	private LocalDateTime fecha;
	
	@Column(name="ESTADO", nullable = false)
	private EstadoEnum estado;
	
	@JoinColumn(name = "DOCTOR_ID", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Doctor doctor;
	
	@JoinColumn(name = "PACIENTE_ID", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Paciente paciente;

	public Long getNotaVisitaId() {
		return notaVisitaId;
	}

	public void setNotaVisitaId(Long notaVisitaId) {
		this.notaVisitaId = notaVisitaId;
	}

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

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	
}
