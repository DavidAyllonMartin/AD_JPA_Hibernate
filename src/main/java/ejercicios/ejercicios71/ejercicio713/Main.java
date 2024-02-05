package ejercicios.ejercicios71.ejercicio713;

import ejercicios.ejercicios71.ejercicio713.entidades.FantasyDraft;
import ejercicios.ejercicios71.ejercicio713.entidades.FantasyTransactions;
import ejercicios.ejercicios71.ejercicio713.entidades.Players;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> stringList = Files.readAllLines(Path.of("src/main/resources/scripts/adds"));

        List<String[]> transactions = new ArrayList<>();

        for (String s : stringList){
            List<String[]> list = TransactionParser.parseHTMLTable(s);
            transactions.addAll(list);
        }

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("nfl");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        /*List<FantasyTransactions> fantasyTransactions = new ArrayList<>();
        fantasyTransactions.add(FantasyTransactions.freeAgencyAdd(entityManager, "Isaiah Simmons", "Allen or Nothing", Date.valueOf("2023-09-04")));
        entityManager.persist(fantasyTransactions.get(0));*/

        List<FantasyTransactions> transactionsList = new ArrayList<>();
        List<String[]> errors = new ArrayList<>();
        /*fantasyTransactions.add(FantasyTransactions.freeAgencyAdd(entityManager, "Brian Branch", "Villalba Rams", Date.valueOf("2023-09-04")));
        fantasyTransactions.add(FantasyTransactions.freeAgencyAdd(entityManager, "Jalen Tolbert", "Vir Saints", Date.valueOf("2023-09-04")));
        fantasyTransactions.add(FantasyTransactions.freeAgencyAdd(entityManager, "Desmond Ridder", "Santa Eugenia Reapers", Date.valueOf("2023-09-06")));
        fantasyTransactions.add(FantasyTransactions.freeAgencyAdd(entityManager, "Gus Edwards", "Rhapsody Dragons", Date.valueOf("2023-09-06")));
        fantasyTransactions.add(FantasyTransactions.freeAgencyAdd(entityManager, "Tremaine Edmunds", "Rhapsody Dragons", Date.valueOf("2023-09-06")));*/
        for (String[] strings : transactions){
            FantasyTransactions ft = null;
            try{
                ft = FantasyTransactions.freeAgencyAdd(entityManager, strings[3], strings[5], LocalDateTime.parse(strings[0]));
                transactionsList.add(ft);
            }catch (Exception e){
                errors.add(strings);
            }
        }

        //fantasyTransactions.add(FantasyTransactions.freeAgencyAdd(entityManager, "Brian Branch", "Villalba Rams", Date.valueOf("2023-09-04")));

        //transactionsList.forEach(entityManager::persist);

        errors.forEach(array -> Arrays.asList(array).forEach(System.out::println));


        entityManager.getTransaction().commit();




        entityManager.close();
        entityManagerFactory.close();
    }
}
