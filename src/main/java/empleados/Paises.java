package empleados;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Paises {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_pais")
    private String idPais;
    @Basic
    @Column(name = "nombre_pais")
    private String nombrePais;
    @Basic
    @Column(name = "id_region")
    private Integer idRegion;
    @OneToMany
    private List<Localizaciones> localizaciones;
    @ManyToOne
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

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paises paises = (Paises) o;

        if (idPais != null ? !idPais.equals(paises.idPais) : paises.idPais != null) return false;
        if (nombrePais != null ? !nombrePais.equals(paises.nombrePais) : paises.nombrePais != null) return false;
        if (idRegion != null ? !idRegion.equals(paises.idRegion) : paises.idRegion != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPais != null ? idPais.hashCode() : 0;
        result = 31 * result + (nombrePais != null ? nombrePais.hashCode() : 0);
        result = 31 * result + (idRegion != null ? idRegion.hashCode() : 0);
        return result;
    }
}
