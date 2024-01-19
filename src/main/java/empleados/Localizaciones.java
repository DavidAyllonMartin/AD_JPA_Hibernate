package empleados;

import jakarta.persistence.*;

@Entity
public class Localizaciones {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_localizacion")
    private int idLocalizacion;
    @Basic
    @Column(name = "direccion")
    private String direccion;
    @Basic
    @Column(name = "codigo_postal")
    private String codigoPostal;
    @Basic
    @Column(name = "ciudad")
    private String ciudad;
    @Basic
    @Column(name = "provincia")
    private String provincia;
    @Basic
    @Column(name = "id_pais")
    private String idPais;
    @ManyToOne
    @JoinColumn (name = "id_pais")
    private Paises pais;

    public int getIdLocalizacion() {
        return idLocalizacion;
    }

    public void setIdLocalizacion(int idLocalizacion) {
        this.idLocalizacion = idLocalizacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Localizaciones that = (Localizaciones) o;

        if (idLocalizacion != that.idLocalizacion) return false;
        if (direccion != null ? !direccion.equals(that.direccion) : that.direccion != null) return false;
        if (codigoPostal != null ? !codigoPostal.equals(that.codigoPostal) : that.codigoPostal != null) return false;
        if (ciudad != null ? !ciudad.equals(that.ciudad) : that.ciudad != null) return false;
        if (provincia != null ? !provincia.equals(that.provincia) : that.provincia != null) return false;
        if (idPais != null ? !idPais.equals(that.idPais) : that.idPais != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLocalizacion;
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + (codigoPostal != null ? codigoPostal.hashCode() : 0);
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (provincia != null ? provincia.hashCode() : 0);
        result = 31 * result + (idPais != null ? idPais.hashCode() : 0);
        return result;
    }
}
