package ejercicios.ejercicios71.ejercicio713.entidades;

import ejercicios.ejercicios71.ejercicio713.entidades.enums.TransactionType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "fantasy_transactions", schema = "nfl")
public class FantasyTransactions implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    private TransactionType transactionType;
    @Basic
    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Players player;
    @ManyToOne
    @JoinColumn(name = "fantasy_team_id", referencedColumnName = "id")
    private FantasyTeam sourceTeam;
    @ManyToOne
    @JoinColumn(name = "destination_team_id", referencedColumnName = "id")
    private FantasyTeam destinationTeam;

    public FantasyTransactions(TransactionType transactionType, Date transactionDate, Players player, FantasyTeam sourceTeam, FantasyTeam destinationTeam) {
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.player = player;
        this.sourceTeam = sourceTeam;
        this.destinationTeam = destinationTeam;
    }

    public FantasyTransactions() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FantasyTransactions that = (FantasyTransactions) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (transactionType != null ? !transactionType.equals(that.transactionType) : that.transactionType != null)
            return false;
        if (transactionDate != null ? !transactionDate.equals(that.transactionDate) : that.transactionDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (transactionType != null ? transactionType.hashCode() : 0);
        result = 31 * result + (transactionDate != null ? transactionDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FantasyTransactions{");
        sb.append("transactionType=").append(transactionType);
        sb.append(", transactionDate=").append(transactionDate);
        sb.append(", player=").append(player);
        sb.append(", sourceTeam=").append(sourceTeam.getTeamName());
        sb.append('}');
        return sb.toString();
    }

    public Players getPlayer() {
        return player;
    }

    public void setPlayer(Players player) {
        this.player = player;
    }

    public FantasyTeam getSourceTeam() {
        return sourceTeam;
    }

    public void setSourceTeam(FantasyTeam sourceTeam) {
        this.sourceTeam = sourceTeam;
    }

    public FantasyTeam getDestinationTeam() {
        return destinationTeam;
    }

    public void setDestinationTeam(FantasyTeam destinationTeam) {
        this.destinationTeam = destinationTeam;
    }

    public static FantasyTransactions tradeAdd(EntityManager entityManager, String playerName, String sourceTeamName, String destinationTeamName, Date date){
        TypedQuery<Players> selectPlayer = entityManager.createQuery("SELECT player FROM Players as player WHERE player.playerName = :player", Players.class);
        selectPlayer.setParameter("player", playerName);
        Players player = selectPlayer.getSingleResult();

        TypedQuery<FantasyTeam> selectTeam = entityManager.createQuery("SELECT team FROM FantasyTeam as team WHERE team.teamName = :teamName", FantasyTeam.class);
        selectTeam.setParameter("teamName", sourceTeamName);
        FantasyTeam sourceTeam = selectTeam.getSingleResult();

        selectTeam.setParameter("teamName", destinationTeamName);
        FantasyTeam destinationTeam = selectTeam.getSingleResult();

        FantasyTransactions trade = new FantasyTransactions(TransactionType.trade, date, player, sourceTeam, destinationTeam);
        return trade;
    }

    public static FantasyTransactions freeAgencyAdd(EntityManager entityManager, String playerName, String sourceTeamName, Date date){
        TypedQuery<Players> selectPlayer = entityManager.createQuery("SELECT player FROM Players as player WHERE player.playerName = :player", Players.class);
        selectPlayer.setParameter("player", playerName);
        Players player = selectPlayer.getSingleResult();

        TypedQuery<FantasyTeam> selectTeam = entityManager.createQuery("SELECT team FROM FantasyTeam as team WHERE team.teamName = :teamName", FantasyTeam.class);
        selectTeam.setParameter("teamName", sourceTeamName);
        FantasyTeam sourceTeam = selectTeam.getSingleResult();

        FantasyTransactions add = new FantasyTransactions(TransactionType.add, date, player, sourceTeam, null);
        return add;
    }

    public static FantasyTransactions freeAgencyDrop(EntityManager entityManager, String playerName, Date date){
        TypedQuery<Players> selectPlayer = entityManager.createQuery("SELECT player FROM Players as player WHERE player.playerName = :player", Players.class);
        selectPlayer.setParameter("player", playerName);
        Players player = selectPlayer.getSingleResult();

        FantasyTeam sourceTeam = player.getFantasyTeam();

        FantasyTransactions drop = new FantasyTransactions(TransactionType.drop, date, player, sourceTeam, null);
        return drop;
    }
}
