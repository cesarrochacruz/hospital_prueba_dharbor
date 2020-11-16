package com.digitalharbor.hospital.controller.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.digitalharbor.hospital.controller.PacienteController;
import com.digitalharbor.hospital.exception.HospitalException;
import com.digitalharbor.hospital.request.dto.NotaVisitaNuevoRequestDTO;
import com.digitalharbor.hospital.request.dto.PacienteRequestDTO;
import com.digitalharbor.hospital.response.dto.PacienteResponseDTO;
import com.digitalharbor.hospital.service.PacienteService;

@RestController
public class PacienteControllerImpl implements PacienteController{
	
	private static final Logger LOGGER = LogManager.getLogger(PacienteControllerImpl.class);
	
	@Autowired
	PacienteService pacienteService;
	
	@Override
	public ResponseEntity<List<PacienteResponseDTO>> todos() {
		
		LOGGER.debug("llamando lista pacientes...");
		List<PacienteResponseDTO> doctores = null;
		try {
			doctores = pacienteService.todos();
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<List<PacienteResponseDTO>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<PacienteResponseDTO>>(doctores, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<PacienteResponseDTO>> todosPorDoctorId(Long doctorId) {
		
		LOGGER.debug("llamando lista pacientes...");
		List<PacienteResponseDTO> pacientes = null;
		try {
			pacientes = pacienteService.todosPorDoctorId(doctorId);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<List<PacienteResponseDTO>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<PacienteResponseDTO>>(pacientes, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<PacienteResponseDTO> porId(Long pacienteId) {
		
		LOGGER.debug("Llamando paciente por Id...");
		PacienteResponseDTO paciente = null;
		try {
			paciente = pacienteService.porId(pacienteId);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<PacienteResponseDTO>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<PacienteResponseDTO>(paciente, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Long> guardar(Long doctorId,PacienteRequestDTO pacienteDTO) {
		
		LOGGER.debug("llamando guardar paciente...");
		Long pacienteId = null;
		try {
			pacienteId = pacienteService.guardar(doctorId,pacienteDTO);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Long>(pacienteId, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Void> actualizar(Long pacienteId, PacienteRequestDTO pacienteDTO) {
		
		LOGGER.debug("Llamando actualizar paciente...");
		try {
			pacienteService.actualizar(pacienteId, pacienteDTO);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>( HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Void> eliminar(Long pacienteId) {
		
		LOGGER.debug("Llamando eliminar paciente...");
		try {
			pacienteService.eliminar(pacienteId);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>( HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Long> guardarNotaVisita(NotaVisitaNuevoRequestDTO notaVisitaDTO) {
		
		LOGGER.debug("llamando guardar paciente...");
		Long pacienteId = null;
		try {
			pacienteId = pacienteService.guardarNotaVisita(notaVisitaDTO);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Long>(pacienteId, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<PacienteResponseDTO>> busquedaTodos(String nombre, String apellido){
		
		LOGGER.debug("llamando lista pacientes busqueda...");
		List<PacienteResponseDTO> pacientes = null;
		try {
			pacientes = pacienteService.busquedaTodos(nombre, apellido);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<List<PacienteResponseDTO>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<PacienteResponseDTO>>(pacientes, HttpStatus.OK);
	}

}
