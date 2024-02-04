package ejercicios.ejercicios71.ejercicio713.entidades;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "fantasy_draft", schema = "nfl")
public class FantasyDraft implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "draft_year", nullable = true)
    private Integer draftYear;
    @Basic
    @Column(name = "pick_round", nullable = false)
    private Integer pickRound;
    @Basic
    @Column(name = "pick_num", nullable = false)
    private Integer pickNum;
    @Basic
    @Column(name = "pick_overall", nullable = false)
    private Integer pickOverall;
    @Basic
    @Column(name = "keeper", nullable = false)
    private Boolean keeper;
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    private Players player;
    @ManyToOne
    @JoinColumn(name = "fantasy_team", referencedColumnName = "id", nullable = false)
    private FantasyTeam fantasyTeam;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDraftYear() {
        return draftYear;
    }

    public void setDraftYear(Integer draftYear) {
        this.draftYear = draftYear;
    }

    public Integer getPickRound() {
        return pickRound;
    }

    public void setPickRound(Integer pickRound) {
        this.pickRound = pickRound;
    }

    public Integer getPickNum() {
        return pickNum;
    }

    public void setPickNum(Integer pickNum) {
        this.pickNum = pickNum;
    }

    public Integer getPickOverall() {
        return pickOverall;
    }

    public void setPickOverall(Integer pickOverall) {
        this.pickOverall = pickOverall;
    }

    public Boolean getKeeper() {
        return keeper;
    }

    public void setKeeper(Boolean keeper) {
        this.keeper = keeper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FantasyDraft that = (FantasyDraft) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (draftYear != null ? !draftYear.equals(that.draftYear) : that.draftYear != null) return false;
        if (pickRound != null ? !pickRound.equals(that.pickRound) : that.pickRound != null) return false;
        if (pickNum != null ? !pickNum.equals(that.pickNum) : that.pickNum != null) return false;
        if (pickOverall != null ? !pickOverall.equals(that.pickOverall) : that.pickOverall != null) return false;
        if (keeper != null ? !keeper.equals(that.keeper) : that.keeper != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (draftYear != null ? draftYear.hashCode() : 0);
        result = 31 * result + (pickRound != null ? pickRound.hashCode() : 0);
        result = 31 * result + (pickNum != null ? pickNum.hashCode() : 0);
        result = 31 * result + (pickOverall != null ? pickOverall.hashCode() : 0);
        result = 31 * result + (keeper != null ? keeper.hashCode() : 0);
        return result;
    }

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }

    public FantasyTeam getFantasyTeam() {
        return fantasyTeam;
    }

    public void setFantasyTeam(FantasyTeam fantasyTeam) {
        this.fantasyTeam = fantasyTeam;
    }
}
