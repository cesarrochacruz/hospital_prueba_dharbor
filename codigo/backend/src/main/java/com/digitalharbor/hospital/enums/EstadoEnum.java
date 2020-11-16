package com.digitalharbor.hospital.enums;

public enum EstadoEnum {
    ACTIVO("AC"), ELIMINADO("EL");
 
    private String estado;
 
    private EstadoEnum(String estado) {
        this.estado = estado;
    }
 
    public String getEstadoEnum() {
        return estado;
    }
 
}
