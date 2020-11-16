package com.digitalharbor.hospital.request.dto;

public class PacienteRequestDTO extends PersonaRequestDTO{
	
	private NotaVisitaRequestDTO notaVisita;

	public NotaVisitaRequestDTO getNotaVisita() {
		return notaVisita;
	}

	public void setNotaVisita(NotaVisitaRequestDTO notaVisita) {
		this.notaVisita = notaVisita;
	}
	
}
