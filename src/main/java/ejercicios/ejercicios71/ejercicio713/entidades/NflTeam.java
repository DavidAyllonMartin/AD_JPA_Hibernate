package ejercicios.ejercicios71.ejercicio713.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "nfl_team", schema = "nfl")
public class NflTeam implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "team_name", nullable = false, length = 3)
    private String teamName;
    @Basic
    @Column(name = "full_name", nullable = false, length = 25)
    private String fullName;
    @OneToMany(mappedBy = "nflTeam")
    private List<Players> players;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NflTeam nflTeam = (NflTeam) o;

        if (id != null ? !id.equals(nflTeam.id) : nflTeam.id != null) return false;
        if (teamName != null ? !teamName.equals(nflTeam.teamName) : nflTeam.teamName != null) return false;
        if (fullName != null ? !fullName.equals(nflTeam.fullName) : nflTeam.fullName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        return result;
    }

    public List<Players> getPlayers() {
        return players;
    }

    public void setPlayers(List<Players> players) {
        this.players = players;
    }
}
