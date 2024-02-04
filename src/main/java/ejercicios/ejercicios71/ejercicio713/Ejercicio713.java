package ejercicios.ejercicios71.ejercicio713;

import ejercicios.ejercicios71.ejercicio713.entidades.FantasyTransactions;
import ejercicios.ejercicios71.ejercicio713.entidades.Players;
import ejercicios.ejercicios71.ejercicio713.entidades.enums.TransactionType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Ejercicio713 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("nfl");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        //List<Players> players = playersFilteredByRoundAndCollege(entityManager, 1, "Alabama");
        //List<Players> players = playersFilteredByFantasyTeam(entityManager, "Atletico de David");
        //List<Players> players = playersDraftedByFantasyTeam(entityManager, "Atletico de David");
        //List<FantasyTransactions> players = getTransactionOrderByDate(entityManager, TransactionType.add);

        //players.forEach(System.out::println);

        System.out.println(numberOfTransactionsMadeForFantasyTeam(entityManager, "Allen or Nothing"));

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    private static List<Players> playersFilteredByRoundAndCollege(EntityManager entityManager, int draftRound, String college) {
        String query = "SELECT player FROM Players as player WHERE player.pickRound = :round AND player.college = :college";
        TypedQuery<Players> selectPlayers = entityManager.createQuery(query, Players.class);
        selectPlayers.setParameter("round", draftRound);
        selectPlayers.setParameter("college", college);

        return selectPlayers.getResultList();
    }

    private static List<Players> playersFilteredByFantasyTeam(EntityManager entityManager, String fantasyTeam) {
        String query = "SELECT player FROM Players as player WHERE player.fantasyTeam.teamName = :fantasyTeam";
        TypedQuery<Players> selectPlayers = entityManager.createQuery(query, Players.class);
        selectPlayers.setParameter("fantasyTeam", fantasyTeam);

        return selectPlayers.getResultList();
    }

    private static List<Players> playersDraftedByFantasyTeam(EntityManager entityManager, String fantasyTeam) {
        String query = "SELECT DISTINCT p FROM Players as p INNER JOIN FantasyDraft as fd ON p = fd.player INNER JOIN FantasyTeam as ft ON ft = fd.fantasyTeam WHERE ft.teamName = :fantasyTeam AND fd.keeper = FALSE";
        TypedQuery<Players> selectPlayers = entityManager.createQuery(query, Players.class);
        selectPlayers.setParameter("fantasyTeam", fantasyTeam);

        return selectPlayers.getResultList();
    }

    private static long numberOfTransactionsMadeForFantasyTeam(EntityManager entityManager, String fantasyTeam) {

        String query = "SELECT count(t.id) FROM FantasyTransactions as t GROUP BY t.sourceTeam.teamName HAVING t.sourceTeam.teamName = :teamName";
        TypedQuery<Long> selectAmout = entityManager.createQuery(query, Long.class);
        selectAmout.setParameter("teamName", fantasyTeam);

        return selectAmout.getSingleResult();
    }

    private static List<FantasyTransactions> getTransactionOrderByDate(EntityManager entityManager, TransactionType transactionType) {

        String query = "SELECT t FROM FantasyTransactions as t WHERE t.transactionType = :transactionType ORDER BY t.transactionDate";
        TypedQuery<FantasyTransactions> selectTransactions = entityManager.createQuery(query, FantasyTransactions.class);
        selectTransactions.setParameter("transactionType", transactionType);

        return selectTransactions.getResultList();
    }
}
