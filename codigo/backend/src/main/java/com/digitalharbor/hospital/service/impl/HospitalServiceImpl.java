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

import com.digitalharbor.hospital.assembler.HospitalAssembler;
import com.digitalharbor.hospital.entity.Hospital;
import com.digitalharbor.hospital.enums.EstadoEnum;
import com.digitalharbor.hospital.exception.HospitalException;
import com.digitalharbor.hospital.repository.HospitalRepository;
import com.digitalharbor.hospital.request.dto.HospitalRequestDTO;
import com.digitalharbor.hospital.response.dto.HospitalResponseDTO;
import com.digitalharbor.hospital.service.HospitalService;

@Service
public class HospitalServiceImpl implements HospitalService {

	private static final Logger LOGGER = LogManager.getLogger(HospitalServiceImpl.class);
	
	@Autowired
	HospitalRepository hospitalRepository;
	
	@Override
	public List<HospitalResponseDTO> todos() throws HospitalException{
		LOGGER.debug("lista hospitales...");
		
		List<Hospital> result = StreamSupport.stream(hospitalRepository.hospitalesActivos().spliterator(), false)
				    .collect(Collectors.toList());
		List<HospitalResponseDTO> resultDTO = result.stream().map(HospitalAssembler::entityToResponseDTO)
				.collect(Collectors.toList());
		
		return resultDTO;
	}
	
	@Override
	public HospitalResponseDTO porId(Long hospitalId) throws HospitalException{
		LOGGER.debug("hospital por id...");
		HospitalResponseDTO hospitalDTO;
		Optional<Hospital> result =hospitalRepository.findById(hospitalId);
		if(result.isPresent()) {
			hospitalDTO= HospitalAssembler.entityToResponseDTO(result.get());
		}else{
			throw new HospitalException("Id de hospital para actualizar no existe", LOGGER);
		}
		return hospitalDTO;
	}
	
	@Override
	public Long guardar(HospitalRequestDTO hospitalDTO) throws HospitalException{
		LOGGER.debug("guardar hospital...");
		Long doctorId;
		try {
			Hospital hospital = HospitalAssembler.requestDTOtoEntity(hospitalDTO);
			doctorId = hospitalRepository.save(hospital).getHospitalId();
		}catch (Exception e) {
			throw new HospitalException("No se pudo persistir un Hospital", e, LOGGER);
		}
		return doctorId;
	}
	
	@Override
	public void actualizar(Long hospitalId, HospitalRequestDTO hospitalDTO) throws HospitalException{
		LOGGER.debug("actualizar hospital...");
		try {
			Optional<Hospital> hospitalOpt = hospitalRepository.findById(hospitalId);
			if(hospitalOpt.isPresent()) {
				Hospital hospital = HospitalAssembler.updateRequestDTOtoEntity(hospitalOpt.get(), hospitalDTO);
				hospitalRepository.save(hospital);
			}else {
				throw new HospitalException("Id de hospital para actualizar no existe", LOGGER);
			}
			
		}catch (Exception e) {
			throw new HospitalException("No se pudo persistir un Hospital", e, LOGGER);
		}
	}
	
	@Override
	public void eliminar(Long hospitalId) throws HospitalException{
		LOGGER.debug("eliminando hospital...");
		try {
			Optional<Hospital> hospitalOpt = hospitalRepository.findById(hospitalId);
			if(hospitalOpt.isPresent()) {
				Hospital hospital = hospitalOpt.get();
				hospital.setEstado(EstadoEnum.valueOf("ELIMINADO"));
				hospitalRepository.save(hospital);
			}else {
				throw new HospitalException("Id de hospital para eliminar no existe", LOGGER);
			}
			
		}catch (Exception e) {
			throw new HospitalException("No se pudo eliminar un Hospital", e, LOGGER);
		}
	}
	
	@Override
	public List<HospitalResponseDTO> busquedaTodos(String nombre) throws HospitalException{
		LOGGER.debug("lista hospitales busqueda...");
		List<Hospital> resultParcial = new ArrayList<Hospital>();
		if(nombre.isEmpty()) {
			resultParcial = hospitalRepository.hospitalesActivos();
		}else {
			resultParcial = hospitalRepository.hospitalesActivosPorNombre(nombre);
		}
		
		List<Hospital> result = StreamSupport.stream(resultParcial.spliterator(), false)
				    .collect(Collectors.toList());
		List<HospitalResponseDTO> resultDTO = result.stream().map(HospitalAssembler::entityToResponseDTO)
				.collect(Collectors.toList());
		
		return resultDTO;
	}
}
