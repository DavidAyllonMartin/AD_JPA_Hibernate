package entidades.empleados;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ConversorDireccion implements AttributeConverter<Direccion, String> {

    @Override
    public String convertToDatabaseColumn(Direccion direccion) {
        if (direccion == null) {
            return null;
        }
        return direccion.getDireccion() + ", " + direccion.getCiudad() + ", " + direccion.getCodigoPostal();
    }

    @Override
    public Direccion convertToEntityAttribute(String direccionCompleta) {
        if (direccionCompleta == null) {
            return null;
        }
        String[] partes = direccionCompleta.split(", ");
        if (partes.length == 3) {
            return new Direccion(partes[0], partes[1], partes[2]);
        } else {
            throw new IllegalArgumentException("Formato de dirección incorrecto: " + direccionCompleta);
        }
    }
}

