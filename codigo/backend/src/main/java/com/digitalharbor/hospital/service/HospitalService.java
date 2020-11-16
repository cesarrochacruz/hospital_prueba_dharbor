package com.digitalharbor.hospital.service;

import java.util.List;

import com.digitalharbor.hospital.exception.HospitalException;
import com.digitalharbor.hospital.request.dto.HospitalRequestDTO;
import com.digitalharbor.hospital.response.dto.HospitalResponseDTO;

public interface HospitalService {
	
	List<HospitalResponseDTO> todos()throws HospitalException;
	HospitalResponseDTO porId(Long hospitalId)throws HospitalException;
	Long guardar(HospitalRequestDTO hospitalDTO)throws HospitalException;
	void actualizar(Long hospitalId, HospitalRequestDTO hospitalDTO) throws HospitalException;
	void eliminar(Long hospitalId) throws HospitalException;
	List<HospitalResponseDTO> busquedaTodos(String nombre) throws HospitalException;
}
