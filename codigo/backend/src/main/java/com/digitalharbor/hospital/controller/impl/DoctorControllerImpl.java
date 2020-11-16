package com.digitalharbor.hospital.controller.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.digitalharbor.hospital.controller.DoctorController;
import com.digitalharbor.hospital.exception.HospitalException;
import com.digitalharbor.hospital.request.dto.DoctorRequestDTO;
import com.digitalharbor.hospital.response.dto.DoctorResponseDTO;
import com.digitalharbor.hospital.service.DoctorService;

@RestController
public class DoctorControllerImpl implements DoctorController{
	
	private static final Logger LOGGER = LogManager.getLogger(DoctorControllerImpl.class);
	
	@Autowired
	DoctorService doctorService;
	
	@Override
	public ResponseEntity<List<DoctorResponseDTO>> todos() {
		
		LOGGER.debug("llamando lista doctores...");
		List<DoctorResponseDTO> doctores = null;
		try {
			doctores = doctorService.todos();
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<List<DoctorResponseDTO>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DoctorResponseDTO>>(doctores, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<DoctorResponseDTO>> todosPorHospitalId(Long hospitalId) {
		
		LOGGER.debug("llamando lista doctores...");
		List<DoctorResponseDTO> doctores = null;
		try {
			doctores = doctorService.todosPorHospitalId(hospitalId);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<List<DoctorResponseDTO>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DoctorResponseDTO>>(doctores, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<DoctorResponseDTO> porId(Long doctorId) {
		
		LOGGER.debug("Llamando hospital por Id...");
		DoctorResponseDTO doctor = null;
		try {
			doctor = doctorService.porId(doctorId);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<DoctorResponseDTO>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<DoctorResponseDTO>(doctor, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Long> guardar(Long hospitalId,DoctorRequestDTO doctorDTO) {
		
		LOGGER.debug("llamando guardar doctor...");
		Long doctorId = null;
		try {
			doctorId = doctorService.guardar(hospitalId,doctorDTO);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Long>(doctorId, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Void> actualizar(Long doctorId, DoctorRequestDTO doctorDTO) {
		
		LOGGER.debug("Llamando actualizar doctor...");
		try {
			doctorService.actualizar(doctorId, doctorDTO);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>( HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Void> eliminar(Long doctorId) {
		
		LOGGER.debug("Llamando eliminar doctor...");
		try {
			doctorService.eliminar(doctorId);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>( HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<DoctorResponseDTO>> busquedaTodos(String nombre, String apellido){
		
		LOGGER.debug("llamando lista doctores busqueda...");
		List<DoctorResponseDTO> doctores = null;
		try {
			doctores = doctorService.busquedaTodos(nombre, apellido);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<List<DoctorResponseDTO>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<DoctorResponseDTO>>(doctores, HttpStatus.OK);
	}

}
