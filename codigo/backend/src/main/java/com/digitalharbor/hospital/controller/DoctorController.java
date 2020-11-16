package com.digitalharbor.hospital.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.digitalharbor.hospital.request.dto.DoctorRequestDTO;
import com.digitalharbor.hospital.response.dto.DoctorResponseDTO;

@RequestMapping(value = "/api/doctor")
public interface DoctorController {
	
	@RequestMapping(value = "/",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DoctorResponseDTO>> todos();
	
	
	@RequestMapping(value = "/hospital-id/{hospitalId}",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DoctorResponseDTO>> todosPorHospitalId(
			@PathVariable(required = true, value = "hospitalId")Long hospitalId);
	
	@RequestMapping(value = "/id/{id}",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DoctorResponseDTO> porId(@PathVariable(required = true, value = "id")Long doctorlId);
	
	@RequestMapping(value = "/{hospitalId}",
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> guardar(
			@PathVariable Long hospitalId, 
			@RequestBody(required = true)DoctorRequestDTO doctorDTO) ;
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> actualizar( 
			@PathVariable(required = true, value = "id")Long doctorId,
			@RequestBody(required = true) DoctorRequestDTO doctorDTO) ;
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar( 
			@PathVariable(required = true, value = "id")Long doctorId) ;
	
	@RequestMapping(value = "/busqueda/",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DoctorResponseDTO>> busquedaTodos(
			@RequestParam( value = "nombre")String nombre,
			@RequestParam( value = "apellido")String apellido);
	

}
