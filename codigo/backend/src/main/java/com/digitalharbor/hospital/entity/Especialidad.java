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
@Table(name = "ESPECIALIDADES")
public class Especialidad extends Auditoria{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ESPECIALIDAD_ID")
	private Long especialidadId;
	
	@Column(name="NOMBRE", length = 50, nullable = false)
	private String nombre;
	
	@Column(name="DESCRIPCION", length = 150, nullable = false)
	private String descripcion;
	
	@Column(name="AVATAR", nullable = false)
	private String avatar;
	
	@Column(name="ESTADO", nullable = false)
	private EstadoEnum estado;
	
	@OneToMany( mappedBy = "especialidad", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<DoctorEspecialidad> doctorEspecialidades;

	public Long getEspecialidadId() {
		return especialidadId;
	}

	public void setEspecialidadId(Long especialidadId) {
		this.especialidadId = especialidadId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

	public List<DoctorEspecialidad> getDoctorEspecialidades() {
		return doctorEspecialidades;
	}

	public void setDoctorEspecialidades(List<DoctorEspecialidad> doctorEspecialidades) {
		this.doctorEspecialidades = doctorEspecialidades;
	}
	
	public void addDoctorEspecialidad(DoctorEspecialidad doctorEspecialidad) {
		if(doctorEspecialidades==null) {
			doctorEspecialidades = new ArrayList<DoctorEspecialidad>();
		}
		doctorEspecialidad.setEspecialidad(this);
		doctorEspecialidades.add(doctorEspecialidad);
	}

	@Override
	public String toString() {
		return "Especialidad [especialidadId=" + especialidadId + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", avatar=" + avatar + ", estado=" + estado + "]";
	}
	
}
