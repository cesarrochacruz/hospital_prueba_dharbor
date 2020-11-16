package com.digitalharbor.hospital.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.digitalharbor.hospital.request.dto.HospitalRequestDTO;
import com.digitalharbor.hospital.response.dto.HospitalResponseDTO;

@RequestMapping(value = "/api/hospital")
public interface HospitalController {
	
	
	@RequestMapping(value = "/",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HospitalResponseDTO>> todos();
	
	@RequestMapping(value = "/id/{id}",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HospitalResponseDTO> porId(@PathVariable(required = true, value = "id")Long hospitalId);
	
	@RequestMapping(value = "/",
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> guardar( @RequestBody(required = true) HospitalRequestDTO hospitalDTO) ;
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> actualizar( 
			@PathVariable(required = true, value = "id")Long hospitalId,
			@RequestBody(required = true) HospitalRequestDTO hospitalDTO) ;
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar( 
			@PathVariable(required = true, value = "id")Long hospitalId) ;
	
	@RequestMapping(value = "/busqueda/",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<HospitalResponseDTO>> busquedaTodos(
			@RequestParam( value = "nombre")String nombre);

}
