package com.digitalharbor.hospital.controller.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.digitalharbor.hospital.controller.HospitalController;
import com.digitalharbor.hospital.exception.HospitalException;
import com.digitalharbor.hospital.request.dto.HospitalRequestDTO;
import com.digitalharbor.hospital.response.dto.HospitalResponseDTO;
import com.digitalharbor.hospital.service.HospitalService;

@RestController
public class HospitalControllerImpl implements HospitalController{
	
	private static final Logger LOGGER = LogManager.getLogger(HospitalControllerImpl.class);
	
	@Autowired
	HospitalService hospitalService;
	
	@Override
	public ResponseEntity<List<HospitalResponseDTO>> todos() {
		
		LOGGER.debug("Llamando lista hospitales...");
		List<HospitalResponseDTO> hospitales = null;
		try {
			hospitales = hospitalService.todos();
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<List<HospitalResponseDTO>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<HospitalResponseDTO>>(hospitales, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<HospitalResponseDTO> porId(Long hospitalId) {
		
		LOGGER.debug("Llamando hospital por Id...");
		HospitalResponseDTO hospital = null;
		try {
			hospital = hospitalService.porId(hospitalId);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<HospitalResponseDTO>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<HospitalResponseDTO>(hospital, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Long> guardar(HospitalRequestDTO hospitalDTO) {
		
		LOGGER.debug("Llamando guardar hospital...");
		Long hospitalId = null;
		try {
			hospitalId = hospitalService.guardar(hospitalDTO);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<Long>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Long>(hospitalId, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Void> actualizar(Long hospitalId, HospitalRequestDTO hospitalDTO) {
		
		LOGGER.debug("Llamando actualizar hospital...");
		try {
			hospitalService.actualizar(hospitalId, hospitalDTO);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>( HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Void> eliminar(Long hospitalId) {
		
		LOGGER.debug("Llamando eliminar hospital...");
		try {
			hospitalService.eliminar(hospitalId);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>( HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<List<HospitalResponseDTO>> busquedaTodos(String nombre){
		
		LOGGER.debug("llamando lista hospitales busqueda...");
		List<HospitalResponseDTO> hospitales = null;
		try {
			hospitales = hospitalService.busquedaTodos(nombre);
			
		}catch(HospitalException he) {
			LOGGER.error(he.getMessage());
			return new ResponseEntity<List<HospitalResponseDTO>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<HospitalResponseDTO>>(hospitales, HttpStatus.OK);
	}

}
