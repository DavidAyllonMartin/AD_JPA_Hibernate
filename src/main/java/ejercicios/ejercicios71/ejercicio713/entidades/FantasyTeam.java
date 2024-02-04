package ejercicios.ejercicios71.ejercicio713.entidades;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "fantasy_team", schema = "nfl")
public class FantasyTeam implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "team_name", nullable = false, length = 30)
    private String teamName;
    @Basic
    @Column(name = "owner_name", nullable = true, length = 20)
    private String ownerName;
    @OneToMany(mappedBy = "fantasyTeam")
    private List<FantasyDraft> fantasyDrafts;
    @OneToMany(mappedBy = "sourceTeam")
    private List<FantasyTransactions> activeTransactions;
    @OneToMany(mappedBy = "destinationTeam")
    private List<FantasyTransactions> passiveTransactions;
    @OneToMany(mappedBy = "fantasyTeam")
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FantasyTeam that = (FantasyTeam) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (teamName != null ? !teamName.equals(that.teamName) : that.teamName != null) return false;
        if (ownerName != null ? !ownerName.equals(that.ownerName) : that.ownerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (teamName != null ? teamName.hashCode() : 0);
        result = 31 * result + (ownerName != null ? ownerName.hashCode() : 0);
        return result;
    }

    public List<FantasyDraft> getFantasyDrafts() {
        return fantasyDrafts;
    }

    public void setFantasyDrafts(List<FantasyDraft> fantasyDrafts) {
        this.fantasyDrafts = fantasyDrafts;
    }

    public List<FantasyTransactions> getActiveTransactions() {
        return activeTransactions;
    }

    public void setActiveTransactions(List<FantasyTransactions> sourceTeam) {
        this.activeTransactions = sourceTeam;
    }

    public List<FantasyTransactions> getPassiveTransactions() {
        return passiveTransactions;
    }

    public void setPassiveTransactions(List<FantasyTransactions> destinationTeam) {
        this.passiveTransactions = destinationTeam;
    }

    public List<Players> getPlayers() {
        return players;
    }

    public void setPlayers(List<Players> players) {
        this.players = players;
    }
}
