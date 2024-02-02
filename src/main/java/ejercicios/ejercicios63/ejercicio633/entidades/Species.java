package ejercicios.ejercicios63.ejercicio633.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "species", schema = "star_wars")
public class Species implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = true, length = 60)
    private String name;
    @Basic
    @Column(name = "average_height", nullable = true, length = 10)
    private String averageHeight;
    @Basic
    @Column(name = "average_lifespan", nullable = true, length = 20)
    private String averageLifespan;
    @Basic
    @Column(name = "classification", nullable = true, length = 50)
    private String classification;
    @Basic
    @Column(name = "designation", nullable = true, length = 60)
    private String designation;
    @Basic
    @Column(name = "eye_colors", nullable = true, length = -1)
    private String eyeColors;
    @Basic
    @Column(name = "hair_colors", nullable = true, length = -1)
    private String hairColors;
    @Basic
    @Column(name = "homeworld", nullable = false)
    private int homeworld;
    @Basic
    @Column(name = "language", nullable = true, length = 20)
    private String language;
    @Basic
    @Column(name = "skin_colors", nullable = true, length = -1)
    private String skinColors;
    @Basic
    @Column(name = "created", nullable = true)
    private Timestamp created;
    @Basic
    @Column(name = "edited", nullable = true)
    private Timestamp edited;

    @ManyToMany(mappedBy = "species")
    private List<Films> films;

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

    public String getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(String averageHeight) {
        this.averageHeight = averageHeight;
    }

    public String getAverageLifespan() {
        return averageLifespan;
    }

    public void setAverageLifespan(String averageLifespan) {
        this.averageLifespan = averageLifespan;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEyeColors() {
        return eyeColors;
    }

    public void setEyeColors(String eyeColors) {
        this.eyeColors = eyeColors;
    }

    public String getHairColors() {
        return hairColors;
    }

    public void setHairColors(String hairColors) {
        this.hairColors = hairColors;
    }

    public int getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(int homeworld) {
        this.homeworld = homeworld;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSkinColors() {
        return skinColors;
    }

    public void setSkinColors(String skinColors) {
        this.skinColors = skinColors;
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

        Species species = (Species) o;

        if (id != species.id) return false;
        if (homeworld != species.homeworld) return false;
        if (!Objects.equals(name, species.name)) return false;
        if (!Objects.equals(averageHeight, species.averageHeight))
            return false;
        if (!Objects.equals(averageLifespan, species.averageLifespan))
            return false;
        if (!Objects.equals(classification, species.classification))
            return false;
        if (!Objects.equals(designation, species.designation)) return false;
        if (!Objects.equals(eyeColors, species.eyeColors)) return false;
        if (!Objects.equals(hairColors, species.hairColors)) return false;
        if (!Objects.equals(language, species.language)) return false;
        if (!Objects.equals(skinColors, species.skinColors)) return false;
        if (!Objects.equals(created, species.created)) return false;
        if (!Objects.equals(edited, species.edited)) return false;
        return Objects.equals(films, species.films);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (averageHeight != null ? averageHeight.hashCode() : 0);
        result = 31 * result + (averageLifespan != null ? averageLifespan.hashCode() : 0);
        result = 31 * result + (classification != null ? classification.hashCode() : 0);
        result = 31 * result + (designation != null ? designation.hashCode() : 0);
        result = 31 * result + (eyeColors != null ? eyeColors.hashCode() : 0);
        result = 31 * result + (hairColors != null ? hairColors.hashCode() : 0);
        result = 31 * result + homeworld;
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (skinColors != null ? skinColors.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (edited != null ? edited.hashCode() : 0);
        result = 31 * result + (films != null ? films.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Species{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averageHeight='" + averageHeight + '\'' +
                ", averageLifespan='" + averageLifespan + '\'' +
                ", classification='" + classification + '\'' +
                ", designation='" + designation + '\'' +
                ", eyeColors='" + eyeColors + '\'' +
                ", hairColors='" + hairColors + '\'' +
                ", homeworld=" + homeworld +
                ", language='" + language + '\'' +
                ", skinColors='" + skinColors + '\'' +
                ", created=" + created +
                ", edited=" + edited +
                '}';
    }
}
