package com.digitalharbor.hospital.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitalharbor.hospital.assembler.DoctorAssembler;
import com.digitalharbor.hospital.entity.Doctor;
import com.digitalharbor.hospital.entity.Hospital;
import com.digitalharbor.hospital.enums.EstadoEnum;
import com.digitalharbor.hospital.exception.HospitalException;
import com.digitalharbor.hospital.repository.DoctorRepository;
import com.digitalharbor.hospital.repository.HospitalRepository;
import com.digitalharbor.hospital.request.dto.DoctorRequestDTO;
import com.digitalharbor.hospital.response.dto.DoctorResponseDTO;
import com.digitalharbor.hospital.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	private static final Logger LOGGER = LogManager.getLogger(DoctorServiceImpl.class);
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Override
	public List<DoctorResponseDTO> todos() throws HospitalException{
		LOGGER.debug("lista doctores...");
		
		List<Doctor> result = StreamSupport.stream(doctorRepository.doctoresActivos().spliterator(), false)
				    .collect(Collectors.toList());
		List<DoctorResponseDTO> resultDTO = result.stream().map(DoctorAssembler::entityToResponseDTO)
				.collect(Collectors.toList());
		
		return resultDTO;
	}
	
	@Override
	public List<DoctorResponseDTO> todosPorHospitalId(Long hospitalId) throws HospitalException{
		LOGGER.debug("lista doctores por hospital...");
		
		List<Doctor> result = StreamSupport.stream(doctorRepository.doctoresActivosPorHospital(hospitalId).spliterator(), false)
				    .collect(Collectors.toList());
		List<DoctorResponseDTO> resultDTO = result.stream().map(DoctorAssembler::entityToResponseDTO)
				.collect(Collectors.toList());
		
		return resultDTO;
	}
	
	@Override
	public DoctorResponseDTO porId(Long doctorId) throws HospitalException{
		LOGGER.debug("doctor por id...");
		DoctorResponseDTO doctorDTO;
		Optional<Doctor> result =doctorRepository.findById(doctorId);
		if(result.isPresent()) {
			doctorDTO= DoctorAssembler.entityToResponseDTO(result.get());
		}else{
			throw new HospitalException("Id de doctor para actualizar no existe", LOGGER);
		}
		return doctorDTO;
	}
	
	@Override
	public Long guardar(Long hospitalId, DoctorRequestDTO doctorDTO) throws HospitalException{
		LOGGER.debug("guardar doctor...");
		Long doctorId;
		try {
			Optional<Hospital> hospitalOpt = hospitalRepository.findById(hospitalId);
			if(hospitalOpt.isPresent()) {
				Doctor doctor = DoctorAssembler.requestDTOtoEntity(doctorDTO);
				doctor.setHospital(hospitalOpt.get());
				doctorId = doctorRepository.save(doctor).getDoctorId();
			}else {
				throw new HospitalException("El hospital al que desea agregar el doctor no existe",LOGGER);
			}
			
			
		}catch (Exception e) {
			throw new HospitalException("No se pudo persistir un doctor", e, LOGGER);
		}
		return doctorId;
	}
	
	@Override
	public void actualizar(Long doctorId, DoctorRequestDTO doctorDTO) throws HospitalException{
		LOGGER.debug("actualizar doctor...");
		try {
			Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
			if(doctorOpt.isPresent()) {
				Doctor doctor = DoctorAssembler.updateRequestDTOtoEntity(doctorOpt.get(), doctorDTO);
				doctorRepository.save(doctor);
			}else {
				throw new HospitalException("Id de doctor para actualizar no existe", LOGGER);
			}
			
		}catch (Exception e) {
			throw new HospitalException("No se pudo persistir un doctor", e, LOGGER);
		}
	}
	
	@Override
	public void eliminar(Long doctorId) throws HospitalException{
		LOGGER.debug("eliminando doctor...");
		try {
			Optional<Doctor> doctorOpt = doctorRepository.findById(doctorId);
			if(doctorOpt.isPresent()) {
				Doctor doctor = doctorOpt.get();
				doctor.setEstado(EstadoEnum.ELIMINADO);
				doctorRepository.save(doctor);
			}else {
				throw new HospitalException("Id de doctor para eliminar no existe", LOGGER);
			}
			
		}catch (Exception e) {
			throw new HospitalException("No se pudo eliminar un doctor", e, LOGGER);
		}
	}
	
	@Override
	public List<DoctorResponseDTO> busquedaTodos(String nombre, String apellido) throws HospitalException{
		LOGGER.debug("lista doctores busqueda...");
		List<Doctor> resultParcial = new ArrayList<Doctor>();
		if(!nombre.isEmpty() && !apellido.isEmpty()) {
			resultParcial = doctorRepository.doctoresActivosPorNombreOApellido(nombre, apellido);
		}else {
			if(apellido.isEmpty()) {
				resultParcial = doctorRepository.doctoresActivosPorNombre(nombre);
			}else {
				if(nombre.isEmpty()) {
					resultParcial = doctorRepository.doctoresActivosPorApellido(apellido);
				}
			}
		}
		
		
		List<Doctor> result = StreamSupport.stream(resultParcial.spliterator(), false)
				    .collect(Collectors.toList());
		List<DoctorResponseDTO> resultDTO = result.stream().map(DoctorAssembler::entityToResponseDTO)
				.collect(Collectors.toList());
		
		return resultDTO;
	}
}
