package ejercicios.ejercicios71.ejercicio711;

import ejercicios.ejercicios63.ejercicio633.entidades.People;
import jakarta.persistence.*;

import java.util.List;

public class Ejercicio711 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ejercicios633");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        //printAllCharacters(entityManager);

        //printFilteredCharacters(entityManager, "C%");

        starshipsNameOrderByAmountOfPilots(entityManager);

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    private static void starshipsNameOrderByAmountOfPilots(EntityManager entityManager){
        //Utilizamos SIZE, que es propio de Hibernate, para poder contar en las colecciones de Starships
        String query = "SELECT starship.starshipClass, SIZE(starship.pilots) FROM Starships as starship ORDER BY SIZE(starship.pilots) DESC";
        Query starshipsList = entityManager.createQuery(query);
        List<Object[]> objectsList = (List<Object[]>) starshipsList.getResultList();

        for (Object[] objects : objectsList){
            System.out.println(objects[0] + " " +objects[1]);
        }
    }

    private static void numberOfCharactersForSpecies(EntityManager entityManager) {
        String query = "SELECT character.species.name, count(*) FROM People as character GROUP BY character.species";
        Query numberCharactersSpecies = entityManager.createQuery(query);
        List<Object[]> objectsList = (List<Object[]>) numberCharactersSpecies.getResultList();

        for (Object[] objects : objectsList){
            System.out.println(objects[0] + " " +objects[1]);
        }
    }

    private static void printFilteredCharacters(EntityManager entityManager, String filter) {
        String query = "SELECT character from People as character WHERE character.homeworld.name LIKE ?1";
        TypedQuery<People> filteredCharacters = entityManager.createQuery(query, People.class);
        filteredCharacters.setParameter(1, filter);

        List<People> filteredCharacterList = filteredCharacters.getResultList();

        filteredCharacterList.forEach(System.out::println);
    }

    private static void printAllCharacters(EntityManager entityManager) {
        TypedQuery<People> allCharacters = entityManager.createQuery("SELECT character FROM People as character", People.class);

        List<People> characters = allCharacters.getResultList();

        characters.forEach(System.out::println);
    }
}
