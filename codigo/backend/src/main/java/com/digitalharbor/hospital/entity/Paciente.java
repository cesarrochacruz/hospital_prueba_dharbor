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
@Table(name = "PACIENTES")
public class Paciente extends Persona{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PACIENTE_ID")
	private Long pacienteId;
	
	@JoinColumn(name = "HOSPITAL_ID", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private Hospital hospital;
	
	@OneToMany( mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<NotaVisita> notasVisitas;

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
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
		notaVisita.setPaciente(this);
		notasVisitas.add(notaVisita);
	}

	@Override
	public String toString() {
		return "Paciente [pacienteId=" + pacienteId + ", " + super.toString() + "]";
	}
	
}
