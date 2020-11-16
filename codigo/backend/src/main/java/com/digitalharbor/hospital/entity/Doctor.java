package com.digitalharbor.hospital.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "DOCTORES")
public class Doctor extends Persona{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="DOCTOR_ID")
	private Long doctorId;
	
	@JoinColumn(name = "HOSPITAL_ID", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Hospital hospital;
	
	@OneToMany( mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<DoctorEspecialidad> doctorEspecialidades;

	@OneToMany( mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<NotaVisita> notasVisitas;
	
	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	

	public List<DoctorEspecialidad> getDoctorEspecialidades() {
		return doctorEspecialidades;
	}

	public void setDoctorEspecialidades(List<DoctorEspecialidad> doctorEspecialidades) {
		this.doctorEspecialidades = doctorEspecialidades;
	}
	
	public List<NotaVisita> getNotasVisitas() {
		return notasVisitas;
	}

	public void setNotasVisitas(List<NotaVisita> notasVisitas) {
		this.notasVisitas = notasVisitas;
	}
	
	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public void addNotasVisitas(NotaVisita notaVisita) {
		if(notasVisitas==null) {
			notasVisitas = new ArrayList<NotaVisita>();
		}
		notaVisita.setDoctor(this);
		notasVisitas.add(notaVisita);
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", " + super.toString() + "]";
	}
	
}
