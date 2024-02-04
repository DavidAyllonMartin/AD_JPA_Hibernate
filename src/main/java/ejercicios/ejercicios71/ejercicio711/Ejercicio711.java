package ejercicios.ejercicios71.ejercicio711;

import ejercicios.ejercicios63.ejercicio633.entidades.People;
import ejercicios.ejercicios63.ejercicio633.entidades.Starships;
import jakarta.persistence.*;

import java.util.List;

public class Ejercicio711 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ejercicios633");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        getAllCharacters(entityManager).forEach(System.out::println);

        getCharactersFilteredByHomeworld(entityManager, "C%").forEach(System.out::println);

        getNumberOfCharactersBySpecies(entityManager).forEach(line -> System.out.println(line[0] + " " + line[1]));

        getStarshipsOrderByAmountOfPilots(entityManager).forEach(System.out::println);

        getStarshipNamesOrderByAmountOfPilots(entityManager).forEach(line -> System.out.println(line[0] + " " + line[1]));

        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    private static List<Starships> getStarshipsOrderByAmountOfPilots(EntityManager entityManager){
        //Utilizamos SIZE, que es propio de Hibernate, para poder contar en las colecciones de Starships
        String query = "SELECT starship FROM Starships as starship ORDER BY SIZE(starship.pilots) DESC";
        TypedQuery<Starships> selectStarships = entityManager.createQuery(query, Starships.class);
        List<Starships> starships = selectStarships.getResultList();

        return starships;
    }

    private static List<Object[]> getStarshipNamesOrderByAmountOfPilots(EntityManager entityManager){
        //Utilizamos SIZE, que es propio de Hibernate, para poder contar en las colecciones de Starships
        String query = "SELECT starship.starshipClass, SIZE(starship.pilots) FROM Starships as starship ORDER BY SIZE(starship.pilots) DESC";
        Query starshipsList = entityManager.createQuery(query);
        List<Object[]> objectsList = (List<Object[]>) starshipsList.getResultList();

        return objectsList;
    }

    private static List<Object[]> getNumberOfCharactersBySpecies(EntityManager entityManager) {
        String query = "SELECT character.species.name, count(*) FROM People as character GROUP BY character.species";
        Query numberCharactersSpecies = entityManager.createQuery(query);
        List<Object[]> objectsList = (List<Object[]>) numberCharactersSpecies.getResultList();

        return objectsList;
    }

    private static List<People> getCharactersFilteredByHomeworld(EntityManager entityManager, String filter) {
        String query = "SELECT character from People as character WHERE character.homeworld.name LIKE ?1";
        TypedQuery<People> filteredCharacters = entityManager.createQuery(query, People.class);
        filteredCharacters.setParameter(1, filter);

        List<People> filteredCharacterList = filteredCharacters.getResultList();

        return filteredCharacterList;
    }

    private static List<People> getAllCharacters(EntityManager entityManager) {
        TypedQuery<People> allCharacters = entityManager.createQuery("SELECT character FROM People as character", People.class);

        List<People> characters = allCharacters.getResultList();

        return characters;
    }
}
