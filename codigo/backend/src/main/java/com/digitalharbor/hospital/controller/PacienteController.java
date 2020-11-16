package com.digitalharbor.hospital.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.digitalharbor.hospital.request.dto.NotaVisitaNuevoRequestDTO;
import com.digitalharbor.hospital.request.dto.PacienteRequestDTO;
import com.digitalharbor.hospital.response.dto.PacienteResponseDTO;

@RequestMapping(value = "/api/paciente")
public interface PacienteController {
	
	@RequestMapping(value = "/",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PacienteResponseDTO>> todos();
	
	
	@RequestMapping(value = "/doctor-id/{doctorId}",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PacienteResponseDTO>> todosPorDoctorId(
			@PathVariable(required = true, value = "doctorId")Long doctorId);
	
	@RequestMapping(value = "/id/{id}",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PacienteResponseDTO> porId(@PathVariable(required = true, value = "id")Long pacientelId);
	
	@RequestMapping(value = "/{doctorId}",
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> guardar(
			@PathVariable Long doctorId, 
			@RequestBody(required = true)PacienteRequestDTO pacienteDTO) ;
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> actualizar( 
			@PathVariable(required = true, value = "id")Long pacienteId,
			@RequestBody(required = true) PacienteRequestDTO pacienteDTO) ;
	
	@RequestMapping(value = "/{id}",
			method = RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar( 
			@PathVariable(required = true, value = "id")Long pacienteId) ;
	
	@RequestMapping(value = "/nota-visita/guardar",
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Long> guardarNotaVisita(
			@RequestBody(required = true)NotaVisitaNuevoRequestDTO notaVisitaDTO);
	
	@RequestMapping(value = "/busqueda/",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PacienteResponseDTO>> busquedaTodos(
			@RequestParam( value = "nombre")String nombre,
			@RequestParam( value = "apellido")String apellido);
	

}
