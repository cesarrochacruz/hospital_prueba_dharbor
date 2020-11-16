package com.digitalharbor.hospital.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class EstadoEnumConverter implements AttributeConverter<EstadoEnum, String> {
 
    @Override
    public String convertToDatabaseColumn(EstadoEnum estadoEnum) {
        if (estadoEnum == null) {
            return null;
        }
        return estadoEnum.getEstadoEnum();
    }
 
    @Override
    public EstadoEnum convertToEntityAttribute(String estado) {
        if (estado == null) {
            return null;
        }
 
        return Stream.of(EstadoEnum.values())
          .filter(c -> c.getEstadoEnum().equals(estado))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}
