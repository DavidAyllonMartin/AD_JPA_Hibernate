package ejercicios.ejercicios71.ejercicio713;

import ejercicios.ejercicios71.ejercicio713.entidades.FantasyDraft;
import ejercicios.ejercicios71.ejercicio713.entidades.FantasyTransactions;
import ejercicios.ejercicios71.ejercicio713.entidades.Players;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("nfl");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        /*List<FantasyTransactions> fantasyTransactions = new ArrayList<>();
        fantasyTransactions.add(FantasyTransactions.freeAgencyAdd(entityManager, "Isaiah Simmons", "Allen or Nothing", Date.valueOf("2023-09-04")));
        entityManager.persist(fantasyTransactions.get(0));*/

        List<FantasyTransactions> fantasyTransactions = new ArrayList<>();
        fantasyTransactions.add(FantasyTransactions.freeAgencyAdd(entityManager, "Brian Branch", "Villalba Rams", Date.valueOf("2023-09-04")));
        fantasyTransactions.add(FantasyTransactions.freeAgencyAdd(entityManager, "Jalen Tolbert", "Vir Saints", Date.valueOf("2023-09-04")));
        fantasyTransactions.add(FantasyTransactions.freeAgencyAdd(entityManager, "Desmond Ridder", "Santa Eugenia Reapers", Date.valueOf("2023-09-06")));
        fantasyTransactions.add(FantasyTransactions.freeAgencyAdd(entityManager, "Gus Edwards", "Rhapsody Dragons", Date.valueOf("2023-09-06")));
        fantasyTransactions.add(FantasyTransactions.freeAgencyAdd(entityManager, "Tremaine Edmunds", "Rhapsody Dragons", Date.valueOf("2023-09-06")));

        fantasyTransactions.forEach(entityManager::persist);


        entityManager.getTransaction().commit();




        entityManager.close();
        entityManagerFactory.close();
    }
}
