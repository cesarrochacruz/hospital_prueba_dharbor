package com.digitalharbor.hospital.exception;

import org.apache.logging.log4j.Logger;

public class HospitalException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HospitalException(Exception e,Logger logger) {
		super(e);
		logger.error(e);
		e.printStackTrace();
	}
	public HospitalException(String mensaje, Exception e,Logger logger) {
		super(mensaje, e);
		logger.error(mensaje);
		logger.error(e);
		e.printStackTrace();
	}
	public HospitalException(String mensaje,Logger logger) {
		super(mensaje);
		logger.error(mensaje);
		super.printStackTrace();
	}
	
	public HospitalException(Exception e) {
		super(e);
		e.printStackTrace();
	}
	public HospitalException(String mensaje, Exception e) {
		super(mensaje, e);
		e.printStackTrace();
	}
	public HospitalException(String mensaje) {
		super(mensaje);
		super.printStackTrace();
	}
}
