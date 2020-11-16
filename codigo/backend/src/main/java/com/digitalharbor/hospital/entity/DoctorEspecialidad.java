package com.digitalharbor.hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "DOCTORES_ESPECIALIDADES")
public class DoctorEspecialidad extends Persona{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DOCTOR_ESPECIALIDAD_ID")
	private Long doctorEspecialidadId;
	
	@JoinColumn(name = "DOCTOR_ID", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Doctor doctor;
	
	@JoinColumn(name = "ESPECIALIDAD_ID", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Especialidad especialidad;

	public Long getDoctorEspecialidadId() {
		return doctorEspecialidadId;
	}

	public void setDoctorEspecialidadId(Long doctorEspecialidadId) {
		this.doctorEspecialidadId = doctorEspecialidadId;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

}
