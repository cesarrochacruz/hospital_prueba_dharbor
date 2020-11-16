package com.digitalharbor.hospital.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.digitalharbor.hospital.entity.Doctor;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
	
	@Query("SELECT d FROM Doctor d WHERE d.estado = 'AC'")
	List<Doctor> doctoresActivos();
	
	@Query("SELECT d FROM Doctor d WHERE d.hospital.hospitalId = :hospitalId  AND d.estado ='AC'")
	List<Doctor> doctoresActivosPorHospital(@Param("hospitalId") Long hospitalId);
	
	@Query("SELECT d FROM Doctor d WHERE  d.nombre like %:nombre% AND d.apellido like %:apellido% AND d.estado ='AC'")
	List<Doctor> doctoresActivosPorNombreOApellido(@Param("nombre") String nombre,@Param("apellido") String apellido);
	
	@Query("SELECT d FROM Doctor d WHERE  d.nombre like %:nombre% AND d.estado ='AC'")
	List<Doctor> doctoresActivosPorNombre(@Param("nombre") String nombre);
	
	@Query("SELECT d FROM Doctor d WHERE  d.apellido like %:apellido% AND d.estado ='AC'")
	List<Doctor> doctoresActivosPorApellido(@Param("apellido") String apellido);
	
	//List<Doctor> fetchHospitalId(@Param("hospitalId") Long hospitalId);
	
}
