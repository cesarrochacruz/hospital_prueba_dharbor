package com.digitalharbor.hospital.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.digitalharbor.hospital.enums.EstadoEnum;

@Entity
@Table(name = "HOSPITALES")
public class Hospital extends Auditoria{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="HOSPITAL_ID")
	private Long hospitalId;
	
	@Column(name="NOMBRE",  length = 100, nullable = false)
	private String nombre;
	
	@Column(name="DIRECCION", nullable = false)
	private String direccion;
	
	@Column(name="ESTADO", nullable = false)
	private EstadoEnum estado;
	
	@OneToMany( mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<Paciente> pacientes;
	
	@OneToMany( mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<Doctor> doctores;

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}
	
	public void addPaciente(Paciente paciente) {
		if(pacientes==null) {
			pacientes = new ArrayList<Paciente>();
		}
		paciente.setHospital(this);
		pacientes.add(paciente);
	}
	
	public void addDoctor(Doctor doctor) {
		if(doctores==null) {
			doctores = new ArrayList<Doctor>();
		}
		doctor.setHospital(this);
		doctores.add(doctor);
	}
	
	
	

	@Override
	public String toString() {
		return "Hospital [hospitalId=" + hospitalId + ", nombre=" + nombre + ", direccion=" + direccion + ", estado="
				+ estado + "]";
	}
	
	
}
