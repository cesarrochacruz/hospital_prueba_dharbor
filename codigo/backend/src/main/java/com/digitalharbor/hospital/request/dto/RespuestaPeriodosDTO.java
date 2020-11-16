package com.digitalharbor.hospital.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RespuestaPeriodosDTO {
	@JsonProperty("diaPicoMaximoLluvia")
	private int diaPicoMaximo;
	private int periodosLluvia;
	private int periodosSequia;
	private int periodosOptimo;
	public int getDiaPicoMaximo() {
		return diaPicoMaximo;
	}
	public void setDiaPicoMaximo(int diaPicoMaximo) {
		this.diaPicoMaximo = diaPicoMaximo;
	}
	public int getPeriodosLluvia() {
		return periodosLluvia;
	}
	public void setPeriodosLluvia(int periodosLluvia) {
		this.periodosLluvia = periodosLluvia;
	}
	public int getPeriodosSequia() {
		return periodosSequia;
	}
	public void setPeriodosSequia(int periodosSequia) {
		this.periodosSequia = periodosSequia;
	}
	public int getPeriodosOptimo() {
		return periodosOptimo;
	}
	public void setPeriodosOptimo(int periodosOptimo) {
		this.periodosOptimo = periodosOptimo;
	}
	
}
