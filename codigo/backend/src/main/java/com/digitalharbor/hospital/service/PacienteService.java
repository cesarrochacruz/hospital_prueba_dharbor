package com.digitalharbor.hospital.service;

import java.util.List;

import com.digitalharbor.hospital.exception.HospitalException;
import com.digitalharbor.hospital.request.dto.NotaVisitaNuevoRequestDTO;
import com.digitalharbor.hospital.request.dto.PacienteRequestDTO;
import com.digitalharbor.hospital.response.dto.PacienteResponseDTO;

public interface PacienteService {
	
	List<PacienteResponseDTO> todos()throws HospitalException;
	List<PacienteResponseDTO> todosPorDoctorId(Long doctorId) throws HospitalException;
	PacienteResponseDTO porId(Long pacienteId) throws HospitalException;
	Long guardar(Long doctorId, PacienteRequestDTO pacienteDTO)throws HospitalException;
	void actualizar(Long pacienteId, PacienteRequestDTO pacienteDTO) throws HospitalException;
	void eliminar(Long pacienteId) throws HospitalException;
	Long guardarNotaVisita(NotaVisitaNuevoRequestDTO notaVisitaDTO) throws HospitalException;
	List<PacienteResponseDTO> busquedaTodos(String nombre, String apellido) throws HospitalException;
}
