package com.digitalharbor.hospital.assembler;

import java.time.LocalDateTime;

import com.digitalharbor.hospital.entity.Paciente;
import com.digitalharbor.hospital.enums.EstadoEnum;
import com.digitalharbor.hospital.request.dto.PacienteRequestDTO;
import com.digitalharbor.hospital.response.dto.PacienteResponseDTO;

public class PacienteAssembler {
	
	public static Paciente requestDTOtoEntity(PacienteRequestDTO dto) {
		Paciente entity = new Paciente();
		entity.setNombre(dto.getNombre());
		entity.setApellido(dto.getApellido());
		entity.setDireccion(dto.getDireccion());
		entity.setFechaNacimiento(dto.getFechaNacimiento());
		
		entity.setCreadoEn(LocalDateTime.now());
		entity.setCreadoPor("XXX");
		entity.setEstado(EstadoEnum.ACTIVO);
		return entity;
	}
	
	public static Paciente updateRequestDTOtoEntity(Paciente entity, PacienteRequestDTO dto) {
		entity.setNombre(dto.getNombre());
		entity.setApellido(dto.getApellido());
		entity.setDireccion(dto.getDireccion());
		entity.setFechaNacimiento(dto.getFechaNacimiento());
		entity.setActualizadoEn(LocalDateTime.now());
		entity.setActualizadoPor("YYY");
		return entity;
	}
	
	public static PacienteResponseDTO entityToResponseDTO(Paciente entity) {
		PacienteResponseDTO dto = new PacienteResponseDTO();
		dto.setId(entity.getPacienteId());
		dto.setNombre(entity.getNombre());
		dto.setApellido(entity.getApellido());
		dto.setDireccion(entity.getDireccion());
		dto.setFechaNacimiento(entity.getFechaNacimiento());
		return dto;
	}
}
