package ejercicios.ejercicios63.ejercicio633.entidades;

import ejercicios.ejercicios63.ejercicio633.excepciones.IllegalStarWarsException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "starships", schema = "star_wars")
public class Starships {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "starship_class", nullable = true, length = -1)
    private String starshipClass;
    @Basic
    @Column(name = "MGLT", nullable = true, length = 10)
    private String mglt;
    @Basic
    @Column(name = "hyperdrive_rating", nullable = true, length = 10)
    private String hyperdriveRating;

    @ManyToMany( cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "starships_pilots",
            joinColumns = {@JoinColumn(name = "starship_id")},
            inverseJoinColumns = {@JoinColumn(name = "people_id")}
    )
    private List<People> pilots = new ArrayList<>();

    @ManyToMany(mappedBy = "starships", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Films> films = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStarshipClass() {
        return starshipClass;
    }

    public void setStarshipClass(String starshipClass) {
        this.starshipClass = starshipClass;
    }

    public String getMglt() {
        return mglt;
    }

    public void setMglt(String mglt) {
        this.mglt = mglt;
    }

    public String getHyperdriveRating() {
        return hyperdriveRating;
    }

    public void setHyperdriveRating(String hyperdriveRating) {
        this.hyperdriveRating = hyperdriveRating;
    }

    public List<People> getPilots() {
        return pilots;
    }

    public void setPilots(List<People> people) {
        this.pilots = people;
    }

    public void addPilot(People pilot) throws IllegalStarWarsException {
        if (pilot == null) {
            throw new IllegalStarWarsException("Pilot cannot be null.");
        }
        this.pilots.add(pilot);
        pilot.getStarships().add(this);
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

        Starships starships = (Starships) o;

        if (id != starships.id) return false;
        if (!Objects.equals(starshipClass, starships.starshipClass))
            return false;
        if (!Objects.equals(mglt, starships.mglt)) return false;
        if (!Objects.equals(hyperdriveRating, starships.hyperdriveRating))
            return false;
        if (!Objects.equals(pilots, starships.pilots)) return false;
        return Objects.equals(films, starships.films);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (starshipClass != null ? starshipClass.hashCode() : 0);
        result = 31 * result + (mglt != null ? mglt.hashCode() : 0);
        result = 31 * result + (hyperdriveRating != null ? hyperdriveRating.hashCode() : 0);
        result = 31 * result + (pilots != null ? pilots.hashCode() : 0);
        result = 31 * result + (films != null ? films.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Starships{" +
                "id=" + id +
                ", starshipClass='" + starshipClass + '\'' +
                ", mglt='" + mglt + '\'' +
                ", hyperdriveRating='" + hyperdriveRating + '\'' +
                '}';
    }
}
