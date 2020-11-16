package com.digitalharbor.hospital.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalharbor.hospital.assembler.PacienteAssembler;
import com.digitalharbor.hospital.entity.Doctor;
import com.digitalharbor.hospital.entity.NotaVisita;
import com.digitalharbor.hospital.entity.Paciente;
import com.digitalharbor.hospital.enums.EstadoEnum;
import com.digitalharbor.hospital.exception.HospitalException;
import com.digitalharbor.hospital.repository.DoctorRepository;
import com.digitalharbor.hospital.repository.NotaVisitaRepository;
import com.digitalharbor.hospital.repository.PacienteRepository;
import com.digitalharbor.hospital.request.dto.NotaVisitaNuevoRequestDTO;
import com.digitalharbor.hospital.request.dto.PacienteRequestDTO;
import com.digitalharbor.hospital.response.dto.PacienteResponseDTO;
import com.digitalharbor.hospital.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

	private static final Logger LOGGER = LogManager.getLogger(PacienteServiceImpl.class);
	
	@Autowired
	PacienteRepository pacienteRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	NotaVisitaRepository notaVisitaRepository;
	
	@Override
	public List<PacienteResponseDTO> todos() throws HospitalException{
		LOGGER.debug("lista pacientes...");
		
		List<Paciente> result = StreamSupport.stream(pacienteRepository.pacientesActivos().spliterator(), false)
				    .collect(Collectors.toList());
		List<PacienteResponseDTO> resultDTO = result.stream().map(PacienteAssembler::entityToResponseDTO)
				.collect(Collectors.toList());
		
		return resultDTO;
	}
	
	@Override
	public List<PacienteResponseDTO> todosPorDoctorId(Long doctorId) throws HospitalException{
		LOGGER.debug("lista pacientes por doctor...");
		
		List<Paciente> result = StreamSupport.stream(pacienteRepository.pacientesActivosPorDoctor(doctorId).spliterator(), false)
				    .collect(Collectors.toList());
		List<PacienteResponseDTO> resultDTO = result.stream().map(PacienteAssembler::entityToResponseDTO)
				.collect(Collectors.toList());
		
		return resultDTO;
	}
	
	@Override
	public PacienteResponseDTO porId(Long pacienteId) throws HospitalException{
		LOGGER.debug("paciente por id...");
		PacienteResponseDTO pacienteDTO;
		Optional<Paciente> result =pacienteRepository.findById(pacienteId);
		if(result.isPresent()) {
			pacienteDTO= PacienteAssembler.entityToResponseDTO(result.get());
		}else{
			throw new HospitalException("Id de paciente para actualizar no existe", LOGGER);
		}
		return pacienteDTO;
	}
	
	@Override
	public Long guardar(Long doctorId, PacienteRequestDTO pacienteDTO) throws HospitalException{
		LOGGER.debug("guardar paciente...");
		Long pacienteId;
		try {
			Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
			if(doctorOpt.isPresent()) {
				Paciente paciente = PacienteAssembler.requestDTOtoEntity(pacienteDTO);
				paciente.setHospital(doctorOpt.get().getHospital());
				
				NotaVisita notaVisita = new NotaVisita();
				notaVisita.setDoctor(doctorOpt.get());
				notaVisita.setPaciente(paciente);
				notaVisita.setDescripcion(pacienteDTO.getNotaVisita().getDescripcion());
				notaVisita.setFecha(pacienteDTO.getNotaVisita().getFecha());
				notaVisita.setCreadoEn(LocalDateTime.now());
				notaVisita.setCreadoPor("XXX");
				notaVisita.setEstado(EstadoEnum.ACTIVO);
				
				paciente.addNotasVisitas(notaVisita);
				
				pacienteId = pacienteRepository.save(paciente).getPacienteId();
				
			}else {
				throw new HospitalException("El paciente al que desea agregar el paciente no existe",LOGGER);
			}
			
		}catch (Exception e) {
			throw new HospitalException("No se pudo persistir un paciente", e, LOGGER);
		}
		return pacienteId;
	}
	
	@Override
	public void actualizar(Long pacienteId, PacienteRequestDTO pacienteDTO) throws HospitalException{
		LOGGER.debug("actualizar paciente...");
		try {
			Optional<Paciente> pacienteOpt = pacienteRepository.findById(pacienteId);
			if(pacienteOpt.isPresent()) {
				Paciente paciente = PacienteAssembler.updateRequestDTOtoEntity(pacienteOpt.get(), pacienteDTO);
				pacienteRepository.save(paciente);
			}else {
				throw new HospitalException("Id de paciente para actualizar no existe", LOGGER);
			}
			
		}catch (Exception e) {
			throw new HospitalException("No se pudo persistir un paciente", e, LOGGER);
		}
	}
	
	@Override
	public void eliminar(Long pacienteId) throws HospitalException{
		LOGGER.debug("eliminando paciente...");
		try {
			Optional<Paciente> pacienteOpt = pacienteRepository.findById(pacienteId);
			if(pacienteOpt.isPresent()) {
				Paciente paciente = pacienteOpt.get();
				paciente.setEstado(EstadoEnum.ELIMINADO);
				pacienteRepository.save(paciente);
			}else {
				throw new HospitalException("Id de paciente para eliminar no existe", LOGGER);
			}
			
		}catch (Exception e) {
			throw new HospitalException("No se pudo eliminar un paciente", e, LOGGER);
		}
	}
	
	@Override
	public Long guardarNotaVisita(NotaVisitaNuevoRequestDTO notaVisitaDTO) throws HospitalException{
		LOGGER.debug("guardar paciente...");
		Long notaVisitaId;
		try {
			Optional<Paciente> pacienteOpt = pacienteRepository.findById(notaVisitaDTO.getPacienteId());
			Optional<Doctor> doctorOpt = doctorRepository.findById(notaVisitaDTO.getDoctorId());
			
			if(pacienteOpt.isPresent() && doctorOpt.isPresent()) {
				NotaVisita notaVisita = new NotaVisita();
				notaVisita.setDoctor(doctorOpt.get());
				notaVisita.setPaciente(pacienteOpt.get());
				notaVisita.setDescripcion(notaVisitaDTO.getDescripcion());
				notaVisita.setFecha(notaVisitaDTO.getFecha());
				notaVisita.setCreadoEn(LocalDateTime.now());
				notaVisita.setCreadoPor("XXX");
				notaVisita.setEstado(EstadoEnum.ACTIVO);
				notaVisitaId = notaVisitaRepository.save(notaVisita).getNotaVisitaId();
			}else {
				throw new HospitalException("El paciente al que desea agregar el paciente no existe",LOGGER);
			}
			
			
		}catch (Exception e) {
			throw new HospitalException("No se pudo persistir la noda visita", e, LOGGER);
		}
		return notaVisitaId;
	}
	
	@Override
	public List<PacienteResponseDTO> busquedaTodos(String nombre, String apellido) throws HospitalException{
		LOGGER.debug("lista pacientes busqueda...");
		List<Paciente> resultParcial = new ArrayList<Paciente>();
		if(!nombre.isEmpty() && !apellido.isEmpty()) {
			resultParcial = pacienteRepository.pacientesActivosPorNombreOApellido(nombre, apellido);
		}else {
			if(apellido.isEmpty()) {
				resultParcial = pacienteRepository.pacientesActivosPorNombre(nombre);
			}else {
				if(nombre.isEmpty()) {
					resultParcial = pacienteRepository.pacientesActivosPorApellido(apellido);
				}
			}
		}
		
		List<Paciente> result = StreamSupport.stream(resultParcial.spliterator(), false)
				    .collect(Collectors.toList());
		List<PacienteResponseDTO> resultDTO = result.stream().map(PacienteAssembler::entityToResponseDTO)
				.collect(Collectors.toList());
		
		return resultDTO;
	}
}
