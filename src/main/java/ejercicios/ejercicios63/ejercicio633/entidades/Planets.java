package ejercicios.ejercicios63.ejercicio633.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "planets", schema = "star_wars")
public class Planets implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 60)
    private String name;
    @Basic
    @Column(name = "climate", nullable = true, length = -1)
    private String climate;
    @Basic
    @Column(name = "rotation_period", nullable = true)
    private Integer rotationPeriod;
    @Basic
    @Column(name = "orbital_period", nullable = true)
    private Integer orbitalPeriod;
    @Basic
    @Column(name = "diameter", nullable = true)
    private Integer diameter;
    @Basic
    @Column(name = "gravity", nullable = true, length = -1)
    private String gravity;
    @Basic
    @Column(name = "terrain", nullable = true, length = -1)
    private String terrain;
    @Basic
    @Column(name = "surface_water", nullable = true, length = 10)
    private String surfaceWater;
    @Basic
    @Column(name = "population", nullable = true)
    private Long population;
    @Basic
    @Column(name = "created", nullable = true)
    private Timestamp created;
    @Basic
    @Column(name = "edited", nullable = true)
    private Timestamp edited;

    @ManyToMany(mappedBy = "planets")
    private List<Films> films = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public Integer getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(Integer rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public Integer getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(Integer orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public Integer getDiameter() {
        return diameter;
    }

    public void setDiameter(Integer diameter) {
        this.diameter = diameter;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getSurfaceWater() {
        return surfaceWater;
    }

    public void setSurfaceWater(String surfaceWater) {
        this.surfaceWater = surfaceWater;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getEdited() {
        return edited;
    }

    public void setEdited(Timestamp edited) {
        this.edited = edited;
    }

    public List<Films> getFilms() {
        return films;
    }

    public void setFilms(List<Films> films) {
        this.films = films;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Planets planets = (Planets) o;

        if (id != planets.id) return false;
        if (!Objects.equals(name, planets.name)) return false;
        if (!Objects.equals(climate, planets.climate)) return false;
        if (!Objects.equals(rotationPeriod, planets.rotationPeriod))
            return false;
        if (!Objects.equals(orbitalPeriod, planets.orbitalPeriod))
            return false;
        if (!Objects.equals(diameter, planets.diameter)) return false;
        if (!Objects.equals(gravity, planets.gravity)) return false;
        if (!Objects.equals(terrain, planets.terrain)) return false;
        if (!Objects.equals(surfaceWater, planets.surfaceWater))
            return false;
        if (!Objects.equals(population, planets.population)) return false;
        if (!Objects.equals(created, planets.created)) return false;
        if (!Objects.equals(edited, planets.edited)) return false;
        return Objects.equals(films, planets.films);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (climate != null ? climate.hashCode() : 0);
        result = 31 * result + (rotationPeriod != null ? rotationPeriod.hashCode() : 0);
        result = 31 * result + (orbitalPeriod != null ? orbitalPeriod.hashCode() : 0);
        result = 31 * result + (diameter != null ? diameter.hashCode() : 0);
        result = 31 * result + (gravity != null ? gravity.hashCode() : 0);
        result = 31 * result + (terrain != null ? terrain.hashCode() : 0);
        result = 31 * result + (surfaceWater != null ? surfaceWater.hashCode() : 0);
        result = 31 * result + (population != null ? population.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (edited != null ? edited.hashCode() : 0);
        result = 31 * result + (films != null ? films.hashCode() : 0);
        return result;
    }
}
