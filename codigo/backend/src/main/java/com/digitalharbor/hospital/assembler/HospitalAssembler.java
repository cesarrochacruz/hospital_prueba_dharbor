package com.digitalharbor.hospital.assembler;

import java.time.LocalDateTime;

import com.digitalharbor.hospital.entity.Hospital;
import com.digitalharbor.hospital.enums.EstadoEnum;
import com.digitalharbor.hospital.request.dto.HospitalRequestDTO;
import com.digitalharbor.hospital.response.dto.HospitalResponseDTO;

public class HospitalAssembler {
	
	public static Hospital requestDTOtoEntity(HospitalRequestDTO dto) {
		Hospital entity = new Hospital();
		entity.setNombre(dto.getNombre());
		entity.setDireccion(dto.getDireccion());
		
		entity.setCreadoEn(LocalDateTime.now());
		entity.setCreadoPor("XXX");
		entity.setEstado(EstadoEnum.ACTIVO);
		return entity;
	}
	
	public static Hospital updateRequestDTOtoEntity(Hospital entity, HospitalRequestDTO dto) {
		entity.setNombre(dto.getNombre());
		entity.setDireccion(dto.getDireccion());
		entity.setActualizadoEn(LocalDateTime.now());
		entity.setActualizadoPor("YYY");
		return entity;
	}
	
	public static HospitalResponseDTO entityToResponseDTO(Hospital entity) {
		HospitalResponseDTO dto = new HospitalResponseDTO();
		dto.setId(entity.getHospitalId());
		dto.setNombre(entity.getNombre());
		dto.setDireccion(entity.getDireccion());
		return dto;
	}
}
