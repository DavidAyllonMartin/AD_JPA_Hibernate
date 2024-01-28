package ejercicios.ejercicios63.ejercicio631;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Departamentos implements Serializable {
    @Id
    @Column(name = "id_departamento")
    private int idDepartamento;
    @Basic
    @Column(name = "nombre_departamento", nullable = false, length = 30)
    private String nombreDepartamento;
    @Basic
    @Column(name = "id_director", nullable = true)
    private Integer idDirector;

    @ManyToMany(mappedBy = "departamentos", cascade = {CascadeType.ALL})
    private List<Localizaciones> localizaciones;


    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public Integer getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(Integer idDirector) {
        this.idDirector = idDirector;
    }

}
