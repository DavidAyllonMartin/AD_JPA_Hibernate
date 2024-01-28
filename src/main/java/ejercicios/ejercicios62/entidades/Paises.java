package ejercicios.ejercicios62.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table (name = "paises")
public class Paises implements Serializable {
    @Id
    @Column(name = "id_pais")
    private String idPais;
    @Basic
    @Column(name = "nombre_pais")
    private String nombrePais;
    @OneToMany (mappedBy = "pais")
    private List<Localizaciones> localizaciones;
    @ManyToOne
    @JoinColumn (name = "id_region")
    private Regiones region;

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    public List<Localizaciones> getLocalizaciones() {
        return localizaciones;
    }

    public void setLocalizaciones(List<Localizaciones> localizaciones) {
        this.localizaciones = localizaciones;
    }

    public Regiones getRegion() {
        return region;
    }

    public void setRegion(Regiones region) {
        this.region = region;
    }

    public String showInfo() {
        return "ID País: " + idPais + " | " + "Nombre del país: " + nombrePais + '\n';
    }

    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder(showInfo());
        strb.append("\t").append(getRegion().showInfo());
        for (Localizaciones localizacion : this.getLocalizaciones()){
            strb.append("\t").append("\t").append(localizacion.showInfo());
        }
        return strb.toString();
    }
}
