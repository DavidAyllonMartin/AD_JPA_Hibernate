package empleados;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Regiones {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_region")
    private int idRegion;
    @Basic
    @Column(name = "nombre_region")
    private String nombreRegion;
    @OneToMany
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Regiones regiones = (Regiones) o;

        if (idRegion != regiones.idRegion) return false;
        if (nombreRegion != null ? !nombreRegion.equals(regiones.nombreRegion) : regiones.nombreRegion != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRegion;
        result = 31 * result + (nombreRegion != null ? nombreRegion.hashCode() : 0);
        return result;
    }
}
