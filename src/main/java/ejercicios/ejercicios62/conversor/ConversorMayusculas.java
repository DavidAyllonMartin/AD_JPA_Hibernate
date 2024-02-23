package ejercicios.ejercicios62.conversor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ConversorMayusculas implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String direccion) {
        return direccion.toUpperCase();
    }

    @Override
    public String convertToEntityAttribute(String direccionCompleta) {
        if (direccionCompleta == null) {
            return null;
        }
        return direccionCompleta.toLowerCase();
    }
}

