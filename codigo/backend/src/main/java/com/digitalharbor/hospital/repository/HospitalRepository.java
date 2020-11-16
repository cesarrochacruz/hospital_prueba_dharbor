package com.digitalharbor.hospital.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.digitalharbor.hospital.entity.Hospital;

public interface HospitalRepository extends CrudRepository<Hospital, Long> {
	
	@Query("SELECT h FROM Hospital h WHERE h.estado = 'AC'")
	List<Hospital> hospitalesActivos();
	
	@Query("SELECT h FROM Hospital h WHERE  h.nombre like %:nombre% AND h.estado ='AC'")
	List<Hospital> hospitalesActivosPorNombre(@Param("nombre") String nombre);
	
}
