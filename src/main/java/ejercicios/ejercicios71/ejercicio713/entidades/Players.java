package ejercicios.ejercicios71.ejercicio713.entidades;

import ejercicios.ejercicios71.ejercicio713.entidades.enums.Position;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
@Entity
@Table(name = "players", schema = "nfl")
public class Players implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "player_name", nullable = false, length = 70)
    private String playerName;
    @Basic
    @Column(name = "slug", nullable = true, length = 70)
    private String slug;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private Position position;
    @Basic
    @Column(name = "rookie", nullable = true)
    private Boolean rookie;
    @Basic
    @Column(name = "age", nullable = true, precision = 1)
    private BigDecimal age;
    @Basic
    @Column(name = "height_feet", nullable = true)
    private Integer heightFeet;
    @Basic
    @Column(name = "height_inches", nullable = true)
    private Integer heightInches;
    @Basic
    @Column(name = "weight", nullable = true)
    private Integer weight;
    @Basic
    @Column(name = "seasons_experience", nullable = true)
    private Integer seasonsExperience;
    @Basic
    @Column(name = "pick_round", nullable = true)
    private Integer pickRound;
    @Basic
    @Column(name = "pick_num", nullable = true)
    private Integer pickNum;
    @Basic
    @Column(name = "player_number", nullable = true)
    private Integer playerNumber;
    @Basic
    @Column(name = "birthday", nullable = true)
    private Date birthday;
    @Basic
    @Column(name = "draft_year", nullable = true)
    private Integer draftYear;
    @Basic
    @Column(name = "college", nullable = true, length = 70)
    private String college;
    @OneToMany(mappedBy = "player")
    private List<FantasyDraft> fantasyDrafts;
    @OneToMany(mappedBy = "player")
    private List<FantasyTransactions> fantasyTransactions;
    @ManyToOne
    @JoinColumn(name = "nfl_team", referencedColumnName = "id")
    private NflTeam nflTeam;
    @ManyToOne
    @JoinColumn(name = "fantasy_team", referencedColumnName = "id")
    private FantasyTeam fantasyTeam;
    @OneToMany(mappedBy = "player")
    private List<StandardValueHistory> standardValueHistories;
    @OneToMany(mappedBy = "player")
    private List<SuperflexValueHistory> superflexValueHistories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Boolean getRookie() {
        return rookie;
    }

    public void setRookie(Boolean rookie) {
        this.rookie = rookie;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public Integer getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(Integer heightFeet) {
        this.heightFeet = heightFeet;
    }

    public Integer getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(Integer heightInches) {
        this.heightInches = heightInches;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSeasonsExperience() {
        return seasonsExperience;
    }

    public void setSeasonsExperience(Integer seasonsExperience) {
        this.seasonsExperience = seasonsExperience;
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

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(Integer playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getDraftYear() {
        return draftYear;
    }

    public void setDraftYear(Integer draftYear) {
        this.draftYear = draftYear;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Players players = (Players) o;

        if (id != null ? !id.equals(players.id) : players.id != null) return false;
        if (playerName != null ? !playerName.equals(players.playerName) : players.playerName != null) return false;
        if (slug != null ? !slug.equals(players.slug) : players.slug != null) return false;
        if (position != null ? !position.equals(players.position) : players.position != null) return false;
        if (rookie != null ? !rookie.equals(players.rookie) : players.rookie != null) return false;
        if (age != null ? !age.equals(players.age) : players.age != null) return false;
        if (heightFeet != null ? !heightFeet.equals(players.heightFeet) : players.heightFeet != null) return false;
        if (heightInches != null ? !heightInches.equals(players.heightInches) : players.heightInches != null)
            return false;
        if (weight != null ? !weight.equals(players.weight) : players.weight != null) return false;
        if (seasonsExperience != null ? !seasonsExperience.equals(players.seasonsExperience) : players.seasonsExperience != null)
            return false;
        if (pickRound != null ? !pickRound.equals(players.pickRound) : players.pickRound != null) return false;
        if (pickNum != null ? !pickNum.equals(players.pickNum) : players.pickNum != null) return false;
        if (playerNumber != null ? !playerNumber.equals(players.playerNumber) : players.playerNumber != null)
            return false;
        if (birthday != null ? !birthday.equals(players.birthday) : players.birthday != null) return false;
        if (draftYear != null ? !draftYear.equals(players.draftYear) : players.draftYear != null) return false;
        if (college != null ? !college.equals(players.college) : players.college != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (playerName != null ? playerName.hashCode() : 0);
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (rookie != null ? rookie.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (heightFeet != null ? heightFeet.hashCode() : 0);
        result = 31 * result + (heightInches != null ? heightInches.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (seasonsExperience != null ? seasonsExperience.hashCode() : 0);
        result = 31 * result + (pickRound != null ? pickRound.hashCode() : 0);
        result = 31 * result + (pickNum != null ? pickNum.hashCode() : 0);
        result = 31 * result + (playerNumber != null ? playerNumber.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (draftYear != null ? draftYear.hashCode() : 0);
        result = 31 * result + (college != null ? college.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Players{");
        sb.append("id=").append(id);
        sb.append(", playerName='").append(playerName).append('\'');
        sb.append(", slug='").append(slug).append('\'');
        sb.append(", position=").append(position);
        sb.append(", rookie=").append(rookie);
        sb.append('}');
        return sb.toString();
    }

    public List<FantasyDraft> getFantasyDrafts() {
        return fantasyDrafts;
    }

    public void setFantasyDrafts(List<FantasyDraft> fantasyDrafts) {
        this.fantasyDrafts = fantasyDrafts;
    }

    public List<FantasyTransactions> getFantasyTransactions() {
        return fantasyTransactions;
    }

    public void setFantasyTransactions(List<FantasyTransactions> fantasyTransactions) {
        this.fantasyTransactions = fantasyTransactions;
    }

    public NflTeam getNflTeam() {
        return nflTeam;
    }

    public void setNflTeam(NflTeam nflTeam) {
        this.nflTeam = nflTeam;
    }

    public FantasyTeam getFantasyTeam() {
        return fantasyTeam;
    }

    public void setFantasyTeam(FantasyTeam fantasyTeam) {
        this.fantasyTeam = fantasyTeam;
    }

    public List<StandardValueHistory> getStandardValueHistories() {
        return standardValueHistories;
    }

    public void setStandardValueHistories(List<StandardValueHistory> standardValueHistories) {
        this.standardValueHistories = standardValueHistories;
    }

    public List<SuperflexValueHistory> getSuperflexValueHistories() {
        return superflexValueHistories;
    }

    public void setSuperflexValueHistories(List<SuperflexValueHistory> superflexValueHistories) {
        this.superflexValueHistories = superflexValueHistories;
    }
}
