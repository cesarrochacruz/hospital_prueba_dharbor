package com.digitalharbor.hospital.response.dto;



public class DoctorResponseDTO extends PersonaResponseDTO{
	
	private Long hospitalId;

	public Long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}
	
}
