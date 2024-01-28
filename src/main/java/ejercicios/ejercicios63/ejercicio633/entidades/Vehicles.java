package ejercicios.ejercicios63.ejercicio633.entidades;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vehicles", schema = "star_wars")
public class Vehicles {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "vehicle_class", nullable = true, length = 100)
    private String vehicleClass;

    @ManyToMany(mappedBy = "vehicles")
    private List<Films> films = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "vehicles_pilots",
            joinColumns = {@JoinColumn(name = "vehicle_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "people_id", referencedColumnName = "id")}
    )
    private List<People> people;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleClass() {
        return vehicleClass;
    }

    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    public List<Films> getFilms() {
        return films;
    }

    public void setFilms(List<Films> films) {
        this.films = films;
    }

    public List<People> getPeople() {
        return people;
    }

    public void setPeople(List<People> people) {
        this.people = people;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicles vehicles = (Vehicles) o;

        if (id != vehicles.id) return false;
        if (!Objects.equals(vehicleClass, vehicles.vehicleClass))
            return false;
        if (!Objects.equals(films, vehicles.films)) return false;
        return Objects.equals(people, vehicles.people);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (vehicleClass != null ? vehicleClass.hashCode() : 0);
        result = 31 * result + (films != null ? films.hashCode() : 0);
        result = 31 * result + (people != null ? people.hashCode() : 0);
        return result;
    }
}
