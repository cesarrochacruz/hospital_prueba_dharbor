package com.digitalharbor.hospital.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotaVisitaNuevoRequestDTO extends NotaVisitaRequestDTO{
	
	
	@JsonProperty(required = true)
	private Long doctorId;
	
	@JsonProperty(required = true)
	private Long pacienteId;

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		this.pacienteId = pacienteId;
	}
	

}