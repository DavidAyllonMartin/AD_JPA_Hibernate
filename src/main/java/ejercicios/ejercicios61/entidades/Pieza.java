package ejercicios.ejercicios61.entidades;

import excepciones.CodigoIlegalException;
import excepciones.ColorIlegalException;
import excepciones.NombreIlegalException;
import excepciones.PrecioIlegalException;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PIEZA")
public class Pieza implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codigo;
    private BigDecimal precio;
    private String nombre;
    private String color;

    public Pieza() {
    }

    public Pieza(double precio, String nombre, String color) throws PrecioIlegalException, NombreIlegalException, ColorIlegalException {
        setPrecio(precio);
        setNombre(nombre);
        setColor(color);
    }

    public Pieza(long codigo, double precio, String nombre, String color) throws CodigoIlegalException, PrecioIlegalException, NombreIlegalException, ColorIlegalException {
        this(precio, nombre, color);
        setCodigo(codigo);
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) throws CodigoIlegalException {
        if (codigo < 0) {
            throw new CodigoIlegalException();
        }
        this.codigo = codigo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) throws PrecioIlegalException {
        if (precio < 0) {
            throw new PrecioIlegalException();
        }
        this.precio = new BigDecimal(String.valueOf(precio));
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws NombreIlegalException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NombreIlegalException();
        }
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) throws ColorIlegalException {
        if (color == null || color.trim().isEmpty()) {
            throw new ColorIlegalException();
        }
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Pieza{" +
                "codigo=" + codigo +
                ", precio=" + precio +
                ", nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
