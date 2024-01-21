package entidades.taller;

import excepciones.*;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "PROVEEDOR", schema = "ejercicio61")
public class Proveedor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo_proveedor")
    private long codigo;
    @Column(name = "telefono_proveedor")
    private String telefono;
    @Column(name = "nombre_proveedor")
    private String nombre;
    @Column(name = "provincia_proveedor")
    private String provincia;
    @Column(name = "ciudad_proveedor")
    private String ciudad;

    public Proveedor() {
    }

    public Proveedor(String telefono, String nombre, String provincia, String ciudad) throws NombreIlegalException, ProvinciaIlegalException, CiudadIlegalException, TelefonoIlegalException {
        setTelefono(telefono);
        setNombre(nombre);
        setProvincia(provincia);
        setCiudad(ciudad);
    }

    public Proveedor(long codigo, String telefono, String nombre, String provincia, String ciudad) throws CodigoIlegalException, ProvinciaIlegalException, NombreIlegalException, CiudadIlegalException, TelefonoIlegalException {
        this(telefono, nombre, provincia, ciudad);
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) throws TelefonoIlegalException {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new TelefonoIlegalException();
        }
        this.telefono = telefono;
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

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) throws ProvinciaIlegalException {
        if (provincia == null || provincia.trim().isEmpty()) {
            throw new ProvinciaIlegalException();
        }
        this.provincia = provincia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) throws CiudadIlegalException {
        if (ciudad == null || ciudad.trim().isEmpty()) {
            throw new CiudadIlegalException();
        }
        this.ciudad = ciudad;
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
        return "Proveedor{" +
                "codigo=" + codigo +
                ", telefono='" + telefono + '\'' +
                ", nombre='" + nombre + '\'' +
                ", provincia='" + provincia + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
