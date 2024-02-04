package ejercicios.ejercicios63.ejercicio633.entidades;

import ejercicios.ejercicios63.ejercicio633.excepciones.IllegalStarWarsException;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "people", schema = "star_wars")
public class People implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 80)
    private String name;
    @Basic
    @Column(name = "gender", nullable = true, length = 15)
    private String gender;
    @Basic
    @Column(name = "birth_year", nullable = true, length = 10)
    private String birthYear;
    @Basic
    @Column(name = "height", nullable = false)
    private short height;
    @Basic
    @Column(name = "mass", nullable = false, precision = 0)
    private double mass;
    @Basic
    @Column(name = "hair_color", nullable = true, length = 20)
    private String hairColor;
    @Basic
    @Column(name = "skin_color", nullable = true, length = 30)
    private String skinColor;
    @Basic
    @Column(name = "eye_color", nullable = true, length = 20)
    private String eyeColor;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "homeworld_id", referencedColumnName = "id")
    private Planets homeworld;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "species_id", referencedColumnName = "id")
    private Species species;
    @Basic
    @Column(name = "created", nullable = true)
    private Timestamp created;
    @Basic
    @Column(name = "edited", nullable = true)
    private Timestamp edited;

    @ManyToMany(mappedBy = "pilots", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Starships> starships = new ArrayList<>();

    @ManyToMany(mappedBy = "people", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Films> films = new ArrayList<>();

    @ManyToMany(mappedBy = "people", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Vehicles> vehicles = new ArrayList<>();

    public People() {
    }
    public People(int id, String name, String gender, String birthYear, short height, double mass, String hairColor, String skinColor, String eyeColor, Planets homeworld, Species species, Timestamp created, Timestamp edited, List<Starships> starships, List<Films> films, List<Vehicles> vehicles) throws IllegalStarWarsException {
        setId(id);
        setName(name);
        setGender(gender);
        setBirthYear(birthYear);
        setHeight(height);
        setMass(mass);
        setHairColor(hairColor);
        setSkinColor(skinColor);
        setEyeColor(eyeColor);
        setHomeworld(homeworld);
        setSpecies(species);
        setCreated(created);
        setEdited(edited);
        setStarships(starships);
        setFilms(films);
        setVehicles(vehicles);
    }

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
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }
    public short getHeight() {
        return height;
    }

    public void setHeight(short height) {
        this.height = height;
    }
    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }
    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }
    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Planets getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(Planets homeworld) {
        this.homeworld = homeworld;
    }
    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
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

    public List<Starships> getStarships() {
        return starships;
    }

    public void setStarships(List<Starships> starships) throws IllegalStarWarsException {
        if (starships == null) {
            throw new IllegalStarWarsException("Starships list cannot be null.");
        }
        this.starships = starships;
    }

    public void addStarship(Starships starship) throws IllegalStarWarsException {
        if (starship == null) {
            throw new IllegalStarWarsException("Starship cannot be null.");
        }
        this.starships.add(starship);
        starship.getPilots().add(this);
    }
    public List<Films> getFilms() {
        return films;
    }

    public void setFilms(List<Films> films) throws IllegalStarWarsException {
        if (films == null) {
            throw new IllegalStarWarsException("Films list cannot be null.");
        }
        this.films = films;
    }

    public void addFilm(Films film) throws IllegalStarWarsException {
        if (film == null) {
            throw new IllegalStarWarsException("Film cannot be null.");
        }
        this.films.add(film);
        film.getPeople().add(this);
    }
    public List<Vehicles> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicles> vehicles) throws IllegalStarWarsException {
        if (vehicles == null) {
            throw new IllegalStarWarsException("Vehicles list cannot be null.");
        }
        this.vehicles = vehicles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        People people = (People) o;

        if (id != people.id) return false;
        if (height != people.height) return false;
        if (Double.compare(mass, people.mass) != 0) return false;
        if (!Objects.equals(name, people.name)) return false;
        if (!Objects.equals(gender, people.gender)) return false;
        if (!Objects.equals(birthYear, people.birthYear)) return false;
        if (!Objects.equals(hairColor, people.hairColor)) return false;
        if (!Objects.equals(skinColor, people.skinColor)) return false;
        if (!Objects.equals(eyeColor, people.eyeColor)) return false;
        if (!Objects.equals(homeworld, people.homeworld)) return false;
        if (!Objects.equals(species, people.species)) return false;
        if (!Objects.equals(created, people.created)) return false;
        if (!Objects.equals(edited, people.edited)) return false;
        if (!Objects.equals(starships, people.starships)) return false;
        if (!Objects.equals(films, people.films)) return false;
        return Objects.equals(vehicles, people.vehicles);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthYear != null ? birthYear.hashCode() : 0);
        result = 31 * result + (int) height;
        temp = Double.doubleToLongBits(mass);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (hairColor != null ? hairColor.hashCode() : 0);
        result = 31 * result + (skinColor != null ? skinColor.hashCode() : 0);
        result = 31 * result + (eyeColor != null ? eyeColor.hashCode() : 0);
        result = 31 * result + (homeworld != null ? homeworld.hashCode() : 0);
        result = 31 * result + (species != null ? species.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (edited != null ? edited.hashCode() : 0);
        result = 31 * result + (starships != null ? starships.hashCode() : 0);
        result = 31 * result + (films != null ? films.hashCode() : 0);
        result = 31 * result + (vehicles != null ? vehicles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", height=" + height +
                ", mass=" + mass +
                ", hairColor='" + hairColor + '\'' +
                ", skinColor='" + skinColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", homeworld=" + homeworld +
                ", species=" + species +
                ", created=" + created +
                ", edited=" + edited +
                '}';
    }

    public static class Builder {
        private int id = 0;
        private String name = null;
        private String gender = null;
        private String birthYear = null;
        private short height = 0;
        private double mass = 0.0;
        private String hairColor = null;
        private String skinColor = null;
        private String eyeColor = null;
        private Planets homeworld = null;
        private Species species = null;
        private Timestamp created = null;
        private Timestamp edited = null;
        private List<Starships> starships = new ArrayList<>();
        private List<Films> films = new ArrayList<>();
        private List<Vehicles> vehicles = new ArrayList<>();

        public Builder() {
        }

        public Builder id(int id){
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder birthYear(String birthYear) {
            this.birthYear = birthYear;
            return this;
        }

        public Builder height(short height) {
            this.height = height;
            return this;
        }

        public Builder mass(double mass) {
            this.mass = mass;
            return this;
        }

        public Builder hairColor(String hairColor) {
            this.hairColor = hairColor;
            return this;
        }

        public Builder skinColor(String skinColor) {
            this.skinColor = skinColor;
            return this;
        }

        public Builder eyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public Builder homeworld(Planets planet) {
            this.homeworld = planet;
            return this;
        }

        public Builder species(Species species) {
            this.species = species;
            return this;
        }

        public Builder created(Timestamp created) {
            this.created = created;
            return this;
        }

        public Builder edited(Timestamp edited) {
            this.edited = edited;
            return this;
        }

        public Builder starships(List<Starships> starships){
            this.starships = starships;
            return this;
        }

        public Builder films(List<Films> films){
            this.films = films;
            return this;
        }

        public Builder vehicles(List<Vehicles> vehicles){
            this.vehicles = vehicles;
            return this;
        }

        public People build() throws IllegalStarWarsException {
            return new People(
                    this.id,
                    this.name,
                    this.gender,
                    this.birthYear,
                    this.height,
                    this.mass,
                    this.hairColor,
                    this.skinColor,
                    this.eyeColor,
                    this.homeworld,
                    this.species,
                    this.created,
                    this.edited,
                    this.starships,
                    this.films,
                    this.vehicles);
        }
    }
}
