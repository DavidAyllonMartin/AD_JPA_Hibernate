package ejercicios.ejercicios63.ejercicio633.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "films", schema = "star_wars")
public class Films implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "title", nullable = true, length = 80)
    private String title;
    @Basic
    @Column(name = "director", nullable = true, length = 60)
    private String director;
    @Basic
    @Column(name = "producer", nullable = true, length = -1)
    private String producer;
    @Basic
    @Column(name = "episode_id", nullable = true)
    private Integer episodeId;
    @Basic
    @Column(name = "opening_crawl", nullable = true, length = -1)
    private String openingCrawl;
    @Basic
    @Column(name = "release_date", nullable = true)
    private Timestamp releaseDate;
    @Basic
    @Column(name = "created", nullable = true)
    private Timestamp created;
    @Basic
    @Column(name = "edited", nullable = true)
    private Timestamp edited;

    @ManyToMany
    @JoinTable(
            name = "films_species",
            joinColumns = {@JoinColumn(name = "film_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "species_id", referencedColumnName = "id")}
    )
    List<Species> species = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "films_planets",
            joinColumns = {@JoinColumn(name = "film_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "planet_id", referencedColumnName = "id")}
    )
    List<Planets> planets = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "films_characters",
            joinColumns = {@JoinColumn(name = "film_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "people_id", referencedColumnName = "id")}
    )
    List<People> people = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "films_vehicles",
            joinColumns = {@JoinColumn(name = "film_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "vehicle_id", referencedColumnName = "id")}
    )
    List<Vehicles> vehicles = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "films_starships",
            joinColumns = {@JoinColumn(name = "film_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "starship_id", referencedColumnName = "id")}
    )
    List<Starships> starships = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Integer getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Integer episodeId) {
        this.episodeId = episodeId;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public void setOpeningCrawl(String openingCrawl) {
        this.openingCrawl = openingCrawl;
    }

    public Timestamp getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Timestamp releaseDate) {
        this.releaseDate = releaseDate;
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

    public List<Species> getSpecies() {
        return species;
    }

    public void setSpecies(List<Species> species) {
        this.species = species;
    }

    public List<Planets> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planets> planets) {
        this.planets = planets;
    }

    public List<People> getPeople() {
        return people;
    }

    public void setPeople(List<People> people) {
        this.people = people;
    }

    public List<Vehicles> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicles> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Starships> getStarships() {
        return starships;
    }

    public void setStarships(List<Starships> starships) {
        this.starships = starships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Films films = (Films) o;

        if (id != films.id) return false;
        if (!Objects.equals(title, films.title)) return false;
        if (!Objects.equals(director, films.director)) return false;
        if (!Objects.equals(producer, films.producer)) return false;
        if (!Objects.equals(episodeId, films.episodeId)) return false;
        if (!Objects.equals(openingCrawl, films.openingCrawl)) return false;
        if (!Objects.equals(releaseDate, films.releaseDate)) return false;
        if (!Objects.equals(created, films.created)) return false;
        if (!Objects.equals(edited, films.edited)) return false;
        if (!Objects.equals(species, films.species)) return false;
        if (!Objects.equals(planets, films.planets)) return false;
        if (!Objects.equals(people, films.people)) return false;
        if (!Objects.equals(vehicles, films.vehicles)) return false;
        return Objects.equals(starships, films.starships);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (producer != null ? producer.hashCode() : 0);
        result = 31 * result + (episodeId != null ? episodeId.hashCode() : 0);
        result = 31 * result + (openingCrawl != null ? openingCrawl.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (edited != null ? edited.hashCode() : 0);
        result = 31 * result + (species != null ? species.hashCode() : 0);
        result = 31 * result + (planets != null ? planets.hashCode() : 0);
        result = 31 * result + (people != null ? people.hashCode() : 0);
        result = 31 * result + (vehicles != null ? vehicles.hashCode() : 0);
        result = 31 * result + (starships != null ? starships.hashCode() : 0);
        return result;
    }
}
