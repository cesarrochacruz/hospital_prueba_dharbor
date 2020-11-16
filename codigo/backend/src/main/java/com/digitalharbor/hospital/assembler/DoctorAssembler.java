package com.digitalharbor.hospital.assembler;

import java.time.LocalDateTime;

import com.digitalharbor.hospital.entity.Doctor;
import com.digitalharbor.hospital.enums.EstadoEnum;
import com.digitalharbor.hospital.request.dto.DoctorRequestDTO;
import com.digitalharbor.hospital.response.dto.DoctorResponseDTO;

public class DoctorAssembler {
	
	public static Doctor requestDTOtoEntity(DoctorRequestDTO dto) {
		Doctor entity = new Doctor();
		entity.setNombre(dto.getNombre());
		entity.setApellido(dto.getApellido());
		entity.setDireccion(dto.getDireccion());
		entity.setFechaNacimiento(dto.getFechaNacimiento());
		
		entity.setCreadoEn(LocalDateTime.now());
		entity.setCreadoPor("XXX");
		entity.setEstado(EstadoEnum.ACTIVO);
		return entity;
	}
	
	public static Doctor updateRequestDTOtoEntity(Doctor entity, DoctorRequestDTO dto) {
		entity.setNombre(dto.getNombre());
		entity.setApellido(dto.getApellido());
		entity.setDireccion(dto.getDireccion());
		entity.setFechaNacimiento(dto.getFechaNacimiento());
		entity.setActualizadoEn(LocalDateTime.now());
		entity.setActualizadoPor("YYY");
		return entity;
	}
	
	public static DoctorResponseDTO entityToResponseDTO(Doctor entity) {
		DoctorResponseDTO dto = new DoctorResponseDTO();
		dto.setId(entity.getDoctorId());
		dto.setNombre(entity.getNombre());
		dto.setApellido(entity.getApellido());
		dto.setDireccion(entity.getDireccion());
		dto.setFechaNacimiento(entity.getFechaNacimiento());
		dto.setHospitalId(entity.getHospital().getHospitalId());
		return dto;
	}
}
