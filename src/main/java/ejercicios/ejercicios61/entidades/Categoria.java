package ejercicios.ejercicios61.entidades;

import excepciones.CodigoIlegalException;
import excepciones.NombreIlegalException;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "CATEGORIA")
public class Categoria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long codigo;
    private String nombre;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) throws CodigoIlegalException {
        if (codigo <= 0) {
            throw new CodigoIlegalException();
        }
        this.codigo = codigo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categoria categoria = (Categoria) o;

        if (codigo != categoria.codigo) return false;
        return Objects.equals(nombre, categoria.nombre);
    }

    @Override
    public int hashCode() {
        int result = (int) (codigo ^ (codigo >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
