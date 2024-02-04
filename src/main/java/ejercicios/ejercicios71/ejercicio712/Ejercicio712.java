package ejercicios.ejercicios71.ejercicio712;

import ejercicios.ejercicios63.ejercicio633.entidades.People;
import ejercicios.ejercicios63.ejercicio633.entidades.Planets;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class Ejercicio712 {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ejercicios633");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Object[] characterPlusHomeworld = getCharacterWithHomeworld(entityManager, 1);
        People character = (People) characterPlusHomeworld[0];
        Planets planet = (Planets) characterPlusHomeworld[1];
        System.out.println("Name: "+ character.getName() + "\nHomeworld: " + planet.getName());

        CharacterPlanet characterPlanet = getObjectCharacterPlanet(entityManager, 1);
        System.out.println(characterPlanet);


        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();
    }

    private static Object[] getCharacterWithHomeworld(EntityManager entityManager, int characterID){
        String select = "SELECT character, character.homeworld FROM People character WHERE character.id = ?1";
        Query query = entityManager.createQuery(select);
        query.setParameter(1, characterID);
        Object[] characterPlusHomeworld = (Object[]) query.getSingleResult();

        return characterPlusHomeworld;
    }

    private static CharacterPlanet getObjectCharacterPlanet(EntityManager entityManager, int characterID){
        String select = "SELECT new ejercicios.ejercicios71.ejercicio712.CharacterPlanet(character, character.homeworld) FROM People character WHERE character.id = ?1";
        Query query = entityManager.createQuery(select);
        query.setParameter(1, characterID);
        CharacterPlanet characterPlanet = (CharacterPlanet) query.getSingleResult();

        return characterPlanet;
    }
}

