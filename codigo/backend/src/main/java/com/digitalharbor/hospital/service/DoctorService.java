package com.digitalharbor.hospital.service;

import java.util.List;

import com.digitalharbor.hospital.exception.HospitalException;
import com.digitalharbor.hospital.request.dto.DoctorRequestDTO;
import com.digitalharbor.hospital.response.dto.DoctorResponseDTO;

public interface DoctorService {
	
	List<DoctorResponseDTO> todos()throws HospitalException;
	List<DoctorResponseDTO> todosPorHospitalId(Long hospitalId) throws HospitalException;
	DoctorResponseDTO porId(Long doctorId) throws HospitalException;
	Long guardar(Long hospitalId, DoctorRequestDTO doctorDTO)throws HospitalException;
	void actualizar(Long doctorId, DoctorRequestDTO doctorDTO) throws HospitalException;
	void eliminar(Long doctorId) throws HospitalException;
	List<DoctorResponseDTO> busquedaTodos(String nombre, String apellido) throws HospitalException;
}
