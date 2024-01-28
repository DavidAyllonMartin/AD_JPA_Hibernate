package ejercicios.ejercicios62.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table (name = "regiones")
public class Regiones implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_region")
    private int idRegion;
    @Basic
    @Column(name = "nombre_region")
    private String nombreRegion;
    @OneToMany (mappedBy = "region")
    private List<Paises> paises;

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }

    public List<Paises> getPaises() {
        return paises;
    }

    public void setPaises(List<Paises> paises) {
        this.paises = paises;
    }

    public String showInfo() {
        return "ID de región: " + this.idRegion + " | " + "Nombre de región: " + this.nombreRegion + "\n";
    }
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder(showInfo());
        for (Paises pais : this.paises){
            strb.append("\t").append(pais.showInfo());
            for (Localizaciones localizacion : pais.getLocalizaciones()){
                strb.append("\t").append("\t").append(localizacion.showInfo());
            }
        }
        return strb.toString();
    }
}
