package ejercicios.ejercicios62.entidades;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table (name = "localizaciones")
@PersistenceUnit (unitName = "empleados")
public class Localizaciones implements Serializable {
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

    public Paises getPais() {
        return pais;
    }

    public void setPais(Paises pais) {
        this.pais = pais;
    }

    public String showInfo() {
        return "ID Localización: " + idLocalizacion + " | " +
                "Direccion: " + direccion + " | " +
                "Código Postal: " + codigoPostal + " | " +
                "Ciudad: " + ciudad + " | " +
                "Provincia: " + provincia + '\n';
    }

    @Override
    public String toString() {
        final StringBuilder strb = new StringBuilder(showInfo());
        strb.append("\t").append(getPais().getRegion().showInfo());
        strb.append("\t").append("\t").append(getPais().showInfo());
        return strb.toString();
    }
}
