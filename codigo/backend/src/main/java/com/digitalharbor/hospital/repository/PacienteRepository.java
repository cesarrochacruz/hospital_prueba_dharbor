package com.digitalharbor.hospital.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.digitalharbor.hospital.entity.Paciente;

public interface PacienteRepository extends CrudRepository<Paciente, Long> {
	@Query("SELECT p FROM Paciente p WHERE p.estado = 'AC'")
	List<Paciente> pacientesActivos();
	
	@Query("SELECT nv.paciente FROM NotaVisita nv WHERE nv.doctor.doctorId = :doctorId  AND nv.paciente.estado ='AC'")
	List<Paciente> pacientesActivosPorDoctor(@Param("doctorId") Long doctorId);
	
	@Query("SELECT p FROM Paciente p WHERE  p.nombre like %:nombre% AND p.apellido like %:apellido% AND p.estado ='AC'")
	List<Paciente> pacientesActivosPorNombreOApellido(@Param("nombre") String nombre,@Param("apellido") String apellido);
	
	@Query("SELECT p FROM Paciente p WHERE  p.nombre like %:nombre% AND p.estado ='AC'")
	List<Paciente> pacientesActivosPorNombre(@Param("nombre") String nombre);
	
	@Query("SELECT p FROM Paciente p WHERE  p.apellido like %:apellido% AND p.estado ='AC'")
	List<Paciente> pacientesActivosPorApellido(@Param("apellido") String apellido);
}
